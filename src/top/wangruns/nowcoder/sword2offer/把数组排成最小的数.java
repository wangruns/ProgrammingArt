package top.wangruns.nowcoder.sword2offer;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * 题目描述
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 */
public class 把数组排成最小的数 {
	
	//使用lambda表达式，即匿名函数编程，简化代码
	//不过简单往往是需要付出时间的代码，因为自己的做少了，表示环境为我们做的更多了
	//以下为牛客网上的运行大致运行时间
	//运行时间：139ms
    //占用内存：15212k
	public String PrintMinNumber(int [] numbers) {
		ArrayList<Integer> list=new ArrayList<>();
		StringBuilder sb=new StringBuilder();
		for(int i:numbers) list.add(i);
		list.sort((o1,o2)->(o1+""+o2).compareTo(o2+""+o1));
		list.forEach(e -> sb.append(e));
		return sb.toString();
    }
	
	//不使用lambda表达式
	//运行时间：22ms
	//占用内存：10540k
	public String PrintMinNumber2(int [] numbers) {
		 ArrayList<Integer> list=new ArrayList<Integer>();
		 StringBuilder res=new StringBuilder();
		 for(int i:numbers)	list.add(i);
		 list.sort(new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2) {
				String s1=o1+""+o2;
				String s2=o2+""+o1;
				return s1.compareTo(s2);
			}
		 });
		 for(int i:list)	res.append(i);
		 return res.toString();
   	}
	
}
