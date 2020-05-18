package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目描述
从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 */
public class P60_把二叉树打印成多行 {
	
	ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        if(pRoot==null) return res;
        Queue<TreeNode> q1=new LinkedList<>();
        Queue<TreeNode> q2=new LinkedList<>();
        ArrayList<Integer> list=new ArrayList<>();
        q1.add(pRoot);
        while(!q1.isEmpty() || !q2.isEmpty()){
            while(!q1.isEmpty()){
                TreeNode cur=q1.poll();
                list.add(cur.val);
                if(cur.left!=null) q2.add(cur.left);
                if(cur.right!=null) q2.add(cur.right);
            }
            res.add(list);
            list=new ArrayList<>();
            if(q2.isEmpty()) break;
            while(!q2.isEmpty()){
                TreeNode cur=q2.poll();
                list.add(cur.val);
                if(cur.left!=null) q1.add(cur.left);
                if(cur.right!=null) q1.add(cur.right);
            }
            res.add(list);
            list=new ArrayList<>();
        }
        return res;
    }

}
