package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 分析
根据二叉检索树和后序遍历的性质，数组的最后一个元素为根节点，且可以将数组分为左右两棵子二叉检索树
 */
public class P23_二叉搜索树的后序遍历序列 {
	
	public boolean VerifySquenceOfBST(int [] sequence) {
		if(sequence==null||sequence.length==0) return false;
        return verify(sequence,0,sequence.length-1);
    }
	
	private boolean verify(int []a,int l,int r){
        if(l>=r) return true;
        int p=l;
        while(a[p]<a[r]) p++;//找到分界面点
        for(int i=p;i<r;i++) if(a[i]<a[r]) return false;//验证分界点
        return verify(a,l,p-1) && verify(a,p,r-1);
    }
	
}
