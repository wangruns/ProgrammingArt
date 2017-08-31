package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//18、clone-graph[图]
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
 *非递归版本：
 *复制图和之前的复制链表有着类似的地方：
 *先建立新节点和老节点之间的关系，
 *根据老节点的关系，建立新节点的关系.
 *
 *递归版本：
 *函数返回当前节点的复制后的一个新节点，图的DFS
 */

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

public class CloneGraph018 {

	//非递归版本
	/**
	 * 运行时间：684ms
	 * 占用内存：19796k
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return node;
		// 记录新老节点之间的映射关系
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		// 保存需要处理的节点
		LinkedList<UndirectedGraphNode> stack = new LinkedList<>();
		// 标识已经处理过的节点
		HashSet<UndirectedGraphNode> set = new HashSet<>();
		stack.push(node);
		map.put(node, new UndirectedGraphNode(node.label));
		set.add(node);
		UndirectedGraphNode curNode = null;
		ArrayList<UndirectedGraphNode> newNeighobrs = null;
		while (!stack.isEmpty()) {
			curNode = stack.pop();
			for (UndirectedGraphNode t : curNode.neighbors) {
				// 没有处理过的
				if (set.add(t)) {
					stack.push(t);
					map.put(t, new UndirectedGraphNode(t.label));
				}
			}
		}
		// 根据老节点的关系，建立新节点的关系
		stack.clear();
		set.clear();
		stack.push(node);
		while (!stack.isEmpty()) {
			curNode = stack.pop();
			newNeighobrs = new ArrayList<>();
			// 没有处理过的
			if (set.add(curNode)) {
				for (UndirectedGraphNode t : curNode.neighbors) {
					// 建立新的邻居关系
					stack.push(t);
					newNeighobrs.add(map.get(t));
				}
				map.get(curNode).neighbors = newNeighobrs;
			}
		}
		return map.get(node);
	}
	
	
	//递归版本 图的DFS，该cloneGraphWithReCursive()函数返回当前节点的复制后的新节点
	/**
	 * 运行时间：740ms
	 * 占用内存：19564k
	 */
	private HashMap<UndirectedGraphNode, UndirectedGraphNode> oldNewMap = new HashMap<>();

	public UndirectedGraphNode cloneGraphWithReCursive(UndirectedGraphNode node) {
		if (node == null)
			return null;
		// 如果该节点的关系已经建立了
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
