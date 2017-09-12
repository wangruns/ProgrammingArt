package com.leetcode.wangruns;

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
public class BestTimeToBuyAndSellStock028 {

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
	
}
