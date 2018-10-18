package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 分析
可以看做二分查找的一个变形。可以看出最小的元素就是一个分界线，将数组分为两部分。
左边子数组一部分非减排序，右边数组也非减排序。
(1)当中间元素大于最后一个元素时，中间元素落在左部分，则最小元素应该在中间值的右边。如{3,4,5,1,2}
(2)当中间元素小于最后一个元素时，中间元素落在右部分，则最小元素应该在包括中间值的左边。如{4,5,1,2,3}
(3)当中间元素等于最后一个元素时，如{1,1,1,0,1}或者{1,0,1,1,1}，无法判定中间元素落在哪个部分，只能逐个尝试
 */
public class 旋转数组的最小数字 {
	
	public int minNumberInRotateArray(int [] array) {
		if(array.length==0) return 0;
		int l=0,r=array.length-1;
		while(l<r) {
			int middle=(l+r)/2;
			if(array[middle]<array[r]) r=middle;
			else if(array[middle]>array[r]) l=middle+1;
			else r--;
		}
		
		return array[l];
    }
}
