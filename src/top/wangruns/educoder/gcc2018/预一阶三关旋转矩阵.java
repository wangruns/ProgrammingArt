package top.wangruns.educoder.gcc2018;

/**
 * 
 * 挑战任务
在计算机中，一张数字图像，可以被看做是一个矩阵或者说数组。学过线性代数的同学对矩阵肯定不陌生。
一般来说，图像是一个标准的矩形，有着宽度（width）和高度（height）。
而矩阵有着行（row）和列（column），矩阵的操作在数学和计算机中的处理都很常见且成熟，
于是很自然的就把图像作为一个矩阵，把对图像的操作转换成对矩阵的操作，实际上所有的图像处理工具都是这么做的。
所以我们如果要对图像进行操作，其实也就是在对一个数组进行操作。
本关要求你编写代码实现对一张图像的90°旋转，即对矩阵的90°旋转。
编程要求
补全函数void rotate(int[][] matrix)，实现对输入的数组进行旋转的功能。
注意：
你必须使用原地算法来旋转数组，而不能重新创建一个数组，本关不需要你输出数组，只需要修改数组matrix即可。
输入：
1 2 3
4 5 6
7 8 9
原地旋转，使其变为：
7 4 1
8 5 2
9 6 3
 *
 * 分析
由"只要修改matrix即可"可以得出这个需要旋转的矩阵其实是一个N*N的方阵...
 */
public class 预一阶三关旋转矩阵 {
	
	//先将矩阵按照反对角线对称互换，然后将i行和(n-i-1)行交换
	public void rotate(int[][] matrix){
		int N=matrix.length;
		//调换反对角线元素
		for(int i=0;i<N-1;i++) {
			for(int j=0;j<N-1-i;j++) {
				int t=matrix[i][j];
				matrix[i][j]=matrix[N-1-j][N-1-i];
				matrix[N-1-j][N-1-i]=t;
			}
		}
		//调换i行和n-i-1行元素
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N;j++) {
				int t=matrix[i][j];
				matrix[i][j]=matrix[N-i-1][j];
				matrix[N-i-1][j]=t;
			}
		}
	}

}
