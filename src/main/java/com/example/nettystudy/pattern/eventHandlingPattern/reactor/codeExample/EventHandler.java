package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

import java.io.InputStream;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : EventHandler
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public interface EventHandler {
    // 실제로 이벤트를 처리하는 handleEvent 메서드,
    // 어떠한 이벤트 코드에 해당하는지를 알려주는 getHandler() 메서드로 구성

    public String getHandler();
    public void handleEvent(InputStream inputStream);
}