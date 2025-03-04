package com.example.javaserver.common.config;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {
    private static final String LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n";

    @Bean
    public LoggerContext loggerContext() {
        // LoggerContext 초기화
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset(); // 기존 설정 초기화

        // 환경변수에서 프로파일 가져오기 (기본값: local)
        String activeProfile = System.getProperty("spring.profiles.active");
        if (activeProfile == null || activeProfile.isEmpty()) {
            activeProfile = "local";
        }

        String logFilePath = "logs/" + activeProfile + "-app.log"; // 환경별 로그 파일 설정

        // 콘솔 로그 설정
        // 변경된 부분: ConsoleAppender의 제네릭 타입을 명시적으로 ILoggingEvent로 지정
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(context);

        // PatternLayoutEncoder를 ILoggingEvent 타입으로 설정
        PatternLayoutEncoder consoleEncoder = new PatternLayoutEncoder();
        consoleEncoder.setContext(context);
        consoleEncoder.setPattern(LOG_PATTERN);
        consoleEncoder.start();

        consoleAppender.setEncoder(consoleEncoder); // 콘솔 출력에 사용될 Encoder 설정
        consoleAppender.start(); // 콘솔 Appender 시작

        // 파일 로그 설정
        // 변경된 부분: FileAppender의 제네릭 타입을 명시적으로 ILoggingEvent로 지정
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(context);
        fileAppender.setFile(logFilePath); // 파일 경로 설정

        // PatternLayoutEncoder를 ILoggingEvent 타입으로 설정
        PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
        fileEncoder.setContext(context);
        fileEncoder.setPattern(LOG_PATTERN);
        fileEncoder.start();

        fileAppender.setEncoder(fileEncoder); // 파일 출력에 사용될 Encoder 설정
        fileAppender.start(); // 파일 Appender 시작

        // 루트 로거 설정
        Logger rootLogger = context.getLogger("ROOT");
        rootLogger.setLevel(getLogLevel(activeProfile)); // 로그 레벨 설정 (프로파일에 따라 다르게 설정)
        rootLogger.addAppender(consoleAppender); // 콘솔 로그 추가
        rootLogger.addAppender(fileAppender); // 파일 로그 추가

        return context;
    }

    // 환경별 로그 레벨 설정
    private Level getLogLevel(String profile) {
        switch (profile) {
            case "prod":
                return Level.INFO;
            case "dev":
                return Level.DEBUG;
            default:
                return Level.DEBUG;
        }
    }
}
