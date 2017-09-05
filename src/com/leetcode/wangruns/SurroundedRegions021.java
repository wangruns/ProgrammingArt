package com.leetcode.wangruns;

//21��	surrounded-regions[����]
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
 *���⣺������������������O������������O����ΪX	.
 *�����������ϵ�O������ȱ�������������O������ЩO��תΪ*
 *��ʣ���O��ΪX
 *��ʣ���*��ΪO
 */
public class SurroundedRegions021 {

	private int rowNum,colNum;
	public void solve(char[][] board) {
		if(board==null||board.length==0) return;
		//��ȱ����������ϵ�O����������O������ת����*
		rowNum=board.length;
		colNum=board[0].length;
		//�����һ�к����һ�еı߽ڵ�
		for(int i=0;i<colNum;i++){
			dfs(board,0,i);//�����һ��
			dfs(board,rowNum-1,i);//�������һ��
		}
		//�����һ�к����һ��
		for(int i=0;i<rowNum;i++){
			dfs(board,i,0);//�����һ��
			dfs(board,i,colNum-1);//�������һ��
		}
		//�Ƚ�ʣ���O���X��Ȼ���ٽ�*����O
		for(int i=0;i<rowNum;i++){
			for(int j=0;j<colNum;j++){
				if(board[i][j]=='O') board[i][j]='X';
				if(board[i][j]=='*') board[i][j]='O';
			}
		}
	}
	
	//�����ǰ�߽��ΪO���������*�������������ȱ���
	private void dfs(char[][] board, int row, int col) {
		if(board[row][col]=='O'){
			board[row][col]='*';
			//����(if��ֹԽ��)
			if(row>1) dfs(board,row-1,col);
			//����
			if(row+1<rowNum) dfs(board,row+1,col);
			//����
			if(col>1) dfs(board,row,col-1);
			//����
			if(col+1<colNum) dfs(board,row,col+1);
		}
	}
	
}
