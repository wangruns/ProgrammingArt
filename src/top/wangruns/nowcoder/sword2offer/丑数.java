package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
把只包含质因子2、3和5的数称作丑数（Ugly Number）。
例如6、8都是丑数，但14不是，因为它包含质因子7。 
习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * 分析
1
2= 1*2
3= 1*3
4= 2*2
5= 1*5
6= 2*3
不难发现，后面的丑数是前面出现过的一个丑数乘以2，3，5中的一个得来
故分别用3个标记来记录{2,3,5}已经被前面的哪些数字乘过了，迭代求解
 */
public class 丑数 {
	
	public int GetUglyNumber_Solution(int index) {
		if(index<=1) return index;
		int[]a=new int[index];
		a[0]=1;
		int f2=0,f3=0,f5=0;
		for(int i=1;i<index;i++) {
			int t2=a[f2]*2,t3=a[f3]*3,t5=a[f5]*5;
			int min=Math.min(t2, Math.min(t3, t5));
			a[i]=min;
			if(min==t2) f2++;
			if(min==t3) f3++;
			if(min==t5) f5++;
		}
		return a[index-1];
	}
	
}
