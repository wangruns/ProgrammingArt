package com.leetcode.wangruns;

import java.util.HashMap;

//3��max-points-on-a-line[ö��]
/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * ������һ������б����ͬ���ص㣬����б�ʼ���������Ҫ�����غϵĵ�ʹ�ֱ�����
 * (�������ÿһ��б���ϵĵ������ѡ������)
 * ��ѭ����ÿ���㶼��Ϊ��ʼ��; ��ѭ�����ɸõ�������ĵ����ߣ���map<б�ʣ�����>������
 * ��a,b,c��һ��������б�ʴ��ڣ���aΪ��㣬��ȻK(a->b)=K(a->c)�����a��һ��ֱ���ϵ�
 * �������㣬��map.get(a)��ֵΪ2��Ҳ����˵�ڸ�б�ʵ�����£�һ����3��������������
 * ���������ظ��ĵ㣬��б�ʲ����ڵ������������΢����Ĵ���һ�±��.
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

public class MaxPointsOnALine_ö��_003 {

	public int maxPoints(Point[] points) {
		int res = 0;
		// ÿһ���㶼��Ϊ��ʼ��
		for (int i = 0; i < points.length; i++) {
			int tempRes = 0;
			int duplicate = 0;// ��¼�ظ���
			int vcnt = 0;// ��¼б�ʲ����ڵģ�����ֱ��
			HashMap<Double, Integer> map = new HashMap<>();// ��¼б�ʴ��ڵģ�б��ΪKey������ΪValue
			// ����ʼ��������ĵ����ߣ�����һ��
			for (int j = i + 1; j < points.length; j++) {
				double deltaX = points[i].x - points[j].x;
				double deltaY = points[i].y - points[j].y;
				// �ظ�������
				if (deltaX == 0 && deltaY == 0)
					duplicate++;
				// ��ֱ��б�ʲ����ڵ����
				else if (deltaX == 0)
					vcnt++;
				// б�ʴ��ڵ����
				else {
					double k = deltaY / deltaX;
					if (k == 0)
						k = 0;// ���0��-0�ǲ�һ����key
					map.put(k, map.get(k) == null ? 1 : map.get(k) + 1);
					tempRes = Math.max(tempRes, map.get(k));// ��¼��ʱ����
				}
			}
			// max(��б�ʵ�,��ֱ��)+�������+�ͱ�������ظ��ĵ�
			res = Math.max(res, Math.max(vcnt, tempRes) + duplicate + 1);
		}
		return res;
	}
}
