package com.leetcode.wangruns;

//15��single-number-ii[���Ӷ�]
/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note: 
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * Single Number�ı��ʣ�������һ������¼ÿ��bit���ֵĴ��������һ��bit�������ξ͹�0��
 * ����������ö����Ƶ��µ�λ����^�Ǻ���Ȼ��.
 * Single Number II�У�����ܶ��������Ƶ��µ�ĳ��λ������Ҳ���Դﵽ��ͬ��Ч��,
 * Single Number II����Ҫ��¼ÿ��bit���ֵĴ�����һ�����㲻���ͼ�����������ones����¼ֻ���ֹ�һ�ε�bits,
 * ��twos����¼ֻ���ֹ����ε�bits��ones&twosʵ���Ͼͼ�¼�˳��ֹ����ε�bits��
 * ��ʱ��������ģ����г���3�ξ͵���Ϊ0�Ĳ�����Ĩȥones��twos�ж�Ϊ1��bits��
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
		//��¼������һ�Ρ����Ρ����ε�bits
		int ones=0;
		int twos=0;
		int threes=0;
		for(int v:A){
			//�ȸ���twos
			twos|=ones&v;
			ones^=v;
			//one��two�ж�Ϊ1�������3��
			threes=ones&twos;
			//���������3�ε�
			twos&=~threes;
			ones&=~threes;
		}
		return ones;
	}

}
