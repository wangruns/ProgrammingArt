package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//24��	word-ladder-ii[����]
/**
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
 * Only one letter can be changed at a time.  Each intermediate word must exist in the dictionary.
 * For example,
 * Given:
 * start ="hit"
 * end ="cog"
 * dict =["hot","dot","dog","lot","log"]
 * Return
 * [  ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]  ]
 * 
 * Note:
 *All words have the same length.
 *All words contain only lowercase alphabetic characters.
 *
 *1��bfs�����ڽӾ���
  		[hit]->hot
  		[hot]->dot->lot
  		[dot]->....
  		һ��һ���
  				[hit]
  		        hot
        dot			lot
        dog			log
       [cog]		   [cog]
  *2��dfs�����ϲ㿪ʼ����·��
 */
public class WordLadderii024 {
	
	private HashMap<String,ArrayList<String>> adjacencyMatrix=new HashMap<>();
	private LinkedList<String> queue=new LinkedList<>();
	private HashMap<String,Integer> layorFromStart=new HashMap<>();
	private ArrayList<ArrayList<String>> res=new ArrayList<>();
	private LinkedList<String> pathList=new LinkedList<>();
	
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		/**
		 * bfs�������·�����ڽӾ���
		 */
		int minLayor=0x7fffffff;
		int curLayor=0;
		for(String n:dict) layorFromStart.put(n, 0x7fffffff);
		layorFromStart.put(start, 0);
		queue.add(start);
		dict.add(end);
		StringBuilder sb=null;
		while(!queue.isEmpty()){
			String upLayorWord=queue.poll();
			curLayor=layorFromStart.get(upLayorWord)+1;
			//�������߶��Ѿ�������С���ˣ���ô���������²�Ѱ���ˣ�ֱ���˳�
			if(curLayor>minLayor) break;
			//�����滻���ʵ�ÿ����ĸ
			for(int i=0;i<upLayorWord.length();i++){
				sb=new StringBuilder(upLayorWord);
				//ÿ�����滻����ĸ������'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curLayorWord=sb.toString();
					//�ֵ������еĲ�����
					if(dict.contains(curLayorWord)){
						//����layorFromStart������ǰ��ڵ������У����ڰ�����ʽ����ڽӾ���
						if(curLayor>layorFromStart.get(curLayorWord)){
							//����ýڵ��Ѿ��Ź��ˣ��򲻷š�(��Ϊ�Ǵ��ϲ㿪ʼ�ģ������ϲ�϶����²�֮ǰ����)
							continue;
						}else if(curLayor<layorFromStart.get(curLayorWord)){
							layorFromStart.put(curLayorWord, curLayor);
							queue.add(curLayorWord);
						}else
							//curLayor==layorFromStart.get(curLayorWord)������������ٴ���ӵ�������
							//"It is a KEY line. If one word already appeared in one ladder."
							//"Do not insert the same word inside the queue twice. Otherwise it gets TLE."
							;
						//�������·���ڽӾ���
						if(adjacencyMatrix.containsKey(upLayorWord)){
							adjacencyMatrix.get(upLayorWord).add(curLayorWord);
						}else{
							ArrayList<String> list=new ArrayList<>();
							list.add(curLayorWord);
							adjacencyMatrix.put(upLayorWord, list);
						}
						//������̲���
						if(curLayorWord.equals(end)){
							minLayor=curLayor;
						}
					}
				}
			}
		}//End while
		
		/**
		 * dfs�����ϲ㿪ʼ���½������·��
		 */
		backTrace(start,end,dict);
		return res;
	}

	private void backTrace(String start, String end, HashSet<String> dict) {
		pathList.add(start);
		if(start.equals(end)){
			ArrayList<String> t=new ArrayList<>(pathList);
			res.add(t);
			return;
		}
		ArrayList<String> layorList=adjacencyMatrix.get(start);
		if(layorList!=null){
			for(String s:layorList){
				backTrace(s,end,dict);
				//����
				pathList.removeLast();
			}
		}
	}

}
