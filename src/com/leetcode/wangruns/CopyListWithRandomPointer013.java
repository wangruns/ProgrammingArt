package com.leetcode.wangruns;

import java.util.HashMap;

//13��copy-list-with-random-pointer[����]
/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 
 * �µ�����;ɵ�����һ�ֶ�Ӧ��ϵ�����뵽	map
 * �����Ͻڵ���½ڵ��һһ��Ӧ��ϵ			map(old,new)
 * �����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ		map.get(old).next=map.get(old.next);
 */
public class CopyListWithRandomPointer013 {

	class RandomListNode {
		int label;
		RandomListNode next, random;
		
		RandomListNode(int x) { this.label = x; }
	}

	// ��Ŀ��˵��RandomListNode��list�У���������ֻ���½���ǰ�ڵ㼴��
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return head;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
		// �����½ڵ���Ͻڵ�Ĺ�ϵ
		RandomListNode p = head;
		while (p != null) {
			map.put(p, new RandomListNode(p.label));
			p = p.next;
		}
		// �����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
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
	
	//���RandomListNode����ָ��list����Ļ�����ô����ֻ��ҪСС���޸�һ�£���randomҲ���½�
	public RandomListNode copyRandomListWithRanomOutofList(RandomListNode head) {
        if(head==null) return head;
        RandomListNode p=head;
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        //�����Ͻڵ���½ڵ��һһ��Ӧ��ϵ
        while(p!=null){
        	map.put(p, new RandomListNode(p.label));
        	if(p.random!=null) map.put(p.random, new RandomListNode(p.random.label));
        	p=p.next;
        }
        //�����Ͻڵ�Ĺ�ϵ�������½ڵ�Ĺ�ϵ
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
