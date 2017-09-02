package com.leetcode.wangruns;

import java.util.ArrayList;

//19、palindrome-partitioning[字符串]
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s ="aab",
 * Return:
 * 
			[
			    ["aa","b"],
			    ["a","a","b"]
			 ]
 * DFS的思想回溯
 * a是回文，则检测ab
 * 							a是回文，则检测b
 * 													b是回文
 * aa是回文，则检测b
 * 							 b是回文
 * aab不是回文
 */
public class PalindromePartitioning019 {
	
	private ArrayList<ArrayList<String>> res;
	private ArrayList<String> curList;
	public ArrayList<ArrayList<String>> partition(String s) {
		res=new ArrayList<>();
		curList=new ArrayList<>();
		backTrack(s);
		return res;
	}
	
	private void backTrack(String s) {
		//终止条件为s被找完了
		if(curList.size()>0&&s.length()==0){
			ArrayList<String> t=(ArrayList<String>) curList.clone();
			res.add(t);
		}
		//从第一个长度为i的字符串subStr开始检测
		for(int i=0;i<s.length();i++){
			String subStr=s.substring(0, i+1);
			//当前字符串开头有回文串，保存该回文并继续找
			if(isPalindrome(subStr)){
				curList.add(subStr);
				backTrack(s.substring(i+1));
				//回退一步，好走其他路线
				curList.remove(curList.size()-1);
			}
		}
	}

	//判定字符串s是否为回文串
	private boolean isPalindrome(String s) {
		int l=0,r=s.length()-1;
		while(l<r){
			if(s.charAt(l)!=s.charAt(r)) return false;
			l++;r--;
		}
		return true;
	}
	
}
