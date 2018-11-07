package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *
 */
public class 左旋转字符串 {
	
	//借助一个O(n)的空间
	public String LeftRotateString1(String str,int n) {
		if(str==null||str.length()==0)return "";
		int len=str.length();
		n%=len;
		StringBuilder sb=new StringBuilder(str+str);
		return sb.substring(n, n+len);
	}
	
	//翻转字符串：假设字符串abcdef，n=3，设X=abc，Y=def，所以字符串可以表示成XY
	//假设X的翻转为XT，XT=cba;同理YT=fed;那么YX=(XTYT)T，三次翻转后可得结果。
	public String LeftRotateString2(String str,int n) {
		if(str==null||str.length()==0)return "";
		char[] c=str.toCharArray();
		reverse(c,0,n-1);
		reverse(c,n,c.length-1);
		reverse(c,0,c.length-1);
		return new String(c);
	}

	private void reverse(char[] c, int i, int j) {
		while(i<j) {
			char t=c[i];c[i]=c[j];c[j]=t;
			i++;j--;
		}
	}
	

}
