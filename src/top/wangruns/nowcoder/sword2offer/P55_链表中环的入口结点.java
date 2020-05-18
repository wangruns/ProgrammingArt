package top.wangruns.nowcoder.sword2offer;

import java.util.HashSet;

/**
 * 
 * 题目描述
给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 */
public class P55_链表中环的入口结点 {
	
	//借助一个集合辅助空间
	public ListNode EntryNodeOfLoop1(ListNode pHead) {
		HashSet<ListNode> set=new HashSet<>();
		while(pHead!=null) {
			if(!set.add(pHead)) return pHead;
			else pHead=pHead.next;
		}
		return null;
	}
	
	//快慢指针
	/**
	 * 不妨设A为起点，B为环的入口，C为快慢指针第一次相遇的点
	 * AB的距离为a,BC的距离为b,CB的距离为c，圆环的长度为l
	 * 因为快指针的速度是慢指针的两倍，所以 快指针的路程/(2*慢指针的速度)=慢指针的路程/慢指针的速度
	 * 所以有 快指针的路程=2*慢指针的路程
	 * 所以有 a+m*l+b=2(a+n*l+b)
	 * 所以有 a+m*l+b=2a+2nl+2b
	 * 所以有 a=(m-2n)l-b
	 * 所以有 a=(m-2n)l-b+c-c
	 * 所以有 a=(m-2n-1)l+c 即a的长度为几个圈的长度加上相遇点到环入口的长度
	 * 所以这个时候可以让两个速度相同的指针分别从起点A，和相遇点C同时开始走
	 * 这样，当一个点停在B点时，另一个点也必定停在B点。
	 * https://www.nowcoder.com/questionTerminal/253d2c59ec3e4bc68da16833f79a38e4
	 */
	public ListNode EntryNodeOfLoop2(ListNode pHead) {
		ListNode slowP=pHead,fastP=pHead;
		while(fastP!=null&&fastP.next!=null&&fastP.next.next!=null) {
			slowP=slowP.next;
			fastP=fastP.next.next;
			if(slowP==fastP) break;
		}
		if(fastP==null||fastP.next==null||fastP.next.next==null) return null;//无环
		slowP=pHead;
		while(slowP!=fastP) {
			slowP=slowP.next;
			fastP=fastP.next;
		}
		return slowP;
	}


}
