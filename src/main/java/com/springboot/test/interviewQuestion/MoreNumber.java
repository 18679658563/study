package com.springboot.test.interviewQuestion;

import java.util.HashMap;
import java.util.Map;

/***
 * Created with IntelliJ IDEA.
 * Description: 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * User: silence
 * Date: 2019-05-20
 * Time: 上午9:41
 */
public class MoreNumber {

    /**
     * 我的方案
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ;i++){
            if(map.containsKey(nums[i])){
                map.replace(nums[i],map.get(nums[i]),map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
            if(map.get(nums[i]) > nums.length/2){
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 思路：众数个数大于一半，所以众数一定会让count==0，且其他数不会被替换
     * 权威的
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        int result = 0;int count=0;
            for(int i=0; i<nums.length; i++) {
            if(count==0){
                result=nums[i];
                count=1;
            }else {
                count+=(result==nums[i]?1:-1);
            }
        }
        return result;
    }

    public static void main(String[] srga){
        int[] nums = {2,2,1,1,1,2,2,2,2,1,1,3,3,3,3,4,4,4,4,5,5,5,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        MoreNumber m = new MoreNumber();
        System.out.println(m.majorityElement1(nums));
    }

}
