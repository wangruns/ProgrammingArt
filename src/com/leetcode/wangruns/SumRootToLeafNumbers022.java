package com.leetcode.wangruns;

import com.leetcode.wangruns.Leetcode.TreeNode;

//22��sum-root-to-leaf-numbers[��]
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
 * ���Թ۲쵽��ǰ�ڵ��·����Ϊ��һ���ڵ�ĺ�*10+��ǰ�ڵ��ֵ
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

	// ��ȡ���ڵ㵽��ǰ�ڵ��·����
	private int preorderSum(TreeNode root, int sum) {
		if (root == null)
			return 0;
		sum = sum * 10 + root.val;
		//����Ѿ�����Ҷ�ڵ㣬�������һ��·��������
		if (root.left == null && root.right == null)
			return sum;
		return preorderSum(root.left, sum) + preorderSum(root.right, sum);
	}
	
}
