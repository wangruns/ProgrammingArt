package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;

/**
 * 
 * 题目描述
输入一个字符串,按字典序打印出该字符串中字符的所有排列。
例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
输入描述:
输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 *
 */
public class P27_字符串的排列 {
	
	ArrayList<String> res=new ArrayList<>();
	StringBuilder sb=new StringBuilder();
	public ArrayList<String> Permutation(String str) {
		//对于一个当前长度为n的字符串，当前位置的选择有n种
		for(int i=0;i<str.length();i++) {
			//考虑去掉重复的情况，当前位置的字符在i位置的前面已经出现过了，则跳过当前位置的这种情况
			//如aab第一个位置原本有3种选择，但是将第一a作为第一个位置和将第二个a作为第一个位置的情况是一样的
			if(str.substring(0, i).contains(str.charAt(i)+"")) continue;
			sb.append(str.charAt(i));
			if(str.length()>1) Permutation(str.substring(0, i)+str.substring(i+1));
			else res.add(sb.toString());
			sb.deleteCharAt(sb.length()-1);
		}
		//对于字典排序，将最后得到的结果结果字典排序，或先将原始的字符串字典排序
		return res;
    }
	
}
