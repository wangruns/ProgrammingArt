package top.wangruns.nowcoder.sword2offer;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * 题目描述
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 */
public class 数组中出现次数超过一半的数字 {

	//比较直观的一个方法是用哈希计数，O(n)但需要一个辅助哈希
	public int MoreThanHalfNum_Solution1(int [] array) {
        if(array==null || array.length==0) return 0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<array.length;i++) {
        	if(map.containsKey(array[i])) map.put(array[i], map.get(array[i])+1);
        	else map.put(array[i], 1);
        }
        int count=array.length/2;
        for(Entry<Integer, Integer> entry:map.entrySet()) {
        	if(entry.getValue()>count) return entry.getKey();
        }
        return 0;
    }
	
	//"打擂台法"，O(n),假设数组里面的数字在参加武林大会，相同的数字是属于同一个门派的
	//他们的战斗力都相同，只能采取和对方同归于尽的方式消灭对手，这样的话，如果有人数超过
	//总人数一半的门派，那么他们肯定能够获胜，对吧？也有可能压根就没有人数超过一半的门派
	//只是最后上场的门派捡了个耙货，故在比武结束后，只需对最后一个门派进的人数进行判定即可
	//若该门派人数超过一半，则表示该门派确实是靠人数多的实力取胜的;否则就是运气取胜了...
	public int MoreThanHalfNum_Solution2(int [] array) {
		if(array==null || array.length==0) return 0;
		int door=array[0];//记录当前擂台上还存在的门派
		int cnt=1;//记录当前擂台上还存在的门派的人数
		for(int i=0;i<array.length-1;i++) {
			if(array[i]==array[i+1]) cnt++;//两个相同门派的人，不打，组成一队
			else cnt--;//两个不同门派的人比武，这两个人同归于尽了...
			if(cnt==0) {
				//当前擂台上面已经没有活人了，重新找人上来
				if(i+2<array.length) door=array[i+2];
				cnt=1;
				i++;
			}
		}
		//验证获胜的门派是靠的人数实力，还是出场的运气
		cnt=0;
		for(int i=0;i<array.length;i++) if(array[i]==door) cnt++;
		if(cnt>array.length/2) return door;//靠人数实力
		else return 0;//靠出场运气
	}
	
}
