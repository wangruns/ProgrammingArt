package top.wangruns.nowcoder.sword2offer;

import java.util.HashMap;

/**
 * 
 * 题目描述
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 */
public class P25_复杂链表的复制 {
	
	public RandomListNode Clone(RandomListNode pHead){
		if(pHead==null) return null;
		HashMap<RandomListNode,RandomListNode> nodesMap=new HashMap<>();
		RandomListNode pHeadBackup=pHead;
        while(pHead!=null) {
        	RandomListNode newNode=new RandomListNode(pHead.val);
        	nodesMap.put(pHead,newNode);
        	pHead=pHead.next;
        }
        pHead=pHeadBackup;
        while(pHead!=null) {
        	RandomListNode curNewNode=nodesMap.get(pHead);
        	if(pHead.next!=null) curNewNode.next=nodesMap.get(pHead.next);
        	if(pHead.random!=null) curNewNode.random=nodesMap.get(pHead.random);
        	pHead=pHead.next;
        }
        
        return nodesMap.get(pHeadBackup);
    }
	
}
