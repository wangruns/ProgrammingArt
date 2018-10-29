package top.wangruns.educoder.gcc2018;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 挑战任务
我们吃的食物都有保质期，现在食品监督管理局想要制作一个能准确计算食品过期日期的小程序，需要请你来进行设计。
例如：A食品在2018年1月1日生产，保质期是20天，则它的过期日期在2018年1月21日。
编程要求
补全函数String getDate(String releaseDate,int day)其中releaseDate表示食品出厂日期day表示保质期，
请根据传入的数据计算食品的过期日期，格式为yyyy-mm-dd即4位年份2位月份2位日期。比如：2015-02-19
请严格按照格式书写，不能出现其它文字或符号，并将最终结果做为函数的返回值返回。
输入：2016-01-01，20
输出：2016-01-21
 *
 */
public class 预一阶四关计算日期 {
	
	//在日历上面操作的方法
	public String getDate1(String releaseDate,int day){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date=sdf.parse(releaseDate);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//日期的齿轮转动方法，一天一天的转动
	public String getDate2(String releaseDate,int day){
		String[] strs=releaseDate.split("-");
		int y=Integer.valueOf(strs[0]);
		int m=Integer.valueOf(strs[1]);
		int d=Integer.valueOf(strs[2]);
		int[] runY={31,29,31,30,31,30,31,31,30,31,30,31};
		int[] pinY={31,28,31,30,31,30,31,31,30,31,30,31};
		int[] curY;
		while(day>0) {
			//是否闰年
			if(y%4==0 && y%100!=0 || y%400==0) curY=runY;
			else curY=pinY;
			d++;day--;
			//日份超了
			if(d>curY[m-1]) {
				d=1;
				m++;
			}
			//月份超了
			if(m>12) {
				m=1;
				y++;
			}
		}
		if(d>=10 && m>=10) return y+"-"+m+"-"+d;
		else if(d>=10) return y+"-0"+m+"-"+d;
		else if(m>=10) return y+"-"+m+"-0"+d;
		else return y+"-0"+m+"-0"+d;
	}
	
}
