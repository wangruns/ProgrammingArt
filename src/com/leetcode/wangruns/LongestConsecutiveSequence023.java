package com.leetcode.wangruns;

import java.util.HashSet;

//23、longest-consecutive-sequence[数组]
/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4]. Return its length:4.
 * Your algorithm should run in O(n) complexity.
 * 
 * 由于需要O(n)的时间复杂度，那么肯定不可能用nlog(n)排序
 * 联想到hash.
 * 哈希表搜是O(1),因为每个数字只会添加一次
 * 首先将数组元素存放到hashSet中
 * 然后遍历数组：
 * 如果v在hashSet中，tempMaxLen++，并判定其左右有没有连续的元素在hashSet中，同样+1
 * 对于每一次v，如果临时最大值比最大值大了，则更新最大值
 */
public class LongestConsecutiveSequence023 {

	public int longestConsecutive(int[] num) {
		HashSet<Integer> hashSet = new HashSet<>();
		// 将数组元素放到hashSet
		for (int v : num)
			hashSet.add(v);
		int maxLen = 0;
		// 遍历数组
		for (int v : num) {
			int tempMaxLen = 0;
			// 判定hashSet中是否包含当前元素及其左右相连续的元素，更新最大值，删除已经遍历过的
			if (hashSet.contains(v)) {
				tempMaxLen++;
				hashSet.remove(v);
				// 处理左边和右边的连续的元素
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
				// 更新最大值
				if (maxLen < tempMaxLen)
					maxLen = tempMaxLen;
			}
		}
		return maxLen;
	}
	
}
