package top.wangruns.nowcoder.leetcode;

import java.util.HashMap;

/**
 * 
 * 题目描述
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line. 
输入[(0,0),(0,1)]			输出2
输入[(2,3),(3,3),(-5,3)]		输出2

分析
利用在同一条直线上的点的斜率相同的性质进行判定
而在平面内的直线可以有无数条，所以可以分别枚举过每个点的直线
即外层循环为过该点的直线，内部循环为以该点为起点到其他点的两点连线
这里需要特别注意的是，可能存在斜率不存在的情况，即垂直，或者存在和当前点一样的重复点。
重复点虽然无法连线，但是仍然是满足在同一个直线上的条件的。所以这两个特殊情况需要稍微处理一下即可。
 *
 */
class Point {
    int x;
    int y;
}
 
public class P3_MaxPointsOnALine {
	
	public int maxPoints (Point[] points) {
		int max=0;
		//外循环，每个点都作为起始点，统计过该点的直线
		for(int i=0;i<points.length;i++) {
			int tempMax=0;//记录过当前起点的直线中的最大值
			int duplicate=0;//记录和当前起点重复的
			int vcnt=0;//记录和当前起点垂直的即斜率不存在的
			//记录两点连线斜率存在的，斜率为key,出现的次数为value
			//这里的斜率用分数来表示，x分母，y分子。如果用double浮点的话，可能出现精度丢失导致误判
			HashMap<String, Integer> map=new HashMap<>();
			for(int j=i+1;j<points.length;j++) {
				int deltaX=points[i].x-points[j].x;
				int deltaY=points[i].y-points[j].y;
				if(deltaX==0&&deltaY==0) duplicate++;//重复点
				else if(deltaX==0) vcnt++;//斜率不存在垂直
				else {
					//获取deltaY / deltaX的最简分数作为斜率保存
					int gcdValuve=gcd(deltaX,deltaY);
					//分子分母同时除以最大公约数
					String key=deltaY/gcdValuve + "*" + deltaX/gcdValuve;
					map.put(key,map.get(key)==null?1:map.get(key)+1);
					tempMax=Math.max(tempMax, map.get(key));
				}
			}
			//max(有斜率的,垂直的)+本身起点+和本身起点重复的点
			max=Math.max(max, Math.max(tempMax, vcnt)+duplicate+1);
		}
		return max;
	}

	private int gcd(int a, int b) {
		if(b==0) return a;
		else return gcd(b, a%b);
	}

}
