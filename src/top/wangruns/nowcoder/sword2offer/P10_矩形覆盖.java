package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 分析
f(1)=1
f(2)=2
f(3)=f(2)+f(1)
不难发现这个问题抽象出来其实还是斐波那契数列，所以勇敢地分析问题就显得比较重要了
 */
public class P10_矩形覆盖 {

	public int RectCover(int target) {
		if(target<=2) return target;
		else return RectCover(target-1)+RectCover(target-2);
	}
}
