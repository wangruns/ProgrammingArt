package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
统计一个数字在排序数组中出现的次数
 *
 * 分析
看看到了排序的数组中查找某一个值很自然的就想到了二分查找，这里可以先查找第一次和最后一次出现的位置
 */
public class P37_数字在排序数组中出现的次数 {
	
	public int GetNumberOfK(int [] array , int k) {
		int l=0,r=array.length-1,firstIndex=1,lastIndex=0;
		//获取第一次出现的下标
		while(l<=r) {
			int mid=(l+r)/2;
			if(array[mid]<k) l=mid+1;
			else if(array[mid]==k) {
				r=mid-1;
				firstIndex=mid;
			}
			else r=mid-1;
		}
		l=0;r=array.length-1;
		//获取第最后一次出现的下标
		while(l<=r) {
			int mid=(l+r)/2;
			if(array[mid]<k) l=mid+1;
			else if(array[mid]==k) {
				l=mid+1;
				lastIndex=mid;
			}
			else r=mid-1;
		}
		return lastIndex-firstIndex+1;
    }

}
