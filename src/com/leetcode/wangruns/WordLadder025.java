package com.leetcode.wangruns;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//25、	word-ladder[查找]
/**
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
 * Only one letter can be changed at a time Each intermediate word must exist in the dictionary
 * For example,
 * Given:
 * start ="hit"
 * end ="cog"
 * dict =["hot","dot","dog","lot","log"]
 * As one shortest transformation is"hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * 思路：最短路径，找到一条最短的路径即可，联想到bfs，可以从起点(根节点一层一层的向下搜索知道遇到目标end)
					hit
					hot
			dot				lot
			dog				log
			cog
 * 从图可以看到从起点hit就这样一层一层的向下搜索(bfs)，直到左边的路径遇到了end，那么搜索就结束了
 * 所以我们可以用curPath=0来表示起点hit时候的层数，然后没向下搜索一层，那么curPath=其对应的上一层的curPath+1
 * 所以这里我们需要记录一下上一层的层数状态(distanceFromStart).				
 */
public class WordLadder025 {

	public int ladderLength(String start, String end, HashSet<String> dict) {
		int curPath=0;
		LinkedList<String> queue=new LinkedList<>();
		HashMap<String,Integer> distanceFromStart=new HashMap<>();
		//初始化起点到其他店的距离为无穷远，到自己的距离为0
		for(String s:dict) distanceFromStart.put(s, 0x7fffffff);
		distanceFromStart.put(start, 0);
		dict.add(end);
		queue.add(start);
		while(!queue.isEmpty()){
			String upLayWord=queue.poll();
			//当前的层数=上层的层数+1
			curPath=distanceFromStart.get(upLayWord)+1;
			//逐次替换单词的每个字母
			for(int i=0;i<upLayWord.length();i++){
				StringBuilder sb=new StringBuilder(upLayWord);
				//替换的位置可以是'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curWord=sb.toString();
					//在字典中的curWord才是可以用的
					if(dict.contains(curWord)){
						//找到end，返回
						if(curWord.equals(end)){
							return curPath+1;
						}
						//更从新起点到当前点的层数，并将当前节点如队列，便于继续向下搜索
						if(curPath<distanceFromStart.get(curWord)){
							/**
							 * 不要再将上面层已经出现过的节点重复的添加到队列中
							 * 因为如果curWord是新的还没有处理过的节点，其距离是无限远的
							 * 所以curPath<distanceFromStart.get(curWord)保证curWord之前没有被添加到队列过
							 */
							distanceFromStart.put(curWord, curPath);
							queue.add(curWord);
						}
					}
				}//End 替换的位置可以是'a'-'z'
			}//End 逐次替换单词的每个字母
		}//End while
		//没有这样的路径存在，返回0
		return 0;
	}
	
}
