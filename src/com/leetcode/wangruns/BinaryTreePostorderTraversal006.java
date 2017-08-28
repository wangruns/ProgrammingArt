package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.TreeNode;

//6、binary-tree-postorder-traversal[树]
/**
Given a binary tree, return the postorder traversal of its nodes' values.
For example:
Given binary tree{1,#,2,3},
   1
    \
     2
    /
   3

return[3,2,1].
Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal006 {
	
	//Solution 1: recursive version 
	/**
	 * 递归版本比较简单，先递归左节点，再右结点，最后根节点即可.
	 */
	public ArrayList<Integer> postorderTraversalRecursiveVersion(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		recursiveHelper(res,root);
		return res;
	}
	private void recursiveHelper(ArrayList<Integer> res, TreeNode root) {
		if(root==null) return ;
		recursiveHelper(res,root.left);
		recursiveHelper(res,root.right);
		res.add(root.val);
	}
	
	
	//Solution 2: iterative version
	/**
	 * 迭代版本可以借助一个辅助栈来实现：
	 * 先将左节点入栈，直到为空.
	 * 判定栈顶元素的右节点是否为空或者已经访问过了，如果是，则访问该栈顶元素.
	 * 如果不是，则将该右结点入栈，重复左节点的入栈过程.
	 */
	public ArrayList<Integer> postorderTraversalIterativeVersion(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		LinkedList<TreeNode> stack=new LinkedList<>();
		TreeNode lastVisit=null;
		while(root!=null||!stack.isEmpty()){
			//先将左节点入栈，直到为空
			while(root!=null){
				stack.push(root);
				root=root.left;
			}
			//如果栈顶元素的右结点为空或者已经访问过了,则访问该栈顶元素
			root=stack.peek();
			if(root.right==null||root.right==lastVisit){
				root=stack.pop();
				res.add(root.val);
				lastVisit=root;
				root=null;
			}
			//否则，将该右节点入栈，重复
			else{
				root=root.right;
			}
		}
		return res;
	}
	
}
