package com.springboot.test.interviewQuestion;

import java.util.Arrays;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *              每行的元素从左到右升序排列。
 *              每列的元素从上到下升序排列。
 * User: silence
 * Date: 2019-05-20
 * Time: 上午10:08
 */
public class TwoData {
    public static void main(String[] args){
        int[][] result = {{1,   4,  7, 11, 15},
                          {2,   5,  8, 12, 19},
                          {3,   6,  9, 16, 22},
                          {10, 13, 14, 17, 24},
                          {18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(result,20));
    }

    /**
     * 我的
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] x : matrix) {
            if (x == null || x.length < 1) {
                continue;
            }
            if (x[x.length - 1] >= target && x[0] <= target) {
                for (int y : x) {
                    if (y == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 权威的
     * 思路：横纵一起比较
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (target == matrix[row][col]) {
                return true;
            //横向变小
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                //纵向加1
                row++;
            }
        }
        return false;
    }
}
