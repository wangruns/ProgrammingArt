package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 */
public class 二叉搜索树与双向链表 {
	
	TreeNode cur,head;
	public TreeNode Convert(TreeNode pRootOfTree) {
		if(pRootOfTree==null) return null;
		Convert(pRootOfTree.left);
		//第一次的时候记录下head
		if(cur==null) {
			head=pRootOfTree;
			cur=pRootOfTree;
		}else {
			cur.right=pRootOfTree;
			pRootOfTree.left=cur;
			cur=pRootOfTree;
		}
		Convert(pRootOfTree.right);
		
		return head;
    }

}
