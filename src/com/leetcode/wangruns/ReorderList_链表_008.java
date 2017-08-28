package com.leetcode.wangruns;

import java.util.LinkedList;

import com.leetcode.wangruns.Leetcode.ListNode;

//8��reorder-list[����]
/**
Given a singly linked list L: L 0��L 1������L n-1��L n,
reorder it to: L 0��L n ��L 1��L n-1��L 2��L n-2����
You must do this in-place without altering the nodes' values.
For example,
Given{1,2,3,4}, reorder it to{1,4,2,3}.
*
*�ҵ������е㣬����ָ��. {1,2} �� {3,4}
*������ĺ��η�ת. {1,2} �� {4,3}
*�ϲ�. {1,4,2,3}
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
public class ReorderList_����_008 {
	
	/**
	 * �����������ռ�
	 * 
	 * �ҵ������е㣬����ָ��. {1,2} �� {3,4}
	 * ������ĺ��η�ת. {1,2} �� {4,3}
	 * �ϲ�. {1,4,2,3}
	 */
	public void reorderList(ListNode head) {
		if(head==null) return;
		//�ҵ��е�Ͽ�
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;
		//��ת�������
		ListNode pre=null,next=null;
		while(middle!=null){
			next=middle.next;
			middle.next=pre;
			pre=middle;
			middle=next;
		}
		//�ϲ�ǰ������������
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
	 * ����һ������LinkedList�ĸ����ռ�.
	 * 
	 * ����һ�ν����нڵ��������LinkedList��,
	 * ������LinkedList�����ԣ�ÿ�ο���ֱ��ȡ��һ���������һ��
	 */
	public void reorderListWithHelperSpace(ListNode head) {
		if(head==null) return;
		//��������
        LinkedList<ListNode> nodeList=new LinkedList<ListNode>();
        ListNode res=head;
        while(res!=null){
        	nodeList.add(res);
        	res=res.next;
        }
        //ֱ��ȡ��
        res=nodeList.removeFirst();
        boolean isFirst=false;
        ListNode node=null;
        while(!nodeList.isEmpty()){
        	if(isFirst) node=nodeList.removeFirst();
        	else node=nodeList.removeLast();
        	isFirst=!isFirst;
        	node.next=null;//�Ͽ�
        	res.next=node;
        	res=res.next;
        }
    }

}
