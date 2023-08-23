package com.example.nettystudy.netty.chapter2.blockingNonBlocking.blocking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName : com.example.nettystudy.netty.chapter2.blockingNonBlocking.blocking
 * fileName : BlockingServer
 * author : taeil
 * date : 2023/08/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/24        taeil                   최초생성
 */
public class BlockingServer {
    // 블로킹 모드로 동작하는 서버

    public static void main(String[] args) throws IOException {
        BlockingServer server = new BlockingServer();
        server.run();
    }

    private void run() throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("접속 대기중..");

        while (true) {
            // 연결되는 클라이언트가 없으면 프로그램은 아무런 동작도 하지 않는다. 스레드 또한 해당 함수의 완료를 기다리며 이 위치에서 대기하게 된다.
            Socket sock = server.accept();
            System.out.println("클라이언트 연결됨");

            OutputStream out = sock.getOutputStream();
            InputStream in = sock.getInputStream();

            while (true) {
                try {
                    // 클라이언트와 연결이 되었다고 해도 데이터의 입력을 기다리면서 메서드의 동작이 멈추게 된다.
                    // 즉, 클라이언트로부터 데이터가 수신되기를 기다리면서 스레드가 블로킹된다.
                    // 이와 같이 블로킹 소켓은 호출된 입출력 메서드의 처리가 완료될 때까지 응답을 돌려주지 않고 대기한다.
                    int request = in.read();
                    out.write(request);
                    // write 메서드는 운영체제의 송신 버퍼에 전송할 데이터를 기록한다.
                    // 이때 송신 버퍼의 남은 크기가 write 메서드에서 기록한 데이터의 크기보다 작다면 송신 버퍼가 비워질 때까지 블로킹된다.
                }
                catch (IOException e) {
                    break;
                }
            }
        }
    }


}