package com.leetcode.wangruns;

//28��best-time-to-buy-and-sell-stock[����]
/**
 * Say you have an array for which the i^(th) element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction(ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit. 
 * 
 * ������ķ�ʽ����¼��Сֵ��ÿ�θ���
 * ��ǰ�������Ϊ��ǰ�۸�-��¼����Сֵ
 * �Ƚϵ�ǰ���󣬲������������
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
