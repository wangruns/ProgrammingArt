package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1
 *
 */
public class 连续子数组的最大和 {
	
	public int FindGreatestSumOfSubArray1(int[] array) {
		int max=array[0];
		int lastSequence=array[0];
		for(int i=1;i<array.length;i++) {
			//如果前面的累计值大于零，则表示前面的累计值对当前是有贡献的
			if(lastSequence>0) lastSequence+=array[i];
			else lastSequence=array[i];
			if(max<lastSequence) max=lastSequence;
		}
		
		return max;
    }
	
	//动态规划思想，dp[i]表示array[i]元素结尾的最大连续子数组和
	//以{6,-3,-2,7,-15,1,2,2}为例，可以发现
	//dp[0]=6
	//dp[1]=3
	//dp[2]=1
	//dp[3]=8
	//以此类推，会发现dp[i]=max{dp[i-1]+array[i] , array[i]}
	public int FindGreatestSumOfSubArray2(int[] array) {
		int max=0x80000000;
		int dp=0;
		for(int i=0;i<array.length;i++) {
			dp=Math.max(dp+array[i], array[i]);
			max=Math.max(max, dp);
		}
		
		return max;
    }

}
