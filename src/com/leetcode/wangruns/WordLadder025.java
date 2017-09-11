package com.leetcode.wangruns;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//25��	word-ladder[����]
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
 * ˼·�����·�����ҵ�һ����̵�·�����ɣ����뵽bfs�����Դ����(���ڵ�һ��һ�����������֪������Ŀ��end)
					hit
					hot
			dot				lot
			dog				log
			cog
 * ��ͼ���Կ��������hit������һ��һ�����������(bfs)��ֱ����ߵ�·��������end����ô�����ͽ�����
 * �������ǿ�����curPath=0����ʾ���hitʱ��Ĳ�����Ȼ��û��������һ�㣬��ôcurPath=���Ӧ����һ���curPath+1
 * ��������������Ҫ��¼һ����һ��Ĳ���״̬(distanceFromStart).				
 */
public class WordLadder025 {

	public int ladderLength(String start, String end, HashSet<String> dict) {
		int curPath=0;
		LinkedList<String> queue=new LinkedList<>();
		HashMap<String,Integer> distanceFromStart=new HashMap<>();
		//��ʼ����㵽������ľ���Ϊ����Զ�����Լ��ľ���Ϊ0
		for(String s:dict) distanceFromStart.put(s, 0x7fffffff);
		distanceFromStart.put(start, 0);
		dict.add(end);
		queue.add(start);
		while(!queue.isEmpty()){
			String upLayWord=queue.poll();
			//��ǰ�Ĳ���=�ϲ�Ĳ���+1
			curPath=distanceFromStart.get(upLayWord)+1;
			//����滻���ʵ�ÿ����ĸ
			for(int i=0;i<upLayWord.length();i++){
				StringBuilder sb=new StringBuilder(upLayWord);
				//�滻��λ�ÿ�����'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curWord=sb.toString();
					//���ֵ��е�curWord���ǿ����õ�
					if(dict.contains(curWord)){
						//�ҵ�end������
						if(curWord.equals(end)){
							return curPath+1;
						}
						//��������㵽��ǰ��Ĳ�����������ǰ�ڵ�����У����ڼ�����������
						if(curPath<distanceFromStart.get(curWord)){
							/**
							 * ��Ҫ�ٽ�������Ѿ����ֹ��Ľڵ��ظ�����ӵ�������
							 * ��Ϊ���curWord���µĻ�û�д�����Ľڵ㣬�����������Զ��
							 * ����curPath<distanceFromStart.get(curWord)��֤curWord֮ǰû�б���ӵ����й�
							 */
							distanceFromStart.put(curWord, curPath);
							queue.add(curWord);
						}
					}
				}//End �滻��λ�ÿ�����'a'-'z'
			}//End ����滻���ʵ�ÿ����ĸ
		}//End while
		//û��������·�����ڣ�����0
		return 0;
	}
	
}
