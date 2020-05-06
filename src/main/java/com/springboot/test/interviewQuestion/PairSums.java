package com.springboot.test.interviewQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 * 示例 1:
 * 输入: nums = [5,6,5], target = 11
 * 输出: [[5,6]]
 * 示例 2:
 * 输入: nums = [5,6,5,6], target = 11
 * 输出: [[5,6],[5,6]]
 * 提示：
 * nums.length <= 100000
 * */
public class PairSums {

    public static void main(String[] args) {
    }

    public static List<List<Integer>> solution(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length >= 2){
            Arrays.sort(nums);
            int end = nums.length - 1;
            for (int i = 0; i < nums.length && end > i;) {
                if(nums[i] + nums[end] > target){
                    --end;
                }else if(nums[i] + nums[end] < target){
                    ++i;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[end]);
                    result.add(list);
                    --end;
                    ++i;
                }
            }
        }
        return result;
    }
}
