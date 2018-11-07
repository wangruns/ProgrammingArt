package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
不能使用除法。
 *
 * 分析
B[0]=A[1] * A[2] * ... * A[n-1]
B[1]=A[0] * A[2] * ... * A[n-1]
B[2]=A[0] * A[1] * ... * A[n-1]
没有的看做是乘了1，左边下三角形可以连乘，而右边的上三角形也是可以的
B[0]= 1   * A[1] * A[2] * ... * A[n-1]
B[1]=A[0] *  1   * A[2] * ... * A[n-1]
B[2]=A[0] * A[1] *  1   * ... * A[n-1]
 */
public class 构建乘积数组 {
	
	public int[] multiply(int[] A) {
		int[]B=new int[A.length];
		//左边下三角连乘
		B[0]=1;
		for(int i=1;i<B.length;i++) B[i]=B[i-1]*A[i-1];
		//右边上三角连乘
		int temp=1;
		for(int i=B.length-2;i>=0;i--) {
			temp*=A[i+1];
			B[i]*=temp;
		}
		return B;
    }

}
