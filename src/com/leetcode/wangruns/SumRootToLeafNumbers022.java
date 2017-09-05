package com.leetcode.wangruns;

import com.leetcode.wangruns.Leetcode.TreeNode;

//22、sum-root-to-leaf-numbers[树]
/**
 * Given a binary tree containing digits from0-9only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path1->2->3which represents the number123
 * Find the total sum of all root-to-leaf numbers.
 * For example,
    1
   / \
  2   3
 * The root-to-leaf path1->2represents the number12.
 * The root-to-leaf path1->3represents the number13.
 * Return the sum = 12 + 13 =25.
 * 
 * 可以观察到当前节点的路径和为上一个节点的和*10+当前节点的值
 * 0*10+1=1
 * 1*10+2=12
 * 1*10+3=13
 */
public class SumRootToLeafNumbers022 {

	public int sumNumbers(TreeNode root) {
		int sum = 0;
		sum = preorderSum(root, 0);
		return sum;
	}

	// 获取根节点到当前节点的路径和
	private int preorderSum(TreeNode root, int sum) {
		if (root == null)
			return 0;
		sum = sum * 10 + root.val;
		//如果已经到了叶节点，则完成了一条路径，返回
		if (root.left == null && root.right == null)
			return sum;
		return preorderSum(root.left, sum) + preorderSum(root.right, sum);
	}
	
}
