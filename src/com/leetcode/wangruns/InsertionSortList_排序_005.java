package com.leetcode.wangruns;

//5��insertion-sort-list[����]
/**
 * Sort a linked list using insertion sort.
 * 
 * Ҫ��ʹ�ò������򣬶�������Ĳ����������ǿ��ܻ�Ƚ���Ϥ.
 * ���Ƕ�������Ĳ�����������Ҳ���Բο��������˼·��.
 * �����ⲿѭ��Ϊÿ��Ҫ�����Ԫ�أ�
 * ��ѭ�����Ѿ��źõľֲ�ɨ�裬�����������.
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
public class InsertionSortList_����_005 {

	public ListNode insertionSortList(ListNode head) {
		//Ϊ�ջ�ֻ��һ���ڵ㣬��������
		if(head==null||head.next==null) return head;
		ListNode res=new ListNode(Integer.MIN_VALUE);
		//����ÿһ����Ҫ�����Ԫ��
		while(head!=null){
			ListNode next=head.next;
			ListNode resRef=res;
			//�����ҵ����ʵĲ���λ��
			while(resRef.next!=null&&head.val>resRef.next.val){
				resRef=resRef.next;
			}
			head.next=resRef.next;
			resRef.next=head;
			head=next;
		}
		return res.next;
	}
	
	//���������Ĳ�������Ļ����ƺ��򵥵Ķ�..
	public void insertionSortArray(int []a){
		//ÿһ����Ҫ�����Ԫ��
		for(int i=1;i<a.length;i++){
			//�Ҹ����ʵ�λ�ò���
			for(int j=i;j>0;j--){
				if(a[j]<a[j-1]){
					//swap
					int t=a[j];
					a[j]=a[j-1];
					a[j-1]=t;
				}
			}
		}
	}
	
}
