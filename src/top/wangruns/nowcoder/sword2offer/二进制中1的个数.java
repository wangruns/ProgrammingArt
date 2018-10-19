package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 */
public class 二进制中1的个数 {
	
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
	public int NumberOf1_2(int n) {
		int cnt=0;
		while(n!=0) {
			n&=(n-1);
			cnt++;
		}
		return cnt;
    }
	
}
