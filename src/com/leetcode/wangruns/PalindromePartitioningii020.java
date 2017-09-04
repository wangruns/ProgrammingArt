package com.leetcode.wangruns;

//20、palindrome-partitioning-ii[动态规划]
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s ="aab", 
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * a，using 0 cut
 * aa，using 0 cut
 * aab，using 1 cut
 * aabb，using 1 cut
 * aabbh，using 2 cut
 * ...
 * 尝试当前状态和之前的状态的关系，联想到动态规划
 * dp[i]表示0到下标i这个字符的最小分割数，所dp[s.length()-1]就是最佳解
 * 那么dp[i]和dp[i-1]有什么关系呢？
 * 如s="aab"
 * dp[0]=0
 * dp[1]=0
 * dp[2]=1
 * 可以发现对于dp[i]来说
 * 如果s[0:i]是回文：那么dp[i]=0
 * 如果s[0:i]不是回文：
 * 							1<=j<=i
 * 							如果s[j:i]是回文，那么dp[i]=dp[j-1]+1
 * 						    如果s[j:i]不是回文，那么dp[i]=dp[j-1]+i-j+1
 */
public class PalindromePartitioningii020 {

	public int minCut(String s) {
		int len=s.length();
		int dp[]=new int[len];
		//对于每一个长度a,aa,aab得到对应的dp[i]
		for(int i=0;i<len;i++){
			String curStr=s.substring(0, i+1);
			//如果当前的字符串是回文，则dp[i]直接赋值0
			if(isPalindrome(curStr)) dp[i]=0;
			else{
				//最多i次分割(初始化成最大的便于比较得到最小的)
				dp[i]=i;
				//如果当前字符串不是回文的话，则需要观看之前的记录来确定当前字符串的最小分割数
				for(int j=1;j<=i;j++){
					if(isPalindrome(s.substring(j,i+1))){
						//如果存在1<=k<=i，使得s[k:i]是回文，那么dp[i]=d[k-1]+1
						dp[i]=Math.min(dp[i], dp[j-1]+1);
					}else{
						//如果不存在，那么dp[i]=dp[k-1]+i-k+1;如 acd -> a|c|d
						dp[i]=Math.min(dp[i], dp[j-1]+i-j+1);
					}
				}
			}
		}
		return dp[len-1];
	}

	//判定一个字符串是不是回文
	private boolean isPalindrome(String s) {
		int l=0,r=s.length()-1;
		while(l<r){
			if(s.charAt(l)!=s.charAt(r)) return false;
			l++;r--;
		}
		return true;
	}
	
}
