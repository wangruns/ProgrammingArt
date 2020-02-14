package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
请实现一个函数，将一个字符串中的每个空格替换成“%20”。
例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 *
 */
public class P2_替换空格 {
	
	//直接使用String的replaceAll方法正则替换
	public String replaceSpace1(StringBuffer str) {
		//\s在正则里面就表示空白符，\本身就具有转义功能，若要取消\的转义功能可以用\\,这样就单纯的表示"\"了
		//而在这些本身就具有转义功能的工具中的，我们需要先关闭\的转义功能，然后再作为参数传递到工具中
    	return str.toString().replaceAll("\\s", "%20");
    }
	
	//从后面开始向后面移动元素(每个元素最多只移动一次)，先数出空格的个数计算好新的字符串长度，从最后的元素开始依次后移动
	public String replaceSpace2(StringBuffer str) {
		int spaceNum=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==' ') spaceNum++;
		}
		int oldIndex=str.length()-1;
		int newLength=str.length()+spaceNum*2;
		//确保新的字符数组capacity足够，该函数如果设置的新长度大于原本字符数组capacity的时候需先扩容一次
		str.setLength(newLength);
		int newIndex=str.length()-1;
		for(;oldIndex>=0 && oldIndex<newIndex;oldIndex--) {
			if(str.charAt(oldIndex)==' ') {
				str.setCharAt(newIndex--, '0');
				str.setCharAt(newIndex--, '2');
				str.setCharAt(newIndex--, '%');
			}else {
				str.setCharAt(newIndex--, str.charAt(oldIndex));
			}
		}
		return str.toString();
	}		
}
