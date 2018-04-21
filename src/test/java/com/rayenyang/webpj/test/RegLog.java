package com.rayenyang.webpj.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:
 * Created by rayenyang on 2017/7/4.
 */
public class RegLog {
    public static void main(String[] args) {
        String msg = "2015-05-28 16:13:45,873 [main][INFO ] sender.EtermSessionConnectPool - 准备初始化 EtermSessionConnectPool\n";
        String logTime = "(?<logTime>^\\d{4}\\-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2},\\d{3}\\s)";
        String threadName = "\\[(?<threadName>.+)\\]";
        String logLevel = "\\[(?<logLevel>\\w+\\s*)\\]";
        String className = "\\s(?<className>\\w+\\.\\w+)";
        String logInfo = "\\s\\-\\s(?<logInfo>.+$)";
        String pattern = logTime + threadName + logLevel + className + logInfo;

        final Matcher matcher = Pattern.compile(pattern).matcher(msg);
        while (matcher.find()) {
            System.out.println(matcher.group("logTime"));
            System.out.println(matcher.group("threadName"));
            System.out.println(matcher.group("logLevel"));
            System.out.println(matcher.group("className"));
            System.out.println(matcher.group("logInfo"));
        }
    }
    
}
