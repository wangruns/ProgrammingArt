package top.wangruns.nowcoder.leetcode;

/**
 * 
 * 题目描述
Sort a linked list in O(n log n) time using constant space complexity.
输入{3,2,4}	输出{2,3,4}

分析
就是一个链表的单纯排序问题，很容易联系到链表的归并排序。
先对链表进行二分拆分，最后合并。链表不向数组，可以很容易
的找到中间位置，一种常用的方式是快慢指针。
即快指针以二倍慢指针速度前行，当快指针结束时，慢指针自然停在中间
 *
 */
public class P4_SortList {
	
	//链表的归并排序
	public ListNode sortList (ListNode head) {
		if(head==null||head.next==null) return head;
		ListNode fast=head,slow=head;
		while(fast!=null&&fast.next!=null&&fast.next.next!=null) {
			fast=fast.next.next;
			slow=slow.next;
		}
		ListNode middle=slow.next;
		slow.next=null;//一分为二断开
		ListNode left=sortList(head);
		ListNode right=sortList(middle);
		//合并
		ListNode res=new ListNode(0);
		ListNode resHead=res;
		while(left!=null||right!=null) {
			//左边没有了
			if(left==null) {
				res.next=right;
				break;
			}
			//右边没有了
			else if(right==null) {
				res.next=left;
				break;
			}
			else if(left.val<right.val) {
				res.next=left;
				left=left.next;
				res=res.next;
			}
			else {
				res.next=right;
				right=right.next;
				res=res.next;
			}
		}
		return resHead.next;
	}
	
	//数组的归并排序
	public void sortArray(int a[]) {
		int []temp=new int[a.length];
		mergeSort(a,0,a.length,temp);
	}
	private void mergeSort(int[] a, int l, int r, int[] t) {
		if(l==r) return;
		int middle=(l+r)/2;
		mergeSort(a, 0, middle, t);
		mergeSort(a, middle+1, r, t);
		for(int i=l;i<=r;i++) t[i]=a[i];
		int i1=l,i2=middle+1;
		for(int i=l;i<=r;i++) {
			if(i1>middle) a[i]=t[i2++];
			else if(i2>r) a[i]=t[i1++];
			else if(t[i1]<t[i2]) a[i]=t[i1++];
			else a[i]=t[i2++];
		}
	}

}
