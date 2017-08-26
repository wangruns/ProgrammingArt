package com.leetcode.wangruns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.TreeNode;



public class Leetcode {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int v) {
			val = v;
		}
	}
	class Point{
		int x;
		int y;
		Point(){}
		Point(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	class ListNode{
		int val;
		ListNode next;
		ListNode(int v){
			val=v;
			next=null;
		}
		
		public class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;

			TreeNode(int x) {
				val = x;
			}
		}
		 
	}
	public static void main(String[] args) {
		Leetcode o = new Leetcode();
		TreeNode r1 = o.new TreeNode(1);
		TreeNode r2 = o.new TreeNode(2);
		TreeNode r3 = o.new TreeNode(3);
		TreeNode r4 = o.new TreeNode(4);
		r1.right = r2;
		r2.left = r3;
		r2.right=r4;
		ListNode l1=o.new ListNode(3);
		ListNode l2=o.new ListNode(4);
		ListNode l3=o.new ListNode(1001);
		ListNode l4=o.new ListNode(1110);
		l1.next=l2;
		l2.next=l3;
		l3.next=l4;
		
		
		
		ArrayList<Integer> res=o.postorderTraversal(r1);
		System.out.println(res);
		
		
	}
	
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
	//Recursive version
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
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
	public ArrayList<Integer> preorderTraversalWithIterative(TreeNode root) {
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
	//Trivial version
	public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res=new ArrayList<>();
        postorderTraversalWithRecursive(res,root);
        return res;
    }
	private void postorderTraversalWithRecursive(ArrayList<Integer> res, TreeNode root) {
		if(root==null) return;
		postorderTraversalWithRecursive(res,root.left);
		postorderTraversalWithRecursive(res,root.right);
		res.add(root.val);
	}
	//Iterator version 1
	public ArrayList<Integer> postorderTraversalItetative(TreeNode root) {
		ArrayList<Integer> res=new ArrayList<>();
		LinkedList<TreeNode> stack=new LinkedList<>();
		TreeNode lastVisit=null;
		while(root!=null||!stack.isEmpty()){
			//左节点一直入栈，直到为空
			if(root!=null){
				stack.push(root);
				root=root.left;
			}else{
				root=stack.peek();
				root=root.right;
				//如果栈顶元素的右节点不为空且未访问过
				if(root!=null&&root!=lastVisit){
					//该右节点入栈，重复左结点入栈
					stack.push(root);
					root=root.left;
				}else{
					//否则，访问并记录
					root=stack.pop();
					res.add(root.val);
					lastVisit=root;
					root=null;
				}
			}
		}
		return res;
	}
	
	//iterative version 2
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

	//5、insertion-sort-list[排序]
	/**
	 * Sort a linked list using insertion sort
	 */
	public ListNode insertionSortList(ListNode head) {
		if(head==null||head.next==null) return head;
		ListNode res=new ListNode(Integer.MIN_VALUE);
		while(head!=null){
			ListNode next=head.next;
			ListNode begin=res;
			while(begin.next!=null&&head.val>begin.next.val){
				begin=begin.next;
			}
			head.next=begin.next;
			begin.next=head;
			head=next;
		}
		return res.next;
	}
	
	//4、sort-list[链表]
	/**
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 */
	public ListNode sortList(ListNode head) {
		if(head==null||head.next==null) return head;
		ListNode fast=head,low=head;
		//找到中间位置
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode midldle=low.next;
		//断开
		low.next=null;
		ListNode left=sortList(head);
		ListNode right=sortList(midldle);
		//合并
		ListNode t=new ListNode(0);
		ListNode tRef=t;
		while(left!=null||right!=null){
			if(left==null){
				t.next=right;
				right=right.next;
				t=t.next;
			}else if(right==null){
				t.next=left;
				left=left.next;
				t=t.next;
			}else if(left.val<right.val){
				t.next=left;
				left=left.next;
				t=t.next;
			}else{
				t.next=right;
				right=right.next;
				t=t.next;
			}
		}
		return tRef.next;
    }
	
	//3、max-points-on-a-line[枚举]
	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
	 * 
	 * 利用在一条线上斜率相同的特点，按照斜率计数，但需要考虑重合的点和垂直的情况
	 * (即，求出每一种斜率上的点个数，选择最多的)
	 * 外循环，每个点都作为起始点; 内循环，由该点向其余的点连线，用map<斜率，点数>来计数
	 * 如a,b,c在一条线上且斜率存在，以a为起点，显然K(a->b)=K(a->c)，则和a在一条直线上的
	 * 有两个点，即map.get(a)的值为2，也就是说在该斜率的情况下，一共有3个点在这条线上
	 * 而对于有重复的点，和斜率不存在的情况，我们稍微特殊的处理一下便可.
	 */
	/**
	 * Definition for a point.
	 * class Point {
	 *     int x;
	 *     int y;
	 *     Point() { x = 0; y = 0; }
	 *     Point(int a, int b) { x = a; y = b; }
	 * }
	 */
	public int maxPoints(Point[] points) {
		int res=0;
		for(int i=0;i<points.length;i++){
			int tempRes=0;
			int dup=0;
			int vcnt=0;
			HashMap<Double,Integer> map=new HashMap<>();
			for(int j=i+1;j<points.length;j++){
				double deltaX=points[i].x-points[j].x;
				double deltaY=points[i].y-points[j].y;
				//重复的点
				if(deltaX==0&&deltaY==0) dup++;
				//垂直的点
				else if(deltaX==0) vcnt++;
				//可以计算斜率的点
				else{
					double k=deltaY/deltaX;
					if(k==0) k=0;
					map.put(k, map.get(k)==null?1:map.get(k)+1);
					tempRes=Math.max(tempRes, map.get(k));
				}
			}
			//max(有斜率的,垂直的)+本身起点+和本身起点重复的点
			res=Math.max(res, Math.max(vcnt, tempRes)+1+dup);
		}
		return res;
    }

	// 2、evaluate-reverse-polish-notation[栈]
	/**
	 * Evaluate the value of an arithmetic expression in Reverse Polish
	 * Notation. Valid operators are+,-,*,/. Each operand may be an integer or
	 * another expression. Some examples.
	 * ["2", "1", "+", "3", "*"] -> ((2 + 1)* 3) -> 9.
	 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6.
	 * 
	 * 可以利用栈的特性来模拟解决
	 */
	public int evalRPN(String[] tokens) {
		int len=tokens.length;
		LinkedList<String> stack=new LinkedList<>();
		for(int i=0;i<len;i++){
			if(tokens[i].equals("*")||tokens[i].equals("/")||tokens[i].equals("+")||tokens[i].equals("-")){
				int operand1=Integer.valueOf(stack.pop());
				int operand2=Integer.valueOf(stack.pop());
				int tempRes=0;
				if(tokens[i].equals("*")) tempRes=operand1*operand2;
				else if(tokens[i].equals("/")) tempRes=operand2/operand1;
				else if(tokens[i].equals("+")) tempRes=operand1+operand2;
				else  tempRes=operand2-operand1;
				stack.push(String.valueOf(tempRes));
			}
			else stack.push(tokens[i]);
		}
		return Integer.valueOf(stack.pop());
	}

	// 1、minimum-depth-of-binary-tree[树]
	/**
	 * Given a binary tree, find its minimum depth. The minimum depth is the
	 * number of nodes along the shortest path from the root node down to the
	 * nearest leaf node.
	 * 
	 * 可以利用递归思想来解决，但是需要处理一下斜树的情况
	 */
	public int run(TreeNode root) {
		if (root == null)
			return 0;
		int leftLen = run(root.left);
		int rightLen = run(root.right);
		if (leftLen == 0 || rightLen == 0)
			return leftLen + rightLen + 1;
		return Math.min(leftLen, rightLen) + 1;
	}

}
