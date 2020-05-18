package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
请实现一个函数用来找出字符流中第一个只出现一次的字符。
例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
输出描述:如果当前字符流没有存在出现一次的字符，返回#字符。
 *
 */
public class P54_字符流中第一个不重复的字符 {
	private int[]hashTable=new int[256];
	private char[] c=new char[256];
	private int cIndex=0;
	
	//Insert one char from stringstream
    public void Insert(char ch) {
		hashTable[ch]+=1;
		if(hashTable[ch]<=1) c[cIndex++]=ch;
    }
    
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
    	for(int i=0;i<cIndex;i++) {
    		if(hashTable[c[i]]==1) return c[i];
    	}
    	return '#';
    }

}
