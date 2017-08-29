package com.leetcode.wangruns;

import java.util.Set;

//11��word-break[��̬�滮]
/**
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given s ="leetcode", dict =["leet", "code"].
 * Return true because"leetcode"can be segmented as"leet code".
 * 
 * ��ǰ�ַ������Ա��ָ��ǰ���ǣ����ַ���֮ǰ���ַ������Ա��ָ���������ַ������ֵ���.
 * 
 * ��leetcode֮ǰ���ַ���leet���Ա��ָ�������������ַ���code���ֵ���.
 * dp[i]��ʾs[0-i]�Ƿ���Ա��и�
 * dp[i]Ϊtrue ���ҽ��� ����0<=K<=i, ʹ��dp[k]==true���Ӵ�s[k,i]���ֵ���
 * dp[0]����λĬ�ϵ���true
 * ��s="leet"��ʱ�� 
 *							 |leet
 * ��i=4, ��dp[0]==true,����s[0,4)��"leet"��dict�ֵ��У�����dp[4]=true;
 * s="leetcode"��ʱ��
 * 							leet|code
 * ��i=8, ��dp[4]==true, ����s[4,8)��"code"���ֵ��У�����dp[8]=true;
 */
public class WordBreak012 {
	
	public boolean wordBreak(String s, Set<String> dict) {
		int len=s.length()+1;
		boolean dp[]=new boolean[len];
		dp[0]=true;//��ʼ����λ
		//���ȴ�1�����ӵ��ַ���s
		for(int i=1;i<len;i++){
			//����ÿ�����ȵ��ַ�s�ж����Ƿ���Էָ�
			for(int j=0;j<i;j++){
				if(dp[j]&&dict.contains(s.substring(j, i))){
					dp[i]=true;//s[0,i]�ǿ��Էָ��
					break;
				}
			}
		}
		return dp[s.length()];
	}

}
