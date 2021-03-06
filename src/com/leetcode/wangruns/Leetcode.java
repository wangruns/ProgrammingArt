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
		TreeNode r4 = o.new TreeNode(-4);
		r1.right = r3;
		r1.left = r2;
//		r2.right=r4;
		ListNode l1=o.new ListNode(1);
		ListNode l2=o.new ListNode(2);
		ListNode l3=o.new ListNode(3);
		ListNode l4=o.new ListNode(4);
		l1.next=l2;
		l2.next=l3;
		l3.next=l4;
		

		int a[]= {2,6,3,10,8};
		System.out.println(o.maxProfitiii(a));
		
		
	}
	
	//30、best-time-to-buy-and-sell-stock-iii[数组]
	/**
	 * Say you have an array for which the i^(th) element is the price of a given stock on day i
	 * Design an algorithm to find the maximum profit. 
	 * You may complete at most two transactions. 
	 * Note:
	 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again)
	 *
	 * 假设最开始手里面只有0元
	 * hold1表示买了第一支赚的钱，是花钱，所以-todayPrice
	 * release1表示将第一支卖了赚的钱，所以hold1+todayPrice
	 * 
	 * [买入第二支，是在第一支卖出后的基础上买的]
	 * 
	 * hold2表示买了第二支赚的钱，第二支中用了第一支卖了赚的钱去买的，所以release1-todayPrice
	 * release2表示将第二支卖了赚的钱，所以hold2+todayPrice
	 */
	public int maxProfitiii(int[] prices) {
		int hold1=0x8fffffff,release1=0;
		int hold2=0x8fffffff,release2=0;
		for(int todayPrice:prices) {
			hold1=Math.max(hold1,-todayPrice);//如果今天买入第一支
			release1=Math.max(release1, hold1+todayPrice);//如果今天将第一支卖出
			hold2=Math.max(hold2, release1-todayPrice);//如果今天买入第二支，releas1为第一支赚的钱
			release2=Math.max(release2, hold2+todayPrice);//如果今天将第二支卖出
			}
		return release2;
    }
	
	
	//29、best-time-to-buy-and-sell-stock-ii[数组]
	/**
	 * Say you have an array for which the i^(th) element is the price of a given stock on day i.
	 * Design an algorithm to find the maximum profit. 
	 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
	 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	 * 
	 * 分析可知，只要明天的价格比今天贵，那么就可以今天买入，明天卖出，肯定赚钱；
	 * 如8,10,3,100,50,6
	 *   当天价格		明天价格		是否买入
	 * 1、8			10			买(明天卖出就可以赚差价2)
	 * 2、10			3			不买
	 * 3、3			100			买(明天卖出就可以赚差价97)
	 * 4、100		50			不买
	 * ......
	 */
	public int maxProfitii(int[] prices) {
		if(prices.length==0||prices==null) return 0;
        int maxProfit=0;
        for(int i=0;i<prices.length-1;i++) {
        	int todayPrice=prices[i];
        	int tomorrowPrice=prices[i+1];
        	//只要明天的价格比今天贵，就买入,明天卖出就可以赚钱差价利润
        	if(todayPrice<tomorrowPrice) {
        		maxProfit+=tomorrowPrice-todayPrice;
        	}
        }
        return maxProfit;
    }
	
	//28、best-time-to-buy-and-sell-stock[数组]
	/**
	 * Say you have an array for which the i^(th) element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction(ie, buy one and sell one share of the stock),
	 * design an algorithm to find the maximum profit. 
	 * 
	 * 很巧妙的方式，记录最小值并每次更新
	 * 当前最大利润，为当前价格-记录的最小值
	 * 比较当前利润，并更新最大利润
	 */
	public int maxProfit(int[] prices) {
		if(prices.length==0||prices==null) return 0;
		int maxProfit=0x80000000;
		int minPrice=prices[0];
		for(int i=0;i<prices.length;i++) {
			minPrice=Math.min(minPrice, prices[i]);
			maxProfit=Math.max(maxProfit, prices[i]-minPrice);
		}
		return maxProfit;
    }
	
	//27,binary-tree-maximum-path-sum[树]
	/**
	 * Given a binary tree, find the maximum path sum. 
	 * The path may start and end at any node in the tree. 
	 * For example:
	 * For example:
	       1
	      / \
	     2   3
	 * Return 6.
	 * 
	 * 这个题目的意思求最短的路径，独特之处在于，其起点可以是任意的并非一定是根节点
	 * 即可以越过根，从根的左边到右边
	 * 树的题目，通常会联想到递归操作：
	 * 用maxPathSum来记录最长的路径，而maxPathSum有如下三种来源：
	 * 1、仅包含顶点（[顶点]是最大的）
	 * 2、包含一个子树和顶点（[子路径中最大的一条+顶点]是最大的）
	 * 3、包含左子树和右子树以及顶点（[两条子路径+顶点]是最大的
	 * maxPath（root)函数返回其子节点到当前root的最长路径，
	 */
	private int maxPathSum=0x80000000;
	public int maxPathSum(TreeNode root) {
		maxPath(root);
		return maxPathSum;
	}
	
	private int maxPath(TreeNode root) {
		if(root==null) return 0;
		int leftV=maxPath(root.left);
		int rightV=maxPath(root.right);
		int son=Math.max(leftV, rightV);
        //三种情况：1.仅包含顶点，2.包含一个子树和顶点，3.包含左子树和右子树以及顶点
		int situation1=root.val;
		int situation2=root.val+son;
		int situation3=root.val+leftV+rightV;
		int tempMax=Math.max(situation1, Math.max(situation2, situation3));
		if(tempMax>maxPathSum) maxPathSum=tempMax;
		return Math.max(root.val, root.val+son);
    }
	

	//26,  valid-palindrome[递归] 
	/**
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	 * For example,
	 * "A man, a plan, a canal: Panama"is a palindrome."race a car"is not a palindrome. 
	 * Note:
	 * Have you consider that the string might be empty? This is a good question to ask during an interview. 
	 *  For the purpose of this problem, we define empty string as valid palindrome. 
	 */
	public boolean isPalindrome(String s) {
		s=s.toLowerCase();
        int l=0,r=s.length()-1;
        while(l<r) {
        	while(l<r&&!((s.charAt(l)<='z'&&s.charAt(l)>='a')||(s.charAt(l)<='9'&&s.charAt(l)>='0'))) l++;
        	while(l<r&&!((s.charAt(r)<='z'&&s.charAt(r)>='a')||(s.charAt(r)<='9'&&s.charAt(r)>='0'))) r--;
        	if(s.charAt(l)!=s.charAt(r)) return false;
        	l++;r--;
        }
        return true;
    }
	
	//25、	word-ladder[查找]
	/**
	 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
	 * Only one letter can be changed at a time Each intermediate word must exist in the dictionary
	 * For example,
	 * Given:
	 * start ="hit"
	 * end ="cog"
	 * dict =["hot","dot","dog","lot","log"]
	 * As one shortest transformation is"hit" -> "hot" -> "dot" -> "dog" -> "cog",
	 * return its length 5.
	 * 
	 * Note:
	 * Return 0 if there is no such transformation sequence.
	 * All words have the same length.
	 * All words contain only lowercase alphabetic characters.
	 * 
	 * 思路：最短路径，找到一条最短的路径即可，联想到bfs，可以从起点(根节点一层一层的向下搜索知道遇到目标end)
						hit
						hot
				dot				lot
				dog				log
				cog
	 * 从图可以看到从起点hit就这样一层一层的向下搜索(bfs)，直到左边的路径遇到了end，那么搜索就结束了
	 * 所以我们可以用curPath=0来表示起点hit时候的层数，然后没向下搜索一层，那么curPath=其对应的上一层的curPath+1
	 * 所以这里我们需要记录一下上一层的层数状态(distanceFromStart).				
	 */
	public int ladderLength(String start, String end, HashSet<String> dict) {
		int curPath=0;
		LinkedList<String> queue=new LinkedList<>();
		HashMap<String,Integer> distanceFromStart=new HashMap<>();
		//初始化起点到其他店的距离为无穷远，到自己的距离为0
		for(String s:dict) distanceFromStart.put(s, 0x7fffffff);
		distanceFromStart.put(start, 0);
		dict.add(end);
		queue.add(start);
		while(!queue.isEmpty()){
			String upLayWord=queue.poll();
			//当前的层数=上层的层数+1
			curPath=distanceFromStart.get(upLayWord)+1;
			//逐次替换单词的每个字母
			for(int i=0;i<upLayWord.length();i++){
				StringBuilder sb=new StringBuilder(upLayWord);
				//替换的位置可以是'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curWord=sb.toString();
					//在字典中的curWord才是可以用的
					if(dict.contains(curWord)){
						//找到end，返回
						if(curWord.equals(end)){
							return curPath+1;
						}
						//更从新起点到当前点的层数，并将当前节点如队列，便于继续向下搜索
						if(curPath<distanceFromStart.get(curWord)){
							/**
							 * 不要再将上面层已经出现过的节点重复的添加到队列中
							 * 因为如果curWord是新的还没有处理过的节点，其距离是无限远的
							 * 所以curPath<distanceFromStart.get(curWord)保证curWord之前没有被添加到队列过
							 */
							distanceFromStart.put(curWord, curPath);
							queue.add(curWord);
						}
					}
				}//End 替换的位置可以是'a'-'z'
			}//End 逐次替换单词的每个字母
		}//End while
		//没有这样的路径存在，返回0
		return 0;
	}
	
	//24、	word-ladder-ii[查找]
	/**
	 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
	 * Only one letter can be changed at a time.  Each intermediate word must exist in the dictionary.
	 * For example,
	 * Given:
	 * start ="hit"
	 * end ="cog"
	 * dict =["hot","dot","dog","lot","log"]
	 * Return
	 * [  ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]  ]
	 * 
	 * Note:
	 *All words have the same length.
	 *All words contain only lowercase alphabetic characters.
	 *
	 *1、bfs建立邻接矩阵
	  		[hit]->hot
	  		[hot]->dot->lot
	  		[dot]->....
	  		一层一层的
	  				[hit]
	  		        hot
	        dot			lot
	        dog			log
	       [cog]		   [cog]
	  *2、dfs从最上层开始回溯路径
	 */
	private HashMap<String,ArrayList<String>> adjacencyMatrix024=new HashMap<>();
	private LinkedList<String> queue024=new LinkedList<>();
	private HashMap<String,Integer> layorFromStart=new HashMap<>();
	private ArrayList<ArrayList<String>> res024=new ArrayList<>();
	private LinkedList<String> pathList024=new LinkedList<>();
	
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		/**
		 * bfs建立最短路径的邻接矩阵
		 */
		int minLayor=0x7fffffff;
		int curLayor=0;
		for(String n:dict) layorFromStart.put(n, 0x7fffffff);
		layorFromStart.put(start, 0);
		queue024.add(start);
		dict.add(end);
		StringBuilder sb=null;
		while(!queue024.isEmpty()){
			String upLayorWord=queue024.poll();
			curLayor=layorFromStart.get(upLayorWord)+1;
			//如果当层高度已经大于最小的了，那么不必在向下层寻找了，直接退出
			if(curLayor>minLayor) break;
			//依次替换单词的每个字母
			for(int i=0;i<upLayorWord.length();i++){
				sb=new StringBuilder(upLayorWord);
				//每个被替换的字母可以是'a'-'z'
				for(char ch='a';ch<='z';ch++){
					sb.setCharAt(i, ch);
					String curLayorWord=sb.toString();
					//字典里面有的才能用
					if(dict.contains(curLayorWord)){
						//更新layorFromStart并将当前层节点放入队列，便于按层访问建立邻接矩阵
						if(curLayor>layorFromStart.get(curLayorWord)){
							//如果该节点已经放过了，则不放。(因为是从上层开始的，所以上层肯定在下层之前被放)
							continue;
						}else if(curLayor<layorFromStart.get(curLayorWord)){
							layorFromStart.put(curLayorWord, curLayor);
							queue024.add(curLayorWord);
						}else
							//curLayor==layorFromStart.get(curLayorWord)这种情况不必再次添加到队列了
							//"It is a KEY line. If one word already appeared in one ladder."
							//"Do not insert the same word inside the queue twice. Otherwise it gets TLE."
							;
						//建立最短路径邻接矩阵
						if(adjacencyMatrix024.containsKey(upLayorWord)){
							adjacencyMatrix024.get(upLayorWord).add(curLayorWord);
						}else{
							ArrayList<String> list=new ArrayList<>();
							list.add(curLayorWord);
							adjacencyMatrix024.put(upLayorWord, list);
						}
						//更新最短层数
						if(curLayorWord.equals(end)){
							minLayor=curLayor;
						}
					}
				}
			}
		}//End while
		
		/**
		 * dfs从最上层开始向下建立最短路径
		 */
		backTrace(start,end,dict);
		return res024;
	}

	private void backTrace(String start, String end, HashSet<String> dict) {
		pathList024.add(start);
		if(start.equals(end)){
			ArrayList<String> t=new ArrayList<>(pathList024);
			res024.add(t);
			return;
		}
		ArrayList<String> layorList=adjacencyMatrix024.get(start);
		if(layorList!=null){
			for(String s:layorList){
				backTrace(s,end,dict);
				//回溯
				pathList024.removeLast();
			}
		}
	}

	

	//23、longest-consecutive-sequence[数组]
	/**
	 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	 * For example,
	 * Given[100, 4, 200, 1, 3, 2],
	 * The longest consecutive elements sequence is[1, 2, 3, 4]. Return its length:4.
	 * Your algorithm should run in O(n) complexity.
	 * 
	 * 由于需要O(n)的时间复杂度，那么肯定不可能用nlog(n)排序
	 * 联想到hash.
	 * 哈希表搜是O(1),因为每个数字只会添加一次
	 * 首先将数组元素存放到hashSet中
	 * 然后遍历数组：
	 * 如果v在hashSet中，tempMaxLen++，并判定其左右有没有连续的元素在hashSet中，同样+1
	 * 对于每一次v，如果临时最大值比最大值大了，则更新最大值
	 */
	public int longestConsecutive(int[] num) {
		HashSet<Integer> hashSet=new HashSet<>();
		//将数组元素放到hashSet
		for(int v:num) hashSet.add(v);
		int maxLen=0;
		//遍历数组
		for(int v:num){
			int tempMaxLen=0;
			//判定hashSet中是否包含当前元素及其左右相连续的元素，更新最大值，删除已经遍历过的
			if(hashSet.contains(v)){
				tempMaxLen++;
				hashSet.remove(v);
				//处理左边和右边的连续的元素
				int leftV=v-1,rightV=v+1;
				while(hashSet.contains(leftV)){
					tempMaxLen++;
					hashSet.remove(leftV);
					leftV--;
				}
				while(hashSet.contains(rightV)){
					tempMaxLen++;
					hashSet.remove(rightV);
					rightV++;
				}
				//更新最大值
				if(maxLen<tempMaxLen) maxLen=tempMaxLen;
			}
		}
		return maxLen;
	}
	
	//22、sum-root-to-leaf-numbers[树]
	/**
	 * Given a binary tree containing digits from0-9only, each root-to-leaf path could represent a number.
	 * An example is the root-to-leaf path1->2->3which represents the number123
	 * Find the total sum of all root-to-leaf numbers.
	 * For example,
	    1
	   / \
	  2   3
	 * The root-to-leaf path1->2represents the number12.
	 * The root-to-leaf path1->3represents the number13.
	 * Return the sum = 12 + 13 =25.
	 * 
	 * 可以观察到当前节点的路径和为上一个节点的和*10+当前节点的值
	 */
	public int sumNumbers(TreeNode root) {
        return preorderSumNumers(root,0);
    }
	
	//返回从根节点到当前节点的路径和
	private int preorderSumNumers(TreeNode root, int sum) {
		if(root==null) return 0;
		sum=sum*10+root.val;
		if(root.left==null&&root.right==null) return sum;
		return preorderSumNumers(root.left, sum)+preorderSumNumers(root.right, sum);
	}

	//21、	surrounded-regions[数组]
	/**
	 * Given a 2D board containing'X'and'O', capture all regions surrounded by'X'.
	 * A region is captured by flipping all'O's into'X's in that surrounded region .
	 * For example,
		X X  X X
		X O O X
		X X O X
		X O X X
	 * After running your function, the board should be:
		X X X X
		X X X X
		X X X X
		X O X X
	 * 
	 * 所有与四条边相连的O都保留，其他O都变为X
	 * 遍历四条边上的O，并深度遍历与其相连的O，将这些O都转为*
	 * 将剩余的O变为X
	 * 将剩余的*变为O
	 */
	private int rowNum,colNum;
	public void solve(char[][] board) {
        if(board==null||board.length==0) return;
        //深度遍历四条边上的O及其相连的O，将其转化成*
        rowNum=board.length;
        colNum=board[0].length;
        
        //处理第一列和最后一列
        for(int i=0;i<rowNum;i++){
        	dfs(board,i,0);//处理第一列
        	dfs(board,i,colNum-1);//处理最后一个列
        }
        //处理第一行和最后一行
        for(int i=0;i<colNum;i++){
        	dfs(board,0,i);//处理第一行
        	dfs(board,rowNum-1,i);//处理最后一行
        }
        //将剩余的O变成X，将剩余的*变成O，
        for(int i=0;i<rowNum;i++){
        	for(int j=0;j<colNum;j++){
        		if(board[i][j]=='O') board[i][j]='X';
        		if(board[i][j]=='*') board[i][j]='O';
        	}
        }
        
    }
	
	//如果当前点为O，把它变成*，并对其进行深度遍历
	private void dfs(char[][] board, int row, int col) {
		if(board[row][col]=='O'){
			board[row][col]='*';
			//上
			if(row>1) dfs(board,row-1,col);
			//下
			if(row<rowNum-1) dfs(board,row+1,col);
			//左
			if(col>1) dfs(board,row,col-1);
			//右
			if(col<colNum-1) dfs(board,row,col+1);
		}
	}

	//20、palindrome-partitioning-ii[动态规划]
	/**
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return the minimum cuts needed for a palindrome partitioning of s.
	 * For example, given s ="aab", 
	 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
	 * 
	 * dp[i]表示从0-i这段的最小分割数
	 */
	 public int minCut(String s) {
		 int dp[]=new int[s.length()];
		 //字符的总长度逐步变长
		 for(int i=0;i<s.length();i++){
			 String curStr=s.substring(0,i+1);
			 if(isPalindrom(curStr)) dp[i]=0;
			 else{
				 dp[i]=i;//最多i次分割(初始化成最大的)
				 for(int j=1;j<=i;j++){
					 if(isPalindrom(s.substring(j, i+1))) dp[i]=Math.min(dp[i], dp[j-1]+1);
					 else dp[i]=Math.min(dp[i], dp[j-1]+i-j+1);
				 }
			 }
		 }
		 return dp[s.length()-1];
	 }
	        
	
	
	//19、palindrome-partitioning[字符串]
	/**
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return all possible palindrome partitioning of s.
	 * For example, given s ="aab",
	 * Return:
	 * 
				[
				    ["aa","b"],
				    ["a","a","b"]
				 ]
	 */
	private ArrayList<ArrayList<String>> res018;
	private ArrayList<String> curList018;
	public ArrayList<ArrayList<String>> partition(String s) {
		res018=new ArrayList<>();
		curList018=new ArrayList<>();
		handle018(s);
		return res018;
	}
	
	private void handle018(String s) {
		//完成一次对s的搜索，position位置移动到了最后
		if(curList018.size()>0&&s.length()==0){
			ArrayList<String> t=(ArrayList<String>) curList018.clone();
			res018.add(t);
		}
		for(int i=0;i<s.length();i++){
			String subStr=s.substring(0,i+1);
			if(isPalindrom(subStr)){
				curList018.add(subStr);
				handle018(s.substring(i+1));
				curList018.remove(curList018.size()-1);
			}
		}
	}

	private boolean isPalindrom(String s) {
		int l=0,r=s.length()-1;
		while(l<r){
			if (s.charAt(l) != s.charAt(r)) return false;
			l++;r--;
		}
		return true;
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
