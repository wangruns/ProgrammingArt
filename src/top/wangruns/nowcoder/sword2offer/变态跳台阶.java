package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 分析方式一
每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳，所以共有2^(n-1)种情况。
 * 
 * 分析方式二
f(n)=f(n-1) + f(n-2) + ... + f(1)
f(n-1)= f(n-2) + ... + f(1)
所以 f(n)= 2f(n-1)
显然为等比数列，而且f(1)=1，所以f(n)=2^(n-1)
 */
public class 变态跳台阶 {
	
	public int JumpFloorII(int target) {
        return 1<<--target;
    }
}
