package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
输入描述:输入一个字符串,包括数字字母符号,可以为空
输出描述:如果是合法的数值表达则返回该数字，否则返回0
输入+2147483647
输出2147483647
输入1a33
输出0
 *
 */
public class P49_把字符串转换成整数 {
	
	public int StrToInt(String str) {
		if(str==null||str.isEmpty()) return 0;
		int base=1,res=0;
		for(int i=str.length()-1;i>=0;i--) {
			char c=str.charAt(i);
			if(i==0&&c=='+') return res;
			if(i==0&&c=='-') return -res;
			if(i==0&&c=='0') return 0;//首位不能为0
			if(c<='9'&&c>='0') {
				res+=(c-'0')*base;
				base*=10;
			}else return 0;
		}
		return res;
    }

}
