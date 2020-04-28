package com.springboot.test.interviewQuestion;

import org.omg.CORBA.INTERNAL;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:* 输入:* [*  [ 1, 2, 3 ],*  [ 4, 5, 6 ],*  [ 7, 8, 9 ]* ]* 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:* 输入:* [*   [1, 2, 3, 4],*   [5, 6, 7, 8],*   [9,10,11,12]* ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * User: silence
 * Date: 2019-08-01
 * Time: 上午9:19
 */
public class LuoXuanShuZu {

    public static void main(String[] args){
        StringUtils.hasText("");
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix2 = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        List<Integer> list = spiralOrder(matrix );
        for(int result : list){
            System.out.print(result+"\t");
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length==0){
            return list;
        }
        int start = 0; int end = matrix.length - 1;
        int left = 0; int right = matrix[0].length - 1;
        while(end >= start && right >= left){
            for(int i = left; i <= right; i++){
                list.add(matrix[start][i]);
            }
            start ++;
            if(start > end){ break;}
            for(int i = start; i <= end; i++){
                list.add(matrix[i][right]);
            }
            right --;
            if(left > right){ break;}
            for(int i = right; i >= left ; i--){
                list.add(matrix[end][i]);
            }
            end --;
            if(start > end){ break;}
            for(int i = end; i >= start; i --){
                list.add(matrix[i][left]);
            }
            left ++;
        }
        return list;
    }
}
