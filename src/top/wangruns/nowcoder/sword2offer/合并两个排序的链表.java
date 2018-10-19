package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 */
public class 合并两个排序的链表 {
	
	//常规循环合并法
	public ListNode Merge1(ListNode list1,ListNode list2) {
        ListNode cur=new ListNode(-1);
        ListNode mergeNode=cur;
        while(list1!=null || list2!=null) {
        	if(list1==null) {
        		cur.next=list2;
        		break;
        	}
        	else if(list2==null) {
        		cur.next=list1;
        		break;
        	}
        	else if(list1.val<=list2.val) {
        		cur.next=list1;
        		cur=list1;
        		list1=list1.next;
        	}else {
        		cur.next=list2;
        		cur=list2;
        		list2=list2.next;
        	}
        }
        return mergeNode.next;
    }
	
	//递归合并法
	public ListNode Merge2(ListNode list1,ListNode list2) {
		if(list1==null) return list2;
		if(list2==null) return list1;
		ListNode mergeNode=null;
		if(list1.val<=list2.val) {
			mergeNode=list1;
			mergeNode.next=Merge2(list1.next,list2);
		}else {
			mergeNode=list2;
			mergeNode.next=Merge2(list1,list2.next);
		}
		return mergeNode;
	}

}
