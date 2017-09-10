package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//24、	word-ladder-ii[查找]
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
 *1、bfs建立邻接矩阵
  		[hit]->hot
  		[hot]->dot->lot
  		[dot]->....
  		一层一层的
  				[hit]
  		        hot
        dot			lot
        dog			log
       [cog]		   [cog]
  *2、dfs从最上层开始回溯路径
 */
public class WordLadderii024 {
	
	private HashMap<String,ArrayList<String>> adjacencyMatrix=new HashMap<>();
	private LinkedList<String> queue=new LinkedList<>();
	private HashMap<String,Integer> layorFromStart=new HashMap<>();
	private ArrayList<ArrayList<String>> res=new ArrayList<>();
	private LinkedList<String> pathList=new LinkedList<>();
	
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		/**
		 * bfs建立最短路径的邻接矩阵
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
			//如果当层高度已经大于最小的了，那么不必在向下层寻找了，直接退出
			if(curLayor>minLayor) break;
			//依次替换单词的每个字母
			for(int i=0;i<upLayorWord.length();i++){
				sb=new StringBuilder(upLayorWord);
				//每个被替换的字母可以是'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curLayorWord=sb.toString();
					//字典里面有的才能用
					if(dict.contains(curLayorWord)){
						//更新layorFromStart并将当前层节点放入队列，便于按层访问建立邻接矩阵
						if(curLayor>layorFromStart.get(curLayorWord)){
							//如果该节点已经放过了，则不放。(因为是从上层开始的，所以上层肯定在下层之前被放)
							continue;
						}else if(curLayor<layorFromStart.get(curLayorWord)){
							layorFromStart.put(curLayorWord, curLayor);
							queue.add(curLayorWord);
						}else
							//curLayor==layorFromStart.get(curLayorWord)这种情况不必再次添加到队列了
							//"It is a KEY line. If one word already appeared in one ladder."
							//"Do not insert the same word inside the queue twice. Otherwise it gets TLE."
							;
						//建立最短路径邻接矩阵
						if(adjacencyMatrix.containsKey(upLayorWord)){
							adjacencyMatrix.get(upLayorWord).add(curLayorWord);
						}else{
							ArrayList<String> list=new ArrayList<>();
							list.add(curLayorWord);
							adjacencyMatrix.put(upLayorWord, list);
						}
						//更新最短层数
						if(curLayorWord.equals(end)){
							minLayor=curLayor;
						}
					}
				}
			}
		}//End while
		
		/**
		 * dfs从最上层开始向下建立最短路径
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
				//回溯
				pathList.removeLast();
			}
		}
	}

}
