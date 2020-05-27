package top.wangruns.nowcoder.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 题目描述
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along 
the shortest path from the root node down to the nearest leaf node.
 *
 */
public class P1_MinimumDepthOfBinaryTree {
	
	/**
	 * 思路1
	 * BFS广度优先搜索
	 * 即从根节点开始一层一层的搜索，哪一层先碰到叶节点则结束了
	 * 这个走的层数就是树的最小深度
	 */
	public int run1 (TreeNode root) {
		if(root==null) return 0;
		Queue<TreeNode> queue1=new LinkedList<>();
		Queue<TreeNode> queue2=new LinkedList<>();
		int cnt=0;
		queue1.add(root);
		boolean isEnd=false;
		while(!queue1.isEmpty()||!queue2.isEmpty()) {
			while(!queue1.isEmpty()) {
				TreeNode cur=queue1.poll();
				if(cur.left==null&&cur.right==null) {
					isEnd=true;
					break;
				}
				if(cur.left!=null) queue2.add(cur.left);
				if(cur.right!=null) queue2.add(cur.right);
			}
			cnt++;
			if(isEnd) break;
			if(queue2.isEmpty()) break;
			while(!queue2.isEmpty()) {
				TreeNode cur=queue2.poll();
				if(cur.left==null&&cur.right==null) {
					isEnd=true;
					break;
				}
				if(cur.left!=null) queue1.add(cur.left);
				if(cur.right!=null) queue1.add(cur.right);
			}
			cnt++;
			if(isEnd) break;
		}
		return cnt;
    }
	
	/**
	 * 思路2
	 * 用递归的思想
	 * 当前节点的最小深度=min{其左节点的最小深度，其右节点的最小深度}
	 */
	public int run2 (TreeNode root) {
		if(root==null) return 0;
		if(root.left==null&&root.right==null) return 1;
		if(root.left==null) return 1+run2(root.right);
		if(root.right==null) return 1+run2(root.left);
		return 1+Math.min(run2(root.left),run2(root.right));
	}

}
