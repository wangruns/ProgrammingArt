package com.leetcode.wangruns;

//14、single-number[复杂度]
/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note: 
 * Your algorithm should have a linear runtime complexity. 
 * Could you implement it without using extra memory?
 * 
 * 除了一个单身的，其他都是两两配对的，联想到异或
 * 两个相同的数异或为0，而0和任何数异或为任何数
 * 所以可以将他们全部异或一次，最后的结果就是那个单身的数.
 */
public class SingleNumber014 {

	public int singleNumber(int[] A) {
		int singleNumber=0;
        for(int i=0;i<A.length;i++) singleNumber^=A[i];
        return singleNumber;
    }
	
}
