package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:* 输入: 123* 输出: 321
 *  示例 2:* 输入: -123* 输出: -321
 * 示例 3:* 输入: 120* 输出: 21
 * 注意:* 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * User: silence
 * Date: 2019-07-18
 * Time: 上午8:58
 */
public class FanInt {
    public static void main(String[] args){
        System.out.println(reverse(-2147447412));
    }

    public static int reverse(int x) {
        boolean flag = true;
        if(x < 0){ flag = false;}
        String str = new StringBuilder(""+x).reverse().toString();
        if(!flag){
            str = "-" + str.substring(0,str.length()-1);
        }
        Long longInt = Long.parseLong(str);
        if(longInt > Integer.MAX_VALUE || longInt < Integer.MIN_VALUE){
            return 0;
        }
        return longInt.intValue();
    }
}
