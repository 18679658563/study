package com.springboot.test.interviewQuestion;

import java.util.Arrays;

public class TianShu {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println(trap(height)); //6
    }

    public static int trap(int[] height) {
        if(height.length == 0){
            return 0;
        }
        int[] temp = Arrays.copyOf(height,height.length);
        Arrays.sort(temp);
        int max = temp[temp.length - 1];
        int maxIndex = 0;
        for (int i = height.length - 1; i >= 0 ; i--) {
            if(height[i] == max){
                maxIndex = i;
                break;
            }
        }
        int count = 0;
        for (int i = 0; i < maxIndex; i++) {
            if(height[i] > height[i+1]){
                count += height[i] - height[i+1];
                height[i+1] = height[i];
            }

        }
        for (int i = height.length - 1; i > maxIndex ; i--) {
            if(height[i] > height[i-1]){
                count += height[i] - height[i-1];
                height[i-1] = height[i];
            }
        }
        return count;
    }
}
