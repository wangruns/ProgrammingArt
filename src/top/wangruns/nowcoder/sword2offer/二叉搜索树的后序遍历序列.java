package top.wangruns.nowcoder.sword2offer;

import java.util.Arrays;

/**
 * 
 * 题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 分析
根据二叉检索树和后序遍历的性质，数组的最后一个元素为根节点，且可以将数组分为左右两棵子二叉检索树
 */
public class 二叉搜索树的后序遍历序列 {
	
	public boolean VerifySquenceOfBST(int [] sequence) {
		if(sequence==null || sequence.length==0) return false;
		if(sequence.length==1) return true;
		int p=sequence.length-1;
		int root=sequence[p];
		while(p>0 && sequence[p-1]>root) p--;//找到分界面点
		for(int i=p-1;i>=0;i--) if(sequence[i]>root) return false;//验证分界点
		
		if(p==0) return VerifySquenceOfBST(Arrays.copyOfRange(sequence, p, sequence.length-1));
		else if(p==sequence.length-1) return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, p));
		else return VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, p)) && 
				VerifySquenceOfBST(Arrays.copyOfRange(sequence, p, sequence.length-1));
    }
	
}
