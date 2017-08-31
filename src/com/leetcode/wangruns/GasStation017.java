package com.leetcode.wangruns;

//17��gas-station[̰��]
/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: 
 * The solution is guaranteed to be unique.
 * 
 * ̰�ģ�
 * ��start������ ��������㹻�� ����һֱ����� end++
 * ����������ʱ��start--�����
 * ���� start == end��ʱ������н�һ���ǵ�ǰ start����λ�ã����Ƿ�ʣ����
 * 
 * ������
 * ÿ��վi����Ϊ��ʼվstart
 * ����iվ��ʼ���ܲ�������һ��
 */
public class GasStation017 {
	
	//̰��
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start=gas.length-1;
		int end=0;
		int availableGas=gas[start]-cost[start];
		while(start!=end){
			//������Դ�start�ߵ�end
			if(availableGas>=0){
				//̰������end�䳤�����ܲ����ߵĸ�Զ����end++
				availableGas+=gas[end]-cost[end];
				end++;
			}else{
				//���ܴӵ�ǰ��start�ߵ�end������һ���ӵ��Ϳ�������start--
				start--;
				availableGas+=gas[start]-cost[start];
			}
		}
		//����start��end���������availableGas�����͵Ļ�,��������һȦ
		return availableGas>=0?start:-1;
	}
	
	
	//����ö��
	public int canCompleteCircuitWithEnum(int[] gas, int[] cost) {
		int len=gas.length;
		//��ÿ������Ϊ��ʼ��վȥ����
        for(int i=0;i<len;i++){
        	int availableGas=0;
        	boolean isOk=true;
        	//���ڴ�i��ʼ�ߵ�վ�����ܲ�������
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
