package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//11、word-break-ii[动态规划]
/**
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given
s ="catsanddog",
dict =["cat", "cats", "and", "sand", "dog"].
A solution is["cats and dog", "cat sand dog"].

直接使用DFS可能会时间超时,可以利用
动态规划思想，用map<s,list>把已经求得的结果存起来，避免重复计算.
s表示当前字符串，list表示该字符串的拆分情况
 */
public class WordBreakii011 {
	
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		ArrayList<String> res=new ArrayList<>();
		if(s.length()==0||dict.isEmpty()) return res;
		HashMap<String,ArrayList<String>>map=new HashMap<>();
		res=DFS(s,dict,map);
		return res;
	}
	
	//返回当前字符串s的组合序列list
	private ArrayList<String> DFS(String s, Set<String> dict, HashMap<String, ArrayList<String>> map) {
		ArrayList<String> ans=new ArrayList<>();
		//该字符串之前有过记录
		if(map.containsKey(s)) return map.get(s);
		//字符串已经被找完了
		if(s.length()==0){
			ans.add("");
			return ans;
		}
		//开始查找
		for(String word:dict){
			//如果字典中的某一个单词和s的开头匹配,那么就从这里深入的找下去
			if(s.startsWith(word)){
				ArrayList<String> subList=DFS(s.substring(word.length()),dict,map);
				//结果就等于当前word和其子串想加
				for(String sub:subList)
					ans.add(word+(sub.isEmpty()?"":" ")+sub);
			}
		}
		//用map保存之前的状态结果
		map.put(s, ans);
		return ans;
	}
	
}
