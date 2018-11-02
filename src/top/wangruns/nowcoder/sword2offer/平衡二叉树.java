package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 分析
与计算树的深度类似，不过这里不同的是，如果当前子树已经不平衡了，则直接将当前子树的深度设置为-1返回
 */
public class 平衡二叉树 {
	
	public boolean IsBalanced_Solution(TreeNode root) {
		return treeDepth(root)>=0;
	}

	private int treeDepth(TreeNode root) {
		if(root==null) return 0;
		int left=treeDepth(root.left);
		int right=treeDepth(root.right);
		if(left<0||right<0||Math.abs(left-right)>1) return -1;
		else return Math.max(left, right)+1;
	}

}
