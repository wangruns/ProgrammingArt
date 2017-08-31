package com.leetcode.wangruns;

//17、gas-station[贪心]
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: 
 * The solution is guaranteed to be unique.
 * 
 * 贪心：
 * 从start出发， 如果油量足够， 可以一直向后走 end++
 * 油量不够的时候，start--向后退
 * 最终 start == end的时候，如果有解一定是当前 start所在位置，看是否还剩有油
 * 
 * 暴力：
 * 每个站i都作为起始站start
 * 看从i站开始走能不能走完一次
 */
public class GasStation017 {
	
	//贪心
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start=gas.length-1;
		int end=0;
		int availableGas=gas[start]-cost[start];
		while(start!=end){
			//如果可以从start走到end
			if(availableGas>=0){
				//贪婪的让end变长，看能不能走的更远，即end++
				availableGas+=gas[end]-cost[end];
				end++;
			}else{
				//不能从当前的start走到end，则退一步加点油看看，即start--
				start--;
				availableGas+=gas[start]-cost[start];
			}
		}
		//最终start和end相遇，如果availableGas还有油的话,即可以走一圈
		return availableGas>=0?start:-1;
	}
	
	
	//暴力枚举
	public int canCompleteCircuitWithEnum(int[] gas, int[] cost) {
		int len=gas.length;
		//把每个都作为开始的站去尝试
        for(int i=0;i<len;i++){
        	int availableGas=0;
        	boolean isOk=true;
        	//对于从i开始走的站，看能不能走完
        	for(int j=i;j<i+len;j++){
        		int index=j%len;
        		availableGas+=gas[index];
        		if(availableGas>=cost[index]){
        			availableGas-=cost[index];
        			 continue;
        		}
        		else{
        			isOk=false;
        			break;
        		}
        	}
        	if(isOk) return i;
        }
        return -1;
	}
	
	
}
