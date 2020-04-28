package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description:编写一个函数来查找字符串数组中的最长公共前缀。* 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:* 输入: ["flower","flow","flight"]* 输出: "fl"
 * 示例 2:* 输入: ["dog","racecar","car"]* 输出: ""* 解释: 输入不存在公共前缀。
 * 说明:* 所有输入只包含小写字母 a-z 。
 * User: silence
 * Date: 2019-07-18
 * Time: 上午10:04
 */
public class MaxString {

    public static void main(String[] args){
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }
        String minString = strs[0];
        for(String s : strs){
            if(minString.length() > s.length()){
                minString = s;
            }
        }
        String result = "";
//        顺序
//        for(int i = 1; i <= minString.length(); i ++){
//            String str = minString.substring(0,i);
//            for(String s : strs){
//                if(s.indexOf(str) != 0){
//                    return result;
//                }
//            }
//            result = str;
//        }
        //倒序
        int count = 0;
        for(int i = minString.length(); i > 0; i --){
            String str = minString.substring(0,i);
            for(String s : strs){
                if(s.indexOf(str) != 0){
                    count = i;
                    break;
                }
            }
            if(count != i){
                result = str;
                break;
            }
        }
        return result;
    }
}
