package com.leetcode.wangruns;

import com.leetcode.wangruns.Leetcode.TreeNode;

//27,binary-tree-maximum-path-sum[树]
/**
 * Given a binary tree, find the maximum path sum. 
 * The path may start and end at any node in the tree. 
 * For example:
 * For example:
       1
      / \
     2   3
 * Return 6.
 * 
 * 这个题目的意思求最短的路径，独特之处在于，其起点可以是任意的并非一定是根节点
 * 即可以越过根，从根的左边到右边
 * 树的题目，通常会联想到递归操作：
 * 用maxPathSum来记录最长的路径，而maxPathSum有如下三种来源：
 * 1、仅包含顶点（[顶点]是最大的）
 * 2、包含一个子树和顶点（[子路径中最大的一条+顶点]是最大的）
 * 3、包含左子树和右子树以及顶点（[两条子路径+顶点]是最大的
 * maxPath（root)函数返回其子节点到当前root的最长路径，
 */
public class BinaryTreeMaximumPathSum027 {
	
	private int maxPathSum=0x80000000;
	public int maxPathSum(TreeNode root) {
		maxPath(root);
		return maxPathSum;
	}
	private int maxPath(TreeNode root) {
		if(root==null) return 0;
		int leftV=maxPath(root.left);
		int rightV=maxPath(root.right);
		int son=Math.max(leftV, rightV);
		//三种情况：1.仅包含顶点，2.包含一个子树和顶点，3.包含左子树和右子树以及顶点
		int situation1=root.val;
		int situation2=root.val+son;
		int situation3=root.val+leftV+rightV;
		int tempMax=Math.max(situation1, Math.max(situation2, situation3));
		//更新最长路径
		if(tempMax>maxPathSum) maxPathSum=tempMax;
		//maxPath（root)函数返回其子节点到当前root的最长路径
		return Math.max(root.val, root.val+son);
	}
}
