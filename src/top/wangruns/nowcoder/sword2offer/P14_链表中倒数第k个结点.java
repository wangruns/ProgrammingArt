package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个链表，输出该链表中倒数第k个结点。
 *
 * 分析
第一个结点先跑k，然后第二个结点再跑，这样当第一个结点到达终点的时候，
第二个结点刚好跑到倒数第k个节点。
 */
public class P14_链表中倒数第k个结点 {
	
	public ListNode FindKthToTail(ListNode head,int k) {
		ListNode h1=head,h2=head;
		int cnt=0;
		while(h1!=null) {
			h1=h1.next;
			cnt++;
			if(cnt>k) h2=h2.next;
		}
		return k<=cnt?h2:null;
    }
	
}
