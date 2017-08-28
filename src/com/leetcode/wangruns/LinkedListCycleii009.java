package com.leetcode.wangruns;

import java.util.HashSet;


//9��linked-list-cycle-ii[����]
/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * ����ָ���ҵ������ڻ��е������㣬Ȼ������һ��ָ��ָ��ͷ����ָ������һ��ͬ�ٶ���,
 * �ٴ������ĵط����ǻ���ڣ�������ʼ�ĵط�. 
 * A - -  -  a-  -  -    -  B-   -     -   b -
 *                               -                      -
 *                                -   c-   -    -    -   -C
 * ������һ���򵥵�ͼAΪ�������㣬BΪ����ʼ�ĵط���CΪ����ָ���һ�������ĵط�
 * AB�ĳ���=a
 * BC�ĳ���=b
 * CB�ĳ���Ϊc
 * ���ĳ���Ϊn
 * �������ָ������֮ǰ����ָ���Ѿ��ڻ�����ת��kȦ
 * ��Ϊ��ָ����ٶ�����ָ���ٶȵĶ���������:
 * 2*��ָ���ߵ�·��=��ָ���ߵ�·��
 * 2(a+b)=a+b+n*k
 * =>
 * a=n*k-b
 * =>
 * a=n*k-(n-c) => a=n(k-1) +c
 * =>	a=n*m +c
 * �����ʾ��a�ĳ���=�����ȵ�m��+c�ĳ���
 * ��ô�ڿ���ָ���һ��������C�㣬��һ��ָ���ͷ��ʼ�ߣ���һ���ʹ�C�������
 * ���Ǳ�Ȼ���ڻ�����ڴ�����B������
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
	 * �����⿪�ٿռ�
	 */
	public ListNode detectCycle(ListNode head) {
		if(head==null) return head;
		//�ҵ�������
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			low=low.next;
			//�л���϶�������
			if(fast==low){
				//��ָ��ָ��ͷ������ָ������ͬ���ٶ��ߣ��ٴ��������ǻ����
				low=head;
				while(low!=fast){
					low=low.next;
					fast=fast.next;
				}
				return low;
			}
		}
		//�޻�
		return null;
	}
	
	
	/**
	 * �����¿ռ䣬��Ŀ���÷ǳ���
	 */
	//detectCycleWithExtraSpace   1   ����set���ظ�Ԫ�ص�����
	public ListNode detectCycleWithExtraSpace1(ListNode head) {
        if(head==null) return head;
        HashSet<ListNode> set=new HashSet<>();
        while(head!=null){
        	if(!set.add(head)) return head;
        	head=head.next;
        }
        return null;
    }
	
	//detectCycleWithExtraSpace   2    �����������ʹ���ָ��trap(���ƻ���ԭ��������)
	public ListNode detectCycleWithExtraSpace2(ListNode head) {
		if(head==null) return head;
		ListNode trap=new ListNode(0);
		ListNode next=null;
		while(head!=null){
			next=head.next;
			if(next==trap) return head;
			head.next=trap;//����ָ������trap
			head=next;
		}
		return null;
	}
	
}
