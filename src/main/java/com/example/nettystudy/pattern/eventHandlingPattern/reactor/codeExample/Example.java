package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : Example
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class Example {
    // 효율적인 리액트 패턴을 위한 주의사항
    // 이벤트 핸들러의 작업이 Blocking인 작업이면 안된다. -> 성능 저하
    // 불필요한 동기화 로직이나 Context Switching, 발생되는 경우 처리율이 떨어지므로 가급적 피한다 -> 성능 저하
    // 새로운 이벤트 핸들러를 추가할 수 있어야한다.
    // 복잡한 멀티 스레드나, 동기화 코드에 영향을 최대한 안받게 구현한다.

    // 해결법 : 중간에 Dispatcher 객체를 하나 두고, 이벤트 요청을 미리 매핑된 이벤트 핸들러에 던짐
    // 실제 이벤트에 대한 처리는 이벤트 핸들러가 처리 하도록 하면 된다.


    public static void main(String[] args) {
        int port = 5000;

        Reactor reactor = new Reactor(port);
        reactor.registerHandler(new SamsungDeviceEventHandler());
        reactor.registerHandler(new LGDeviceEventHandler());

        reactor.startServer();
    }
}