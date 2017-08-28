package com.leetcode.wangruns;

import java.util.ArrayList;
import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.TreeNode;

//6��binary-tree-postorder-traversal[��]
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
	 * �ݹ�汾�Ƚϼ򵥣��ȵݹ���ڵ㣬���ҽ�㣬�����ڵ㼴��.
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
	 * �����汾���Խ���һ������ջ��ʵ�֣�
	 * �Ƚ���ڵ���ջ��ֱ��Ϊ��.
	 * �ж�ջ��Ԫ�ص��ҽڵ��Ƿ�Ϊ�ջ����Ѿ����ʹ��ˣ�����ǣ�����ʸ�ջ��Ԫ��.
	 * ������ǣ��򽫸��ҽ����ջ���ظ���ڵ����ջ����.
	 */
	public ArrayList<Integer> postorderTraversalIterativeVersion(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		LinkedList<TreeNode> stack=new LinkedList<>();
		TreeNode lastVisit=null;
		while(root!=null||!stack.isEmpty()){
			//�Ƚ���ڵ���ջ��ֱ��Ϊ��
			while(root!=null){
				stack.push(root);
				root=root.left;
			}
			//���ջ��Ԫ�ص��ҽ��Ϊ�ջ����Ѿ����ʹ���,����ʸ�ջ��Ԫ��
			root=stack.peek();
			if(root.right==null||root.right==lastVisit){
				root=stack.pop();
				res.add(root.val);
				lastVisit=root;
				root=null;
			}
			//���򣬽����ҽڵ���ջ���ظ�
			else{
				root=root.right;
			}
		}
		return res;
	}
	
}
