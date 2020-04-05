package top.wangruns.nowcoder.sword2offer;

import java.util.LinkedList;

/**
 * 
 * 题目描述
每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,
继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,
并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 分析
可以使用链表模拟游戏
 */
public class P46_孩子们的游戏圆圈中最后剩下的数约瑟夫问题 {
	
	//使用链表模拟
	public int LastRemaining_Solution1(int n, int m) {
		if(n<=0||m<=0) return -1;
		LinkedList<Integer> aList=new LinkedList<>();
        for(int i=0;i<n;i++) aList.add(i);//编号
        int start=0;
        while(aList.size()!=1) {
        	int selectedIndex=(start+m-1)%aList.size();
        	aList.remove(selectedIndex);//移除满足的编号
        	start=selectedIndex%aList.size();
        }
        return aList.getFirst();
    }
	
	//约瑟夫问题寻找递推关系
	/**
	 * 初始编号    0 1 2 3 ... n-1
	 * 选择编号k后，重新从k+1开始从0开始编号有
	 * k+1 k+2 k+3 ... k-1
	 * 0   1   2   ... n-2
	 * 这样相当于原本长度为n的问题P(n)，转变为了新的长度为n-1的问题P(n-1)，则自然的想到用递归解决
	 * 不过现在需要解决的是P(n)和P(n-1)它们的下标对应有什么联系。
	 * 初始编号    0 1 2 3 4 5 6 7 8 9
	 * 假设m=4,则需去掉3，然后重新编号，将P(10)变成P(9)
	 * 初始编号  0 1 2 4 5 6 7 8 9
	 * 新的编号  6 7 8 0 1 2 3 4 5
	 * 不难发现初始编号O和新的编号N具有如下关系：O=(N+m)%length(P(O)
	 */
	public int LastRemaining_Solution2(int n, int m) {
		if(n<=0||m<=0) return -1;
		if(n==1) return 0;
		else return (LastRemaining_Solution2(n-1, m)+m)%n;
    }

}
