package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
并返回它的位置, 如果没有则返回 -1（需要区分大小写）
 *
 */
public class P34_第一个只出现一次的字符 {
	
	public int FirstNotRepeatingChar(String str) {
		if(str==null || str.length()==0) return -1;
		int []a=new int['z'+1];
		for(int i=0;i<str.length();i++) a[str.charAt(i)]++;
		for(int i=0;i<str.length();i++) {
			if(a[str.charAt(i)]==1) return i;
		}
        return -1;
    }
	
}
