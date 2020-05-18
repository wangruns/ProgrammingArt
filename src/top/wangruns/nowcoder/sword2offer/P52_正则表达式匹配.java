package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 */
public class P52_正则表达式匹配 {
	
	 public boolean match(char[] str, char[] pattern){
		//return new String(str).matches(new String(pattern));
		if(str==null||pattern==null) return false;
		return _match(str,0,pattern,0);
	 }
	 
	 private boolean _match(char[] str, int sIndex, char[] pattern, int pIndex) {
			if(str.length==sIndex&&pattern.length==pIndex) return true;//str和pattern都到尾
			if(sIndex>str.length||pIndex>pattern.length) return false;//某一方先到尾
			//pattern后面一个不是*
			if(pIndex+1<pattern.length&&pattern[pIndex+1]!='*'){
				//匹配
				if(pattern[pIndex]==str[sIndex]||pattern[pIndex]=='.') return _match(str,sIndex+1,pattern,pIndex+1);
				else return false;
			}
			//pattern后面一个是*
			if(pIndex+1<pattern.length&&pattern[pIndex+1]=='*'){
				//匹配
				if(pattern[pIndex]=='.'||(str.length!=0&&str.length>sIndex&&pattern[pIndex]==str[sIndex])){
					return _match(str,sIndex,pattern,pIndex+2)//模式后移2，视为x*匹配0个字符
							//|| _match(str,sIndex+1,pattern,pIndex+2)//视为模式匹配1个字符
							|| _match(str,sIndex+1,pattern,pIndex);//视为模式匹配多个字符
				}else{
					return _match(str,sIndex,pattern,pIndex+2);
				}
			}
			//处理pattern没有下一个的情况如:"a","."
			if(pIndex==pattern.length-1&& sIndex==str.length-1&& (pattern[pIndex]==str[sIndex]||pattern[pIndex]=='.')) return true;
			return false;
		}

}
