package top.wangruns.educoder.gcc2018;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * 挑战任务
“绿盟杯”决赛完美落幕之后，赛事团队组织去一个风景优美的山区进行团建。由于人数众多必须选择一块较大的场地。
他们找到了一块足够大的地方，但是场地上却散布着许多石头，为了方便活动，必须把这些石头挪开。
现在我们假设整个场地是一块矩形的地图，地图坐标的横纵坐标均为非负整数，每个坐标点上有一个值：
0：代表无法从这个点通过
1：代表这个点可以顺利通过
N(大于1)：代表这个点上有一个可以去除的石头，而且石头的大小是N
现在要求你按照以下规则移除石头：从当前位置出发，按石头的大小依次移除石头，每次先移除从当前位置出发能够达到的最小的石头，
每移除一个石头，该石头所在坐标就可以通行即值变为1。你需要编写一个程序计算出从坐标（0,0）出发，
移除所有石头需要走的最小步数，注意，石头是无法翻越的,而且如果（0,0）上有石头可以直接移除。如果无法移除所有的石头就输出-1。
编程要求
补全右侧代码区中的getMinimumSteps (List<List<Integer>> stones)函数，完成挑战任务中提出的要求：
按照石头大小，从小到大依次移除场地中的石头，返回最小的步数，如果无法移除所有的石头就返回-1。
输入：
1 2 3
0 0 4
7 6 5
输出：6
输入：
1 2 3
0 0 0
7 6 5
输出：-1
输入：
12 34 5 7 8 0 13
1 0 8 9 12 0 11
13 0 0 0 11 24 21
23 32 17 0 0 10 15
1 2 3 0 0 6 23
4 8 12 0 0 19 16
5 7 0 0 9 10 11
输出：54
 *
 * 分析
思路一，暴力搜索。从起点(0,0)开始DFS寻找当前点可达的所有路径并选择权重最小的点作为下一个新起点。
这里需要注意的是，从某一个起点可达的权重的最小的点可能不止一个，这个时候需要将这些点保存起来作为
路径分支，最终的最优路径方案就是这些分支路径中步长计数最小的路径，这里需要的只是最小计数步长。
 */
public class 预二阶三关完美的团建活动 {
	
	int[] directions= {0,1,2,3};//表示上下左右的方向即up=0,down=1,left=2,right=3;
	int[] prow= {-1,+1,0,0};//表示向上下左右移动一步row的坐标变化
	int[] pcol= {0,0,-1,+1};
	int cnt=0x7fffffff;//最小步长计数
	boolean isCleanAll=true;//记录是否可以清除全部
	boolean isFirstJudge=true;//记录第一次判定是否可以全部清除，只需要判定一次就行
	class P{
		int row;int col;int val;int step;boolean visited;
		P(int row,int col,int val){
			this.row=row;this.col=col;this.val=val;
		}
	}
	//思路一
	public int getMinimumSteps1 (List<List<Integer>> stones){
		stones.get(0).set(0, 1);//根据题意确保(0,0)直接可行
		HashMap<String,P> coordinate2p=new HashMap<>();
		int Rows=stones.size();
		int Cols=stones.get(0).size();
		for(int row=0;row<Rows;row++) {
			for(int col=0;col<Cols;col++) {
				coordinate2p.put(row+"+"+col, new P(row,col,stones.get(row).get(col)));
			}
		}
		P curP=coordinate2p.get(0+"+"+0);//从(0,0)开始
		int curCnt=0;
		__getMinimumSteps(coordinate2p,curP,curCnt,Rows,Cols);
		
		//检验看是否所有障碍都已经清除
		return isCleanAll?cnt:-1;
	}
	
	private void __getMinimumSteps(HashMap<String, P> coordinate2p, P curP, int curCnt, int Rows, int Cols) {
		//寻找当前位置可达的所有路径
		LinkedList<LinkedList<P>> paths=new LinkedList<>();
		LinkedList<P> path=new LinkedList<>();
		dfs(curP,coordinate2p,paths,path);
		//判定是否走完，当找不到有石头的路径时，即完了
		if(paths.size()==0) {
			if(cnt>curCnt) cnt=curCnt;
			if(isFirstJudge) {
				//检验看是否所有障碍都已经清除
				for(int i=0;i<Rows;i++) {
					for(int j=0;j<Cols;j++) {
						if(coordinate2p.get(i+"+"+j).val>1) {
							isCleanAll=false;
							break;
						}
					}
				}//检验看是否所有障碍都已经清除End
				isFirstJudge=false;
			}
			return;
		}
		
		//选择所有路径中末端石头最小的，而当前位置可达的最小石头可能有多个，需要保存分别行走
		PriorityQueue<P> heap=new PriorityQueue<>((o1,o2)->o1.val-o2.val);
		for(int i=0;i<paths.size();i++) {
			P tempP=paths.get(i).getLast();
			if(!heap.contains(tempP)) {
				heap.add(tempP);
				//记录当前位置到下一个位置需要的步数
				tempP.step=paths.get(i).size();
			}else {
				//如果存在当前位置到同一个目标石头的多条路径，则选择路径短的
				if(paths.get(i).size()<tempP.step) tempP.step=paths.get(i).size();
			}
		}
		int minWeight=heap.peek().val;
		HashMap<P,Integer> stepsBackup=new HashMap<>();//当前步长备份
		heap.forEach(p->stepsBackup.put(p, p.step));
		while(!heap.isEmpty()&&minWeight==heap.peek().val) {
			P nextP=heap.poll();
			
			System.out.println(nextP.val+": "+nextP.row+","+nextP.col);//打印出下一步的行走路径
			
			int weightBackup=nextP.val;
			nextP.val=1;//访问下一个节点，移开石头
			__getMinimumSteps(coordinate2p,nextP,curCnt+nextP.step,Rows,Cols);
			nextP.val=weightBackup;//权重回溯
			heap.forEach(p->p.step=stepsBackup.get(p));//步长回溯
		}
	}

	private void dfs(P curP, HashMap<String, P> coordinate2p, LinkedList<LinkedList<P>> paths, LinkedList<P> path) {
		if(curP.val>1) {
			paths.add(new LinkedList<>(path));
			return;//碰到石头返回
		}
		//对于一个可行位置来说，下一步有上下左右四种选择
		for(int direction:directions) {
			int newRow=prow[direction]+curP.row;
			int newCol=pcol[direction]+curP.col;
			P nextP=coordinate2p.get(newRow+"+"+newCol);
			//下一步不越界不遇墙，则可以继续行走
			if(nextP!=null && nextP.val!=0 && !nextP.visited) {
				path.add(nextP);
				curP.visited=true;
				dfs(nextP,coordinate2p,paths,path);
				path.removeLast();//回溯
				curP.visited=false;
			}
		}
	}

}
