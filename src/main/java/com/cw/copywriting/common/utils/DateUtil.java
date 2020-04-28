package com.cw.copywriting.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiaoZiYang
 * @version 1.0
 * @date 2020/4/28 18:11
 * @Desc
 */
public class DateUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String sdfYYYYMMDDHHMMSS(Date date) {
        return format.format(date);
    }

    public static String thisTime() {
        return sdfYYYYMMDDHHMMSS(new Date());
    }
}
