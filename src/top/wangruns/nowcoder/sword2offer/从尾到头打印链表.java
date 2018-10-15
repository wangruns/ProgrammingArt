package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 题目描述
输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 *
 */
public class 从尾到头打印链表 {
	
	//先翻转链表，然后在遍历
	public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
		ArrayList<Integer> res = new ArrayList<>();
		ListNode head=null;
		while(listNode!=null) {
			ListNode temp=listNode.next;
			listNode.next=head;
			head=listNode;
			listNode=temp;
		}
		while(head!=null) {
			res.add(head.val);
			head=head.next;
		}
		return res;
    }
	
	//直接遍历链表，然后使用Collections内置翻转函数
	public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
		ArrayList<Integer> res = new ArrayList<>();
		while(listNode!=null) {
			res.add(listNode.val);
			listNode=listNode.next;
		}
		Collections.reverse(res);
		return res;
    }
	
	//递归思想
	ArrayList<Integer> res = new ArrayList<>();
	public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
		if(listNode==null) return res;
		printListFromTailToHead3(listNode.next);
		res.add(listNode.val);
		return res;
    }
}
