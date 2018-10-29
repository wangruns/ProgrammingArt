package top.wangruns.educoder.gcc2018;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * 挑战任务
“绿盟杯”比赛过后，赛事承办方的各位工作人员们就开始分头统计各个参赛队伍和同学的成绩了。
赛事规模很大，有10000个队伍参加。经过工作人员认真负责的统计，本来已经统计好了这一万个队伍的分数和排名，
并按照排名从高到低依次进行了编号（从1到10000）但是由于一个非常偶然的因素，导致其中三个编号的数据丢失，
而且剩余编号的顺序也全被打乱了。你需要编写一个程序，根据还保留的统计数据，来判断哪些编号的数据丢失了，
并将这些编号按照从小到大的顺序重新拼接为一个新数字,然后计算这个新数字除以11的余数。
如丢失了编号为41、17、25的数据，则最后你需要返回的结果是172541除以11的余数。
编程要求
补全右侧代码区中的getLostScores(int[] nums)函数，找出丢失的三个编号并按指定格式返回一个新数字除以11的余数
输入：除15、48、56外的其余9997个数组成的乱序数组
输出：9
 *
 * 分析
Java中一个int最大(0x7fffffff)可以表示10位数字，而这里如果3个4的数字拼接为12位了，可用BigInteger
 */
public class 预二阶一关统计分数的麻烦 {
	
	public int getLostScores(int[] nums){
		if(nums==null) return 0;
		int[]a=new int[10001];
		int[]lostNums=new int[3];
		for(int e:nums) a[e]=1;
		int index=0;
		for(int i=1;i<a.length;i++) {
			if(a[i]==0) lostNums[index++]=i;
			if(index==lostNums.length) break;
		}
		Arrays.sort(lostNums);
		StringBuilder sb=new StringBuilder();
		for(int e:lostNums) sb.append(e);
		BigInteger bigInt=new BigInteger(sb.toString());
		return bigInt.mod(BigInteger.valueOf(11)).intValue();
	}

}
