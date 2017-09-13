package com.leetcode.wangruns;

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
public class BestTimeToBuyAndSellStockiii030 {

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
	
}
