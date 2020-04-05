package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 分析
题目说了不许用循环，那就摆明了是要递归了
 */
public class P47_求1加到n求和 {
	
	public int Sum_Solution(int n) {
		int res=n;
		boolean isEnd=n>0 && (res+=Sum_Solution(n-1))>0;
		return res;
    }

}
