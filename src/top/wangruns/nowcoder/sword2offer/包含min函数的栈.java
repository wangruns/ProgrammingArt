package top.wangruns.nowcoder.sword2offer;

import java.util.Stack;

/**
 * 
 * 题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * 分析
要求复杂度为O（1），所以需要借助辅助栈方法
dataStack:存放所有数据的栈	5,  4,  3, 8, 10, 11, 12, 1 ,1
minStack:存放当前栈中的最小值	5,  4,  3，1, 1	
 */
public class 包含min函数的栈 {
	private Stack<Integer> dataStack=new Stack<>();
	private Stack<Integer> minStack=new Stack<>();
	
	public void push(int node) {
		dataStack.push(node);
		if(minStack.isEmpty() || node<=minStack.peek()) minStack.push(node);
    }
    
    public void pop() {
    	if(dataStack.pop()==minStack.peek()) minStack.pop();
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}
