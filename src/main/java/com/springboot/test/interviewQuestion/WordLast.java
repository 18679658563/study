package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description:给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * 输入: "Hello World"
 * 输出: 5
 * User: silence
 * Date: 2019-07-29
 * Time: 上午9:34
 */
public class WordLast {

    public static void main(String[] args){
        System.out.println(lengthOfLastWord("  "));
    }

    public static int lengthOfLastWord(String s) {
        if(s == null || "".equals(s.trim())){ return 0; }
        s = s.trim();
        if(s.indexOf(" ") == -1){ return s.length(); }
        int last = s.lastIndexOf(" ")+1;
        return s.substring(last).length();
    }
    /***
     * Created with IntelliJ IDEA.
     * Description:给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1:* 输入: [1,2,3]* 输出: [1,2,4]* 解释: 输入数组表示数字 123。
     * 示例 2:* 输入: [4,3,2,1]* 输出: [4,3,2,2]* 解释: 输入数组表示数字 4321。
     * User: silence
     * Date: 2019-07-29
     * Time: 上午9:34
     */

    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1; i >= 0 ; i --){
            digits[i] += 1;
            if(digits[i] == 10){
                digits[i] = 0;
            } else {
                return digits;
            }
        }
        int[] nums = new int[digits.length + 1];
        if(digits[0] == 0){
            nums[0] = 1;
            for(int i = 1 ; i < digits.length ; i ++){
                nums[i] = digits[i];

            }
        }
        return nums;
    }

}
