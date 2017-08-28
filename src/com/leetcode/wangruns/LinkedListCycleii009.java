package com.leetcode.wangruns;

import java.util.HashSet;


//9、linked-list-cycle-ii[链表]
/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * 快慢指针找到他们在环中的相遇点，然后其中一个指针指向头，两指针重新一相同速度走,
 * 再次相遇的地方便是环入口，即环开始的地方. 
 * A - -  -  a-  -  -    -  B-   -     -   b -
 *                               -                      -
 *                                -   c-   -    -    -   -C
 * 如上面一个简单的图A为链表的起点，B为环开始的地方，C为快慢指针第一次相遇的地方
 * AB的长度=a
 * BC的长度=b
 * CB的长度为c
 * 环的长度为n
 * 假设快慢指针相遇之前，快指针已经在环里面转了k圈
 * 因为快指针的速度是慢指针速度的二倍，所有:
 * 2*慢指针走的路程=快指针走的路程
 * 2(a+b)=a+b+n*k
 * =>
 * a=n*k-b
 * =>
 * a=n*k-(n-c) => a=n(k-1) +c
 * =>	a=n*m +c
 * 这个表示：a的长度=环长度的m倍+c的长度
 * 那么在快慢指针第一次相遇的C点，让一个指针从头开始走，另一个就从C点继续走
 * 他们必然会在环的入口处，即B点相遇
 * 
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycleii009 {
	
	/**
	 * 不另外开辟空间
	 */
	public ListNode detectCycle(ListNode head) {
		if(head==null) return head;
		//找到相遇点
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			low=low.next;
			//有环则肯定会相遇
			if(fast==low){
				//慢指针指向头，快慢指针以相同的速度走，再次相遇便是环入口
				low=head;
				while(low!=fast){
					low=low.next;
					fast=fast.next;
				}
				return low;
			}
		}
		//无环
		return null;
	}
	
	
	/**
	 * 开辟新空间，题目会变得非常简单
	 */
	//detectCycleWithExtraSpace   1   利用set无重复元素的特征
	public ListNode detectCycleWithExtraSpace1(ListNode head) {
        if(head==null) return head;
        HashSet<ListNode> set=new HashSet<>();
        while(head!=null){
        	if(!set.add(head)) return head;
        	head=head.next;
        }
        return null;
    }
	
	//detectCycleWithExtraSpace   2    断链法，访问过的指向trap(会破坏掉原来的链表)
	public ListNode detectCycleWithExtraSpace2(ListNode head) {
		if(head==null) return head;
		ListNode trap=new ListNode(0);
		ListNode next=null;
		while(head!=null){
			next=head.next;
			if(next==trap) return head;
			head.next=trap;//断链指向陷阱trap
			head=next;
		}
		return null;
	}
	
}
