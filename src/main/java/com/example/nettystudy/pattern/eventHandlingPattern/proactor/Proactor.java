package com.example.nettystudy.pattern.eventHandlingPattern.proactor;

/**
 * packageName : com.example.nettystudy.pattern
 * fileName : Proactor
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class Proactor {
    // Proactor 패턴

    // Reactor 패턴의 문제점은 event handler가 많아지는 경우 발생한다.
    // reactor가 event handler를 많이 들고 있어야 하는 이유는 이벤트에 반응하기 위해 각 클라이언트의 상태를 지속적으로 관찰해야하기 때문이다.
    // 이를 해결하려고 한게 Proactor 패턴이다.

    // Proactor 패턴은 이벤트를 수동적으로 기다리지 않는다. -> 능동적으로 비동기 작업을 지시한다.
    // 지시하게 되면 비동기 프로세스가 가능한 일거리들을 demultiplexing한 뒤, 작업까지 비동기로 처리한다.
    // 그리고 작업이 완료되면 비동기 프로세스가 completion dispatch에게 이벤트를 넘기고 dispatcher는 적절한 completion handler(Queue)에 이벤트를 dispatch 해준다.
    // Reactor에서 event가 작업이 가능함을 알리는 event였다면, Proactor에서 event는 작업의 완료를 알리는 event다.
    // completion handler에 eventrk dispatch 되면 completion handler는 미리 정해진 콜백을 호출하여 process event를 처리한다.

    // 동작 과정
    // proactor는 비동기 작업을 지시하고 완료 이벤트를 받을 completion handler를 등록한다.(initiate)
    // 비동기 프로세스가 가능한 작업을 대기한다. 혹은 작업이 발생하면 깨어나 처리한다(receive)
    // 가능한 작업들이 생기면 비동기 프로세스가 작업을 분리하여 비동기적으로 처리한다. (demultiplex / work processing)
    // 작업이 완료되면 비동기 프로세스는 completion dispatcher에게 완료 정보를 넘기고 dispatcher는 정보를 이벤트로 만들어서 적절한 completion handler에게 발송한다.(dispatch)
    // completion handler는 받은 이벤트의 정보를 토대로 정해진 콜백을 호출하여 process event를 처리한다.(process event)

    // proactor 패턴에서 필수적인 것은 비동기 명령을 처리하는 프로세스이다.
    // Proactor 패턴은 event handler 같은게 없어도 작업이 발생하면 비동기 프로세스가 잘 스케쥴된 환경에서 작업을 알아서 처리하므로, reactor 패턴의 고질적인 문제점을 해결할 수 있다.
    // application은 completion handler에 도착한 이벤트만 처리하면 되기 때문에, 복잡한 조건 체크 같은것 없이도 간결하게 일반 싱글 스레드 환경처럼 구성할 수 있다.
    // 대신 비동기 작업이 순차적으로 동작하지 않아서, 순서를 조정하는데 어려움을 겪을 수 있고, 마찬가지의 이유에서 디버깅이 어렵다..


}