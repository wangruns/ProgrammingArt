package com.leetcode.wangruns;

import java.util.ArrayList;

//19��palindrome-partitioning[�ַ���]
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
 * DFS��˼�����
 * a�ǻ��ģ�����ab
 * 							a�ǻ��ģ�����b
 * 													b�ǻ���
 * aa�ǻ��ģ�����b
 * 							 b�ǻ���
 * aab���ǻ���
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
		//��ֹ����Ϊs��������
		if(curList.size()>0&&s.length()==0){
			ArrayList<String> t=(ArrayList<String>) curList.clone();
			res.add(t);
		}
		//�ӵ�һ������Ϊi���ַ���subStr��ʼ���
		for(int i=0;i<s.length();i++){
			String subStr=s.substring(0, i+1);
			//��ǰ�ַ�����ͷ�л��Ĵ�������û��Ĳ�������
			if(isPalindrome(subStr)){
				curList.add(subStr);
				backTrack(s.substring(i+1));
				//����һ������������·��
				curList.remove(curList.size()-1);
			}
		}
	}

	//�ж��ַ���s�Ƿ�Ϊ���Ĵ�
	private boolean isPalindrome(String s) {
		int l=0,r=s.length()-1;
		while(l<r){
			if(s.charAt(l)!=s.charAt(r)) return false;
			l++;r--;
		}
		return true;
	}
	
}
