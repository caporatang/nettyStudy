package com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent.channelReadComplete;

/**
 * packageName : com.example.nettystudy.netty.chpater4.eventHnadler.channelInboundEvent.channelReadComplete
 * fileName : ChannelReadComplete
 * author : taeil
 * date : 2023/09/02
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/09/02        taeil                   최초생성
 */
public class ChannelReadComplete {
    // channelReadComplete 이벤트는 데이터 수신이 완료되었음을 알려주는 이벤트다.

    // channelRead 이벤트 메서드와 혼동하기도 한다
    // 예를들어 클라이언트가 'A', 'B', 'C' 라는 데이터를 순차적으로 전송했다고 가정한다.
    // 이때 서버에서는 channelRead 이벤트가 발생한다.
    // msg 객체에 수신된 데이터가 'ABC'라면 다음으로 발생하는 이벤트는 channelReadComplete 다.

    // 반대로 msg 객체에 수신된 데이터가 'A'라면 다음으로 발생하는 이벤트는 channelRead 이벤트다.
    // 즉 channelRead 이벤트는 채널에 데이터가 있을 때 발생하고 채널의 데이터를 다 읽어서 더 이상 데이터가 없을 때 channelReadComplete 이벤트가 발생하낟.
}