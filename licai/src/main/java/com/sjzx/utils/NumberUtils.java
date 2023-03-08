package com.sjzx.utils;

import com.sjzx.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : NumberUtils
 * @Description : 数字工具类
 * @Author : Horus
 * @Date: 2020-11-02 19:08
 */
public class NumberUtils {

    public static int maintainAccuracy(double r, int t) {
        return (int) (r * 10000/t);
    }

    public static LocalDate toLocalDate(String date) {
        return LocalDate.of(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
    }

    public static boolean isNumeric(String str) {
        //Pattern pattern = Pattern.compile("^-?[0-9]+"); //这个也行
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static long toLong(String val) {
        if(val == null) {
            throw new ServiceException("数据不能为空");
        }
        String result = val.trim().replace(",", "");
        try {
            BigDecimal bigDecimal = new BigDecimal(result);
            return bigDecimal.longValue();
        } catch (Exception e) {
            throw new ServiceException("数据只能为数字:" + val);
        }
    }

    public static int addRate(long current, long previous) {
        return divide(current - previous, previous);
    }

    /**
     * @param molecule 分子
     * @param denominator 分母
     * @return
     */
    public static int divide(long molecule, long denominator) {
        if(denominator == 0) {
            return 0;
        }
        BigDecimal result = BigDecimal.valueOf(molecule).divide(BigDecimal.valueOf(denominator), 4, BigDecimal.ROUND_HALF_UP);
        return result.multiply(new BigDecimal("10000")).intValue();
    }

    public static String toPercent(String num) {
        if(num != null && isNumeric(num)) {
            return new BigDecimal(num).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP) + "%";
        }
        return num;
    }

    /**
     * @param molecule 分子
     * @param denominator 分母
     * @return
     */
    public static BigDecimal divideToBigDecimal(long molecule, long denominator) {
        if(denominator == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(molecule).divide(BigDecimal.valueOf(denominator), 2, BigDecimal.ROUND_HALF_UP);
    }

}
