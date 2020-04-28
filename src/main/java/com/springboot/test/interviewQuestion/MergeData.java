package com.springboot.test.interviewQuestion;

import java.util.Arrays;

/***
 * Created with IntelliJ IDEA.
 * Description: 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * User: silence
 * Date: 2019-05-20
 * Time: 上午10:43
 */
public class MergeData {

    public static void main(String[] args){
       int[] nums1 = {1,2,3,0,0,0};int m = 3;
       int[] nums2 = {2,5,6};      int n = 3;
       merge(nums1,m,nums2,n);
       for(int x : nums1){
           System.out.println(x);
       }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        while(n>0){
            nums1[m] = nums2[n-1];
            m++;
            n--;
        }
        Arrays.sort(nums1);
    }
}
