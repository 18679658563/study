package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description:给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 示例 1:输入: dividend = 10, divisor = 3  输出: 3
 * 示例 2:输入: dividend = 7, divisor = -3  输出: -2
 * 说明:被除数和除数均为 32 位有符号整数。除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * User: silence
 * Date: 2019-07-15
 * Time: 上午8:39
 */
public class TwoNumberMod {

    public static int divide(int dividend, int divisor) {
         int result = 0,num = 1; boolean flag = false;
         if(dividend == 0 || ((dividend == -1 || dividend == 1) && divisor != 1 && divisor != -1)){
             return 0;
         }
         if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)){
             flag = true;
         }
         if(divisor == -1 || divisor == 1){
             return flag ? (Math.abs(dividend) < 0 ? Integer.MIN_VALUE : 0-Math.abs(dividend)) : (Math.abs(dividend) < 0 ? Integer.MAX_VALUE : Math.abs(dividend)) ;
         }
         int number1 = dividend > 0 ? 0-dividend : dividend;
         long number2 = divisor > 0 ? 0-divisor : divisor;
         if(number1 > number2){return  0;}
         if(number1 == number2){return flag ? -1 : 1;}
         while (number2 >= number1){
             result += num;
             number1-=number2;
             if(number1 - number2 > number2){
                 num = 1;
                 number2 = divisor > 0 ? 0-divisor : divisor;
                 continue;
             }
             number2 += number2;
             num+=num;
         }
         return flag ? 0-result : result;
    }

    public static void main(String[] args){
        Long time = System.currentTimeMillis();
        System.out.println(divide(15,
                2));
        System.out.println(System.currentTimeMillis() - time);
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }

}
