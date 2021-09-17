package com.demo.common.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 描述:公共日期工具类
 */
public class DateUtils {
	
 
  public static String DATE_FORMAT = "yyyy-MM-dd";
 
  public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
 
  public static String DATE_FORMAT_CHINESE = "yyyy年M月d日";
  /**
   * 获取当前日期
   * @return
   * 
   */
  public static String getCurrentDate() {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
    datestr = df.format(new Date());
    return datestr;
  }
 
  /**
   * 获取当前日期时间
   * 
   * 
   * @return
   * 
   */
  public static String getCurrentDateTime() {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
    datestr = df.format(new Date());
    return datestr;
  }
  /**
   * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
   */
  public static String getDate(String pattern) {
      return DateFormatUtils.format(new Date(), pattern);
  }
  /**
   * 获取当前日期时间
   * 
   * 
   * @return
   * 
   */
  public static String getCurrentDateTime(String Dateformat) {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(Dateformat);
    datestr = df.format(new Date());
    return datestr;
  }
 
  public static String dateToDateTime(Date date) {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
    datestr = df.format(date);
    return datestr;
  }
  /**
   * 将字符串日期转换为日期格式
   * 
   * 
   * @param datestr
   * @return
   * 
   */
  public static Date stringToDate(String datestr) {
 
      if(datestr ==null ||datestr.equals("")){
        return null;
      }
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
    try {
      date = df.parse(datestr);
    } catch (ParseException e) {
      date=DateUtils.stringToDate(datestr,"yyyyMMdd");
    }
    return date;
  }
  /**
   * 将字符串日期转换为日期格式
   * 
   * 
   * @param datestr
   * @return
   * 
   */
  public static Date stringToDateTime(String datestr) {
 
      if(datestr ==null ||datestr.equals("")){
        return null;
      }
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
    try {
      date = df.parse(datestr);
    } catch (ParseException e) {
      date=DateUtils.stringToDate(datestr,"yyyyMMdd HH:mm:ss");
    }
    return date;
  }
 
