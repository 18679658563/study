package com.springboot.test.interviewQuestion;

import java.util.ArrayList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:* 输入: num1 = "2", num2 = "3"* 输出: "6"
 * 示例 2:** 输入: num1 = "123", num2 = "456"* 输出: "56088"
 * 说明：* num1 和 num2 的长度小于110。* num1 和 num2 只包含数字 0-9。* num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * User: silence
 * Date: 2019-07-16
 * Time: 上午8:29
 */
public class TwoXString {

    public static void main(String[] args){
        System.out.println(multiply("123","456"));
    }

    public static String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        int[] result = new int[num1.length()+num2.length()];
        for(int i = num1.length()-1;i>=0;i--){
            for(int j = num2.length()-1;j>=0;j--){
                // 取数字相乘，　－＇０＇得到对应的数字，效率高
                result[i+j+1] += (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
            }
        }
        int count = 0;
        for(int i = result.length-1 ; i >= 0 ; i-- ){
            result[i] = result[i]+count ;
            count = result[i]/10;
            result[i] = result[i]%10;
        }
        StringBuilder sb = new StringBuilder();
        for(int x : result){
            if(sb.length() == 0 && x == 0){
                continue;
            }
            sb.append(x);
        }
        return sb.toString();
    }


}
