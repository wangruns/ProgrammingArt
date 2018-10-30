package top.wangruns.educoder.gcc2018;

/**
 * 
 * 挑战任务
绿盟和各大名企合作，举办编程能力大赛，需要选拔一支参赛队伍。
队伍成员全部来自“绿盟杯”中表现优秀的同学，每个同学都根据在比赛中的表现被赋予了一个能力值。
现在被召集的N个同学已经集结完毕，他们按照编号依次站成了一排。你需要编写一个程序，
从这N个同学中选出S个同学，要求选出的同学的能力值的乘积最大，且要求被选出的相邻两个同学的编号的差不超过D。
编程要求
补全右侧代码区中的getBestTeams(int numbers, int[] abilities, int selectedNum, int distance)函数，
实现找出能力值乘积最大而且满足编号要求的同学，并将最终的结果作为返回值返回。函数参数说明如下：
int numbers 召集到的同学的人数
int[] abilities 各个同学的能力值（依次对应不同编号的同学,数组的index就是学生的编号）
int selectedNum 需要选出的同学的人数
int distance 相邻同学的编号的差的最大值
输入：3 , [7,4,7] , 2 , 50
输出：49
 *
 * 分析
"从N个同学中选出S个同学"可以看出是一个组合问题{a1,a2,...,an},从第一个元素开始，对于每一个元素来说，
都有选或者不选两种方式，如果a1元素被选择了，那么下一个被选择的元素只能在a2到a_{1+distance}之间选。
即当前被选择的元素和上一次被选择的元素的索引差值也要在给定的距离之内。
 */
public class 预二阶二关最强战队 {
	
	long maxProduct=0x8fffffff;//记录最大乘积
	public Long getBestTeam(int numbers, int[] abilities, int selectedNum, int distance){
		long curProduct=1;//记录当前乘积
		__getBestTeam(abilities,0,numbers-1,selectedNum,distance,curProduct);
		return maxProduct;
	}
	
	private void __getBestTeam(int[] abilities,int curIndex,int lastIndex,int selectedNum,int distance,long curProduct) {
		//已经选满了
		if(selectedNum==0 && curProduct>maxProduct) maxProduct=curProduct;
		//已经走到尾了
		if(curIndex>abilities.length-1) return;
		//在范围内的元素有两种选择
		if(curIndex-lastIndex<=distance) {
			//选择当前元素
			long curBackup=curProduct;
			curProduct*=abilities[curIndex];
			for(int i=curIndex+1;i<=curIndex+distance;i++) {
				__getBestTeam(abilities,i,curIndex,selectedNum-1,distance,curProduct);
			}
			//不选择当前元素
			curProduct=curBackup;
			for(int i=curIndex+1;i<=curIndex+distance;i++) {
				__getBestTeam(abilities,i,lastIndex,selectedNum,distance,curProduct);
			}
		}
	}
	
}
