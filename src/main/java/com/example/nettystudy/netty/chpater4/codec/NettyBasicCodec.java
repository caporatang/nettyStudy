package com.example.nettystudy.netty.chpater4.codec;

/**
 * packageName : com.example.nettystudy.netty.chpater4.codec
 * fileName : NettyBasicCodec
 * author : taeil
 * date : 2023/09/04
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/04        taeil                   최초생성
 */
public class NettyBasicCodec {
    // 네티는 자주 사용되는 프로토콜의 인코더와 디코더를 기본 제공한다

    // 1. Base64 코덱
    // Base64 인코딩 데이터에 대한 송수신을 지원하는 코덱이다.
    // Base64란 '8비트 이진 데이터'를 '문자 코드에 영향을 받지 않는 공통 ASCII 영역의 문자로 이루어진 일련의 문자열'로 바꾸는 인코딩을 말한다.

    // 2. bytes 코덱 : 바이트 배열 데이터에 대한 송수신을 지원하는 코덱이다.

    // 3. compression 코덱 :
    // 송수신 데이터의 압축을 지원하는 코덱이다.
    // 네티 4.0에서 지원하는 압축 알고리즘은 zlib, gzip, Snappy
    // 네티 4.1에서 추가된 압축 알고리즘은 BZip2, FastLZ, LZ4, LZF 이 있다.

    // 4. http 코덱
    // HTTP 프로토콜을 지원하는 코덱으로서 하위 패키지에서 다양한 데이터 송수신 방법을 지원한다.
    // HTTP 코덱의 세부 구현체로 HTTP 프로토콜의 CORS 송수신을 지원하는 cors 코덱, 파일 송수신과 같은 mulitpart 요청과 응답을 지원하는 multipart 코덱, 웹 소켓 프로토콜의 데이터 송수신을 지원하는 websocketx 코덱이 있다.

    // 5.marshalling 코덱
    // 마샬링이란 객체를 네트워크를 통해서 송신 가능한 형태로 변환하는 과정이다. <-> 반대로 네트워크를 통해서 수신한 데이터를 객체로 변환하는 과정을 언마샬링이라고 한다.
    // marshalling 코덱은 JBoss에서 개발한 마샬링 라이브러리를 지원하고 있어 객체의 네트워크 송수신을 지원한다.
    // JBoss는 기존의 JDK에서 제공하는 직렬화와 역질렬화의 문제점을 해결하기 위한 JBoss marshjalling 라이브러리를 제공한다.

    // 6. protobuf 코덱 : 구글의 프로토콜 버퍼를 사용한 데이터 송수신을 지원하는 코덱이다.

    // 7. rtsp 코덱
    // RTSP는 오디오와 비디오 같은 실시간 데이터의 전달을 위해서 특수하게 만들어진 애플리케이션 레벨의 프로토콜이다. : 보통 실시간 동영상 스트리밍을 위한 제어 프로토콜로 사용된다.

    // 8. sctp 코덱
    // TCP가 아닌 SCTP 전송 계층을 사용하는 코덱이다.
    // 부투스트랩의 채널에 NioSctpChannel 또는 NioSctpServerChannel을 설정하여야 한다.

    // 9. spdy 코덱
    // 구글의 SPDY 프로토콜을 지원하는 코덱이다 : SPDY 프로토콜은 기존의 HTTP의 단점을 보완하려 구글에서 만든 웹 기반 프로토콜이다.

    // 10. string 코덱 : 문자열의 송수신을 지원하는 코덱이다. 주로 텔넷 프로토콜이나 채팅 서버의 프로토콜에 이용된다

    // 11. serialization 코덱
    // 자바의 객체를 네트워크로 전송할 수 있도록 직렬화와 역질렬화를 지원하는 코덱이다.
    // 네티의 serialization 코덱은 JDK의 ObjectOutputStream 및 ObjectInputStream과 호환되지 않는다.

}