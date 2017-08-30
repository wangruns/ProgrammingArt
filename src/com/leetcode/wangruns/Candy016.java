package com.leetcode.wangruns;

//16、candy[动态规划]
/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * dp[i]表示i孩子得到的糖果数
 * 从左到右：
 * 如果当前孩子ratings比前一个孩子高，则该孩子的糖果为前一个孩子的糖果数+1,
 * 从右到左：
 * 如果当前孩子ratings比后面的一个孩子高而且该孩子的糖果数没有后面的孩子多，则该孩子的糖果为后面孩子的数目+1,
 * 最终全部加起来即可
 */
public class Candy016 {

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
	
}
