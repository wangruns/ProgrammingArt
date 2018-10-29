package top.wangruns.educoder.gcc2018;

/**
 * 
 * 挑战任务
将输入的字符串str进行反转。
编程要求
补全右侧String inversion(String str)函数实现字符串的反转并返回反转之后的字符串，
其中函数参数str表示要反转的字符串。
 *
 */
public class 预一阶一关将字符串反正 {
	
	public String inversion(String str){
		if(str==null) return null;
		StringBuilder sb=new StringBuilder();
		for(int i=str.length()-1;i>=0;i--) sb.append(str.charAt(i));
		return sb.toString();
	}

}
