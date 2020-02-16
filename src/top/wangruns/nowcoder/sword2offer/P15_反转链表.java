package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个链表，反转链表后，输出新链表的表头。
 *
 */
public class P15_反转链表 {

	public ListNode ReverseList(ListNode head) {
		ListNode pre=null;
		while(head!=null) {
			ListNode temp=head.next;
			head.next=pre;
			pre=head;
			head=temp;
		}
		return pre;
    }
	
}
