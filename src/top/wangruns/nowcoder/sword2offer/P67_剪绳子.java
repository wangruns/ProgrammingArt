package top.wangruns.nowcoder.sword2offer;

/**
 * 题目描述
给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
每段绳子的长度记为k[0],k[1],...,k[m]。
请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

输入描述
输入一个数n，意义见题面。（2 <= n <= 60）
输入8	输出18​
 *
 */
public class P67_剪绳子 {
	
	/*
	 * 一、找规律法，写几个看看规律
	 * 2 ： 1*2
	 * 3 ： 1*2
	 * 4 ： 2*2
	 * 5 ： 2*3
	 * 6 ： 3*3
	 * 7 ： 2*2*3
	 * 8 ： 2*3*3
	 * 9 ： 3*3*3
	 * 发现要想乘积最大，需要尽可能的多3
	 */
	public int cutRope1(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int i=n%3;
        int j=n/3;
        if(i==0) return (int) Math.pow(3,j);
        else if(i==1) return (int) (2*2*Math.pow(3,j-1));
        else return (int) (2*Math.pow(3,j));
    }
	
	/*
	 * 二、动态规划
	 * 动态规划求解问题的四个特征：
	 * （1）求一个问题的最优解
	 * （2）整体的问题的最优解是依赖于各个子问题的最优解
	 * （3）小问题之间还有相互重叠的更小的子问题
	 * （4）从上往下分析问题，从下往上求解问题
	 * 
	 * 考虑到必然有一个点把绳子分成两份，两份各自分割得出的乘积最大值，组成整条绳子乘积的最大值。
	 * --------|(刀)-------------
	 * -------------|(刀)--------
	 * 因此存在最小子问题。
	 * 
	 * 不妨设dp[i]表示长度为i的绳子的乘积最大值
	 * 刀在位置j把绳子切割成为了两段
	 * --------j-----------------i
	 *   left  |   right
	 * 显然有，在j这种情况下dp[i]的最大值应该是left的最大值*right的最大值
	 * 而，left和right每个又可以看做是一根绳子，这样就将问题进行了更小划分。
	 * 可以看出Max(left)=max(j,dp[j]),Max(right)=max(i-j,dp[i-j])
	 *   
	 */
	public int cutRope2(int n) {
		int []dp=new int[n+1];
		//初始化
		dp[1]=1;
		dp[2]=1;
		for(int i=3;i<=n;i++) {
			int max=0;
			for(int j=1;j<=i/2;j++) {
				int left=Math.max(dp[j], j);//切割后左边的最大值
				int right=Math.max(dp[i-j], i-j);//切割后右边的最大值
				if(left*right>max) max=left*right;
			}
			dp[i]=max;
		}
		return dp[n];
	}
	
	/*
	 * 三、万能搜索
	 * 把剪的过程看做是选择的过程，即第一步选择2，第二步选择3等等
	 * 如长度为8的绳子，第一步选择2，第二步选择3，第三步也选择3得到2*3*3=18
	 * 可以看出这个过程是一个dfs搜索的过程，实际上，第一步的选择范围为[2,4]
	 * 更一般为[2,n/2]，为什么只到n/2呢？
	 * 因为第一步选择2第二步选择6	和	第一步选择6第二步选择2	的结果是一样的
	 * 
	 */
	public int cutRope3(int n) {
		//处理两个分割后变小的特殊情况
		if(n==2) return 1;
		if(n==3) return 2;
		return _help(n);
	}
	private int _help(int n) {
		int max=0;
		if(n==2) return 2;//搜索返回条件
		if(n==3) return 3;
		for(int i=2;i<=n/2;i++) {
			int t=i*_help(n-i);
			if(t>max) max=t;
		}
		return max;
	}

}
