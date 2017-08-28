package com.leetcode.wangruns;

import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.ListNode;

//8、reorder-list[链表]
/**
Given a singly linked list L: L 0→L 1→…→L n-1→L n,
reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…
You must do this in-place without altering the nodes' values.
For example,
Given{1,2,3,4}, reorder it to{1,4,2,3}.
*
*找到链表中点，快慢指针. {1,2} 和 {3,4}
*将链表的后半段反转. {1,2} 和 {4,3}
*合并. {1,4,2,3}
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
public class ReorderList_链表_008 {
	
	/**
	 * 不借助辅助空间
	 * 
	 * 找到链表中点，快慢指针. {1,2} 和 {3,4}
	 * 将链表的后半段反转. {1,2} 和 {4,3}
	 * 合并. {1,4,2,3}
	 */
	public void reorderList(ListNode head) {
		if(head==null) return;
		//找到中点断开
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;
		//反转后半链表
		ListNode pre=null,next=null;
		while(middle!=null){
			next=middle.next;
			middle.next=pre;
			pre=middle;
			middle=next;
		}
		//合并前后两个子链表
		middle=pre;
		ListNode headNext=null,middleNext=null;
		while(head!=null&&middle!=null){
			headNext=head.next;
			middleNext=middle.next;
			middle.next=head.next;
			head.next=middle;
			middle=middleNext;
			head=headNext;
		}
	}
	
	
	/**
	 * 借助一个链表LinkedList的辅助空间.
	 * 
	 * 遍历一次将所有节点放在链表LinkedList中,
	 * 而根据LinkedList的特性，每次可以直接取第一个或者最后一个
	 */
	public void reorderListWithHelperSpace(ListNode head) {
		if(head==null) return;
		//存入链表
        LinkedList<ListNode> nodeList=new LinkedList<ListNode>();
        ListNode res=head;
        while(res!=null){
        	nodeList.add(res);
        	res=res.next;
        }
        //直接取出
        res=nodeList.removeFirst();
        boolean isFirst=false;
        ListNode node=null;
        while(!nodeList.isEmpty()){
        	if(isFirst) node=nodeList.removeFirst();
        	else node=nodeList.removeLast();
        	isFirst=!isFirst;
        	node.next=null;//断开
        	res.next=node;
        	res=res.next;
        }
    }

}
