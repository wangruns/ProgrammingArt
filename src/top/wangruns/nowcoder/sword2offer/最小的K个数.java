package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * 题目描述
输入n个整数，找出其中最小的K个数。
例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 *
 * 分析
寻找最小的前k个数，可以使用一个最大推来保存最小的k个数
如果当前元素比堆顶元素还小，则删除堆顶元素，将新元素入堆
(PriorityQueue优先队列实现了堆)
 */
public class 最小的K个数 {
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> res=new ArrayList<>();
		if(input==null || input.length<k || k<1)return res;
		PriorityQueue<Integer> heap=new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		for(int i=0;i<input.length;i++) {
			if(heap.size()<k) heap.add(input[i]);
			else {
				if(input[i]<heap.peek()) {
					heap.poll();
					heap.add(input[i]);
				}
			}
		}
		for(Integer i:heap) res.add(i);
		
		return res;
    }
	
}
