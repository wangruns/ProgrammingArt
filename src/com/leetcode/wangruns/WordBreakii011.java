package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

//11��word-break-ii[��̬�滮]
/**
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given
s ="catsanddog",
dict =["cat", "cats", "and", "sand", "dog"].
A solution is["cats and dog", "cat sand dog"].

ֱ��ʹ��DFS���ܻ�ʱ�䳬ʱ,��������
��̬�滮˼�룬��map<s,list>���Ѿ���õĽ���������������ظ�����.
s��ʾ��ǰ�ַ�����list��ʾ���ַ����Ĳ�����
 */
public class WordBreakii011 {
	
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		ArrayList<String> res=new ArrayList<>();
		if(s.length()==0||dict.isEmpty()) return res;
		HashMap<String,ArrayList<String>>map=new HashMap<>();
		res=DFS(s,dict,map);
		return res;
	}
	
	//���ص�ǰ�ַ���s���������list
	private ArrayList<String> DFS(String s, Set<String> dict, HashMap<String, ArrayList<String>> map) {
		ArrayList<String> ans=new ArrayList<>();
		//���ַ���֮ǰ�й���¼
		if(map.containsKey(s)) return map.get(s);
		//�ַ����Ѿ���������
		if(s.length()==0){
			ans.add("");
			return ans;
		}
		//��ʼ����
		for(String word:dict){
			//����ֵ��е�ĳһ�����ʺ�s�Ŀ�ͷƥ��,��ô�ʹ��������������ȥ
			if(s.startsWith(word)){
				ArrayList<String> subList=DFS(s.substring(word.length()),dict,map);
				//����͵��ڵ�ǰword�����Ӵ����
				for(String sub:subList)
					ans.add(word+(sub.isEmpty()?"":" ")+sub);
			}
		}
		//��map����֮ǰ��״̬���
		map.put(s, ans);
		return ans;
	}
	
}
