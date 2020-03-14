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
public class P33_丑数 {
	
	public int GetUglyNumber_Solution(int index) {
		if(index==0) return 0;
		int[]a=new int[index];
		a[0]=1;
		int a2=0,a3=0,a5=0;
		for(int i=1;i<index;i++) {
			int u2new=a[a2]*2;
            int u3new=a[a3]*3;
            int u5new=a[a5]*5;
			int min=Math.min(u2new, Math.min(u3new, u5new));
			a[i]=min;
			if(min==u2new) a2++;
			if(min==u3new) a3++;
			if(min==u5new) a5++;
		}
		return a[index-1];
	}
	
}
