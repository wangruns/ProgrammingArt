package com.leetcode.wangruns;

import java.util.Set;

//11、word-break[动态规划]
/**
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given s ="leetcode", dict =["leet", "code"].
 * Return true because"leetcode"can be segmented as"leet code".
 * 
 * 当前字符串可以被分割的前提是，该字符串之前的字符串可以被分割，他们相差的字符串在字典中.
 * 
 * 如leetcode之前的字符串leet可以被分割，而且他们相差的字符串code在字典中.
 * dp[i]表示s[0-i]是否可以被切割
 * dp[i]为true 当且仅当 存在0<=K<=i, 使得dp[k]==true且子串s[k,i]在字典中
 * dp[0]控制位默认等于true
 * 如s="leet"的时候 
 *							 |leet
 * 当i=4, 有dp[0]==true,而且s[0,4)即"leet"在dict字典中，所以dp[4]=true;
 * s="leetcode"的时候
 * 							leet|code
 * 当i=8, 有dp[4]==true, 而且s[4,8)即"code"在字典中，所以dp[8]=true;
 */
public class WordBreak012 {
	
	public boolean wordBreak(String s, Set<String> dict) {
		int len=s.length()+1;
		boolean dp[]=new boolean[len];
		dp[0]=true;//初始控制位
		//长度从1逐渐增加的字符串s
		for(int i=1;i<len;i++){
			//对于每个长度的字符s判定其是否可以分割
			for(int j=0;j<i;j++){
				if(dp[j]&&dict.contains(s.substring(j, i))){
					dp[i]=true;//s[0,i]是可以分割的
					break;
				}
			}
		}
		return dp[s.length()];
	}

}
