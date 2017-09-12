package com.leetcode.wangruns;

import com.leetcode.wangruns.Leetcode.TreeNode;

//27,binary-tree-maximum-path-sum[��]
/**
 * Given a binary tree, find the maximum path sum. 
 * The path may start and end at any node in the tree. 
 * For example:
 * For example:
       1
      / \
     2   3
 * Return 6.
 * 
 * �����Ŀ����˼����̵�·��������֮�����ڣ���������������Ĳ���һ���Ǹ��ڵ�
 * ������Խ�������Ӹ�����ߵ��ұ�
 * ������Ŀ��ͨ�������뵽�ݹ������
 * ��maxPathSum����¼���·������maxPathSum������������Դ��
 * 1�����������㣨[����]�����ģ�
 * 2������һ�������Ͷ��㣨[��·��������һ��+����]�����ģ�
 * 3���������������������Լ����㣨[������·��+����]������
 * maxPath��root)�����������ӽڵ㵽��ǰroot���·����
 */
public class BinaryTreeMaximumPathSum027 {
	
	private int maxPathSum=0x80000000;
	public int maxPathSum(TreeNode root) {
		maxPath(root);
		return maxPathSum;
	}
	private int maxPath(TreeNode root) {
		if(root==null) return 0;
		int leftV=maxPath(root.left);
		int rightV=maxPath(root.right);
		int son=Math.max(leftV, rightV);
		//���������1.���������㣬2.����һ�������Ͷ��㣬3.�������������������Լ�����
		int situation1=root.val;
		int situation2=root.val+son;
		int situation3=root.val+leftV+rightV;
		int tempMax=Math.max(situation1, Math.max(situation2, situation3));
		//�����·��
		if(tempMax>maxPathSum) maxPathSum=tempMax;
		//maxPath��root)�����������ӽڵ㵽��ǰroot���·��
		return Math.max(root.val, root.val+son);
	}
}
