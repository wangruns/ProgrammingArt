package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39
 *
 */
public class P7_斐波那契数列 {
	
	//递归方式
	public int Fibonacci1(int n) {
		if(n<2) return n;
		return Fibonacci1(n-1)+Fibonacci1(n-2);
    }
	
	//迭代方式
	public int Fibonacci2(int n) {
		if(n<2) return n;
		int t1=0,t2=1,t3=0;
		for(int i=2;i<=n;i++) {
			t3=t1+t2;
			t1=t2;
			t2=t3;
		}
		return t3;
	}
	
}
