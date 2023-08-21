package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : Dispatcher
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class Dispatcher {
    // Dispatcher 객체는, 사용자(소켓) 으로부터 요청을 받아, 사용자의 요청(= 이벤트 종류) 에 대응되는 핸들러를 호출하는 역할을 한다.
    // 해당 예제는 demultiplex 함수에서, 사용자의 이벤트 요청에 대응되는 핸들러를 호출하는 역할을 한다.

    private final int HEADER_SIZE = 6;

    public void dispatch(ServerSocket serverSocket, HandleMap handleMap) {
        try {
            Socket socket = serverSocket.accept();
            demultiplex(socket, handleMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void demultiplex(Socket socket, HandleMap handleMap) {
        try {
            InputStream inputStream = socket.getInputStream();

            byte[] buffer = new byte[HEADER_SIZE];
            inputStream.read(buffer);
            String header = new String(buffer);

            handleMap.get(header).handleEvent(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // class end