package com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

/**
 * packageName : com.example.nettystudy.pattern.eventHandlingPattern.reactor.codeExample
 * fileName : SamsungDeviceEventHandler
 * author : taeil
 * date : 2023/08/21
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 2023/08/21        taeil                   최초생성
 */
public class SamsungDeviceEventHandler implements EventHandler{

    private static final int DATA_SIZE = 512;
    private static final int TOKEN_NUM = 2;

    @Override
    public String getHandler() {
        return "0x5001";
    }

    public void handleEvent(InputStream inputStream) {

        try {
            byte[] buffer = new byte[DATA_SIZE];
            inputStream.read(buffer);
            String data = new String(buffer);
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

    protected void processDeviceInfo(String[] params) {
        System.out.println("Samsung Device : " + params[0] + "\nDevice No : " + params[1]);
    }
}