package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 *
 * 分析
两个相同的数的异或为0
 */
public class P40_数组中只出现一次的数字 {
	
	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int xor=0;
        //最终的异或结果oxr其实就是两个只出现一次的数字的异或结果
        for(int i:array) xor^=i;
        //在异或结果中找到一个为1的位置
        int n=1;
        while((n&xor)==0) n<<=1;
        for(int i:array){
            if((i&n)==0) num1[0]^=i;
            else num2[0]^=i;
        }
    }
	
}
