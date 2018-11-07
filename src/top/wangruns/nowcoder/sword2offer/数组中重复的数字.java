package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 
例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 */
public class 数组中重复的数字 {

	/**
	 * 这里要特别注意~返回任意重复的一个，赋值duplication[0]
	 * @param numbers
	 * @param length
	 * @param duplication
	 * @return
	 */
	//借助一个辅助空间
	public boolean duplicate1(int numbers[],int length,int [] duplication) {
		if(numbers==null||numbers.length==1) return false;
    	int[]a=new int[length];
    	for(int e:numbers) {
    		if(a[e]==1) {
    			duplication[0]=e;
    			return true;
    		}
    		a[e]=1;
    	}
    	return false;
    }
	
	//不借助辅助空间，直接在原数组里面map映射
	public boolean duplicate2(int numbers[],int length,int [] duplication) {
		if(numbers==null||numbers.length==1) return false;
    	for(int e:numbers) {
    		if(e<0) e+=length;
    		if(numbers[e]<0) {
    			//当前元素e对应的映射已经变成负数表示该元素之前已经被访问过了
    			duplication[0]=e;
    			return true;
    		}else {
    			//第一次访问，直接在原数组将对应的映射修减去最大值变成负数
    			numbers[e]-=length;
    		}
    	}
		return false;
    }

}
