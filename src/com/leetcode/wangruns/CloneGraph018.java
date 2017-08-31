package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//18��clone-graph[ͼ]
/**
 * Clone an undirected graph. Each node in the graph contains alabeland a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use#as a separator for each node, and,as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph{0,1,2# 1,2# 2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by#.
 * 
 * First node is labeled as0. Connect node0to both nodes1and2.
 * Second node is labeled as1. Connect node1to node2.
 * Third node is labeled as2. Connect node2to node2(itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
						   1
						  / \
						 /   \
						0 --- 2
						     / \
						     \_/
 *�ǵݹ�汾��
 *����ͼ��֮ǰ�ĸ��������������Ƶĵط���
 *�Ƚ����½ڵ���Ͻڵ�֮��Ĺ�ϵ��
 *�����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ.
 *
 *�ݹ�汾��
 *�������ص�ǰ�ڵ�ĸ��ƺ��һ���½ڵ㣬ͼ��DFS
 */

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

public class CloneGraph018 {

	//�ǵݹ�汾
	/**
	 * ����ʱ�䣺684ms
	 * ռ���ڴ棺19796k
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return node;
		// ��¼���Ͻڵ�֮���ӳ���ϵ
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		// ������Ҫ����Ľڵ�
		LinkedList<UndirectedGraphNode> stack = new LinkedList<>();
		// ��ʶ�Ѿ�������Ľڵ�
		HashSet<UndirectedGraphNode> set = new HashSet<>();
		stack.push(node);
		map.put(node, new UndirectedGraphNode(node.label));
		set.add(node);
		UndirectedGraphNode curNode = null;
		ArrayList<UndirectedGraphNode> newNeighobrs = null;
		while (!stack.isEmpty()) {
			curNode = stack.pop();
			for (UndirectedGraphNode t : curNode.neighbors) {
				// û�д������
				if (set.add(t)) {
					stack.push(t);
					map.put(t, new UndirectedGraphNode(t.label));
				}
			}
		}
		// �����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
		stack.clear();
		set.clear();
		stack.push(node);
		while (!stack.isEmpty()) {
			curNode = stack.pop();
			newNeighobrs = new ArrayList<>();
			// û�д������
			if (set.add(curNode)) {
				for (UndirectedGraphNode t : curNode.neighbors) {
					// �����µ��ھӹ�ϵ
					stack.push(t);
					newNeighobrs.add(map.get(t));
				}
				map.get(curNode).neighbors = newNeighobrs;
			}
		}
		return map.get(node);
	}
	
	
	//�ݹ�汾 ͼ��DFS����cloneGraphWithReCursive()�������ص�ǰ�ڵ�ĸ��ƺ���½ڵ�
	/**
	 * ����ʱ�䣺740ms
	 * ռ���ڴ棺19564k
	 */
	private HashMap<UndirectedGraphNode, UndirectedGraphNode> oldNewMap = new HashMap<>();

	public UndirectedGraphNode cloneGraphWithReCursive(UndirectedGraphNode node) {
		if (node == null)
			return null;
		// ����ýڵ�Ĺ�ϵ�Ѿ�������
		if (oldNewMap.containsKey(node))
			return oldNewMap.get(node);
		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		oldNewMap.put(node, newNode);
		for (UndirectedGraphNode i : node.neighbors) {
			newNode.neighbors.add(cloneGraphWithReCursive(i));
		}
		return newNode;
	}
	
}
