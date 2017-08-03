package com.zx.sframe.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.zx.platform.syscontext.util.StringUtil;

public class DateUtil {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";//

	private static final String DEFAULT_FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";//

	/**
	 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
	 * 
	 * @param str 字符串
	 * @param format 日期格式
	 * @return 日期
	 * @throws java.text.ParseException
	 */
	public static Date strDate(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.sql.Date strToSqlDate(String str, String format) {
		if (null == str || "".equals(str)) {
			return null;
		}
		// 如果没有指定字符串转换的格式，则用默认格式进行转换
		if (null == format || "".equals(format)) {
			format = DEFAULT_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(str);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String date2String(Date currentDate, String formate) {
		String result = null;
		SimpleDateFormat formatdater = new SimpleDateFormat(formate);
		result = formatdater.format(currentDate);
		return result;
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */

	public static Timestamp strTimestampDefault(String str) {
		Date date = strDate(str, DEFAULT_FORMAT_TIMESTAMP);
		return new Timestamp(date.getTime());
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */

	public static Timestamp strTimestamp(String str, String dateFormat) {
		if (StringUtil.isBlank(dateFormat)) {
			dateFormat = DEFAULT_FORMAT;
		}
		Date date = strDate(str, dateFormat);
		return new Timestamp(date.getTime());
	}

	/**
	 * 时间转换为字符串
	 * 
	 * @param date
	 *            时间
	 * @param format
	 *            日期格式
	 * @return 字符串
	 */
	public static String timestampStr(Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String timestampStr(Date date) {
		return timestampStr(date, DEFAULT_FORMAT_TIMESTAMP);
	}
	
	/**
	 * @Title: getDate10 
	 * @Description: 获取10位的日期
	 * @param date
	 * @return Date 
	 * @throws
	 * @author lvtu 
	 * @date 2015年11月12日 下午12:50:43
	 */
	public static Date getDate10(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
		String dateStr = dateFormat.format(date);
		return strDate(dateStr, DEFAULT_FORMAT);
	}

	/**
	 * 检验输入是否为正确的日期格式(不含秒的任何情况),严格要求日期正确性,格式:yyyy-MM-dd HH:mm
	 * 
	 * @param sourceDate
	 * @return
	 */
	public static boolean checkDate(String sourceDate) {
		if (sourceDate == null) {
			return false;
		}
		try {
			sourceDate = sourceDate.replaceAll("-", "/");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			dateFormat.setLenient(false);
			dateFormat.parse(sourceDate);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 获取字符串型的 某月份/日期
	 * 
	 * @param start
	 *            指定日期或月份
	 * @param value
	 *            需要增减的月份数或天数
	 * @return 字符串
	 */
	public static String getVorTime(String start, int value) {
		if (start != null) {
			start = start.replaceAll("-", "/");
		} else {
			return "";
		}
		int mode = 2;
		if (start.length() == 7)
			mode = 1;
		else if (start.length() == 10)
			mode = 2;
		else {
			// 我处理不了 你回去吧
			return "";
		}
		SimpleDateFormat simpleDateFormat = null;
		if (mode == 1)
			simpleDateFormat = new SimpleDateFormat("yyyy/MM");
		if (mode == 2)
			simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date date = simpleDateFormat.parse(start);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			if (mode == 1)
				calender.add(Calendar.MONTH, value);
			if (mode == 2)
				calender.add(Calendar.DAY_OF_YEAR, value);
			simpleDateFormat.format(calender.getTime());
			return simpleDateFormat.format(calender.getTime()).toString();
		} catch (Exception e) {
			// System.out.print("DateUtil error:getVorTime()"+e.getMessage());
		}
		return start;
	}

	/**
	 * 2个字符串类型的月份之间差
	 * 
	 * @param datestr1
	 * @param datestr2
	 * @return 数值
	 * @throws ParseException
	 */
	public static int getMonthNum(String datestr1, String datestr2)
			throws ParseException {
		datestr1 = datestr1.replaceAll("-", "").replaceAll("/", "")
				.replaceAll("\\.", "");
		datestr2 = datestr2.replaceAll("-", "").replaceAll("/", "")
				.replaceAll("\\.", "");
		// System.out.println (datestr1+","+datestr2);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date date1 = format.parse(datestr1);
		Date date2 = format.parse(datestr2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return Math.abs((cal2.get(1) - cal1.get(1)) * 12 + (cal2.get(2) - cal1.get(2)));
	}
	
	public static int getMonthNum(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return Math.abs((cal2.get(1) - cal1.get(1)) * 12 + (cal2.get(2) - cal1.get(2)));
	}
	
	/**
	 * @Title: isFirstDayOfMonth 
	 * @Description: 判断日期是否为该月的第一天
	 * @param date
	 * @return boolean    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:04:49
	 */
	public static boolean isFirstDayOfMonth(Date date) {
		boolean b = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//该月的当前天数是第几天
		if(calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			b = true;
		}
		return b; 
	}
	
	/**
	 * @Title: isLastDayOfMonth 
	 * @Description: 判断日期是否为该月的最后一天
	 * @param date
	 * @return boolean    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:04:49
	 */
	public static boolean isLastDayOfMonth(Date date) {
		boolean b = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//该月的当前天数是第几天
		if(calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			b = true;
		}
		return b; 
	}
	
	/**
	 * @Title: getLastDayOfMonth 
	 * @Description: 获取参数日期当月的最后一天
	 * @param date
	 * @return Date    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:19:59
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//设置日期为该月最后一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		/*java.util.Date date_ = calendar.getTime();
        java.sql.Date  date1 = new java.sql.Date(date_.getTime());*/
		date = DateUtil.strDate((new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime()), null);
      	return date;
      	 
	}
	
	/**
	 * @Title: getFirstDayOfNextMonth 
	 * @Description: 获取参数日期当月的下月的第一天
	 * @param date
	 * @return Date    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:40:36
	 */
	public static Date getFirstDayOfNextMonth(Date date) {
		/*Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		//设置日期为该月最后一天
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();*/
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date_String = format.format(date);
        try {
            date = format.parse(date_String);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        //设置日期为该月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
	} 

	/**
	 * @Title: getNextYearDate 
	 * @Description: 明年的今天
	 * @param date
	 * @return Date 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月2日 上午9:06:41
	 */
	public static Date getNextYearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获得一年前的今天
	 * 
	 * @param date
	 * @return
	 *
	 * @author 金志明
	 * @date 2017年10月31日 下午4:46:14
	 */
	public static Date getLastYearDate(Date date)
	{
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
	}
	
	/**
	 * @Title: getDateAddDays 
	 * @Description: 参数日期加N天后的日期
	 * @param date
	 * @param days
	 * @return Date 
	 * @throws
	 * @author lvtu 
	 * @date 2015年9月2日 上午9:48:09
	 */
	public static Date getDateAddDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	/**
	 * @Title: getDaysOfInterval 
	 * @Description: 获取参数日期距离月底还有多少天
	 * @param date
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:46:16
	 */
	public static int getDaysOfInterval(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	/**
	 * @Title: getDaysOfInterval 
	 * @Description: 获取参数日期距离月初还有多少天
	 * @param date
	 * @return int    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午2:46:16
	 */
	public static int getDaysOfIntervalBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_MONTH) - calendar.getActualMinimum(Calendar.DAY_OF_MONTH)) + 1;
	}
	
	/**
	 * @Title: before 
	 * @Description: 判断第一个日期是否在第二个日期前
	 * @param startDate
	 * @param endDate
	 * @return boolean    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月26日 下午3:22:25
	 */
	public static boolean before(Date startDate, Date endDate) {
		//如果参数 Date 等于此 Date，则返回值 0；如果此 Date 在 Date 参数之前，则返回小于 0 的值；如果此 Date 在 Date 参数之后，则返回大于 0 的值。
		if(startDate.compareTo(endDate) < 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: getDaysOfMonth 
	 * @Description: 获取当前月份的天数
	 * @param date
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月27日 下午2:17:20
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @Title: getBetweenDays 
	 * @Description: 两个日期间隔天数
	 * @param d1
	 * @param d2
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月27日 下午4:04:17
	 */
	public static int getBetweenDays(Date d1, Date d2) {
		long betweenDays = 0;
		long to = d1.getTime();
	    long from = d2.getTime();
	    betweenDays = (to - from) / (1000 * 60 * 60 * 24);
		return (int) Math.abs(betweenDays);
	}
	/**
	 * @Title: getBetweenDaysInt 
	 * @Description: 两个日期间隔天数 只是按照日期来计算 不是按照时分秒来计算一天（2016-02-20与2016-02-21 相差一天 ）
	 * @param d1
	 * @param d2
	 * @return int 
	 * @throws
	 * @author baisong
	 * @date 2015年8月27日 下午4:04:17
	 */
	public static int getBetweenDaysInt(Date d1, Date d2) {
		//去掉时间内的时分秒 保留年月日
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateFormat.format(d1.getTime());
		d1 = DateUtil.strDate(date1, null);
		String date2 = dateFormat.format(d2.getTime());
		d2 = DateUtil.strDate(date2, null);
		
		long betweenDays = 0;
		long to = d1.getTime();
	    long from = d2.getTime();
	    betweenDays = (to - from) / (1000 * 60 * 60 * 24);
		return (int) Math.abs(betweenDays);
	}

    /**
     * 
     * @Title: getBetweenSecondsInt
     * @Description: 计算两个日期之间相差的毫秒数
     * @param d1
     * @param d2
     * @return 
     * @author: zhangyunfei
     * @time:2016年12月7日 下午5:52:19
     * history:
     * 1、2016年12月7日 Administrator 创建方法
     */
    public static long getBetweenSecondsInt(Date d1, Date d2)
    {
        long to = d1.getTime();
        long from = d2.getTime();
        return (to - from);
    }
	/**
	 * @Title: getYearMonthInt 
	 * @Description: 获取参数日期的int年月,如201508
	 * @param date
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月27日 下午5:21:42
	 */
	public static int getYearMonthInt(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String res = dateFormat.format(date);
		return Integer.parseInt(res);
	}
	
	/**
	 * @Title: getDayInt 
	 * @Description: 获取参数日期的日
	 * @param date
	 * @return int 
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月28日 下午4:01:48
	 */
	public static int getDayInt(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
     * 设置日期 参数int i是为了方法的公共使用 主要的目的就是计算的月份上有差异对月份进行加减
     * 
     * @param wmsFinaCreLoanApp
     * @return
     */
    public static Date setDatebyCalendar(Date sDate, int i)
    {

    	 java.sql.Date  date1;
         Calendar  calendar=new GregorianCalendar();   
         calendar.setTime(sDate);
         calendar.add(Calendar.MONTH, +i);
         java.util.Date date_=calendar.getTime();
         date1=new java.sql.Date(date_.getTime()); 
         return date1;
    }
    
	/**
	 * @Title: getDayOutTime 
	 * @Description: 把日期的时分秒去掉 
	 * @param d1
	 * @param d2
	 * @return int 
	 * @throws
	 * @author baisong
	 * @date 2016年4月17日 下午4:04:17
	 */
	public static Date getDayOutTime(Date d1) {
		//去掉时间内的时分秒 保留年月日
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateFormat.format(d1.getTime());
		d1 = DateUtil.strDate(date1, null);
		return d1;
	}
	
	/**
	 * @Title: getDayOutTime 
	 * @Description: 获取两个字符串类型时间相差的月份数
	 * @author zhangyunfei
	 * @date 2016年8月14日
	 */
	public static int getMonths(String startDate, String endDate){		
		int result = 0;
		Date date1 = DateUtil.strDate(startDate.toString(), null);
		Date date2 = DateUtil.strDate(endDate.toString(), null);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_MONTH);
		
		result += (year2 - year1) * 12;
		result += (month2 - month1);
		result += (day2 >= day1 ? 0 : -1);

		return result;
	}
	
	/**
	 * @Title: getDatePlusMonths 
	 * @Description: 参数加n个月后的日期
	 * @author zhangyunfei
	 * @date 2016年8月14日
	 */
	public static Date getDatePlusMonths(Date startDate, int months){		
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
        calendar.add(calendar.MONTH, months);
        return calendar.getTime();     
	}
	
	/**
	 * @Title: getDatePlusMonths 
	 * @Description: 参数加n个月后的日期
	 * @author zhangyunfei
	 * @date 2016年8月14日
	 */
	public static Date getDateMinusMonths(Date startDate, int months){		
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
        calendar.add(calendar.MONTH, -months);
        return calendar.getTime();     
	}
	
	/**
	 * @Title: getDaysofMonths 
	 * @Description: 获取赎回月的天数
	 * @author zhangyunfei
	 * @date 2016年9月06日
	 */
	public static int getDaysofMonths(Date date){		
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
	}
	
	/**
	 * @Title: getDaysOfYear 
	 * @Description: 获取赎回月所在年的天数
	 * @author zhangyunfei
	 * @date 2016年9月06日
	 */
	public static int getDaysOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); 
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	
	public static void main(String[] args) {
		/*Date date = new Date();
		date = DateUtil.strDate("2016-02-29", null);
		Date date2 = DateUtil.strDate("2015-09-01", null);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.MONTH, 12);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date3 = dateFormat.format(calendar.getTime());
		
		System.out.println(date3);*/

		System.out.println(getDateMinusMonths(strDate("2018-11-28", null),12));
	}
}
