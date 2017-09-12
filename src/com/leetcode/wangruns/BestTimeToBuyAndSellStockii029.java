package com.leetcode.wangruns;

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
public class BestTimeToBuyAndSellStockii029 {
	
	public int maxProfitii(int[] prices) {
		if (prices.length == 0 || prices == null)
			return 0;
		int maxProfit = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			int todayPrice = prices[i];
			int tomorrowPrice = prices[i + 1];
			// 只要明天的价格比今天贵，就买入,明天卖出就可以赚钱差价利润
			if (todayPrice < tomorrowPrice) {
				maxProfit += tomorrowPrice - todayPrice;
			}
		}
		return maxProfit;
	}
	
}
