package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 题目描述
从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * 分析
由于需要按层依次打印，所以可以借用一个辅助队列
 */
public class 从上往下打印二叉树 {
	
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		if(root==null) return res;
		Queue<TreeNode> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode curNode=queue.poll();
			res.add(curNode.val);
			if(curNode.left!=null) queue.add(curNode.left);
			if(curNode.right!=null) queue.add(curNode.right);
		}
		return res;
    }
}
