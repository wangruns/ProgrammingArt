package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 
例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 */
public class 删除链表中重复的结点 {
	
	public ListNode deleteDuplication(ListNode pHead) {
		ListNode cur=pHead,pre=pHead;
		while(cur!=null&&cur.next!=null) {
			if(cur.val==cur.next.val) {
				ListNode newCur=cur;
				//找出连续相同的元素
				while(newCur!=null&&newCur.val==cur.val) newCur=newCur.next;
				//如果连续相同的元素是从表头开始，则需要更新表头
				if(cur.val==pHead.val) {
					pHead=newCur;
					pre=pHead;
				}else pre.next=newCur;//相同的元素去掉
				cur=newCur;
			}else {
				pre=cur;
				cur=cur.next;
			}
		}
		return pHead;
	}

}
