package top.wangruns.nowcoder.sword2offer;

/**
 * 题目描述
地上有一个m行和n列的方格。
一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
但是不能进入行坐标和列坐标的数位之和大于k的格子。 
例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
请问该机器人能够达到多少个格子？

分析
即求从(0,0)开始，网格中连通的格子数目​
 *
 */
public class P66_机器人的运动范围 {
    int cnt=0;
    public int movingCount(int threshold, int rows, int cols)
    {
        int matrix[][]=new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j]=help(i)+help(j);
            }
        }
        move(threshold,0,0,rows,cols,matrix);
        return cnt;
    }
    
    private void move(int k,int x,int y,int rows,int cols,int [][]m){
    	if(x<0||x>=rows||y>=cols||y<0||m[x][y]>k||m[x][y]==-1) return;
    	m[x][y]=-1;//已经走过标记为-1
    	cnt++;
    	move(k,x-1,y,rows,cols,m);
    	move(k,x+1,y,rows,cols,m);
    	move(k,x,y-1,rows,cols,m);
    	move(k,x,y+1,rows,cols,m);
    }
    
    private int help(int num){
        int sum=0;
        while(num!=0){
            sum+=num%10;
            num/=10;
        }
        return sum;
    }
    
}
