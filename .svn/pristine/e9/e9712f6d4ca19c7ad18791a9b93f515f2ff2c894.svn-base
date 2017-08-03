package com.zx.sframe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zx.sframe.util.vo.UserBean;

public class SysLoggerUtil
{
    private static Logger log = LoggerFactory.getLogger(SysLoggerUtil.class);

    public static void saveLogToFile(UserBean user, String url, String ip, String paramStr, long handleTime,
                                     String userAgent)
    {
        // log.info("url:" + url +"|ip:"+ ip + "|userId:"+ user.getUserId()
        // +"|userAgent:"+ userAgent + "|handleTime:" + handleTime +"|params:"+
        // paramStr);
        // 这个是用来调试的
        log.info("url:" + url + "|ip:" + ip + "|userAgent:" + userAgent + "|handleTime:" + handleTime + "|params:"
                 + paramStr);
    }
}
