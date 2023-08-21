package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : LGDeviceEventHandler
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class LGDeviceEventHandler implements EventHandler {

    private static final int DATA_SIZE = 1024;
    private static final int TOKEN_NUM = 5;

    @Override
    public String getHandler() {
        return "0x6001";
    }

    public void handleEvent(InputStream inputStream) {
        try {
            byte[] buffer = new byte[DATA_SIZE];

            inputStream.read(buffer);
            String data = new String(buffer, StandardCharsets.UTF_8);

            String[] params = new String[TOKEN_NUM];
            StringTokenizer token = new StringTokenizer(data, "|");

            int i = 0;
            while(token.hasMoreTokens()) {
                params[i] = token.nextToken();
                ++i;
            }
            processDeviceInfo(params);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void processDeviceInfo(String[] params) {
        System.out.println("LG Device : " + params[0]
                + "\n Device No : " + params[1]
                + "\n Device Protocol " + params[2]
                + "\n Device Name " + params[3]
                + "\n Device Network " + params[4]);
    }
}