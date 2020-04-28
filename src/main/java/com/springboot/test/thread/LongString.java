package com.springboot.test.thread;

/**
 * Created with IntelliJ IDEA.
 * Description: 两个纯整数的长String的相加
 * User: silence
 * Date: 2018-10-29
 * Time: 上午9:35
 */
public class LongString {

    public static void main(String[] args){
        LongString.addString();
    }

    public static void addString(){
        String num2 = "123456789123456789123456789123456789123456789123456789123456789123456789";
        String num1 = "876543210876543210876543210876543210876543210876543210876543211";
        String result = "";//结果
        //比较长度大小
        if(num1.length() > num2.length()){
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        //使其长度相同
        for( ; num1.length() < num2.length() ; ){
            num1 = "0" + num1;
        }
        //转化成数组
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int[] res = new int[num1.length()];//个个位数的结果
        int[] flag = new int[num1.length()];//判断相加的结果
        for(int i = c1.length-1 ; i >= 0 ; i--){
            //个个位数相加
            if(flag[i] == 1){
                //进1
                res[i] = Integer.parseInt(c1[i] + "") + Integer.parseInt(c2[i] + "")+1;
            } else{
                res[i] = Integer.parseInt(c1[i] + "") + Integer.parseInt(c2[i] + "");
            }
            //最高位不进行进1操作
            if(i == 0){
                break;
            }
            //相加大于9，进1操作
            if(res[i] > 9){
               flag[i-1] = 1;
               res[i] = res[i] - 10;
            }
        }
        for(int i = 0 ; i < res.length ; i++){
            result = result + res[i];
        }
        System.out.println(result);
    }

}
