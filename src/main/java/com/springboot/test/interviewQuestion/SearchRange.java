package com.springboot.test.interviewQuestion;

/***
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class SearchRange {

    public static void main(String[] args) {
        int[] nums = {1,4,4,4,5,6,6,8,9};
        searchRange(nums,1);
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1,-1};
        }
        int left = getIndex(nums,target,true);
        int right = getIndex(nums,target,false);
        if(left == right && nums[left] != target){
            return new int[]{-1,-1};
        }
        return new int[]{left,target == nums[right] ? right : right-1};
    }

    public static int getIndex(int[] nums, int target, boolean flag){
        int start = 0;
        int end = nums.length - 1;
        while(start < end){
            int index = (start + end)/2;
            if(nums[index] > target || (flag && nums[index] == target)){
                end = index;
            } else {
                start = index + 1;
            }
        }
        return start;
    }
}
