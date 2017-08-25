package com.leetcode.wangruns;

//4��sort-list[����]
/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * ������������򣬺���Ȼ�Ļ����뵽�鲢����. �ȶ�������ж��ֵĲ�֣����ϲ�. 
 * �����������飬���Ժ����׵��ҵ��м�λ�ã���Ϊһ�ֳ��õķ�ʽ�ǿ���ָ��. 
 * ������ָ���Զ�����ָ����ٶ�ǰ�У�����ָ�������ʱ����ָ����Ȼ���м�.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int v) {
		val = v;
		next = null;
	}
}

public class SortList_����_004 {

	public ListNode sortList(ListNode head) {
		// ����ǿ��������ֻ��һ���ڵ㣬��������
		if (head == null || head.next == null) return head;
		//���ÿ���ָ�����
		ListNode fast=head,low=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null){
			fast=fast.next.next;
			low=low.next;
		}
		ListNode middle=low.next;
		low.next=null;//�Ͽ�
		ListNode left=sortList(head);
		ListNode right=sortList(middle);
		//�ϲ�
		ListNode res=new ListNode(0);
		ListNode resRef=res;
		while(left!=null||right!=null){
			//���û����
			if(left==null){
				res.next=right;
				right=right.next;
				res=res.next;
			}
			//�ұ�û����
			else if(right==null){
				res.next=left;
				left=left.next;
				res=res.next;
			}
			//��߱Ƚ�С
			else if(left.val<right.val){
				res.next=left;
				left=left.next;
				res=res.next;
			}
			//�ұ߱Ƚ�С
			else{
				res.next=right;
				right=right.next;
				res=res.next;
			}
		}
		return resRef.next;//��ͷ��0�ڵ㲻��
	}

}
