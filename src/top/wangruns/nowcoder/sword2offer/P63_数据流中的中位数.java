package top.wangruns.nowcoder.sword2offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目描述
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 */
public class P63_数据流中的中位数 {
	int count=0;
	PriorityQueue<Integer> minHeap=new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap=new PriorityQueue<Integer>(11,new Comparator<Integer>(){
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	});
    public void Insert(Integer num) {
    	count++;
	    //判定奇偶的高效写法，如果当前个数为偶数，插入最小堆（需要保证，最大推堆顶小于，小于最小堆堆顶）
	    if((count&1)==0){
	    	if(!maxHeap.isEmpty()&&num<maxHeap.peek()){
	    		maxHeap.offer(num);
	    		num=maxHeap.poll();
	    	}
	    	minHeap.offer(num);//插入最小堆
	    }
	    //如果当前个数为奇数，插入最大堆（需要保证，最大推堆顶小于，小于最小堆堆顶）
	    else{
	    	if(!minHeap.isEmpty()&&num>minHeap.peek()){
	    		minHeap.offer(num);
	    		num=minHeap.poll();
	    	}
	    	maxHeap.offer(num);//插入最大堆
	    }
    }

    public Double GetMedian() {
        double res=0;
    	//没有任何数
        if(count==0) return res;
        //总数为奇数，那么最大堆的堆顶就是中位数
        else if((count&1)==1) res= maxHeap.peek();
        //如果为偶数，那么最小堆的堆顶 和 最大堆的堆顶之和/2就是中位数
        else res= (minHeap.peek()+maxHeap.peek())/2.0;
        return res;
    }

}
