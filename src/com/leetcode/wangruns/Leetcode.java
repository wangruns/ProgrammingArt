package com.leetcode.wangruns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		ListNode l1=o.new ListNode(1);
		ListNode l2=o.new ListNode(2);
		ListNode l3=o.new ListNode(3);
		ListNode l4=o.new ListNode(4);
		l1.next=l2;
		l2.next=l3;
		l3.next=l4;
		
		
		
		o.reorderList(l1);
		System.out.println(l1);
		while(l1!=null){
			System.out.println(l1.val);
			l1=l1.next;
		}
		
		
	}
	
	//9��linked-list-cycle-ii[����]
	/**
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * 
	 */
	public ListNode detectCycle(ListNode head) {
		if(head==null) return head;
		//�ҵ�������
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			low=low.next;
			//����
			if(fast==low){
				//��ָ��ָ��ͷ������ָ������ͬ���ٶ��ߣ��ٴ��������ǻ����
				low=head;
				while(low!=fast){
					low=low.next;
					fast=fast.next;
				}
				return low;
			}
		}
		return null;
	}
	
	/**
	 * �����¿ռ�Ļ�����Ŀ���÷ǳ���
	 */
	//detectCycleWithExtraSpace1 �����������ʹ���ָ��trap
	public ListNode detectCycleWithExtraSpace1(ListNode head) {
		if(head==null) return head;
		ListNode next=null;
		ListNode trap=new ListNode(0);
		while(head.next!=null){
			if(head.next==trap) return head;
			next=head.next;
			head.next=trap;
			head=next;
		}
		return null;
	}
	
	//detectCycleWithExtraSpace2 ����set���ظ�Ԫ�ص�����
	public ListNode detectCycleWithExtraSpace2(ListNode head) {
        if(head==null) return head;
        HashSet<ListNode> set=new HashSet<>();
        while(head!=null){
        	if(!set.add(head)) return head;
        	head=head.next;
        }
        return null;
    }
	
	//8��reorder-list[����]
	/**
	Given a singly linked list L: L 0��L 1������L n-1��L n,
	reorder it to: L 0��L n ��L 1��L n-1��L 2��L n-2����
	You must do this in-place without altering the nodes' values.
	For example,
	Given{1,2,3,4}, reorder it to{1,4,2,3}.
	*
	*�ҵ������е㣬����ָ��. {1,2} �� {3,4}
	*�������ĺ��η�ת. {1,2} �� {4,3}
	*�ϲ�. {1,4,2,3}
	*
	 */
	public void reorderList(ListNode head) {
		if(head==null) return;
		//�е�Ͽ�
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;
		//���η�ת
		ListNode pre=null,next=null;
		while(middle!=null){
			next=middle.next;
			middle.next=pre;
			pre=middle;
			middle=next;
		}
		//�ϲ�
		middle=pre;
		ListNode middleNext=null,headNext=null;
		while(head!=null&&middle!=null){
		    middleNext=middle.next;
		    headNext=head.next;
			middle.next=head.next;
			head.next=middle;
			middle=middleNext;
			head=headNext;
		}
	}
	//�����˸����ռ�
	/**
	 * ����ʱ�䣺1566ms
	 * ռ���ڴ棺39440k
	 */
	public void reorderList1(ListNode head) {
		if(head==null) return;
        LinkedList<ListNode> nodeList=new LinkedList<ListNode>();
        ListNode res=head;
        while(res!=null){
        	nodeList.add(res);
        	res=res.next;
        }
        res=nodeList.removeFirst();
        boolean isFirst=false;
        ListNode node=null;
        while(!nodeList.isEmpty()){
        	if(isFirst) node=nodeList.removeFirst();
        	else node=nodeList.removeLast();
        	isFirst=!isFirst;
        	node.next=null;
        	res.next=node;
        	res=res.next;
        }
    }
	
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
			//��ڵ�һֱ��ջ��ֱ��Ϊ��
			if(root!=null){
				stack.push(root);
				root=root.left;
			}else{
				root=stack.peek();
				root=root.right;
				//���ջ��Ԫ�ص��ҽڵ㲻Ϊ����δ���ʹ�
				if(root!=null&&root!=lastVisit){
					//���ҽڵ���ջ���ظ�������ջ
					stack.push(root);
					root=root.left;
				}else{
					//���򣬷��ʲ���¼
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

	//5��insertion-sort-list[����]
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
	
	//4��sort-list[����]
	/**
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 */
	public ListNode sortList(ListNode head) {
		if(head==null||head.next==null) return head;
		ListNode fast=head,low=head;
		//�ҵ��м�λ��
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode midldle=low.next;
		//�Ͽ�
		low.next=null;
		ListNode left=sortList(head);
		ListNode right=sortList(midldle);
		//�ϲ�
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
	
	//3��max-points-on-a-line[ö��]
	/**
	 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
	 * 
	 * ������һ������б����ͬ���ص㣬����б�ʼ���������Ҫ�����غϵĵ�ʹ�ֱ�����
	 * (�������ÿһ��б���ϵĵ������ѡ������)
	 * ��ѭ����ÿ���㶼��Ϊ��ʼ��; ��ѭ�����ɸõ�������ĵ����ߣ���map<б�ʣ�����>������
	 * ��a,b,c��һ��������б�ʴ��ڣ���aΪ��㣬��ȻK(a->b)=K(a->c)�����a��һ��ֱ���ϵ�
	 * �������㣬��map.get(a)��ֵΪ2��Ҳ����˵�ڸ�б�ʵ�����£�һ����3��������������
	 * ���������ظ��ĵ㣬��б�ʲ����ڵ������������΢����Ĵ���һ�±��.
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
				//�ظ��ĵ�
				if(deltaX==0&&deltaY==0) dup++;
				//��ֱ�ĵ�
				else if(deltaX==0) vcnt++;
				//���Լ���б�ʵĵ�
				else{
					double k=deltaY/deltaX;
					if(k==0) k=0;
					map.put(k, map.get(k)==null?1:map.get(k)+1);
					tempRes=Math.max(tempRes, map.get(k));
				}
			}
			//max(��б�ʵ�,��ֱ��)+�������+�ͱ�������ظ��ĵ�
			res=Math.max(res, Math.max(vcnt, tempRes)+1+dup);
		}
		return res;
    }

	// 2��evaluate-reverse-polish-notation[ջ]
	/**
	 * Evaluate the value of an arithmetic expression in Reverse Polish
	 * Notation. Valid operators are+,-,*,/. Each operand may be an integer or
	 * another expression. Some examples.
	 * ["2", "1", "+", "3", "*"] -> ((2 + 1)* 3) -> 9.
	 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6.
	 * 
	 * ��������ջ��������ģ����
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

	// 1��minimum-depth-of-binary-tree[��]
	/**
	 * Given a binary tree, find its minimum depth. The minimum depth is the
	 * number of nodes along the shortest path from the root node down to the
	 * nearest leaf node.
	 * 
	 * �������õݹ�˼���������������Ҫ����һ��б�������
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