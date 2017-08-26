package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.TreeNode;

//7、binary-tree-preorder-traversal[树]
/**
Given a binary tree, return the preorder traversal of its nodes' values.
For example:
Given binary tree{1,#,2,3},
   1
    \
     2
    /
   3

return[1,2,3].
Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal_树_007 {
	
	//Recursive version
	/**
	 * 递归版本比较容易，先遍历根节点，再左节点，最后又节点.
	 */
	public ArrayList<Integer> preorderTraversalWithRecursive(TreeNode root) {
        ArrayList<Integer>res=new ArrayList<>();
        preorderTraversalWithRecursive(res,root);
        return res;
    }
	private void preorderTraversalWithRecursive(ArrayList<Integer> res, TreeNode root) {
		if(root==null) return;
		res.add(root.val);
		preorderTraversalWithRecursive(res,root.left);
		preorderTraversalWithRecursive(res,root.right);
	}
	
	
	//Iterative version 1
	/**
	 * 先遍历根节点及其所有左节点，并记录，直到为空.
	 * 判定一下栈顶元素的右节点，如果不为空且没有访问过，则重复
	 * 否则，将该栈顶元素出栈.
	 */
	public ArrayList<Integer> preorderTraversalWithIterative1(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		LinkedList<TreeNode> stack=new LinkedList<>();
		TreeNode lastVisit=null;
		while(root!=null||!stack.isEmpty()){
			while(root!=null){
				stack.push(root);
				res.add(root.val);
				root=root.left;
			}
			root=stack.peek();
			if(root.right!=null&&lastVisit!=root.right) root=root.right;
			else{
				lastVisit=stack.pop();
				root=null;
			}
		}
		return res;
	}
	
	//Iterative version 2
	/**
	 * 先访问根节点，然后该根节点所有的左节点，然后右节点
	 */
	public ArrayList<Integer> preorderTraversalWithIterative2(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<Integer>();
		LinkedList<TreeNode> stack=new LinkedList<>();
		while(root!=null||!stack.isEmpty()){
			while(root!=null){
				res.add(root.val);
				stack.push(root);
				root=root.left;
			}
			root=stack.pop().right;
		}
		return res;
	}
	
	
}
