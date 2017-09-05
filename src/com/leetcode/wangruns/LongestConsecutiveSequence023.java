package com.leetcode.wangruns;

import java.util.HashSet;

//23��longest-consecutive-sequence[����]
/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4]. Return its length:4.
 * Your algorithm should run in O(n) complexity.
 * 
 * ������ҪO(n)��ʱ�临�Ӷȣ���ô�϶���������nlog(n)����
 * ���뵽hash.
 * ��ϣ������O(1),��Ϊÿ������ֻ�����һ��
 * ���Ƚ�����Ԫ�ش�ŵ�hashSet��
 * Ȼ��������飺
 * ���v��hashSet�У�tempMaxLen++�����ж���������û��������Ԫ����hashSet�У�ͬ��+1
 * ����ÿһ��v�������ʱ���ֵ�����ֵ���ˣ���������ֵ
 */
public class LongestConsecutiveSequence023 {

	public int longestConsecutive(int[] num) {
		HashSet<Integer> hashSet = new HashSet<>();
		// ������Ԫ�طŵ�hashSet
		for (int v : num)
			hashSet.add(v);
		int maxLen = 0;
		// ��������
		for (int v : num) {
			int tempMaxLen = 0;
			// �ж�hashSet���Ƿ������ǰԪ�ؼ���������������Ԫ�أ��������ֵ��ɾ���Ѿ���������
			if (hashSet.contains(v)) {
				tempMaxLen++;
				hashSet.remove(v);
				// ������ߺ��ұߵ�������Ԫ��
				int leftV = v - 1, rightV = v + 1;
				while (hashSet.contains(leftV)) {
					tempMaxLen++;
					hashSet.remove(leftV);
					leftV--;
				}
				while (hashSet.contains(rightV)) {
					tempMaxLen++;
					hashSet.remove(rightV);
					rightV++;
				}
				// �������ֵ
				if (maxLen < tempMaxLen)
					maxLen = tempMaxLen;
			}
		}
		return maxLen;
	}
	
}
