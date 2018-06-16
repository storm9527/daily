package io.github.ylingxiao.daily.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 * @Author ylingxiao
 */
public class DateUtils {

    static String formatDate(String patten){
        return new SimpleDateFormat(patten).format(new Date());
    }
}
