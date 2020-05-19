package top.wangruns.nowcoder.sword2offer;

/**
 * 题目描述
给定一棵二叉搜索树，请找出其中的第k小的结点。
例如， 
（5，3，7，2，4，6，8) 中，按结点数值大小顺序第三小结点的值为4。
 *
 */
public class P62_二叉搜索树的第k个结点 {
	
	int nodeCnt=0;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
       if(pRoot!=null){
			TreeNode node=KthNode(pRoot.left,k);
			if(node!=null) return node;//只有找到第K个的时候node为返回的结果才不为空
			//遍历到第K个返回
			if(++nodeCnt==k) return pRoot;
			node=KthNode(pRoot.right,k);
			if(node!=null) return node;
		}
		return null; 
    }
    
}
