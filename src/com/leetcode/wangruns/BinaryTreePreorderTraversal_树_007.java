package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.TreeNode;

//7��binary-tree-preorder-traversal[��]
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
public class BinaryTreePreorderTraversal_��_007 {
	
	//Recursive version
	/**
	 * �ݹ�汾�Ƚ����ף��ȱ������ڵ㣬����ڵ㣬����ֽڵ�.
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
	 * �ȱ������ڵ㼰��������ڵ㣬����¼��ֱ��Ϊ��.
	 * �ж�һ��ջ��Ԫ�ص��ҽڵ㣬�����Ϊ����û�з��ʹ������ظ�
	 * ���򣬽���ջ��Ԫ�س�ջ.
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
	 * �ȷ��ʸ��ڵ㣬Ȼ��ø��ڵ����е���ڵ㣬Ȼ���ҽڵ�
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
