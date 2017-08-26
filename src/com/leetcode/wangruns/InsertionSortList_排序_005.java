package com.leetcode.wangruns;

//5、insertion-sort-list[排序]
/**
 * Sort a linked list using insertion sort.
 * 
 * 要求使用插入排序，对于数组的插入排序，我们可能会比较熟悉.
 * 但是对于链表的插入排序，我们也可以参考着数组的思路来.
 * 即，外部循环为每次要插入的元素；
 * 内循环在已经排好的局部扫描，看在哪里插入.
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
public class InsertionSortList_排序_005 {

	public ListNode insertionSortList(ListNode head) {
		//为空或只有一个节点，则不用排序
		if(head==null||head.next==null) return head;
		ListNode res=new ListNode(Integer.MIN_VALUE);
		//对于每一个需要插入的元素
		while(head!=null){
			ListNode next=head.next;
			ListNode resRef=res;
			//遍历找到合适的插入位置
			while(resRef.next!=null&&head.val>resRef.next.val){
				resRef=resRef.next;
			}
			head.next=resRef.next;
			resRef.next=head;
			head=next;
		}
		return res.next;
	}
	
	//如果是数组的插入排序的话，似乎简单的多..
	public void insertionSortArray(int []a){
		//每一个需要插入的元素
		for(int i=1;i<a.length;i++){
			//找个合适的位置插入
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
