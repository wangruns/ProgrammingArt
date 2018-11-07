package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 
现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。
为了方便起见,你可以认为大小王是0。
 *
 * 分析
可以看出如果想要连续，则需要满足两个条件：a.除0(王)外不能有重复的元素；b.除0(王)外max-min<5;
 */
public class 扑克牌顺子 {
	
	//开辟一个map计数数组
	public boolean isContinuous1(int [] numbers) {
		if(numbers==null||numbers.length==0) return false;
		int a[]=new int[14];//0-13
		int max=0x80000000,min=0x7fffffff;
		for(int e:numbers) {
			if(e==0) continue;
			if(a[e]==1) return false;//重复出现
			if(e<min) min=e;
			if(e>max) max=e;
			if(max-min>=5) return false;//范围超出5
			a[e]=1;
		}
		return true;
    }
	
	//由于计数元素比较小，也可以直接用bitmap来计数，如将8bit来做一个下标为0-7的数组
	public boolean isContinuous2(int [] numbers) {
		if(numbers==null||numbers.length==0) return false;
		int max=0x80000000,min=0x7fffffff,bitmap=0;
		for(int e:numbers) {
			if(e==0) continue;
			if(((bitmap>>e)&1)==1) return false;//重复出现
			if(e<min) min=e;
			if(e>max) max=e;
			if(max-min>=5) return false;//范围超出5
			bitmap|=1<<e;
		}
		return true;
    }

}
