package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目描述
请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 */
public class P59_按之字形顺序打印二叉树 {
	public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res=new ArrayList<>();
		Stack<TreeNode> s1=new Stack<>();
		Stack<TreeNode> s2=new Stack<>();
		if(pRoot==null) return res;
		s1.add(pRoot);
		ArrayList<Integer> list=new ArrayList<>();
		while(!s1.isEmpty() || (!s2.isEmpty())) {
			while(!s1.isEmpty()) {
				TreeNode cur=s1.pop();
				list.add(cur.val);
				if(cur.left!=null) s2.push(cur.left);
				if(cur.right!=null) s2.push(cur.right);
			}
			res.add(list);
			list=new ArrayList<>();
			if(s2.isEmpty()) break;
			while(!s2.isEmpty()) {
				TreeNode cur=s2.pop();
				list.add(cur.val);
				if(cur.right!=null) s1.push(cur.right);
				if(cur.left!=null) s1.push(cur.left);
			}
			res.add(list);
			list=new ArrayList<>();
		}
		return res;
    }
	
}
