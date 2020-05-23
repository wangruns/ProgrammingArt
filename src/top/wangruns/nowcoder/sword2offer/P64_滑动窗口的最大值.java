package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 题目描述
给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： 
{[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， 
{2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}

分析
可以使用双向队列，首端保存当前窗口的最大值。对于新进入的一个值，从尾端开始比较，将队列里面比它小的删除，然后在插入。
这样就可以保证，队列里面始终保存当前窗口的最大值。
但是还有一个问题就是，首端值过期的问题，即需要删除首端过期的最大值。解决这个问题可以在队列里面保存原始数组的下标索引
而不是具体的值，这样就可以利用当前新加入的值的索引和首端索引比较，如果差值大于了滑动窗口的大小，即过期了，需要删除。
 *
 */
public class P64_滑动窗口的最大值 {
	
	public ArrayList<Integer> maxInWindows(int [] num, int size){
		ArrayList<Integer> res=new ArrayList<>();
		if(size==0) return res;
		LinkedList<Integer> queue=new LinkedList<>();
		for(int i=0;i<num.length;i++) {
			//处理尾端
			while(!queue.isEmpty()&&num[queue.getLast()]<num[i]) queue.removeLast();
			//处理首端
			while(!queue.isEmpty()&&i-queue.getFirst()>=size) queue.removeFirst();
			//插入新值的索引
			queue.add(i);
			//保存每个滑动窗口的最大值
			if(i+1>=size) res.add(num[queue.peek()]);
		}
		return res;
		
	}

}
