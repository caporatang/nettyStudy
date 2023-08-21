package com.example.nettystudy.pattern.eventHandlingPattern.reactor;

/**
 * packageName : com.example.nettystudy.pattern
 * fileName : Reactor
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class ReactorPattern {
    // reactor 패턴

    // reactor패턴은 이벤트 핸들 패턴의 전형적인 모습이다.
    // application이 능동적으로 계속해서 처리하기위한 루프를 도는 것이 아니라, 이벤트에 반응하는 객체(reactor)를 만들고, 사건(이벤트)이 발생하면 application 대신 reactor가 반응하여 처리한다.
    // reactor는 이벤트가 발생하길 기다리고, 이벤트가 발생하면 event handler에게 이벤트를 발송한다.
    // application에서 event를 대기하고 분할하는 작업을 하지 않아도 동작할 수 있기 때문에 이벤트 multiplexing을 구현하는데 좋은 구조이다.

    // 동작 과정 :
    // 이벤트에 반응하는 Reactor를 만들고 reactor에 이벤트를 처리할 event handler들을 등록한다. (initiate)
    // reactor는 이벤트가 발생하기를 기다린다. (receive)
    // 이벤트가 발생하면 이벤트를 처리할 event handler 단위로 분할한다. (demultiplex)
    // 분할된 이벤트를 해당 event handler에게 발송한다. (dispatch)
    // event handler에 알맞은 method를 사용하여 이벤트를 처리한다. (process event)

    // 리액터 패턴에서 리액터는 등록된 이벤트 핸들러들을 들고 관리해야한다.
    // 가지고 있을 수 있는 이벤트 핸드러 개수가 제한된다.
    // 동시에 수많은 I/O 요청이 발생하는 경우 이벤트 핸들러가 엄청 많아지는데 그때 각각을 관리하게 되면 성능이 저하된다.

    // 멀티 스레드 환경에서 활용도가 높지 않다.
    // 하나의 reactor는 하나의 스레드만 사용할 수 있다.
    // 멀티 스레드를 사용하려면 스레드별 reactor를 사용해야하는데, OS의 스케쥴링 능력을 활용할 수 없다는 단점이 발생한다.
    // 특정 스레드에 부하가 걸리면 다른 스레드로 I/O 요청이 넘어가야 하는데, 이 부분은 프로그래머가 직접 구현해야한다. -> OS 자체적으로 지원하는 스케쥴링보다 성능 좋기가 힘들다.
}