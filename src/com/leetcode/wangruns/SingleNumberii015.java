package com.leetcode.wangruns;

//15、single-number-ii[复杂度]
/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note: 
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * Single Number的本质，就是用一个数记录每个bit出现的次数，如果一个bit出现两次就归0，
 * 这种运算采用二进制底下的位操作^是很自然的.
 * Single Number II中，如果能定义三进制底下的某种位操作，也可以达到相同的效果,
 * Single Number II中想要记录每个bit出现的次数，一个数搞不定就加两个数，用ones来记录只出现过一次的bits,
 * 用twos来记录只出现过两次的bits，ones&twos实际上就记录了出现过三次的bits，
 * 这时候我们来模拟进行出现3次就抵消为0的操作，抹去ones和twos中都为1的bits。
 * 
 * A={1,1,1,2},ones=0,twos=0,threes=0
 * i=0	v=1
 * twos=ones & v         =>twos=00
 * ones^=v                   =>ones=01
 * threes=one&twos     =>threes=00
 * 
 * i=1	v=1
 * twos=ones & v		    =>01 & 01 	  =>twos=01
 * ones^=v				    =>01^01   	  =>ones=00
 * threes=ones&twos   =>01&00		  =>threes=00
 */
public class SingleNumberii015 {
	
	public int singleNumber(int[] A) {
		//记录出现了一次、两次、三次的bits
		int ones=0;
		int twos=0;
		int threes=0;
		for(int v:A){
			//先更新twos
			twos|=ones&v;
			ones^=v;
			//one和two中都为1则出现了3次
			threes=ones&twos;
			//清除出现了3次的
			twos&=~threes;
			ones&=~threes;
		}
		return ones;
	}

}
