package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 */
public class P38_二叉树的深度 {
	
	public int TreeDepth(TreeNode root) {
		if(root==null) return 0;
        int leftTreeDepth=TreeDepth(root.left);
        int rightTreeDepth=TreeDepth(root.right);
        return Math.max(leftTreeDepth, rightTreeDepth)+1;
    }

}