  /**
   * 将字符串日期转换为日期格式
   * 自定義格式
   * 
   * @param datestr
   * @return
   * 
   */
  public static Date stringToDate(String datestr, String dateformat) {
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat(dateformat);
    try {
      date = df.parse(datestr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * 获取两个日期间隔的所有日期
   * @param start 格式必须为'YYYY-MM-DD eg: 2019-05-25'
   * @param end 格式必须为'YYYY-MM-DD eg:2019-05-25'
   * @return
   */
  public static List<String> getBetweenDate(String start, String end) {
    List<String> list = new ArrayList<>();
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    long distance = ChronoUnit.DAYS.between(startDate, endDate);
    if (distance < 1) {
      return list;
    }
    Stream.iterate(startDate, d -> {
      return d.plusDays(1);
    }).limit(distance + 1).forEach(f -> {
      list.add(f.toString());
    });
    return list;
  }
 
 
  /**
   * 将日期格式日期转换为字符串格式
   * 
   * 
   * @param date
   * @return
   * 
   */
  public static String dateToString(Date date) {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
    datestr = df.format(date);
    return datestr;
  }
 
  /**
   * 将日期格式日期转换为字符串格式 自定義格式
   * 
   * @param date
   * @param dateformat
   * @return
   */
  public static String dateToString(Date date, String dateformat) {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(dateformat);
    datestr = df.format(date);
    return datestr;
  }
 
  /**
   * 获取日期的DAY值
   * 
   * 
   * @param date
   *      输入日期
   * @return
   * 
   */
  public static int getDayOfDate(Date date) {
    int d = 0;
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    d = cd.get(Calendar.DAY_OF_MONTH);
    return d;
  }
 
  /**
   * 获取日期的MONTH值
   * 
   * 
   * @param date
   *      输入日期
   * @return
   * 
   */
  public static int getMonthOfDate(Date date) {
    int m = 0;
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    m = cd.get(Calendar.MONTH) + 1;
    return m;
  }
 
  /**
   * 获取日期的YEAR值
   * 
   * 
   * @param date
   *      输入日期
   * @return
   * 
   */
  public static int getYearOfDate(Date date) {
    int y = 0;
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    y = cd.get(Calendar.YEAR);
    return y;
  }
 
  /**
   * 获取星期几
   * 
   * 
   * @param date
   *      输入日期
   * @return
   * 
   */
  public static int getWeekOfDate(Date date) {
    int wd = 0;
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
    return wd;
  }
 
  /**
   * 获取输入日期的当月第一天
   * 
   * 
   * @param date
   *      输入日期
   * @return
   * 
   */
  public static Date getFirstDayOfMonth(Date date) {
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    cd.set(Calendar.DAY_OF_MONTH, 1);
    return cd.getTime();
  }
 
  /**
   * 获得输入日期的当月最后一天
   * 
   * @param date
   */
  public static Date getLastDayOfMonth(Date date) {
    return DateUtils.addDay(DateUtils.getFirstDayOfMonth(DateUtils.addMonth(date, 1)), -1);
  }
 
  /**
   * 判断是否是闰年
   * 
   * 
   * @param date
   *      输入日期
   * @return 是true 否false
   * 
   */
  public static boolean isLeapYEAR(Date date) {
 
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
    int year = cd.get(Calendar.YEAR);
 
    if (year % 4 == 0 && year % 100 != 0 | year % 400 == 0) {
      return true;
    } else {
      return false;
    }
  }
 
  /**
   * 根据整型数表示的年月日，生成日期类型格式
   * 
   * @param year
   *      年
   * @param month
   *      月
   * @param day
   *      日
   * @return
   * 
   */
  public static Date getDateByYMD(int year, int month, int day) {
    Calendar cd = Calendar.getInstance();
    cd.set(year, month-1, day);
    return cd.getTime();
  }
 
  /**
   * 获取年周期对应日
   * 
   * @param date
   *      输入日期
   * @param iyear
   *      年数  負數表示之前
   * @return
   * 
   */
  public static Date getYearCycleOfDate(Date date, int iyear) {
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
 
    cd.add(Calendar.YEAR, iyear);
 
    return cd.getTime();
  }
 
  /**
   * 获取月周期对应日
   * 
   * @param date
   *      输入日期
   * @param i
   * @return
   * 
   */
  public static Date getMonthCycleOfDate(Date date, int i) {
    Calendar cd = Calendar.getInstance();
    cd.setTime(date);
 
    cd.add(Calendar.MONTH, i);
 
    return cd.getTime();
  }
 
  /**
   * 计算 fromDate 到 toDate 相差多少年
   * 
   * @param fromDate
   * @param toDate
   * @return 年数
   * 
   */
  public static int getYearByMinusDate(Date fromDate, Date toDate) {
    Calendar df=Calendar.getInstance();
    df.setTime(fromDate);
 
    Calendar dt=Calendar.getInstance();
    dt.setTime(toDate);
 
    return dt.get(Calendar.YEAR)-df.get(Calendar.YEAR);
  }
 
  /**
   * 计算 fromDate 到 toDate 相差多少个月
   * 
   * @param fromDate
   * @param toDate
   * @return 月数
   * 
   */
  public static int getMonthByMinusDate(Date fromDate, Date toDate) {
    Calendar df=Calendar.getInstance();
    df.setTime(fromDate);
 
    Calendar dt=Calendar.getInstance();
    dt.setTime(toDate);
 
    return dt.get(Calendar.YEAR)*12+dt.get(Calendar.MONTH)-
        (df.get(Calendar.YEAR)*12+df.get(Calendar.MONTH));
  }
 
  /**
   * 计算 fromDate 到 toDate 相差多少天
   * 
   * @param fromDate
   * @param toDate
   * @return 天数
   * 
   */
  public static long getDayByMinusDate(Object fromDate, Object toDate) {
 
    Date f=DateUtils.chgObject(fromDate);
 
    Date t=DateUtils.chgObject(toDate);
 
    long fd=f.getTime();
    long td=t.getTime();
 
    return (td-fd)/(24L * 60L * 60L * 1000L);
  }
  /**
   * 计算 fromDate 到 toDate 相差多少秒
   * 
   * @param fromDate
   * @param toDate
   * @return 天数
   * 
   */
  public static long getDayByMinus(Date fromDate, Date toDate) {
	long fd=fromDate.getTime();
    long td=toDate.getTime();
 
    return (td-fd)/(1000L);
  }
  
  /**
   * 计算年龄
   * 
   * @param birthday
   *      生日日期
   * @param calcDate
   *      要计算的日期点
   * @return
   * 
   */
  public static int calcAge(Date birthday, Date calcDate) {
 
    int cYear=DateUtils.getYearOfDate(calcDate);
    int cMonth=DateUtils.getMonthOfDate(calcDate);
    int cDay=DateUtils.getDayOfDate(calcDate);   
    int bYear=DateUtils.getYearOfDate(birthday);
    int bMonth=DateUtils.getMonthOfDate(birthday);
    int bDay=DateUtils.getDayOfDate(birthday);
 
    if(cMonth>bMonth||(cMonth==bMonth&&cDay>bDay)){
      return cYear-bYear;
    }else{
      return cYear-1-bYear;
    }
  }
 
  /**
   * 从身份证中获取出生日期
   * 
   * @param idno
   *      身份证号码
   * @return
   * 
   */
  public static String getBirthDayFromIDCard(String idno) {
    Calendar cd = Calendar.getInstance();
    if (idno.length() == 15) {
      cd.set(Calendar.YEAR, Integer.valueOf("19" + idno.substring(6, 8))
          .intValue());
      cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(8, 10))
          .intValue() - 1);
      cd.set(Calendar.DAY_OF_MONTH,
          Integer.valueOf(idno.substring(10, 12)).intValue());
    } else if (idno.length() == 18) {
      cd.set(Calendar.YEAR, Integer.valueOf(idno.substring(6, 10))
          .intValue());
      cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(10, 12))
          .intValue() - 1);
      cd.set(Calendar.DAY_OF_MONTH,
          Integer.valueOf(idno.substring(12, 14)).intValue());
    }
    return DateUtils.dateToString(cd.getTime());
  }

  public static Date addSecond(Date date, int second) {
    Calendar cd = Calendar.getInstance();

    cd.setTime(date);

    cd.add(Calendar.SECOND, second);

    return cd.getTime();
  }

  public static Date addHour(Date date, int hour) {
    Calendar cd = Calendar.getInstance();

    cd.setTime(date);

    cd.add(Calendar.HOUR_OF_DAY, hour);

    return cd.getTime();
  }

  public static Date addMinute(Date date, int minute) {
    Calendar cd = Calendar.getInstance();

    cd.setTime(date);

    cd.add(Calendar.MINUTE, minute);

    return cd.getTime();
  }

  /**
   * 在输入日期上增加（+）或减去（-）天数
   * 
   * @param date
   *      输入日期
   * @param iday
   *      要增加或减少的天数
   */
  public static Date addDay(Date date, int iday) {
    Calendar cd = Calendar.getInstance();
 
    cd.setTime(date);
 
    cd.add(Calendar.DAY_OF_MONTH, iday);
 
    return cd.getTime();
  }
 
  /**
   * 在输入日期上增加（+）或减去（-）月份
   * 
   * @param date
   *      输入日期
   * @param imonth
   *      要增加或减少的月分数
   */
  public static Date addMonth(Date date, int imonth) {
    Calendar cd = Calendar.getInstance();
 
    cd.setTime(date);
 
    cd.add(Calendar.MONTH, imonth);
 
    return cd.getTime();
  }
 
  /**
   * 在输入日期上增加（+）或减去（-）年份
   * 
   * @param date
   *      输入日期
   * @param iyear
   *      要增加或减少的年数
   */
  public static Date addYear(Date date, int iyear) {
    Calendar cd = Calendar.getInstance();
 
    cd.setTime(date);
 
    cd.add(Calendar.YEAR, iyear);
 
    return cd.getTime();
  }
 
  /**
   * 將OBJECT類型轉換為Date
   * @param date
   * @return
   */
  public static Date chgObject(Object date){
 
    if(date!=null&&date instanceof Date){
      return (Date)date;
    }
 
    if(date!=null&&date instanceof String){
      return DateUtils.stringToDateTime((String)date);
    }
 
    return null;
 
  }
 
  public static long getAgeByBirthday(String date){
 
    Date birthday = stringToDate(date, "yyyy-MM-dd");
    long sec = new Date().getTime() - birthday.getTime();
 
    long age = sec/(1000*60*60*24)/365;
 
    return age;
  }
  /**
         * 根据选中事件段获取日期列表
	* @param dBegin
	* @param dEnd
	* @return
	*/
	public static List<String> findDates(Date dBegin, Date dEnd){
		List<String> lDate = new ArrayList<String>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		lDate.add(sd.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime()))
		{
			   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			   calBegin.add(Calendar.DAY_OF_MONTH, 1);
			   lDate.add(sd.format(calBegin.getTime()));
		}
		return lDate;
	}
	  /**
     * 根据选中事件段获取日期列表
	* @param dBegin
	* @param dEnd
	* @return
	*/
	public static List<String> findDatesByString(String dBegin, String dEnd){
		List<String> lDate = new ArrayList<String>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		Date dateBegin;
		try {
			dateBegin = sd.parse(dBegin);
			calBegin.setTime(dateBegin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		Date dateEnd;
		try {
			dateEnd = sd.parse(dEnd);
			calEnd.setTime(dateEnd);
			// 测试此日期是否在指定日期之后
			while (dateEnd.after(calBegin.getTime()))
			{
			   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			   calBegin.add(Calendar.DAY_OF_MONTH, 1);
			   lDate.add(sd.format(calBegin.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lDate;
	}
	/**
	 * 获得指定日期的前一天
	 * @param specifiedDay 指定日期
	 * @param pattern 需要返回的日期格式，例如：yyyy-MM-dd HH:mm:ss
	 * @return 前一天日期
	 */
	public static String getSpecifiedDayBefore(String pattern,String specifiedDay){

		Calendar c = Calendar.getInstance();
		Date date=null;
		try {
			date = new SimpleDateFormat(pattern).parse(specifiedDay);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		c.setTime(date);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE,day-1);
		String dayBefore=new SimpleDateFormat(pattern).format(c.getTime());
		return dayBefore;
	}

  /**
   * @param args
   */
  public static void main(String[] args) {
    //String temp = DateUtil.dateToString(getLastDayOfMonth(new Date()),
    ///   DateUtil.DATE_FORMAT_CHINESE);
    //String s=DateUtil.dateToString(DateUtil.addDay(DateUtil.addYear(new Date(),1),-1));
 
//	Calendar calendar = Calendar.getInstance();
//  	calendar.setTime(DateUtils.stringToDate("2019-08-09 10:00","yyyy-MM-dd HH:mm"));
//  	calendar.add(Calendar.MINUTE, 70 / 60);
	  Date nowdate=new Date();
	  Date creatime=stringToDateTime("2020-12-30 16:18:43");
      
      long time=getDayByMinus(creatime, nowdate);

      
  	System.out.println(time);
  }
 
}