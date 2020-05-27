package top.wangruns.nowcoder.leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * 题目描述
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
 *
 */
public class P6_BinaryTreePostOrderTraversal {
	
	/**
	 * 思路1
	 * 递归方案，但是题目明确说了不让用，这太没有挑战了
	 * 递归版本比较简单，先递归左节点，再右结点，最后根节点即可.
	 */
	public ArrayList<Integer> postorderTraversal1 (TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		recursive(root,res);
		return res;
    }
	private void recursive(TreeNode root, ArrayList<Integer> res) {
		if(root==null) return;
		recursive(root.left,res);
		recursive(root.right,res);
		res.add(root.val);
	}
	
	/**
	 * 思路2
	 * 迭代
	 * 递归的实质还是入栈的操作
	 * 所以迭代版本可以借助一个辅助栈来实现
	 * 先将左节点入栈，直到为空
	 */
	
	public ArrayList<Integer> postorderTraversal2 (TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		Stack<TreeNode> stack=new Stack<>();
		TreeNode lastVisited=null;
		while(root!=null||!stack.isEmpty()) {
			//先将左节点入栈，直到为空
			while(root!=null) {
				stack.push(root);
				root=root.left;
			}
			root=stack.peek();
			//如果栈顶元素的右结点为空或者已经访问过了,则访问该栈顶元素
			if(root.right==null||root.right==lastVisited) {
				root=stack.pop();
				res.add(root.val);
				lastVisited=root;
				root=null;
			}
			//否则，将该右节点入栈，重复
			else root=root.right;
		}
		return res;
    }

}
