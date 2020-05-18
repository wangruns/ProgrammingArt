package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * 分析
(1)如果该节点的右子树存在，则右子树的最左节点就是下一个节点
(2)如果该节点的右子树不存在，也可以分为两类
	(a)该节点为左节点，那么其父节点就是下一个节点
	(b)否则该节点为右节点，则需向上回退，直到当前节点为其父节点的左节点，那么其父节点就是下一个节点
 */
public class P57_二叉树的下一个结点 {
	
	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if(pNode==null) return null;
		//如果有右子树，则找右子树最左边的
		else if(pNode.right!=null) {
			pNode=pNode.right;
			while(pNode.left!=null) pNode=pNode.left;
			return pNode;
		}
		//如果没有右子树，则找第一个当前节点是父节点左孩子的节点,其父节点就是下一个节点
		else {
			while(pNode.next!=null) {
				if(pNode==pNode.next.left) return pNode.next;
				else pNode=pNode.next;
			}
		}
		//退到了根节点仍没找到，则返回null
		return null;
	}

}
