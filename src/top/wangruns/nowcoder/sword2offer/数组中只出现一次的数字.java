package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 *
 * 分析
两个相同的数的异或为0
 */
public class 数组中只出现一次的数字 {
	
	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int oxr=array[0];
        for(int i=1;i<array.length;i++) oxr^=array[i];
        //按照oxr第N位是否为1将数组分为两个子数组
        int N=0;
        while(oxr!=0) {
        	if((oxr&1)==1) break;
        	oxr>>=1;
        	N++;
        }
        N=1<<N;num1[0]=0;num2[0]=0;
        for(int i=0;i<array.length;i++) {
        	if((array[i]&N)==N) num1[0]^=array[i];
        	else num2[0]^=array[i];
        }
    }
	
}
