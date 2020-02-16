package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
操作给定的二叉树，将其变换为源二叉树的镜像。
输入描述:
二叉树的镜像定义：
		源二叉树 
    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
 *
 */
public class P18_二叉树的镜像 {
	
	public void Mirror(TreeNode root) {
        if(root==null) return;
        TreeNode left=root.left;
        TreeNode right=root.right;
        root.right=left;
        root.left=right;
        Mirror(root.left);
        Mirror(root.right);
    }
	
}
