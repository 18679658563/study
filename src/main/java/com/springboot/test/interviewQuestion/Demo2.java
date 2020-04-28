package com.springboot.test.interviewQuestion;

import java.util.HashMap;
import java.util.Map;

/***
 * Created with IntelliJ IDEA.
 * Description: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
 * User: silence
 * Date: 2019-05-21
 * Time: 上午8:33
 */
public class Demo2 {

    public static void main(String[] args){
        int[] nums = {2, 7, 11, 15};
        System.out.println(twoSum(nums,9)[0]+"\t"+twoSum(nums,9)[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++ ){
            if(map.containsKey(nums[i])){
               return new int[]{map.get(nums[i]),i};
            }else{
                map.put(target - nums[i],i);
            }
        }
        return null;
    }

    /**
     * 最优
     */
    public static int[] twoSum1(int[] nums, int target) {
        int size = 2047;
        int[] maxArr = new int[size + 1];
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            int index = value & size;
            if (maxArr[index] != 0) {
                return new int[]{maxArr[index] - 1, i};
            }
            maxArr[nums[i] & size] = i + 1;
        }
        throw new IllegalArgumentException("cant not find element in nums that sum is target");
    }
}
