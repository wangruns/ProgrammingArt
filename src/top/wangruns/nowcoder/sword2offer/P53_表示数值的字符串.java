package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 
但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 *
 */
public class P53_表示数值的字符串 {
	
	public boolean isNumeric(char[] str) {
		boolean isFirstPoint=true,isFirstE=true;;
		for(int i=0;i<str.length;i++) {
			//处理首位符号
			if(str[i]=='+'||str[i]=='-'){
				if(i==0) continue;
				else if(str[i-1]=='e'||str[i-1]=='E') continue;
				else return false;
			}
			//处理小数点
			if(str[i]=='.') {
				if(isFirstPoint) {
					if(!isFirstE) return false;//小数点不能在e后面
					isFirstPoint=false;
					continue;
				}else return false;
			}
			//处理科学计数10
			if(str[i]=='e'||str[i]=='E') {
				if(i==str.length-1) return false;//e不能在最后
				else if(isFirstE) {
					isFirstE=false;
					continue;
				}else return false;
			}
			//处理字符只能为数字字符
			if(str[i]>'9'||str[i]<'0') return false;
		}
		return true;
    }

}
