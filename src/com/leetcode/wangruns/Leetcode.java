package com.leetcode.wangruns;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;




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
		
		
		

		UndirectedGraphNode graphNode=o.new UndirectedGraphNode(0);
		ArrayList<UndirectedGraphNode> neighbors=new ArrayList<>();
		neighbors.add(graphNode);
		neighbors.add(graphNode);
		graphNode.neighbors=neighbors;
		System.out.println(o.cloneGraph(graphNode).neighbors);
		
		
	}
	
	//18��clone-graph[ͼ]
	/**
	 * Clone an undirected graph. Each node in the graph contains alabeland a list of its neighbors.
	 * OJ's undirected graph serialization:
	 * Nodes are labeled uniquely.
	 * We use#as a separator for each node, and,as a separator for node label and each neighbor of the node.
	 * As an example, consider the serialized graph{0,1,2# 1,2# 2,2}.
	 * The graph has a total of three nodes, and therefore contains three parts as separated by#.
	 * 
	 * First node is labeled as0. Connect node0to both nodes1and2.
	 * Second node is labeled as1. Connect node1to node2.
	 * Third node is labeled as2. Connect node2to node2(itself), thus forming a self-cycle.
	 * Visually, the graph looks like the following:
							   1
							  / \
							 /   \
							0 --- 2
							     / \
							     \_/
	 */
	/**
	 * Definition for undirected graph.
	 * class UndirectedGraphNode {
	 *     int label;
	 *     ArrayList<UndirectedGraphNode> neighbors;
	 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	 * };
	 */
	class UndirectedGraphNode {
		int label;
		ArrayList<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	}
	
	//�ǵݹ�汾
	/**
	 * ����ʱ�䣺684ms
	 * ռ���ڴ棺19796k
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node==null) return node; 
		//�������Ͻڵ�֮��Ĺ�ϵ
		HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
		//������Ҫ����Ľڵ�
		LinkedList<UndirectedGraphNode> stack=new LinkedList<>();
		HashSet<UndirectedGraphNode>set=new HashSet<>();
		stack.push(node);
		map.put(node, new UndirectedGraphNode(node.label));
		set.add(node);
		UndirectedGraphNode curNode=null;
		ArrayList<UndirectedGraphNode> newNeighobrs=null;
		while(!stack.isEmpty()){
			curNode=stack.pop();
			for(UndirectedGraphNode t:curNode.neighbors){
				//û�д������
				if(set.add(t)) {
					stack.push(t);
					map.put(t, new UndirectedGraphNode(t.label));
				}
			}
		}
		//�����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
		stack.clear();
		set.clear();
		stack.push(node);
		while(!stack.isEmpty()){
			curNode=stack.pop();
			newNeighobrs=new ArrayList<>();
			//û�д������
			if(set.add(curNode)){
				for(UndirectedGraphNode t:curNode.neighbors){
					//�����µ��ھӹ�ϵ
					stack.push(t);
					newNeighobrs.add(map.get(t));
				}
				map.get(curNode).neighbors=newNeighobrs;
			}
		}
		return map.get(node);
    }
	
	//�ݹ�汾 ͼ��DFS����cloneGraphWithReCursive()�������ص�ǰ�ڵ�ĸ��ƺ���½ڵ�
	/**
	 * ����ʱ�䣺740ms
	 * ռ���ڴ棺19564k
	 */
	private HashMap<UndirectedGraphNode,UndirectedGraphNode> myMap=new HashMap<>();
	public UndirectedGraphNode cloneGraphWithReCursive(UndirectedGraphNode node) {
		if(node==null) return null;
		//����ýڵ�Ĺ�ϵ�Ѿ�������
		if(myMap.containsKey(node)) return myMap.get(node);
		UndirectedGraphNode newNode=new UndirectedGraphNode(node.label);
		myMap.put(node, newNode);
		for(UndirectedGraphNode i:node.neighbors){
			newNode.neighbors.add(cloneGraphWithReCursive(i));
		}
		return newNode;
	}
	
	//17��gas-station[̰��]
	/**
	 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
	 * You begin the journey with an empty tank at one of the gas stations.
	 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
	 * Note: 
	 * The solution is guaranteed to be unique.
	 * 
	 * ��start������ ��������㹻�� ����һֱ����� end++
	 * ����������ʱ��start--�����
	 * ���� start == end��ʱ������н�һ���ǵ�ǰ start����λ�ã����Ƿ�ʣ����
	 */
	//̰��
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start=gas.length-1;
		int end=0;
		int availableGas=gas[start]-cost[start];
		while(start!=end){
			//������Դ�start�ߵ�end
			if(availableGas>=0){
				//̰������end�䳤�����ܲ����ߵĸ�Զ����end++
				availableGas+=gas[end]-cost[end];
				end++;
			}else{
				//���ܴӵ�ǰ��start�ߵ�end������һ���ӵ��Ϳ�������start--
				start--;
				availableGas+=gas[start]-cost[start];
			}
		}
		//����start��end���������availableGas�����͵Ļ�,��������һȦ
		return availableGas>=0?start:-1;
	}
	
	//ö��
	public int canCompleteCircuitWithEnum(int[] gas, int[] cost) {
		int len=gas.length;
		//��ÿ������Ϊ��ʼ��վȥ����
        for(int i=0;i<len;i++){
        	int g=0;
        	boolean isOk=true;
        	//���ڴ�i��ʼ�ߵ�վ�����ܲ�������
        	for(int j=i;j<i+len;j++){
        		int index=j%len;
        		g+=gas[index];
        		if(g>=cost[index]){
        			g-=cost[index];
        			 continue;
        		}
        		else{
        			isOk=false;
        			break;
        		}
        	}
        	if(isOk) return i;
        }
        return -1;
	}
	
	//16��candy[��̬�滮]
	/**
	 * There are N children standing in a line. Each child is assigned a rating value.
	 * You are giving candies to these children subjected to the following requirements:
	 * Each child must have at least one candy.
	 * Children with a higher rating get more candies than their neighbors.
	 * What is the minimum candies you must give?
	 */
	public int candy(int[] ratings) {
		if(ratings.length==0) return 0;
        int res=0;
        //dp[i]��ʾiС���ѵõ����ǹ�
        int dp[]=new int[ratings.length];
        //��������ɨ��
        for(int i=1;i<ratings.length;i++)
        	if(ratings[i]>ratings[i-1]) dp[i]=dp[i-1]+1;
        //��������ɨ��
        for(int i=ratings.length-1;i>0;i--)
        	if(ratings[i-1]>ratings[i]&&dp[i-1]<=dp[i]) dp[i-1]=dp[i]+1;
        //�����
        for(int v:dp) res+=v;
        return res+ratings.length;
    }
	

	//15��single-number-ii[���Ӷ�]
	/**
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	 * Note: 
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * 
	 * Single Number�ı��ʣ�������һ������¼ÿ��bit���ֵĴ��������һ��bit�������ξ͹�0��
	 * ����������ö����Ƶ��µ�λ����^�Ǻ���Ȼ��.
	 * Single Number II�У�����ܶ��������Ƶ��µ�ĳ��λ������Ҳ���Դﵽ��ͬ��Ч��,
	 * Single Number II����Ҫ��¼ÿ��bit���ֵĴ�����һ�����㲻���ͼ�����������ones����¼ֻ���ֹ�һ�ε�bits,
	 * ��twos����¼ֻ���ֹ����ε�bits��ones&twosʵ���Ͼͼ�¼�˳��ֹ����ε�bits��
	 * ��ʱ��������ģ����г���3�ξ͵���Ϊ0�Ĳ�����Ĩȥones��twos�ж�Ϊ1��bits��
	 * 
	 * A={1,1,1,2},ones=0,twos=0,threes=0
	 * i=0	v=1
	 * twos=ones & v         =>twos=00
	 * ones^=v                   =>ones=01
	 * threes=one&twos     =>threes=00
	 * 
	 * i=1	v=1
	 * twos=ones & v		    =>01 & 01 	  =>twos=01
	 * ones^=v				    =>01^01   	  =>ones=00
	 * threes=ones&twos   =>01&00		  =>threes=00
	 * 
	 */
	public int singleNumberFromThree(int[] A) {
		//��¼������1�ε�bits
        int one=0;
        //��¼������2�ε�bits
        int two=0;
        //��¼������3�ε�bits
        int three=0;
        for(int v:A){
        	//�ȸ���twos
        	two|=one&v;
        	one^=v;
        	//one��two�ж�Ϊ1�������3��
        	three=one&two;
        	//���������3�ε�
        	one&=~three;
        	two&=~three;
        }
        return one;
    }
	
	//14��single-number[���Ӷ�]
	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * Note: 
	 * Your algorithm should have a linear runtime complexity. 
	 * Could you implement it without using extra memory?
	 * 
	 * ����һ������ģ���������������Եģ����뵽���
	 * ������ͬ�������Ϊ0����0���κ������Ϊ�κ���
	 * ���Կ��Խ�����ȫ�����һ�Σ����Ľ�������Ǹ��������.
	 */
	public int singleNumberFromTwo(int[] A) {
		int singleNumber=0;
        for(int i=0;i<A.length;i++) singleNumber^=A[i];
        return singleNumber;
    }
	
	//13��copy-list-with-random-pointer[����]
	/**
	 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
	 * Return a deep copy of the list.
	 * 
	 * �µ�����;ɵ�����һ�ֶ�Ӧ��ϵ�����뵽	map
	 * �����Ͻڵ���½ڵ��һһ��Ӧ��ϵ			map(old,new)
	 * �����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ		map.get(old).next=map.get(old.next);
	 */
	
	/**
	 * Definition for singly-linked list with a random pointer.
	 * class RandomListNode {
	 *     int label;
	 *     RandomListNode next, random;
	 *     RandomListNode(int x) { this.label = x; }
	 * };
	 */
	class RandomListNode{
		int label;
		RandomListNode next,random;
		RandomListNode(int x){ this.label=x; }
	}
	//RandomListNode��list��
	public RandomListNode copyRandomList1(RandomListNode head) {
		if(head==null) return head;
		HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
		//�����½ڵ���Ͻڵ�Ĺ�ϵ
		RandomListNode p=head;
		while(p!=null){
			map.put(p, new RandomListNode(p.label));
			p=p.next;
		}
		//�����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
		p=head;
		RandomListNode newNode=null;
		while(p!=null){
			newNode=map.get(p);
			if(p.next!=null) newNode.next=map.get(p.next);
			if(p.random!=null) newNode.random=map.get(p.random);
			p=p.next;
		}
		return map.get(head);
	}
	
	//RandomListNode���Բ���list��
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return head;
        RandomListNode p=head;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        //�����Ͻڵ���½ڵ��һһ��Ӧ��ϵ
        while(p!=null){
        	map.put(p, new RandomListNode(p.label));
        	if(p.next!=null) map.put(p.next, new RandomListNode(p.next.label));
        	if(p.random!=null) map.put(p.random, new RandomListNode(p.random.label));
        	p=p.next;
        }
        //�����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
        p=head;
        RandomListNode newNode=null;
        while(p!=null){
        	newNode=map.get(p);
        	if(p.next!=null) newNode.next=map.get(p.next);
        	if(p.random!=null) newNode.random=map.get(p.random);
        	p=p.next;
        }
        return map.get(head);
    }
	
	//12��word-break-ii[��̬�滮]
	/**
	Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
	Return all such possible sentences.
	For example, given
	s ="catsanddog",
	dict =["cat", "cats", "and", "sand", "dog"].
	A solution is["cats and dog", "cat sand dog"].
	
	��̬�滮˼�룬��map<s,list>���Ѿ���õĽ���������������ظ��Ͷ�.
	s��ʾ��ǰ�ַ�����list��ʾ���ַ����Ĳ�����
	 */
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res=new ArrayList<>();
        HashMap<String,ArrayList<String>> map=new HashMap<>();
        res=DFS(s,dict,map);
        return res;
    }
	private ArrayList<String> DFS(String s, Set<String> dict, HashMap<String, ArrayList<String>> map) {
		if(map.containsKey(s)) return map.get(s);
		ArrayList<String> ans=new ArrayList<>();
		if(s.length()==0) {
			ans.add("");
			return ans;
		}
		for(String word:dict){
			if(s.startsWith(word)){
				ArrayList<String> subList=DFS(s.substring(word.length()),dict,map);
				for(String sub:subList) {
					ans.add(word+(sub.isEmpty()?"":" ")+ sub);
				}
			}
		}
		map.put(s, ans);
		return ans;
	}

	//11��word-break[��̬�滮]
	/**
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
	 * For example, given s ="leetcode", dict =["leet", "code"].
	 * Return true because"leetcode"can be segmented as"leet code".
	 * 
	 * ��ǰ�ַ������Ա��ָ��ǰ���ǣ����ַ���֮ǰ���ַ������Ա��ָ���������ַ������ֵ���.
	 * 
	 * dp[i]��ʾs[0-i]���Ա��и�
	 * dp[i]Ϊtrue ���ҽ��� ����0<=K<=i, ʹ��dp[k]==true���Ӵ�s[k,i]���ֵ���
	 * dp[0]����λĬ�ϵ���true
	 * ��s="leet"��ʱ�� 
	 * |leet
	 * ��i=4, ��dp[0]==true,����s[0,4)��"leet"��dict�ֵ��У�����dp[4]=true;
	 * s="leetcode"��ʱ��
	 * leet|code
	 * ��i=8, ��dp[4]==true, ����s[4,8)��"code"���ֵ��У�����dp[8]=true;
	 */
	public boolean wordBreak1(String s, Set<String> dict) {
		int len=s.length()+1;
		boolean dp[]=new boolean[len];
		dp[0]=true;
		for(int i=1;i<len;i++)
			for(int j=0;j<i;j++)
				if(dp[j]&&dict.contains(s.substring(j,i))){
					dp[i]=true;
					break;
				}
		return dp[s.length()];
	}
	
	//10��linked-list-cycle[����]
	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * ����ָ���������ʾ�л�
	 */
	public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode fast=head,low=head;
        while(fast!=null&&fast.next!=null){
        	fast=fast.next.next;
        	low=low.next;
        	if(fast==low) return true;
        }
        return false;
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
	*������ĺ��η�ת. {1,2} �� {4,3}
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
