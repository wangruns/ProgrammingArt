package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
给定一个double类型的浮点数base和int类型的整数exponent。
求base的exponent次方
 *
 * 分析
如a^11可以看做a^{2^0 + 2^1 + 2^3}=a^{2^0} * a^{2^1} * a^{2^3}
即a^{01011}
 *
 * 为什么快速幂的时间复杂度是O(log n)呢?
不妨设输入的指数规模n=2^k，而在计算机中仅仅需要k个bit就可以表示这个数。
对于快速幂来说，每次处理1bit，所以只需k次就能处理完，即时间复杂度为THETA(log n)
 */
public class P12_数值的整数次方 {
	
	//快速幂解法O(log(n))
	public double Power(double base, int exponent) {
		if(base<0.00000001 && base >-0.00000001) return 0;
		if(exponent==0) return 1;
		int n=exponent>0?exponent:-exponent;
		double res=1;
		while(n!=0) {
			if((n&1)==1) res*=base;
			base*=base;
			n>>=1;
		}
		return exponent>0?res:1/res;
    }
	
}
