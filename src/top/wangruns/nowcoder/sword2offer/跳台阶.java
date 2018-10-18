package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级。
求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * 分析
 其实就是斐波那契的应用，第n个台阶，肯定只能由n-2，或者n-1跳上来
 */
public class 跳台阶 {
	
	public int JumpFloor(int target) {
		if(target<=2) return target;
		return JumpFloor(target-1) + JumpFloor(target-2);
    }
}
