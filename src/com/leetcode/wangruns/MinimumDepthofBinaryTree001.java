package com.leetcode.wangruns;

//1��minimum-depth-of-binary-tree[��]
/**
 * Given a binary tree, find its minimum depth. The minimum depth is the number
 * of nodes along the shortest path from the root node down to the nearest leaf
 * node.
 * 
 * �������õݹ�˼���������������Ҫ����һ��б�������.
 * ��ǰ���ĸ߶�=�����ĸ߶�+1
 */
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int v) {
		val = v;
	}
}

public class MinimumDepthofBinaryTree001 {

	public int run(TreeNode root) {
		if (root == null)
			return 0;
		int leftDepth = run(root.left);
		int rightDepth = run(root.right);
		if (leftDepth == 0 || rightDepth == 0)
			return leftDepth + rightDepth + 1;
		return Math.min(leftDepth, rightDepth) + 1;
	}

}
