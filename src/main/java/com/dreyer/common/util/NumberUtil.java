
package com.dreyer.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * @author Dreyer
 * @version 1.0
 * @description 数字工具类
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午10:02:24
 */
public class NumberUtil {
    public static void main(String[] args) {
        Short s = new Short("1");


    }

    /**
     * 中文金额单位数组
     */
    private static final String[] straChineseUnit = new String[]
            {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};

    /**
     * 中文数字字符数组
     */
    private static final String[] straChineseNumber = new String[]
            {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};


    /**
     * 判断一个对象是否为一个整型
     *
     * @param o
     * @return
     */
    public static boolean isNumber(Object o) {
        Pattern pattern = Pattern.compile("[0-9]*");

        return (o == null || o.toString() == "") ? false : pattern.matcher(o.toString()).matches();
    }

    /**
     * 将一个对象解析成整型，如果对象不是整型 则返回默认值
     *
     * @param obj
     * @param defaultv
     * @return
     */
    public static Integer parseInt(Object obj, Integer defaultv) {

        return !isNumber(obj) ? defaultv : Integer.parseInt(obj.toString());
    }

    /**
     * 将一个对象解析成整型，如果对象不是整型，则返回0
     *
     * @param obj
     * @return
     */
    public static Integer parseInt(Object obj) {

        return parseInt(obj, 0);
    }

    /**
     * 以指定精度，对指定双精度浮点型数值进行四舍五入
     *
     * @param number 双精度浮点型数值
     * @param digits 精度
     * @return
     */
    public static String decimalFormat(double number, int digits) {
        StringBuffer sb = new StringBuffer("0");
        if (digits > 0) {
            sb.append(".");
        }

        for (int i = 0; i < digits; i++) {
            sb.append("0");
        }

        DecimalFormat df = new DecimalFormat(sb.toString());

        return df.format(number);
    }


    /**
     * 将双精度浮点型数值转换为百分号形式
     *
     * @param number
     * @return
     */
    public static String convertPercentString(double number) {

        return roundDown(number * 100) + "%";
    }

    /**
     * 以指定精度，对指定双精度浮点型数值转换为百分号形式
     *
     * @param number
     * @param digits
     * @return
     */
    public static String convertPercentString(double number, int digits) {

        return decimalFormat(number * 100, digits) + "%";
    }

    /**
     * 加法运算
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法运算，当发生除不尽的情况时，精确到小数点以后2位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, 2);
    }

    /**
     * 除法运算，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确到小数点以后几位
     * @return
     */
    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断 a 与 b 是否相等
     *
     * @param a
     * @param b
     * @return a==b 返回true, a!=b 返回false
     */
    public static boolean equal(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断 a 是否大于等于 b
     *
     * @param a
     * @param b
     * @return a&gt;=b 返回true, a&lt;b 返回false
     */
    public static boolean greaterThanOrEqualTo(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断 a 是否大于 b
     *
     * @param a
     * @param b
     * @return a&gt;b 返回true, a&lt;=b 返回 false
     */
    public static boolean bigger(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断 a 是否小于 b
     *
     * @param a
     * @param b
     * @return a&lt;b 返回true, a&gt;=b 返回 false
     */
    public static boolean lessThan(double a, double b) {
        BigDecimal v1 = BigDecimal.valueOf(a);
        BigDecimal v2 = BigDecimal.valueOf(b);
        if (v1.compareTo(v2) == -1) {
            return true;
        }
        return false;
    }

    /**
     * 四舍五入保留小数点后两位
     *
     * @param num
     * @return
     */
    public static double roundDown(double num) {

        return Double.valueOf(String.format("%.2f", num));
    }

    /**
     * &&存在问题：如果有小数，则只能精确到一位小数
     * <p/>
     * 将数字金额转换为中文金额
     *
     * @param bigdMoneyNumber 数字金额
     * @return
     */
    public static String doNumberCurrencyToChineseCurrency(BigDecimal bigdMoneyNumber) {
        String strChineseCurrency = "";
        //零数位标记
        boolean bZero = true;
        //中文金额单位下标
        int ChineseUnitIndex = 0;
        try {
            if (bigdMoneyNumber.intValue() == 0)
                return "零元整";
            //处理小数部分，四舍五入
            double doubMoneyNumber = Math.round(bigdMoneyNumber.doubleValue() * 100);
            //是否负数
            boolean bNegative = doubMoneyNumber < 0;
            //取绝对值
            doubMoneyNumber = Math.abs(doubMoneyNumber);
            //循环处理转换操作
            while (doubMoneyNumber > 0) {
                //整的处理(无小数位)
                if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0)
                    strChineseCurrency = strChineseCurrency + "整";
                //非零数位的处理
                if (doubMoneyNumber % 10 > 0) {
                    strChineseCurrency = straChineseNumber[(int) doubMoneyNumber % 10] +
                            straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
                    bZero = false;
                }
                //零数位的处理
                else {
                    //元的处理(个位)
                    if (ChineseUnitIndex == 2) {
                        //段中有数字
                        if (doubMoneyNumber > 0) {
                            strChineseCurrency = straChineseUnit[ChineseUnitIndex] +
                                    strChineseCurrency;
                            bZero = true;
                        }
                    }
                    //万、亿数位的处理
                    else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
                        //段中有数字
                        if (doubMoneyNumber % 1000 > 0)
                            strChineseCurrency = straChineseUnit[ChineseUnitIndex] +
                                    strChineseCurrency;
                    }
                    //前一数位非零的处理
                    if (!bZero)
                        strChineseCurrency = straChineseNumber[0] + strChineseCurrency;

                    bZero = true;
                }
                doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
                ChineseUnitIndex++;
            }
            //负数的处理
            if (bNegative)
                strChineseCurrency = "负" + strChineseCurrency;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return strChineseCurrency;
    }
}
