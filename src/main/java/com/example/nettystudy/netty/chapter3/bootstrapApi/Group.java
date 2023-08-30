package com.example.nettystudy.netty.chapter3.bootstrapApi;

/**
 * packageName : com.example.nettystudy.netty.chapter3.bootstrapApi
 * fileName : Group
 * author : taeil
 * date : 2023/08/29
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/29        taeil                   최초생성
 */
public class Group {
    // 데이터 송수신 처리를 위한 이벤트 루프를 설정하는 group 메서드
    // group 메서드는 AbstractBootstrap 클래스 맴버 메서드로 정의되어 있다.

    // 부트스트랩은 ServerBootstrap 클래스와 Bootstrap 클래스로 나뉜다.
    // 클라이언트는 연결 요청이 완료된 이후의 데이터 송수신 처리를 위해서 하나의 이벤트루프로 모든 처리가 가능하다.
    // 반대로 서버는 클라이언트의 연결 요청을 수락하기 위한 이벤트 루프와 데이터 송수신 처리를 위한 이벤트 루프 두 종류의 이벤트 루프가 필요하다.

    // AbstractBootstrap 클래스 정의된 group 메서드는 하나의 이벤트 루프만 설정하도록 되어 있다.
    // 즉, Bootstrap 클래스에서 설정한 이벤트 루프는 클라이언트 소켓의 데이터 입출력만을 담당하게 된다.
    // 서버 애플리케이션을 위한 부트스트랩 클래스인 ServerBootstrap 클래스에서는 AbstractBootstrap 클래스의 group 메서드를 재정의하여 부모 스레드 그룹을 설정한다.


    // ServerBootstrap  내부에 구현되어 있는 group
    //    @Override
    //    public ServerBootstrap group(EventLoopGroup group) {
    //        return group(group, group);
    //    }
    //
    //    public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup) {
    //        super.group(parentGroup);
    //        if (childGroup == null) {
    //            throw new NullPointerException("childGroup");
    //        }
    //        if (this.childGroup != null) {
    //            throw new IllegalStateException("childGroup set already");
    //        }
    //        this.childGroup = childGroup;
    //        return this;
    //    }

    // 상속받은 AbstractBootstrap 클래스의 group 메서드를 재정의한다.
    // 두 스레드 그룹을 인수로 가지는 group 메서드를 오버라이드
    // 두 스레드 그룹을 설정
    // 부모 스레드를 그룹과 자식 스레드 그룹에 동일한 스레드 그룹으로 설정했다. -> 하나의 스레드 그룹이 클라이언트의 연결 수락과 연결 소켓의 데이터 입출력을 모두 처리한다.
    // AbstractBootstrap 클래스의 맴버 변수로 부모 스레드 그룹을 설정
    //      -> ServerBootstrap 클래스는 클라이언트의 연결을 수락하는 부모 스레드 그룹과 연결된 클라이언트 소켓에 대한 데이터 입출력을 처리하는 자식 스레드 즉, 두 개의 스레드 그룹을 설정할 수 있다.
}