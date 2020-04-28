package com.springboot.test.interviewQuestion;

import java.util.ArrayList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 给定一个字符串，请你找出其中不含有重复连续字符的 最长子串 的长度。
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * User: silence
 * Date: 2019-05-21
 * Time: 上午9:07
 */
public class Demo3 {

    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }

    public static int lengthOfLongestSubstring(String s) {
        String result = "";
        char[] ch = s.toCharArray();
        StringBuffer str = new StringBuffer();
        for(char c : ch){
            if(str.indexOf(String.valueOf(c)) == -1){
                str.append(c);
                continue;
            }
            if(result.length() < str.length()){
                result = str.toString();
            }
            str.delete(0,str.indexOf(String.valueOf(c))+1);
            str.append(c);
        }
        if(result.length() < str.length()){
            result = str.toString();
        }
        return result.length();
    }
}
