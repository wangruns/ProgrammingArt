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
	
	//18、clone-graph[图]
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
	
	//非递归版本
	/**
	 * 运行时间：684ms
	 * 占用内存：19796k
	 */
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node==null) return node; 
		//建立新老节点之间的关系
		HashMap<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
		//保存需要处理的节点
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
				//没有处理过的
				if(set.add(t)) {
					stack.push(t);
					map.put(t, new UndirectedGraphNode(t.label));
				}
			}
		}
		//根据老节点的关系，建立新节点的关系
		stack.clear();
		set.clear();
		stack.push(node);
		while(!stack.isEmpty()){
			curNode=stack.pop();
			newNeighobrs=new ArrayList<>();
			//没有处理过的
			if(set.add(curNode)){
				for(UndirectedGraphNode t:curNode.neighbors){
					//建立新的邻居关系
					stack.push(t);
					newNeighobrs.add(map.get(t));
				}
				map.get(curNode).neighbors=newNeighobrs;
			}
		}
		return map.get(node);
    }
	
	//递归版本 图的DFS，该cloneGraphWithReCursive()函数返回当前节点的复制后的新节点
	/**
	 * 运行时间：740ms
	 * 占用内存：19564k
	 */
	private HashMap<UndirectedGraphNode,UndirectedGraphNode> myMap=new HashMap<>();
	public UndirectedGraphNode cloneGraphWithReCursive(UndirectedGraphNode node) {
		if(node==null) return null;
		//如果该节点的关系已经建立了
		if(myMap.containsKey(node)) return myMap.get(node);
		UndirectedGraphNode newNode=new UndirectedGraphNode(node.label);
		myMap.put(node, newNode);
		for(UndirectedGraphNode i:node.neighbors){
			newNode.neighbors.add(cloneGraphWithReCursive(i));
		}
		return newNode;
	}
	
	//17、gas-station[贪心]
	/**
	 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
	 * You begin the journey with an empty tank at one of the gas stations.
	 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
	 * Note: 
	 * The solution is guaranteed to be unique.
	 * 
	 * 从start出发， 如果油量足够， 可以一直向后走 end++
	 * 油量不够的时候，start--向后退
	 * 最终 start == end的时候，如果有解一定是当前 start所在位置，看是否还剩有油
	 */
	//贪心
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start=gas.length-1;
		int end=0;
		int availableGas=gas[start]-cost[start];
		while(start!=end){
			//如果可以从start走到end
			if(availableGas>=0){
				//贪婪的让end变长，看能不能走的更远，即end++
				availableGas+=gas[end]-cost[end];
				end++;
			}else{
				//不能从当前的start走到end，则退一步加点油看看，即start--
				start--;
				availableGas+=gas[start]-cost[start];
			}
		}
		//最终start和end相遇，如果availableGas还有油的话,即可以走一圈
		return availableGas>=0?start:-1;
	}
	
	//枚举
	public int canCompleteCircuitWithEnum(int[] gas, int[] cost) {
		int len=gas.length;
		//把每个都作为开始的站去尝试
        for(int i=0;i<len;i++){
        	int g=0;
        	boolean isOk=true;
        	//对于从i开始走的站，看能不能走完
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
	
	//16、candy[动态规划]
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
        //dp[i]表示i小朋友得到的糖果
        int dp[]=new int[ratings.length];
        //从左向右扫描
        for(int i=1;i<ratings.length;i++)
        	if(ratings[i]>ratings[i-1]) dp[i]=dp[i-1]+1;
        //从右向左扫描
        for(int i=ratings.length-1;i>0;i--)
        	if(ratings[i-1]>ratings[i]&&dp[i-1]<=dp[i]) dp[i-1]=dp[i]+1;
        //计算和
        for(int v:dp) res+=v;
        return res+ratings.length;
    }
	

	//15、single-number-ii[复杂度]
	/**
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	 * Note: 
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * 
	 * Single Number的本质，就是用一个数记录每个bit出现的次数，如果一个bit出现两次就归0，
	 * 这种运算采用二进制底下的位操作^是很自然的.
	 * Single Number II中，如果能定义三进制底下的某种位操作，也可以达到相同的效果,
	 * Single Number II中想要记录每个bit出现的次数，一个数搞不定就加两个数，用ones来记录只出现过一次的bits,
	 * 用twos来记录只出现过两次的bits，ones&twos实际上就记录了出现过三次的bits，
	 * 这时候我们来模拟进行出现3次就抵消为0的操作，抹去ones和twos中都为1的bits。
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
		//记录出现了1次的bits
        int one=0;
        //记录出现了2次的bits
        int two=0;
        //记录出现了3次的bits
        int three=0;
        for(int v:A){
        	//先更新twos
        	two|=one&v;
        	one^=v;
        	//one和two中都为1则出现了3次
        	three=one&two;
        	//清除出现了3次的
        	one&=~three;
        	two&=~three;
        }
        return one;
    }
	
	//14、single-number[复杂度]
	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * Note: 
	 * Your algorithm should have a linear runtime complexity. 
	 * Could you implement it without using extra memory?
	 * 
	 * 除了一个单身的，其他都是两两配对的，联想到异或
	 * 两个相同的数异或为0，而0和任何数异或为任何数
	 * 所以可以将他们全部异或一次，最后的结果就是那个单身的数.
	 */
	public int singleNumberFromTwo(int[] A) {
		int singleNumber=0;
        for(int i=0;i<A.length;i++) singleNumber^=A[i];
        return singleNumber;
    }
	
	//13、copy-list-with-random-pointer[链表]
	/**
	 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
	 * Return a deep copy of the list.
	 * 
	 * 新的链表和旧的链表，一种对应关系，联想到	map
	 * 建立老节点和新节点的一一对应关系			map(old,new)
	 * 根据老节点的关系，建立新节点的关系		map.get(old).next=map.get(old.next);
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
	//RandomListNode在list中
	public RandomListNode copyRandomList1(RandomListNode head) {
		if(head==null) return head;
		HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
		//建立新节点和老节点的关系
		RandomListNode p=head;
		while(p!=null){
			map.put(p, new RandomListNode(p.label));
			p=p.next;
		}
		//更具老节点的关系，建立新节点的关系
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
	
	//RandomListNode可以不在list中
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) return head;
        RandomListNode p=head;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        //建立老节点和新节点的一一对应关系
        while(p!=null){
        	map.put(p, new RandomListNode(p.label));
        	if(p.next!=null) map.put(p.next, new RandomListNode(p.next.label));
        	if(p.random!=null) map.put(p.random, new RandomListNode(p.random.label));
        	p=p.next;
        }
        //根据老节点的关系，建立新节点的关系
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
	
	//12、word-break-ii[动态规划]
	/**
	Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
	Return all such possible sentences.
	For example, given
	s ="catsanddog",
	dict =["cat", "cats", "and", "sand", "dog"].
	A solution is["cats and dog", "cat sand dog"].
	
	动态规划思想，用map<s,list>把已经求得的结果存起来，避免重复劳动.
	s表示当前字符串，list表示该字符串的拆分情况
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

	//11、word-break[动态规划]
	/**
	 * Given a string s and a dictionary of words dict, 
	 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
	 * For example, given s ="leetcode", dict =["leet", "code"].
	 * Return true because"leetcode"can be segmented as"leet code".
	 * 
	 * 当前字符串可以被分割的前提是，该字符串之前的字符串可以被分割，他们相差的字符串在字典中.
	 * 
	 * dp[i]表示s[0-i]可以被切割
	 * dp[i]为true 当且仅当 存在0<=K<=i, 使得dp[k]==true且子串s[k,i]在字典中
	 * dp[0]控制位默认等于true
	 * 如s="leet"的时候 
	 * |leet
	 * 当i=4, 有dp[0]==true,而且s[0,4)即"leet"在dict字典中，所以dp[4]=true;
	 * s="leetcode"的时候
	 * leet|code
	 * 当i=8, 有dp[4]==true, 而且s[4,8)即"code"在字典中，所以dp[8]=true;
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
	
	//10、linked-list-cycle[链表]
	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * 快慢指针相遇则表示有环
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
	
	//9、linked-list-cycle-ii[链表]
	/**
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 * Follow up:
	 * Can you solve it without using extra space?
	 * 
	 * 
	 */
	public ListNode detectCycle(ListNode head) {
		if(head==null) return head;
		//找到相遇点
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			low=low.next;
			//相遇
			if(fast==low){
				//慢指针指向头，快慢指针以相同的速度走，再次相遇便是环入口
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
	 * 开辟新空间的话，题目会变得非常简单
	 */
	//detectCycleWithExtraSpace1 断链法，访问过的指向trap
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
	
	//detectCycleWithExtraSpace2 利用set无重复元素的特征
	public ListNode detectCycleWithExtraSpace2(ListNode head) {
        if(head==null) return head;
        HashSet<ListNode> set=new HashSet<>();
        while(head!=null){
        	if(!set.add(head)) return head;
        	head=head.next;
        }
        return null;
    }
	
	//8、reorder-list[链表]
	/**
	Given a singly linked list L: L 0→L 1→…→L n-1→L n,
	reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…
	You must do this in-place without altering the nodes' values.
	For example,
	Given{1,2,3,4}, reorder it to{1,4,2,3}.
	*
	*找到链表中点，快慢指针. {1,2} 和 {3,4}
	*将链表的后半段反转. {1,2} 和 {4,3}
	*合并. {1,4,2,3}
	*
	 */
	public void reorderList(ListNode head) {
		if(head==null) return;
		//中点断开
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;
		//后半段反转
		ListNode pre=null,next=null;
		while(middle!=null){
			next=middle.next;
			middle.next=pre;
			pre=middle;
			middle=next;
		}
		//合并
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
	//利用了辅助空间
	/**
	 * 运行时间：1566ms
	 * 占用内存：39440k
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
