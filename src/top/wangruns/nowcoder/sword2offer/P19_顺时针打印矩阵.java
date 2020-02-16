package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;

/**
 * 
 * 题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
例如，如果输入如下4 X 4矩阵： 
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * 分析
解题的关键在于定义好矩阵的边界
int left=0,right=col-1,top=0,bottom=row-1;
 */
public class P19_顺时针打印矩阵 {
	
	public ArrayList<Integer> printMatrix(int [][] matrix) {
		if(matrix==null)return null;
		ArrayList<Integer> res=new ArrayList<Integer>();
		int top=0,bottom=matrix.length-1,right=matrix[0].length-1,left=0;
		while(left<=right && top<=bottom) {
			for(int i=left;i<=right;i++) res.add(matrix[top][i]);
			top++;
			for(int i=top;i<=bottom;i++) res.add(matrix[i][right]);
			right--;
			if(top-1!=bottom)//防止只有一行的数组来说，会产生回文效果
					for(int i=right;i>=left;i--) res.add(matrix[bottom][i]);
			bottom--;
			if(left!=right+1)//防止只有一列的数组来说，会产生回文效果
				for(int i=bottom;i>=top;i--) res.add(matrix[i][left]);
			left++;
		}
		return res;
    }

}
