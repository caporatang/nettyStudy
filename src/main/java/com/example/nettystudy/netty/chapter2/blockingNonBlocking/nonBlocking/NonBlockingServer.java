package com.example.nettystudy.netty.chapter2.blockingNonBlocking.nonBlocking;

import org.hibernate.type.StandardBasicTypeTemplate;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.Selection;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * packageName : com.example.nettystudy.netty.chapter2.blockingNonBlocking.nonBlocking
 * fileName : NonBlockingServer
 * author : taeil
 * date : 2023/08/25
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/25        taeil                   최초생성
 */
public class NonBlockingServer {
    // 논블로킹 소켓은 구조적으로 소켓으로부터 읽은 데이터를 바로 소켓에 쓸 수가 없다.
    // 이를 위해서 각 이벤트가 공유하는 데이터 객체를 생성하고 그 객체를 통해서 각 소켓 채널로 데이터를 전송한다.

    private Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();
    private ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

    private void startEchoServer() {
        try (
            // 자바 NIO 컴포넌트 중의 하나인 Selector는 자신에게 등록된 채널에 변경 사항이 발생했는지 검사하고 변경 사항이 발생한 채널에 대한 접근을 가능하게 해준다.
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
        ) {

            // 생성한 Selector와 ServerSocketChannel 객체가 정상적으로 생성되었는지 체크 -> selector 제공 메서드
            if((serverSocketChannel.isOpen()) && (selector.isOpen())) {
                serverSocketChannel.configureBlocking(false); // 소켓 채널의 블로킹 모드의 기본값은 true -> 별도로 지정하지 않으면 자동으로 블로킹 모드로 동작한다.
                serverSocketChannel.bind(new InetSocketAddress(8888)); // 포트 바인딩!

                // serverSocketChannel 객체를 Selector 객체에 등록, Selector가 '감지할 이벤트'는 연결 요청에 해당하는 OP_ACCEPT
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("접속 대기중!");

                while (true) {
                    // Selector에 등록된 채널에서 변경 사항이 발생했는지 검사한다.
                    // Selector에 아무런 I/O 이벤트도 발생하지 않으면 스레드는 이 부분에서 블로킹된다. -> I/O 이벤트가 발생하지 않았을 때 블로킹을 피하고 싶다면 selectNow 메서드를 사용!
                    selector.select();
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator(); // Selector에 등록된 채널 중, I/O 이벤트가 발생한 채널들의 목록을 조회

                 while (keys.hasNext()) {
                     SelectionKey key = (SelectionKey) keys.next();
                     keys.remove(); // I/O 이벤트가 발생한 채널에서 동일한 이벤트가 감지되는 것을 방지. 조회 목록에서 remove

                     if(!key.isValid()) {
                         continue;
                     }

                     if(key.isAcceptable()) { // 조회된 I/O 이벤트의 종류가 연결 요청인지 확인한다. -> 만약 연결 요청 이벤트라면 연결처리 메서드로 이동
                         this.acceptOP(key, selector);
                     }
                     else if (key.isReadable()) { // 조회된 I/O 이벤트의 종류가 데이터 수신인지 확인 -> 데이터 수신 이벤트라면 데이터 읽기 처리 메서드로 이동
                         this.readOP(key);
                     }
                     else if (key.isWritable()) { // 조회된 I/O 이벤트의 종류가 데이터 쓰기 기능인지 확인 -> 데이터 쓰기 가능 이벤트라면 데이터 읽기 처리 메서드로 이동
                         this.writeOP(key);
                     }
                 }
                }
            }
            else {
                System.out.println("서버 소켓을 생성하지 못했습니다");
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    } // startEchoServer end


    private void acceptOP(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel(); // 연결 요청 이벤트가 발생한 채널은 항상 ServerSocketChannel 이므로 이벤트가 발생한 채널을 ServerSocketChannel로 캐스팅
        SocketChannel socketChannel = serverSocketChannel.accept();                    // 클라이언트의 연결을 수락 및 연결된 소켓 채널을 가져온다.
        socketChannel.configureBlocking(false);                                         // 연결된 클라이언트 소켓 채널을 논블로킹 모드로 설정

        System.out.println("클라이언트 연결됨 : " + socketChannel.getRemoteAddress());

        keepDataTrack.put(socketChannel, new ArrayList<byte[]>());
        socketChannel.register(selector, SelectionKey.OP_READ); // 클라이언트 소켓 채널을 Selector에 등록하여 I/O 이벤트를 감시
    }


    private void readOP(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel)  key.channel();
            buffer.clear();
            int numRead = -1;

            try {
                numRead = socketChannel.read(buffer);
            }
            catch (IOException e) {
               System.err.println("데이터 읽기 에러 !!");
            }

            if(numRead == -1) {
                this.keepDataTrack.remove(socketChannel);
                System.out.println("클라이언트 연결 종료 : " + socketChannel.getRemoteAddress());
                socketChannel.close();
                key.cancel();
                return;
            }

            byte[] data = new byte[numRead];
            System.arraycopy(buffer.array(), 0, data, 0 , numRead);
            System.out.println(new String(data, "UTF-8")
                                                            + "from " + socketChannel.getRemoteAddress());

            doEchoJob(key, data);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void writeOP(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        List<byte[]> channelData = keepDataTrack.get(socketChannel);
        Iterator<byte[]> its = channelData.iterator();

        while (its.hasNext()) {
            byte[] it = its.next();
            its.remove();
            socketChannel.write(ByteBuffer.wrap(it));
        }
        key.interestOps();
    }

    private void doEchoJob(SelectionKey key, byte[] data) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        List<byte[]> channelData = keepDataTrack.get(socketChannel);
        channelData.add(data);

        key.interestOpsOr(SelectionKey.OP_WRITE);
    }

    public static void main(String[] args) {
        NonBlockingServer main = new NonBlockingServer();
        main.startEchoServer();
    }

}