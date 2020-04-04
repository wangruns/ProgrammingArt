package top.wangruns.nowcoder.sword2offer;

/**
 * 
 * 题目描述
牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。
后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 *
 */
public class P44_翻转单词顺序列 {
	
	public String ReverseSentence(String str) {
		if(str==null||str.length()==0) return "";
		char[] c=str.toCharArray();
		//先翻转整个句子
		reverse(c,0,c.length-1);
		//再翻转每个单词
		int start=-1,end=0;
		for(int i=0;i<c.length;i++) {
			if(c[i]==' ') {
				end=i;
				reverse(c, start+1, end-1);
				start=end;
			}
		}
		reverse(c, start+1, c.length-1);
		return new String(c);
    }

	private void reverse(char[] c, int i, int j) {
		while(i<j) {
			char t=c[i];c[i]=c[j];c[j]=t;
			i++;j--;
		}
	}
	
}
