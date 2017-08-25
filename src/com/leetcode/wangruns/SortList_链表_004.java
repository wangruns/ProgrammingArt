package com.leetcode.wangruns;

//4、sort-list[链表]
/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * 对于链表的排序，很自然的会联想到归并排序. 先对链表进行二分的拆分，最后合并. 
 * 而链表不像数组，可以很容易的找到中间位置，因为一种常用的方式是快慢指针. 
 * 即，快指针以二倍慢指针的速度前行，当快指针跑完的时候，慢指针自然在中间.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int v) {
		val = v;
		next = null;
	}
}

public class SortList_链表_004 {

	public ListNode sortList(ListNode head) {
		// 如果是空链表或者只有一个节点，不用排序
		if (head == null || head.next == null) return head;
		//利用快慢指针二分
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;//断开
		ListNode left=sortList(head);
		ListNode right=sortList(middle);
		//合并
		ListNode res=new ListNode(0);
		ListNode resRef=res;
		while(left!=null||right!=null){
			//左边没有了
			if(left==null){
				res.next=right;
				right=right.next;
				res=res.next;
			}
			//右边没有了
			else if(right==null){
				res.next=left;
				left=left.next;
				res=res.next;
			}
			//左边比较小
			else if(left.val<right.val){
				res.next=left;
				left=left.next;
				res=res.next;
			}
			//右边比较小
			else{
				res.next=right;
				right=right.next;
				res=res.next;
			}
		}
		return resRef.next;//开头的0节点不算
	}

}
