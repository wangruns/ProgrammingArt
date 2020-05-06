package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * 分析
异或可以看做一种加运算
 */
public class P48_不用加减乘除做加法 {
	
	public int Add(int num1,int num2) {
		while(num2!=0) {
			int addWithoutJinWei=num1^num2;
			num2=(num1&num2)<<1;//处理进位
			num1=addWithoutJinWei;
		}
		return num1;
    }

}
