package top.wangruns.nowcoder.sword2offer;

/**
 * 题目描述
请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 */
public class P58_对称的二叉树 {
	
	boolean isSymmetrical(TreeNode pRoot) {
		if(pRoot==null) return true;
		return isSymmetrical(pRoot.left,pRoot.right);
	}

	private boolean isSymmetrical(TreeNode n1, TreeNode n2) {
		if(n1==null&&n2==null) return true;
		else if(n1==null||n2==null) return false;
		else if(n1.val!=n2.val) return false;
		else return isSymmetrical(n1.left,n2.right)&&isSymmetrical(n1.right,n2.left);
	}
	
}
