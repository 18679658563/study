package com.springboot.test.interviewQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [2,3]
 */
public class ChongfuShu {

    public static void main(String[] args) {
        List<Integer> list = findDuplicates(new int[]{2,1,3,4,5,0,6,9,0,1});
        for (int x : list){
            System.out.println(
                    x
            );
        }
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1 ; i++) {
            if(nums[i] == nums[i+1]){
                list.add(nums[i]);
                ++i;
            }
        }
        Math.abs(1);
        return list;
    }
}
