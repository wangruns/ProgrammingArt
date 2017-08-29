package com.leetcode.wangruns;

import java.util.HashMap;

//13、copy-list-with-random-pointer[链表]
/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 
 * 新的链表和旧的链表，一种对应关系，联想到	map
 * 建立老节点和新节点的一一对应关系			map(old,new)
 * 根据老节点的关系，建立新节点的关系		map.get(old).next=map.get(old.next);
 */
public class CopyListWithRandomPointer013 {

	class RandomListNode {
		int label;
		RandomListNode next, random;
		
		RandomListNode(int x) { this.label = x; }
	}

	// 题目中说了RandomListNode在list中，所以我们只需新建当前节点即可
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return head;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
		// 建立新节点和老节点的关系
		RandomListNode p = head;
		while (p != null) {
			map.put(p, new RandomListNode(p.label));
			p = p.next;
		}
		// 更具老节点的关系，建立新节点的关系
		p = head;
		RandomListNode newNode = null;
		while (p != null) {
			newNode = map.get(p);
			if (p.next != null) newNode.next = map.get(p.next);
			if (p.random != null) newNode.random = map.get(p.random);
			p = p.next;
		}
		return map.get(head);
	}
	
	//如果RandomListNode可以指向list外面的话，那么我们只需要小小的修改一下，即random也需新建
	public RandomListNode copyRandomListWithRanomOutofList(RandomListNode head) {
        if(head==null) return head;
        RandomListNode p=head;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        //建立老节点和新节点的一一对应关系
        while(p!=null){
        	map.put(p, new RandomListNode(p.label));
        	if(p.random!=null) map.put(p.random, new RandomListNode(p.random.label));
        	p=p.next;
        }
        //根据老节点的关系，建立新节点的关系
        p=head;
        RandomListNode newNode=null;
        while(p!=null){
        	newNode=map.get(p);
        	if(p.next!=null) newNode.next=map.get(p.next);
        	if(p.random!=null) newNode.random=map.get(p.random);
        	p=p.next;
        }
        return map.get(head);
    }

}
