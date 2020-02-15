package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 */
public class P11_二进制中1的个数 {
	
	//常规思路，需要循环固定32次
	public int NumberOf1_1(int n) {
		int cnt=0,t=1;
		while(t!=0) {
			if((n&t)==t)cnt++;
			t<<=1;
		}	
		return cnt;
    }
	
	//更优思路，n中有多少个1就循环多少次
	/**
	 * n 和 (n-1) 事实上是相差1的，这里的1在用二进制表示的时候是有体现的
	 * & 符号可以理解为消除差异，因为在 n & (n-1) 只保留它们相同的部分
	 * 即每一次 & 就消除了一个二进制意义上的1，故有几个就消除几次(循环几次)
	 */
	public int NumberOf1_2(int n) {
		int cnt=0;
		while(n!=0) {
			n&=(n-1);
			cnt++;
		}
		return cnt;
    }
	
}
