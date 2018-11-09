package kr.co.within.dubra.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiRecode {
    private static Logger info = LoggerFactory.getLogger(LoggingConstants.Info.class);
    private static Logger error = LoggerFactory.getLogger(LoggingConstants.Error.class);

    private static Long getCurrentTime(){
        return Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").
                format(new Date(System.currentTimeMillis())));
    }

    public static final void info(){
        long currentTime = getCurrentTime();

    }
}
