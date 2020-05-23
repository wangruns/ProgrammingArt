package top.wangruns.nowcoder.sword2offer;

/**
 * 题目描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
a	b	c	e
s	f	c	s
a	d	e	e
矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。​
 *
 */
public class P65_矩阵中的路径 {
	
	public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		//重构矩阵
		int index=0;
		char m[][]=new char[rows][cols];
		for(int i=0;i<rows;i++) for(int j=0;j<cols;j++) m[i][j]=matrix[index++];
		//矩阵中的每个节点都有可能是开始的节点
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if(m[i][j]==str[0]) {
					char temp=m[i][j];
					m[i][j]='*';
					boolean isFind=_has(m,str,i,j,0,rows,cols);
					if(isFind) return true;
					m[i][j]=temp;
				}
			}
		}
		//所有的节点都不行
		return false;
	}

	private boolean _has(char[][] m, char[] str, int x, int y, int index, int rows, int cols) {
		if(index+1==str.length) return true;
		//向上
		if(x-1>=0&&m[x-1][y]==str[index+1]) {
			char temp=m[x-1][y];
			m[x-1][y]='*';
			if(_has(m, str, x-1, y, index+1,rows,cols)) return true;
			else m[x-1][y]=temp;
		}
		//向下
		if(x+1<rows&&m[x+1][y]==str[index+1]) {
			char temp=m[x+1][y];
			m[x+1][y]='*';
			if(_has(m, str, x+1, y, index+1,rows,cols)) return true;
			else m[x+1][y]=temp;
		}
		//向左
		if(y-1>=0&&m[x][y-1]==str[index+1]) {
			char temp=m[x][y-1];
			m[x][y-1]='*';
			if(_has(m, str, x, y-1, index+1,rows,cols)) return true;
			else m[x][y-1]=temp;
		}
		//向右
		if(y+1<cols&&m[x][y+1]==str[index+1]) {
			char temp=m[x][y+1];
			m[x][y+1]='*';
			if(_has(m, str, x, y+1, index+1,rows,cols)) return true;
			else m[x][y+1]=temp;
		}
		return false;
	}
	
	
	//简便一点的写法
	public boolean hasPath2(char[] matrix, int rows, int cols, char[] str) {
		//重构矩阵
		int index=0;
		char m[][]=new char[rows][cols];
		for(int i=0;i<rows;i++) for(int j=0;j<cols;j++) m[i][j]=matrix[index++];
		//矩阵中的每个节点都有可能是开始的节点
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
                boolean isFind=_has2(m,str,i,j,0,rows,cols);
				if(isFind) return true;
			}
		}
		//所有的节点都不行
		return false;
	}
	
	private boolean _has2(char[][] m, char[] str, int x, int y, int index, int rows, int cols) {
		//不行返回的条件
        if(x<0||x>=rows||y<0||y>=cols||m[x][y]=='*'||m[x][y]!=str[index]) return false;
        if(index==str.length-1) return true;//最终成功
        char temp=m[x][y];
        m[x][y]='*';//当前点可选，将其设置为已经访问过的标记位
        if(_has2(m,str,x-1,y,index+1,rows,cols)||
          _has2(m,str,x+1,y,index+1,rows,cols)||
          _has2(m,str,x,y-1,index+1,rows,cols)||
          _has2(m,str,x,y+1,index+1,rows,cols))
            return true;
        m[x][y]=temp;
        return false;
	}
	
	
}
