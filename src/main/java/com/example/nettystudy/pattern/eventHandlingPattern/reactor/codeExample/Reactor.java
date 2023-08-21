package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : Reactor
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class Reactor {
    // 리액터 객체는 이벤트 핸들러를 등록 / 삭제하는 역할 및 사용자에게 받은 요청을 Dispatcher로 넘겨주는 역할을 수행한다.

    private ServerSocket serverSocket;
    private HandleMap handleMap;

    public Reactor(int port) {
        handleMap = new HandleMap();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        Dispatcher dispatcher = new Dispatcher();
        while (true) {
            dispatcher.dispatch(serverSocket, handleMap);
        }
    }

    public void registerHandler(EventHandler handler) {
        handleMap.put(handler.getHandler(), handler);
    }

    public void removeHandler(EventHandler handler) {
        handleMap.remove(handler.getHandler(), handler);
    }



} // class end