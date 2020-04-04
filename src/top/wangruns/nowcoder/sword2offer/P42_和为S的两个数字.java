package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;

/**
 * 
 * 题目描述
输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
如果有多对数字的和等于S，输出两个数的乘积最小的。
输出描述:对应每个测试案例，输出两个数，小的先输出。
 *
 * 分析
既然数组是有序的，则想到用两个指针分别从两端左右夹逼
 */
public class P42_和为S的两个数字 {
	
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
		ArrayList<Integer> res=new ArrayList<>();
        int plow=0,phigh=array.length-1;
		while(plow<phigh) {
			int cur=array[plow]+array[phigh];
			if(cur<sum) plow++;
			else if(cur==sum) {
				res.add(array[plow]);
				res.add(array[phigh]);
				break;
			}else phigh--;
		}
		return res;
    }

}
