package com.leetcode.wangruns;

//20��palindrome-partitioning-ii[��̬�滮]
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s ="aab", 
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * a��using 0 cut
 * aa��using 0 cut
 * aab��using 1 cut
 * aabb��using 1 cut
 * aabbh��using 2 cut
 * ...
 * ���Ե�ǰ״̬��֮ǰ��״̬�Ĺ�ϵ�����뵽��̬�滮
 * dp[i]��ʾ0���±�i����ַ�����С�ָ�������dp[s.length()-1]������ѽ�
 * ��ôdp[i]��dp[i-1]��ʲô��ϵ�أ�
 * ��s="aab"
 * dp[0]=0
 * dp[1]=0
 * dp[2]=1
 * ���Է��ֶ���dp[i]��˵
 * ���s[0:i]�ǻ��ģ���ôdp[i]=0
 * ���s[0:i]���ǻ��ģ�
 * 							1<=j<=i
 * 							���s[j:i]�ǻ��ģ���ôdp[i]=dp[j-1]+1
 * 						    ���s[j:i]���ǻ��ģ���ôdp[i]=dp[j-1]+i-j+1
 */
public class PalindromePartitioningii020 {

	public int minCut(String s) {
		int len=s.length();
		int dp[]=new int[len];
		//����ÿһ������a,aa,aab�õ���Ӧ��dp[i]
		for(int i=0;i<len;i++){
			String curStr=s.substring(0, i+1);
			//�����ǰ���ַ����ǻ��ģ���dp[i]ֱ�Ӹ�ֵ0
			if(isPalindrome(curStr)) dp[i]=0;
			else{
				//���i�ηָ�(��ʼ�������ı��ڱȽϵõ���С��)
				dp[i]=i;
				//�����ǰ�ַ������ǻ��ĵĻ�������Ҫ�ۿ�֮ǰ�ļ�¼��ȷ����ǰ�ַ�������С�ָ���
				for(int j=1;j<=i;j++){
					if(isPalindrome(s.substring(j,i+1))){
						//�������1<=k<=i��ʹ��s[k:i]�ǻ��ģ���ôdp[i]=d[k-1]+1
						dp[i]=Math.min(dp[i], dp[j-1]+1);
					}else{
						//��������ڣ���ôdp[i]=dp[k-1]+i-k+1;�� acd -> a|c|d
						dp[i]=Math.min(dp[i], dp[j-1]+i-j+1);
					}
				}
			}
		}
		return dp[len-1];
	}

	//�ж�һ���ַ����ǲ��ǻ���
	private boolean isPalindrome(String s) {
		int l=0,r=s.length()-1;
		while(l<r){
			if(s.charAt(l)!=s.charAt(r)) return false;
			l++;r--;
		}
		return true;
	}
	
}
