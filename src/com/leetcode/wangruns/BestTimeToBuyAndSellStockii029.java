package com.leetcode.wangruns;

//29��best-time-to-buy-and-sell-stock-ii[����]
/**
 * Say you have an array for which the i^(th) element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. 
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * 
 * ������֪��ֻҪ����ļ۸�Ƚ������ô�Ϳ��Խ������룬�����������϶�׬Ǯ��
 * ��8,10,3,100,50,6
 *   ����۸�		����۸�		�Ƿ�����
 * 1��8			10			��(���������Ϳ���׬���2)
 * 2��10			3			����
 * 3��3			100			��(���������Ϳ���׬���97)
 * 4��100		50			����
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
			// ֻҪ����ļ۸�Ƚ���󣬾�����,���������Ϳ���׬Ǯ�������
			if (todayPrice < tomorrowPrice) {
				maxProfit += tomorrowPrice - todayPrice;
			}
		}
		return maxProfit;
	}
	
}
