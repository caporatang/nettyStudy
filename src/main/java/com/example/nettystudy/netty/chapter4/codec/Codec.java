package com.example.nettystudy.netty.chapter4.codec;

/**
 * packageName : com.example.nettystudy.netty.chpater4.codec
 * fileName : Codec
 * author : taeil
 * date : 2023/09/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/04        taeil                   최초생성
 */
public class Codec {
    // 보통 원본 파일을 압축하는 것을 인코딩이라 하고 압축된 MPEG 파일에서 원본 파일을 추출하는 과정을 디코딩이라 한다.
    // 이 과정을 네티의 소켓 채널로 옮기면 인코더는 인바운드, 디코더는 아웃바운드가 된다.
    // 네티에서 인코더는 전송할 데이터를 전송 프로토콜에 맞추어 변환 작업을 수행하고 디코더는 반대의 작업을 수행한다.

    // --------------------- 코덱의 구조 ---------------------
    // 네티 애플리케이션을 기준으로 보면 수신은 인바운드, 송신은 아웃바운드로 볼 수 있다.
    // ChannelInboundHandler 인터페이스로 각각 인코더와 디코더라고 부른다.
    // 인코딩과 디코딩 대상이 애플리케이션 내부의 데이터다.
    // 데이터를 전송할 때는 인코더를 사용하여 패킷으로 변환하고 데이터를 수신할 떄는 디코더를 사용하여 패킷을 데이터로 변환한다.



}