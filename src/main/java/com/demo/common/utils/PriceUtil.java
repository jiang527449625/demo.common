package com.demo.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author :jky
 * @Description:
 * @ Date: Created in 2021-04-27 10:30
 */
public class PriceUtil {

    /**
     * 根据字符串精确到小数点后2位
     *
     * @param price
     * @param newScale
     * @return
     */
    public static Double StringToDouble(String price, int newScale) {
        Double priceResult = Double.parseDouble(price == null ? "0.00" : price);
        BigDecimal b = new BigDecimal(priceResult);
        priceResult = b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return priceResult;
    }

    /**
     * 根据浮点数精确到小数点后2位
     *
     * @param price
     * @param newScale
     * @return
     */
    public static Double DoubleFormatter(Double price, int newScale) {
        Double priceResult;
        BigDecimal b = new BigDecimal(price);
        priceResult = b.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return priceResult;
    }

    public static int doubleToInt(double param) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String numberStr = df.format(param);
        String str = numberStr.substring(0, numberStr.indexOf(".")) + numberStr.substring(numberStr.indexOf(".") + 1);
        int result = Integer.parseInt(str);
        return result;
    }

    public static String doubleToString(double param) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String numberStr = df.format(param);
        return numberStr;
    }

    /**
     * 加法运算
     * @param m1
     * @param m2
     * @return
     */
    public static double addDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).doubleValue();
    }

    /**
     * 减法运算
     * @param m1
     * @param m2
     * @return
     */
    public static double subDouble(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    /**
     * 乘法运算
     * @param m1
     * @param m2
     * @return
     */
    public static double mul(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.multiply(p2).doubleValue();
    }


    /**
     *  除法运算
     *   @param   m1
     *   @param   m2
     *   @param   scale
     *   @return
     */
    public static double div(double m1, double m2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("Parameter error");
        }
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.divide(p2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
