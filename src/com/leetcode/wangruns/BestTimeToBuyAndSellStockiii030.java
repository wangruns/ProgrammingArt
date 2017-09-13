package com.leetcode.wangruns;

//30��best-time-to-buy-and-sell-stock-iii[����]
/**
 * Say you have an array for which the i^(th) element is the price of a given stock on day i
 * Design an algorithm to find the maximum profit. 
 * You may complete at most two transactions. 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again)
 *
 * �����ʼ������ֻ��0Ԫ
 * hold1��ʾ���˵�һ֧׬��Ǯ���ǻ�Ǯ������-todayPrice
 * release1��ʾ����һ֧����׬��Ǯ������hold1+todayPrice
 * 
 * [����ڶ�֧�����ڵ�һ֧������Ļ��������]
 * 
 * hold2��ʾ���˵ڶ�֧׬��Ǯ���ڶ�֧�����˵�һ֧����׬��Ǯȥ��ģ�����release1-todayPrice
 * release2��ʾ���ڶ�֧����׬��Ǯ������hold2+todayPrice
 */
public class BestTimeToBuyAndSellStockiii030 {

	public int maxProfitiii(int[] prices) {
		int hold1=0x8fffffff,release1=0;
		int hold2=0x8fffffff,release2=0;
		for(int todayPrice:prices) {
			hold1=Math.max(hold1,-todayPrice);//������������һ֧
			release1=Math.max(release1, hold1+todayPrice);//������콫��һ֧����
			hold2=Math.max(hold2, release1-todayPrice);//�����������ڶ�֧��releas1Ϊ��һ֧׬��Ǯ
			release2=Math.max(release2, hold2+todayPrice);//������콫�ڶ�֧����
			}
		return release2;
    }
	
}
