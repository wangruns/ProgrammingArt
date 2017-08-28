package com.leetcode.wangruns;

import com.leetcode.wangruns.Leetcode.ListNode;

//10��linked-list-cycle[����]
/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * ����ָ���������ʾ�л�
 */
public class LinkedListCycle010 {

	public boolean hasCycle(ListNode head) {
        if(head==null) return false;
        ListNode fast=head,low=head;
        while(fast!=null&&fast.next!=null){
        	fast=fast.next.next;
        	low=low.next;
        	if(fast==low) return true;
        }
        return false;
    }
	
}
