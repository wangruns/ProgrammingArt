package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 */
public class P26_二叉搜索树与双向链表 {
	
	private TreeNode pre,head;
	public TreeNode Convert(TreeNode pRootOfTree) {
		if(pRootOfTree!=null){
            Convert(pRootOfTree.left);
            if(head==null) head=pRootOfTree;
            if(pre!=null) pre.right=pRootOfTree;
            pRootOfTree.left=pre;
            pre=pRootOfTree;
            Convert(pRootOfTree.right);
        }
        return head;
    }

}
