package com.leetcode.wangruns;

//14��single-number[���Ӷ�]
/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note: 
 * Your algorithm should have a linear runtime complexity. 
 * Could you implement it without using extra memory?
 * 
 * ����һ������ģ���������������Եģ����뵽���
 * ������ͬ�������Ϊ0����0���κ������Ϊ�κ���
 * ���Կ��Խ�����ȫ�����һ�Σ����Ľ�������Ǹ��������.
 */
public class SingleNumber014 {

	public int singleNumber(int[] A) {
		int singleNumber=0;
        for(int i=0;i<A.length;i++) singleNumber^=A[i];
        return singleNumber;
    }
	
}
