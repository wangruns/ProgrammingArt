package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 */
public class P13_调整数组顺序使奇数位于偶数前面 {
	
	//最容易想到的一种方法，用空间换时间
	public void reOrderArray1(int [] array) {
		int len=array.length;
		int[] temp=new int[len];
		int oddIndex=0,evenIndex=len-1;
		//用一个临时数组的前部保存计数，后部分保存偶数
		for(int i=0;i<len;i++) {
			if(array[i]%2!=0) temp[oddIndex++]=array[i];
			else temp[evenIndex--]=array[i];
		}
		evenIndex=len-1;
		//覆盖掉原来的数组
		for(int i=0;i<len;i++) {
			if(i<oddIndex) array[i]=temp[i];
			else array[i]=temp[evenIndex--];
		}
    }
	
	//插入排序思想，不另开空间，从最后开始查找 如果是偶数就向后移动， 终止条件为遇到一个偶数或者到达数组尾
	public void reOrderArray2(int [] array) {
		int len=array.length;
		for(int i=len-1;i>=0;i--) {
			if(array[i]%2==0) {
				for(int j=i;j<len;j++) {
					if((j+1)==len || array[j+1]%2==0) break;
					else {
						int temp=array[j];
						array[j]=array[j+1];
						array[j+1]=temp;
					}
				}
			}
		}
	}
}
