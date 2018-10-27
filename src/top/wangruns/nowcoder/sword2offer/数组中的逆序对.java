package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。即输出P%1000000007
输入描述:
题目保证输入的数组中没有的相同的数字
数据范围：
	对于%50的数据,size<=10^4
	对于%75的数据,size<=10^5
	对于%100的数据,size<=2*10^5
示例1
输入1,2,3,4,5,6,7,0
输出7
 *
 */
public class 数组中的逆序对 {
	
	int cnt=0,big=1000000007;
	public int InversePairs(int [] array) {
        if(array==null || array.length<2) return 0;
        int[] temp=new int[array.length];
        mergeSortVariant(array,0,array.length-1,temp);
        return cnt;
    }
	
	private void mergeSortVariant(int[] a, int l, int r, int[] t) {
		if(l==r) return;
		int middle=(l+r)/2;
		mergeSortVariant(a,0,middle,t);
		mergeSortVariant(a,middle+1,r,t);
		for(int i=l;i<=r;i++) t[i]=a[i];
		int i1=l,i2=middle+1;
		for(int i=l;i<=r;i++) {
			if(i1>middle) a[i]=t[i2++];
			else if(i2>r) a[i]=t[i1++];
			else if(t[i1]<t[i2]) a[i]=t[i1++];
			else {
				a[i]=t[i2++];
				cnt+=middle-i1+1;
				cnt%=big;
			}
		}
	}
	
}
