package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 *
 */
public class P1_二维数组中的查找 {
	
	//利用二维数组(m行n列)从左到右，从上到下都递增的规律O(n+m)=O(n)，从左下角的位置开始，如果向右走则越来越大，如果向上走则越来越小，
	//利用这个性质，就可以对目标元素进行很好的查找了，即如果a[row][col]<target，col++;如果a[row][col]>target,row--
	public boolean Find3(int target, int [][] array) {
		int row=array.length-1;
		int col=0;
		while(row>=0&&col<array[0].length) {
			if(array[row][col]==target) return true;
			else if(array[row][col]>target) row--;
			else col++;
		}
		return false;
	}
}
