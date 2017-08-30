package com.leetcode.wangruns;

//16��candy[��̬�滮]
/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * dp[i]��ʾi���ӵõ����ǹ���
 * �����ң�
 * �����ǰ����ratings��ǰһ�����Ӹߣ���ú��ӵ��ǹ�Ϊǰһ�����ӵ��ǹ���+1,
 * ���ҵ���
 * �����ǰ����ratings�Ⱥ����һ�����Ӹ߶��Ҹú��ӵ��ǹ���û�к���ĺ��Ӷ࣬��ú��ӵ��ǹ�Ϊ���溢�ӵ���Ŀ+1,
 * ����ȫ������������
 */
public class Candy016 {

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
	
}
