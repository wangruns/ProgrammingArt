package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;

/**
 * 
 * 题目描述
输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
(注意: 在返回值的list中，数组长度大的数组靠前)
 *
 */
public class P24_二叉树中和为某一值的路径 {
	
	ArrayList<ArrayList<Integer>> res=new ArrayList<>();
	ArrayList<Integer> path=new ArrayList<>();
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		if(root!=null){
			path.add(root.val);
			target-=root.val;
			if(target==0 && (root.left==null && root.right==null)) {
				res.add(new ArrayList<Integer>(path));
			}
			FindPath(root.left,target);
			FindPath(root.right,target);
			path.remove(path.size()-1);
		}
		return res;
    }
	
}
