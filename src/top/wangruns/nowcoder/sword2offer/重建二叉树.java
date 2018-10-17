package top.wangruns.nowcoder.sword2offer;

import java.util.Arrays;

/**
 * 
 * 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 分析
 如这里前序的1为树的根节点，而在中序里面可以得到该根节点的左部分{4,7,2}以及右部分{5,3,8,6}
 而{4,7,2} 1 表示根节点的左子树有3个节点，对应前序中的 1 {2,4,7}
 而 1 {5,3,8,6} 表示根节点的右子树有4个节点，对应前序中的 1 {3,5,6,8}
 即 前序1 {2,4,7} {3,5,6,8} 中序{4,7,2} 1 {5,3,8,6}
 */
public class 重建二叉树 {
	
	//直接利用Arrays.copyOfRange([],from,to)复制产生子数组
	public TreeNode reConstructBinaryTree1(int [] pre,int [] in) {
		if(pre.length==0 || in.length==0) return null;
		TreeNode treeNode=new TreeNode(pre[0]);
		int findPos=0;
		for(int i=0;i<in.length;i++) {
			if(in[i]==pre[0]) {
				findPos=i;
				break;
			}
		}
		
		treeNode.left=reConstructBinaryTree1(Arrays.copyOfRange(pre,1, findPos+1),Arrays.copyOfRange(in, 0, findPos));
		treeNode.right=reConstructBinaryTree1(Arrays.copyOfRange(pre,findPos+1, pre.length),Arrays.copyOfRange(in, findPos+1, in.length));
		return treeNode;
	}
	
	//用两个数组下标来表示子数组
	public TreeNode reConstructBinaryTree2(int [] pre,int [] in) {
		return _re(pre,0,pre.length-1,in,0,in.length-1);
	}
	private TreeNode _re(int[] pre, int preL, int preR, int[] in, int inL, int inR) {
		if(preL>preR || inL>inR) return null;
		TreeNode treeNode=new TreeNode(pre[preL]);
		int findPos=0;
		for(int i=inL;i<=inR;i++) {
			if(pre[preL]==in[i]) {
				findPos=i;
				break;
			}
		}
		
		treeNode.left=_re(pre,preL+1,preL+(findPos-inL),in,inL,findPos-1);
		treeNode.right=_re(pre,preL+(findPos-inL)+1,preR,in,findPos+1,inR);
		return treeNode;
	}

}
