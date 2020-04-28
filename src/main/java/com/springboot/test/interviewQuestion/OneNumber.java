package com.springboot.test.interviewQuestion;

/***
 * Created with IntelliJ IDEA.
 * Description: 非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
 * User: silence
 * Date: 2019-05-20
 * Time: 上午8:29
 */
public class OneNumber {

    /**
     * 用^符号可以将两个相同的变成0；以二进制方式运算，只要存在偶数个相同的数，这个数变成0，最后剩下唯一一个不同的数
     * 时间复杂度为0
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums){
        int result = 0;
        for(int i=0; i<nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void main(String[] args){
        OneNumber oneNumber = new OneNumber();
        int[] nums = {1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8};
        System.out.println(oneNumber.singleNumber(nums));
    }
}
