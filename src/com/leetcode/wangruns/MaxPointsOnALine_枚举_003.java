package com.leetcode.wangruns;

import java.util.HashMap;

//3、max-points-on-a-line[枚举]
/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * 利用在一条线上斜率相同的特点，按照斜率计数，但需要考虑重合的点和垂直的情况
 * (即，求出每一种斜率上的点个数，选择最多的)
 * 外循环，每个点都作为起始点; 内循环，由该点向其余的点连线，用map<斜率，点数>来计数
 * 如a,b,c在一条线上且斜率存在，以a为起点，显然K(a->b)=K(a->c)，则和a在一条直线上的
 * 有两个点，即map.get(a)的值为2，也就是说在该斜率的情况下，一共有3个点在这条线上
 * 而对于有重复的点，和斜率不存在的情况，我们稍微特殊的处理一下便可.
 */
/**
 * Definition for a point. class Point { int x; int y; Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; } }
 */
class Point {
	int x;
	int y;

	Point() {
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class MaxPointsOnALine_枚举_003 {

	public int maxPoints(Point[] points) {
		int res = 0;
		// 每一个点都作为起始点
		for (int i = 0; i < points.length; i++) {
			int tempRes = 0;
			int duplicate = 0;// 记录重复的
			int vcnt = 0;// 记录斜率不存在的，即垂直的
			HashMap<Double, Integer> map = new HashMap<>();// 记录斜率存在的，斜率为Key，点数为Value
			// 由起始点向其余的点连线，两点一线
			for (int j = i + 1; j < points.length; j++) {
				double deltaX = points[i].x - points[j].x;
				double deltaY = points[i].y - points[j].y;
				// 重复点的情况
				if (deltaX == 0 && deltaY == 0)
					duplicate++;
				// 垂直点斜率不存在的情况
				else if (deltaX == 0)
					vcnt++;
				// 斜率存在的情况
				else {
					double k = deltaY / deltaX;
					if (k == 0)
						k = 0;// 解决0和-0是不一样的key
					map.put(k, map.get(k) == null ? 1 : map.get(k) + 1);
					tempRes = Math.max(tempRes, map.get(k));// 记录临时最大的
				}
			}
			// max(有斜率的,垂直的)+本身起点+和本身起点重复的点
			res = Math.max(res, Math.max(vcnt, tempRes) + duplicate + 1);
		}
		return res;
	}
}
