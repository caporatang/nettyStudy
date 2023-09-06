package com.example.nettystudy.netty.chapter5;

/**
 * packageName : com.example.nettystudy.netty.chapter5
 * fileName : SingleMultiThreadEventLoop
 * author : taeil
 * date : 2023/09/06
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/06        taeil                   최초생성
 */
public class SingleMultiThreadEventLoop {
    // 단일 스레드와 다중 스레드 이벤트 루프

    // 단일 스레드 이벤트 루프는 이벤트를 처리하는 스레드가 하나인 상태를 말한다.
    // 이벤트 루프의 구현이 단순하고 예측 가능한 동작을 보장한다.
    // 또한 하나의 스레드가 이벤트 큐에 입력된 이벤트를 처리하므로 이벤트가 발생한 순서대로 수행할 수 있다.

    // 단점 : 다중 코어 CPU를 효율적으로 사용하지 못하며 이벤트 메서드에 처리 시간이 오래 걸리는 작업이 섞여 있을 때 나중에 들어온 이벤트는 처리까지 더 오랜 시간이 걸린다.
    // 단일 스레드 이벤트 루프의 단점을 극복하려고 다중 스레드 이벤트 루프를 사용하기도 한다.
    // 다중 코어를 가진 서버에서는 단일 스레드 이벤트 루프를 사용하는 node.js와 같은 애플리케이션을 효율적으로 운영하려고 여러 개의 인스턴스를 실행하기도 한다.


    // 다중 스레드
    // 다중 스레드 이벤트 루프는 이벤트를 처리하는 스레드가 여러 개다.
    // 단일 스레드 이벤트 루프에 비해서 프레임워크의 구현이 복잡하다.
    // 이벤트 루프 스레드들이 이벤트 메서드를 병렬로 수행하므로 다중 코어 CPU를 효율적으로 사용한다.

    // 단점 : 여러 이벤트 루프 스레드가 이벤트 큐 하나에 접근하므로 여러 스레드가 자원 하나를 공유할 때 발생하는 스레드 경합이 발생한다.
    // 여러 스레드가 이벤트 메서드를 수행하므로 이벤트의 발생 순ㅅ허와 실행 순서가 일치하지 않는다.
    // 애플리케이션 개발자는 다중 스레드 이벤트 루프의 단점인 이벤트 발생 순서와 실행 순서 불일치로 인해서 이벤트가 발생한 순서대로 처리된다는 가정을 하지 않아야 한다.

    // 애플리케이션을 다중 스레드로 구현하는 이유는 시간이 많이 걸리는 작업을 여러 스레드로 분할 처리하여 전체 처리 시간을 단축시키기 위함이다.
    // 이런 관점에서 다중 스레드 애플리케이션은 좋은 선택이며 충분한 성능을 제공한다.
    // 다중 스레드의 장점을 얻기 위해서 스레드 개수를 너무 많이 설정하거나 스레드 개수를 제한하지 않는 실수를 한다.
    // 이와 같은 설정은 과도한 가비지 컬랙션이 발생하는 원인이 되거나 OOM 에러를 발생시켜 애플리케이션이 정상적으로 동작하지 못하게 한다.


    // 멀티 스레드의 스레드 경합
    // 다중 스레드 아키텍처는 자원의 효율적 사용이라는 장점도 있지만 컨텍스트 스위칭 비용과 스레드 경합이라는 단점도 존재한다.
    // 다중 스레드 애플리케이션에서 스레드들이 하나의 자원을 공유할 때 각 스레드는 공유 자원의 단일 액세스 권한을 획득하려고 스레드 경합을 벌인다.
    // 스레드 경합은 CPU를 소비하며 스레드가 많아질수록 스레드 경합에 더 많은 CPU 자원을 사용한다.
    // 스레드가 많아질 때 발생하는 또 다른 단점으로 컨텍스트 스위칭 비용을 들 수 있다.
    // 하나의 CPU 코어는 동시에 하나의 스레드만 실행할 수 있다.
    // 즉, 4개의 코어를 가진 시스템에서 동시에 실행할 수 있는 스레드 개수는 4개다. 이로 인해서 각 스레드는 실행, 대기, 준비, 슬립, 지연 상태를 가진다.
    // 운영체제는 현재 상태가 대기, 슬립, 지연인 스레드 중의 하나를 선택하여 실행 상태로 바꾸는데 이때 스레드가 가진 스택 정볼르 현재 코어의 레지스터로 복사하는 작업이 이루어지며 이것을 "컨텍스트 스위칭"이라 한다.
    // 이것은 다중 스레드로 얻는 성능 향상 이외에 스레드 경합과 컨텍스트 스위칭으로 인한 성능 저하가 발생한다는 의미다.



    // 스레드 개수가 적을 때는 다중 스레드의 장점을 얻을 수 있지만 스레드 개수가 지속적으로 증가하면 다중 처리에 의한 성능 향상보다 스레드 경합에 의한 성능 저하가 발생한다..!
    // 개발자가 다중 스레드 아키텍처를 선택한다면 시스템을 고려해서 적정한 수치로 스레드 개수를 설정하여야 한다.!!!!!!!!!!!!!!


    // --------------------- 다중 스레드 애플리케이션의 스레드 개수 선정 ---------------------
    // 스레드 개수의 적정 수치를 찾기 위해서 애플리케이션 부하 테스트 도구를 사용한다.
    // 1. JMeter : 아파치 소프트웨어 재단에서 제공하는 부하 테스트 도구로서 HTTP, EJB, Java Bean, FTP 등의 부하를 포함한 다양한 부하 테스트 기능과 테스트 시나리오를 작성할 수 있다.
    // 2. Grinder : HTTP, RMI, JMS, EJB 등과 같이 다양한 종류의 부하 생성을 위한 스크립트를 다양한 언어로 작성할 수 있다.
    // 이 부하 테스트 도구를 기반으로 하여 NHN에서 만든 오픈 소스 부하 테스트 도구로 nGrinder가 있다.
    // 3. Load Runner : 다양한 시나리오에 대한 부하 테스트를 안정적으로 제공하는 도구. 사용 도구로서 테스트를 위한 라이센스 비용이 크다는 단점이 있다.
}