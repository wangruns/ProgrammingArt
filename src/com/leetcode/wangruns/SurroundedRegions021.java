package com.leetcode.wangruns;

//21、	surrounded-regions[数组]
/**
 * Given a 2D board containing'X'and'O', capture all regions surrounded by'X'.
 * A region is captured by flipping all'O's into'X's in that surrounded region .
 * For example,
	X X  X X
	X O O X
	X X O X
	X O X X
 * After running your function, the board should be:
	X X X X
	X X X X
	X X X X
	X O X X
 *
 *题意：所有与四条边相连的O都保留，其他O都变为X	.
 *遍历四条边上的O，并深度遍历与其相连的O，将这些O都转为*
 *将剩余的O变为X
 *将剩余的*变为O
 */
public class SurroundedRegions021 {

	private int rowNum,colNum;
	public void solve(char[][] board) {
		if(board==null||board.length==0) return;
		//深度遍历四条边上的O及其相连的O，将其转化成*
		rowNum=board.length;
		colNum=board[0].length;
		//处理第一行和最后一行的边节点
		for(int i=0;i<colNum;i++){
			dfs(board,0,i);//处理第一行
			dfs(board,rowNum-1,i);//处理最后一行
		}
		//处理第一列和最后一列
		for(int i=0;i<rowNum;i++){
			dfs(board,i,0);//处理第一列
			dfs(board,i,colNum-1);//处理最后一列
		}
		//先将剩余的O变成X，然后再将*换回O
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<colNum;j++){
				if(board[i][j]=='O') board[i][j]='X';
				if(board[i][j]=='*') board[i][j]='O';
			}
		}
	}
	
	//如果当前边界点为O，把它变成*，并对其进行深度遍历
	private void dfs(char[][] board, int row, int col) {
		if(board[row][col]=='O'){
			board[row][col]='*';
			//向上(if防止越界)
			if(row>1) dfs(board,row-1,col);
			//向下
			if(row+1<rowNum) dfs(board,row+1,col);
			//向左
			if(col>1) dfs(board,row,col-1);
			//向右
			if(col+1<colNum) dfs(board,row,col+1);
		}
	}
	
}
