package top.wangruns.nowcoder.leetcode;

/**
 * 
 * 题目描述
Sort a linked list using insertion sort. 
输入{3,2,4}	输出{2,3,4}

分析
要求使用插入排序，对于数组的插入排序，我们可能会比较熟悉.
但是对于链表的插入排序，我们也可以参考着数组的思路来.
即，外部循环为每次要插入的元素；
内循环在已经排好的局部扫描，看在哪里插入.
 *
 */
public class P5_InsertionSortList {
	
	//链表的插入排序
	public ListNode insertionSortList (ListNode head) {
		if(head==null||head.next==null) return head;
		ListNode res=new ListNode(0x80000000);
		//对于每一个需要插入的元素
		while(head!=null) {
			ListNode next=head.next;
			ListNode resRef=res;
			//遍历找到合适的插入位置
			while(resRef.next!=null&&resRef.next.val<head.val) {
				resRef=resRef.next;
			}
			head.next=resRef.next;
			resRef.next=head;
			head=next;
		}
		return res.next;
		
	}
	
	//数组的插入排序
	public void insertionSortArray(int []a){
		for(int i=1;i<a.length;i++) {
			for(int j=i;j>0;j--) {
				if(a[j]<a[j-1]) {
					int temp=a[j];
					a[j]=a[j-1];
					a[j-1]=temp;
				}
			}
		}
	}

}
