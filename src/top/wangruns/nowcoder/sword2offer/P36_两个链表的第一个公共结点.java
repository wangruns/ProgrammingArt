package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入两个链表，找出它们的第一个公共结点。
 *
 * 分析
两个链表从第一个公共节点之后就是重合的了，所以要是知道两个链表的长度差，即可
 */
public class P36_两个链表的第一个公共结点 {
	
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		ListNode p1=pHead1,p2=pHead1;
		while(p1!=p2) {
			p1=p1==null?pHead2:p1.next;
			p2=p2==null?pHead1:p2.next;
		}
		return p1;
    }

}
