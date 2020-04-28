package com.springboot.test.interviewQuestion;


import java.util.*;

/***
 * Created with IntelliJ IDEA.
 * Description:给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * User: silence
 * Date: 2019-07-12
 * Time: 上午9:07
 */
public class ListThree {

    public static void main(String[] args) {
        int[] nums = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        System.out.println(threeSum(nums).toString());
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Long time = System.currentTimeMillis();
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        if(nums.length < 3 || nums[0] > 0 || nums[nums.length-1] < 0){
            return result;
        }
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > 0 ){
                break;
            }
            if( (i != 0 && nums[i] == nums[i-1]) || (nums[nums.length-1] + nums[i]) < nums[i]  ){
                continue;
            }
            int max = nums.length - 1;
            int min = i + 1;
            while(max > min && nums[max] >= 0 && nums[i] + nums[min] <= 0 ){
                int sum = nums[i] + nums[max] + nums[min];
                if( sum == 0){
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[min]);
                    list.add(nums[max]);
                    result.add(list);
                    while(max > min && nums[min] == nums[min + 1]){
                        min ++;
                    }
                    while(max > min &&  nums[max] == nums[max-1]){
                        max --;
                    }
                    max --;min++;
                } else if(sum > 0){
                    while(max > min &&  nums[max] == nums[max-1]){
                        max --;
                    }
                    max --;
                } else {
                    while(max > min && nums[min] == nums[min + 1]){
                        min ++;
                    }
                    min ++;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - time);
        return result;
    }

}
