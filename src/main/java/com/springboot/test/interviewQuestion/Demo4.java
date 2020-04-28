package com.springboot.test.interviewQuestion;

import java.util.*;

/***
 * Created with IntelliJ IDEA.   动态规划-算法解题思路
 * Description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *              意思是这个字符串顺着念也倒着念都是一样的
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 输入: "cbbd"
 * 输出: "bb"
 * User: silence
 * Date: 2019-05-22
 * Time: 上午8:22
 */
public class Demo4 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //System.out.println(fan("jhgtrclvzumufurdemsogfkpzcwgyepdwucnxrsubrxadnenhvjyglxnhowncsubvdtftccomjufwhjupcuuvelblcdnuchuppqpcujernplvmombpdttfjowcujvxknzbwmdedjydxvwykbbamfnsyzcozlixdgoliddoejurusnrcdbqkfdxsoxxzlhgyiprujvvwgqlzredkwahexewlnvqcwfyahjpeiucnhsdhnxtgizgpqphunlgikogmsffexaeftzhblpdxrxgsmeascmqngmwbotycbjmwrngemxpfakrwcdndanouyhnnrygvntrhcuxgvpgjafijlrewfhqrguwhdepwlxvrakyqgstoyruyzohlvvdhvqmzdsnbtlwctetwyrhhktkhhobsojiyuydknvtxmjewvssegrtmshxuvzcbrabntjqulxkjazrsgbpqnrsxqflvbvzywzetrmoydodrrhnhdzlajzvnkrcylkfmsdode"));
        System.out.println(center("abadd"));
        System.out.println(System.currentTimeMillis()-start);

    }


    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s) || s.length() <= 1) {
            return s;
        }
        int maxLength = 0;
        String maxString = "";
        StringBuffer sb = new StringBuffer();
        for (char ch : s.toCharArray()) {
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }
        for (char c : sb.toString().toCharArray()) {
            int start = s.indexOf(c);
            int end = s.lastIndexOf(c);
            if (end - start + 1 < maxLength) {
                continue;
            }
            //某字符只存在一个
            if (start == end) {
                continue;
            }
            //某字符存在两个或以上
            String str = s.substring(start, end + 1);

            if (str.equals(new StringBuffer(str).reverse().toString())) {
                if (str.length() > maxLength) {
                    maxLength = str.length();
                    maxString = str;
                }
                continue;
            }
            if (s.substring(start + 1, end).indexOf(c) == -1) {
                continue;
            }
            //存在两个以上且这两字符内不相同
            List<Integer> list = new ArrayList<>();
            while (str.indexOf(c) != -1) {
                list.add(str.indexOf(c));
                str = str.replaceFirst(String.valueOf(c), "=");
            }
            for (Integer i : list) {
                for (Integer j : list) {
                    if (i >= j || j - i + 1 <= maxLength) {
                        continue;
                    }
                    String ss = str.substring(i, j + 1);
                    if (new StringBuffer(ss).reverse().toString().equals(ss)) {
                        maxLength = j - i + 1;
                        maxString = ss.replaceAll("=", String.valueOf(c));
                    }
                }
            }
        }
        if (maxLength == 0) {
            maxString = s.substring(0, 1);
        }
        return maxString;
    }

    public static String center(String s){
        if (s == null || "".equals(s) || s.length() <= 1 || s.equals(new StringBuffer(s).reverse().toString())) {
            return s;
        }
        int length = 0; String max = "";
        for(int i = 0 ; i < s.length(); i++){
            int j = 0 ;
            while(i - j >=0 && i + j < s.length() && i+j>=0 ){
                String ss = s.substring(i-j,i+j+1);
                if(ss.equals(new StringBuffer(ss).reverse().toString())) {
                    if(ss.length()>length){
                        max = ss;
                        length = ss.length();
                    }
                    j++;
                }else{
                    break;
                }
            }
        }
        if("".equals(max)){
            return s.substring(0,1);
        }
        return max;
    }


    public static String center1(String s){
        if (s == null || "".equals(s) || s.length() <= 1 || s.equals(new StringBuffer(s).reverse().toString())) {
            return s;
        }
        int length = 0; String max = "";
        for(int i = 0 ; i < s.length(); i++){
             for(int  j = i+2; j<s.length();j++){
                 String ss = s.substring(i,j);
                 if(ss.equals(new StringBuffer(ss).reverse())){
                     if(ss.length()>length){
                         length = ss.length();
                         max = ss;
                     }
                 }
             }
        }
        if("".equals(max)){
            return s.substring(0,1);
        }
        return max;
    }
}
