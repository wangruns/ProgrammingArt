package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;

/**
 * 
 * 题目描述
小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *
 * 分析
连续正数求和，想到利用数列求和公式(a1+an)*n/2=s，
则变成双指针问题，当前总和小于sum，大指针继续+；否则小指针+
 */
public class P41_和为S的连续正数序列 {
	
	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer> > res=new ArrayList<>();
		int plow=1,phigh=2;
		while(plow<phigh) {
			int cur=(plow+phigh)*(phigh-plow+1)/2;
			if(cur<sum) phigh++;
			else if(cur==sum) {
				ArrayList<Integer> ones=new ArrayList<>();
				for(int i=plow;i<=phigh;i++) ones.add(i);
				res.add(ones);
				plow++;
			}else plow++;
		}
		
		return res;
    }
	
	//和用公式的方式类似，也是用两个指针控制移动
	public ArrayList<ArrayList<Integer> > FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        int p1=1,p2=2,s=3;
        while(p2<sum){
            if(s==sum){
                ArrayList<Integer> t=new ArrayList<>();
                for(int i=p1;i<=p2;i++) t.add(i);
                res.add(t);
                p2++;
                s+=p2;
            }
            else if(s<sum){
                p2++;
                s+=p2;
            }
            else{
                s-=p1;
                p1++;
            }
        }
        return res;
    }
	
}
