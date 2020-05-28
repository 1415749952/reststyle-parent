package cn.ccsu.utils.dataUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * Description:时间工具类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-19
 * @Time: 10:12
 */
@Slf4j
public class DateUtil
{
    /**
     * 时间间隔：日
     */
    public final static int DATE_INTERVAL_DAY = 1;
    /**
     * 时间间隔：周
     */
    public final static int DATE_INTERVAL_WEEK = 2;
    /**
     * 时间间隔：月
     */
    public final static int DATE_INTERVAL_MONTH = 3;
    /**
     * 时间间隔：年
     */
    public final static int DATE_INTERVAL_YEAR = 4;
    /**
     * 时间间隔：小时
     */
    public final static int DATE_INTERVAL_HOUR = 5;
    /**
     * 时间间隔：分钟
     */
    public final static int DATE_INTERVAL_MINUTE = 6;
    /**
     * 时间间隔：秒
     */
    public final static int DATE_INTERVAL_SECOND = 7;
    /**
     * 时间格式：年月日
     */
    public final static String DATE_FORMAT_MDY = "MM/dd/yyyy";
    public final static String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public final static String DATE_FORMAT_YMD_ZH = "yyyy年MM月 ";
    public final static String DATE_FORMAT_YMDHMS_ZH = "yyyy年MM月dd日 ";
    public final static String DATE_FORMATE_YM = "yyyyMM";
    public final static String DATE_FORMATE_LC = "yy年MM月";
    public final static String DATE_FORMAT_MD = "MM-dd";
    public final static String DATE_FORMAT_YMD_NL = "yyyyMMdd";
    /**
     * 时间格式：年月日时分秒
     */
    public final static String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMATE_LX_YMDHMS = "yyyyMMddHHmmss";
    public static final String DATE_FORMATE_LX_YMDHMSS = "mmss";
    public static final String MONTH_PATTERN = "yyyy-MM";


    /**
     * 获取两个时间之间的天数
     *
     * @param maxDate 大的日期
     * @param minDate 小的日期
     * @return
     * @throws Exception
     */
    public static int getDay(Date maxDate, Date minDate) throws Exception
    {
        int day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        minDate = sdf.parse(sdf.format(minDate));
        maxDate = sdf.parse(sdf.format(maxDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(minDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(maxDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        if (between_days > 0)
        {
            day = Integer.parseInt(String.valueOf(between_days));
        }
        return day;
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay))
        {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        //获取当前年
        int yearNow = cal.get(Calendar.YEAR);
        //获取当前月
        int monthNow = cal.get(Calendar.MONTH);
        //获取当日
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        //获取出生那年
        int yearBirth = cal.get(Calendar.YEAR);
        //获取出生月
        int monthBirth = cal.get(Calendar.MONTH);
        //获取出生日
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //年纪
        int age = yearNow - yearBirth;
        //如果现在的月份小于生日的月份,年龄-1,如果等于出生的月份,日前小于生日日期,年龄-1
        if (monthNow <= monthBirth)
        {
            if (monthNow == monthBirth)
            {
                if (dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                }
            }
            else
            {
                age--;
            }
        }
        return age;
    }


    /**
     * 获得时间
     *
     * @param date       时间
     * @param dateFormat 时间格式
     * @return 时间
     * @author sunju
     * @creationDate. 2012-7-31 下午03:06:05
     */
    public static Date getDate(Date date, String dateFormat)
    {
        return parseDate(dateFormat(date, dateFormat), dateFormat);
    }

    /**
     * 获得当前日期(年月日)
     *
     * @return 当前时间（yyyy-MM-dd）
     * @author sunju
     * @creationDate. 2010-8-27 下午05:11:23
     */
    public static Date getNowDate()
    {
        return parseDate(dateFormat(new Date(), DATE_FORMAT_YMD), DATE_FORMAT_YMD);
    }

    /**
     * 获取当前时间字符串(年月日)
     *
     * @return 时间字符串
     * @author sunju
     * @creationDate. 2011-5-4 下午08:22:34
     */
    public static String getNowStringDate()
    {
        return dateFormat(new Date(), DATE_FORMAT_YMD);
    }

    /**
     * 获得当前时间(年月日时分秒)
     *
     * @return 当前时间（yyyy-MM-dd HH:mm:ss）
     * @author sunju
     * @creationDate. 2010-8-27 下午05:12:57
     */
    public static Date getNowTime()
    {
        return parseDate(dateFormat(new Date(), DATE_FORMAT_YMDHMS), DATE_FORMAT_YMDHMS);
    }

    /**
     * 获取当前时间字符串(年月日时分秒)
     *
     * @return 时间字符串
     * @author sunju
     * @creationDate. 2014-3-10 下午03:16:42
     */
    public static String getNowStringTime()
    {
        return dateFormat(new Date(), DATE_FORMAT_YMDHMS);
    }

    /**
     * 获得明天的日期字符串(格式年月日)
     *
     * @return 明天的日期
     * @author sunju
     * @creationDate. 2011-5-4 下午08:19:28
     */
    public static String getTomorrowStringDate()
    {
        return dateFormat(getTomorrowTime(), DATE_FORMAT_YMD);
    }

    /**
     * 获得明天的日期(年月日)
     *
     * @return 明天的日期
     * @author sunju
     * @creationDate. 2011-5-4 下午08:19:57
     */
    public static Date getTomorrowDate()
    {
        return dateAdd(DATE_INTERVAL_DAY, getNowDate(), 1);
    }

    /**
     * 获得明天的时间(年月日时分秒)
     *
     * @return 明天的时间
     * @author sunju
     * @creationDate. 2011-5-4 下午08:20:19
     */
    public static Date getTomorrowTime()
    {
        return dateAdd(DATE_INTERVAL_DAY, getNowTime(), 1);
    }

    /**
     * 获得昨天的日期
     *
     * @return 昨天的日期
     * @author sunju
     * @creationDate. 2013-10-22 下午03:54:48
     */
    public static Date getYesterdayDate()
    {
        return dateAdd(DATE_INTERVAL_DAY, getNowDate(), -1);
    }

    /**
     * 获得昨天的时间(年月日时分秒)
     *
     * @return 昨天的日期
     * @author sunju
     * @creationDate. 2013-10-22 下午03:54:48
     */
    public static Date getYseterdayTime()
    {
        return dateAdd(DATE_INTERVAL_DAY, getNowTime(), -1);
    }

    /**
     * 获取当月第一天
     *
     * @return 日期
     * @author sunju
     * @creationDate. 2013-10-22 下午03:45:53
     */
    public static Date getMonthFirst()
    {
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1); // 设为当前月的1号
        return getDate(lastDate.getTime(), DATE_FORMAT_YMD);
    }

    /**
     * 获得下个月第一天的日期
     *
     * @return 日期
     * @author sunju
     * @creationDate. 2013-10-22 下午03:52:38
     */
    public static Date getNextMonthFirst()
    {
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.MONTH, 1); // 加一个月
        lastDate.set(Calendar.DATE, 1);  // 把日期设置为当月第一天
        return getDate(lastDate.getTime(), DATE_FORMAT_YMD);
    }

    public static Date getMonthStartTime(Date date, int monthDiff)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        if (date != null)
        {
            calendar.setTime(date);
        }
        else
        {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        if (monthDiff != 0)
        {
            calendar.add(Calendar.MONTH, monthDiff);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date resultDate = calendar.getTime();
        return resultDate;
    }

    public static Date getMonthEndTime(Date date, int monthDiff)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        if (date != null)
        {
            calendar.setTime(date);
        }
        else
        {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        if (monthDiff != 0)
        {
            calendar.add(Calendar.MONTH, monthDiff);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date resultDate = calendar.getTime();
        return resultDate;
    }

    /**
     * 取得当前星期几
     *
     * @param date 时间
     * @return 星期
     * @author sunju
     * @creationDate. 2010-9-20 下午05:34:36
     */
    public static String getWeekOfDate(Date date)
    {
        if (date == null)
        {
            return null;
        }
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
        {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 时间类型转换返回字符串
     *
     * @param date       时间
     * @param dateFormat 时间格式
     * @return 转换后的时间字符串
     * @author sunju
     * @creationDate. 2010-8-27 下午05:18:37
     */
    public static String dateFormat(Date date, String dateFormat)
    {
        if (date == null)
        {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 字符串时间类型转换返回Date类型
     *
     * @param date       字符串时间
     * @param dateFormat 时间格式
     * @return 转换后的时间
     * @author sunju
     * @creationDate. 2010-8-27 下午05:23:35
     */
    public static Date parseDate(String date, String dateFormat)
    {
        if (StringUtils.isEmpty(date))
        {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try
        {
            return format.parse(date);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }

    public static Date getDateStartTime(Date date)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        if (date != null)
        {
            calendar.setTime(date);
        }
        else
        {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date resultDate = calendar.getTime();
        return resultDate;
    }

    public static Date getDateEndTime(Date date)
    {
        Calendar calendar = GregorianCalendar.getInstance();
        if (date != null)
        {
            calendar.setTime(date);
        }
        else
        {
            calendar.setTimeInMillis(System.currentTimeMillis());
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date resultDate = calendar.getTime();
        return resultDate;
    }

    /**
     * 加时间
     *
     * @param interval 增加项，可以是天数、月份、年数、时间、分钟、秒
     * @param date     时间
     * @param num      加的数目
     * @return 时间加后的时间
     * @author sunju
     * @creationDate. 2010-8-27 下午05:28:06
     */
    public static Date dateAdd(int interval, Date date, int num)
    {
        if (date == null)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (interval)
        {
            case DATE_INTERVAL_DAY:
                calendar.add(Calendar.DATE, num);
                break;
            case DATE_INTERVAL_WEEK:
                calendar.add(Calendar.WEEK_OF_MONTH, num);
                break;
            case DATE_INTERVAL_MONTH:
                calendar.add(Calendar.MONTH, num);
                break;
            case DATE_INTERVAL_YEAR:
                calendar.add(Calendar.YEAR, num);
                break;
            case DATE_INTERVAL_HOUR:
                calendar.add(Calendar.HOUR, num);
                break;
            case DATE_INTERVAL_MINUTE:
                calendar.add(Calendar.MINUTE, num);
                break;
            case DATE_INTERVAL_SECOND:
                calendar.add(Calendar.SECOND, num);
                break;
            default:
        }
        return calendar.getTime();
    }

    /**
     * 两个时间时间差[前面时间和比较时间比,小于比较时间返回负数]
     *
     * @param interval 比较项，可以是天数、月份、年数、时间、分钟、秒
     * @param date     时间
     * @param compare  比较的时间
     * @return 时间差(保留两位小数点, 小数点以后两位四舍五入)
     * @author sunju
     * @creationDate. 2010-8-27 下午05:26:13
     */
    public static double getDateDiff(int interval, Date date, Date compare)
    {
        if (date == null || compare == null)
        {
            return 0;
        }
        double result = 0;
        double time = 0;
        Calendar calendar = null;
        switch (interval)
        {
            case DATE_INTERVAL_DAY:
                time = date.getTime() - compare.getTime();
                result = time / 1000d / 60d / 60d / 24d;
                break;
            case DATE_INTERVAL_HOUR:
                time = date.getTime() - compare.getTime();
                result = time / 1000d / 60d / 60d;
                break;
            case DATE_INTERVAL_MINUTE:
                time = date.getTime() / 1000d / 60d;
                result = time - compare.getTime() / 1000d / 60d;
                break;
            case DATE_INTERVAL_MONTH:
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                time = calendar.get(Calendar.YEAR) * 12d;
                calendar.setTime(compare);
                time -= calendar.get(Calendar.YEAR) * 12d;
                calendar.setTime(date);
                time += calendar.get(Calendar.MONTH);
                calendar.setTime(compare);
                result = time - calendar.get(Calendar.MONTH);
                break;
            case DATE_INTERVAL_SECOND:
                time = date.getTime() - compare.getTime();
                result = time / 1000d;
                break;
            case DATE_INTERVAL_WEEK:
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                time = calendar.get(Calendar.YEAR) * 52d;
                calendar.setTime(compare);
                time -= calendar.get(Calendar.YEAR) * 52d;
                calendar.setTime(date);
                time += calendar.get(Calendar.WEEK_OF_YEAR);
                calendar.setTime(compare);
                result = time - calendar.get(Calendar.WEEK_OF_YEAR);
                break;
            case DATE_INTERVAL_YEAR:
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                time = calendar.get(Calendar.YEAR);
                calendar.setTime(compare);
                result = time - (double) calendar.get(Calendar.YEAR);
                break;
            default:
                break;
        }
        return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取时间差[前面时间和比较时间比,小于比较时间返回负数]
     *
     * @param level   返回时间等级(1:返回天;2:返回天-小时;3:返回天-小时-分4:返回天-小时-分-秒;)
     * @param date    时间
     * @param compare 比较的时间
     * @return 时间差(保留两位小数点, 小数点以后两位四舍五入)
     * @author sunju
     * @creationDate. 2010-9-1 下午04:36:07
     */
    public static String getDateBetween(Integer level, Date date, Date compare)
    {
        if (date == null || compare == null)
        {
            return null;
        }
        long s = new BigDecimal(getDateDiff(DATE_INTERVAL_SECOND, date, compare)).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
        int ss = 1;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = s / dd;
        long hour = (s - day * dd) / hh;
        long minute = (s - day * dd - hour * hh) / mi;
        long second = (s - day * dd - hour * hh - minute * mi) / ss;
        String flag = (day < 0 || hour < 0 || minute < 0 || second < 0) ? "-" : "";
        day = Math.abs(day);
        hour = Math.abs(hour);
        minute = Math.abs(minute);
        second = Math.abs(second);
        StringBuilder result = new StringBuilder(flag);
        switch (level)
        {
            case 1:
                if (day != 0)
                {
                    result.append(day).append("天");
                }
                break;
            case 2:
                if (day != 0)
                {
                    result.append(day).append("天");
                }
                if (hour != 0)
                {
                    result.append(hour).append("小时");
                }
                break;
            case 3:
                if (day != 0)
                {
                    result.append(day).append("天");
                }
                if (hour != 0)
                {
                    result.append(hour).append("小时");
                }
                if (minute != 0)
                {
                    result.append(minute).append("分");
                }
                break;
            case 4:
                if (day != 0)
                {
                    result.append(day).append("天");
                }
                if (hour != 0)
                {
                    result.append(hour).append("小时");
                }
                if (minute != 0)
                {
                    result.append(minute).append("分");
                }
                if (second != 0)
                {
                    result.append(second).append("秒");
                }
                break;
            default:
                break;
        }
        return result.toString();
    }

    /**
     * 时间是否是今天
     *
     * @param date 时间
     * @return 布尔
     * @author sunju
     * @creationDate. 2011-5-4 下午08:24:58
     */
    public static boolean isToday(Date date)
    {
        if (date == null)
        {
            return false;
        }
        return getNowStringDate().equals(dateFormat(date, DATE_FORMAT_YMD));
    }

    /**
     * 时间是否合法
     *
     * @param date       时间
     * @param dateFormat 时间格式
     * @return
     * @author sunju
     * @creationDate. 2012-6-19 下午01:07:59
     */
    public static boolean isValidDate(String date, String dateFormat)
    {
        try
        {
            new SimpleDateFormat(dateFormat).parse(date);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 是否大于现在的时间
     * true 大于
     * <ul>
     * <li>
     * <b>原因：<br/>
     * <p>
     * [2014-8-27]gaozhanglei<br/>
     *
     * @param date
     * @param dateFormate
     * @return TODO
     * </p>
     * </li>
     * </ul>
     */
    public static boolean isgtnow(String date, String dateFormate)
    {
        boolean flag = false;
        try
        {
            Date nowdt = new Date();
            Date compt = DateUtil.parseDate(date, dateFormate);
            long nowtm = nowdt.getTime();
            long comptm = compt.getTime();
            if (comptm > nowtm)
            {
                flag = true;
            }
        }
        catch (Exception e)
        {
            flag = false;
        }

        return flag;
    }

    /**
     * 得到本月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat(calendar.getTime(), DATE_FORMAT_YMD);
    }

    /**
     * 获取时间在当月的天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMaxDayOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //当前时间转时间戳
    public static String timeStamp()
    {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
        String time = "1970-01-06 11:45:55";
        String date = null;
        try
        {
            date = format.parse(time).toString();
        }
        catch (ParseException e)
        {
        }
        return date;
    }

    /**
     * 计算产品到期时间
     *
     * @param productCycle 产品包周期值
     * @return 产品包到期时间
     */
    public static Date getDueTime(String productCycle)
    {
        Date payTime = new Date(); //付费成功时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payTime);
        switch (productCycle)
        {
            case "0":    // 包年
                calendar.add(Calendar.MONTH, 13);
                break;
            case "1":       //包半年
                calendar.add(Calendar.MONTH, 7);
                break;
            case "2":       //包季
                calendar.add(Calendar.MONTH, 4);
                break;
            case "3":       //包月
                calendar.add(Calendar.MONTH, 1);
                break;
            case "4":       //包天
                calendar.add(Calendar.DATE, 1);
                break;
            case "5":       //单点
                calendar.add(Calendar.MONTH, 1);
                break;
            default:
                break;
        }
        return calendar.getTime();
    }

    /***
     * 用户产品包实际不可看时间（用于产品通知）
     * @param productCycle
     * @return
     */
    public static Date getActualDueTime(String productCycle)
    {
        Date payTime = new Date(); //付费成功时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(payTime);
        switch (productCycle)
        {
            case "0":    // 包年
                calendar.add(Calendar.MONTH, 13);
                break;
            case "1":       //包半年
                calendar.add(Calendar.MONTH, 7);
                break;
            case "2":       //包季
                calendar.add(Calendar.MONTH, 4);
                break;
            case "3":       //包月
                calendar.add(Calendar.MONTH, 1);
                break;
            case "4":       //包天
                calendar.add(Calendar.DATE, 1);
                break;
            case "5":       //单点
                calendar.add(Calendar.MONTH, 1);
                break;
            default:
                break;
        }
        return calendar.getTime();
    }


    /**
     * 将Date日期时间格式转成指定字符串格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date:
     * @return: java.lang.String
     */
    public static String dateToDateTimeString(Date date) throws ParseException
    {
        return DateUtil.dateFormat(date, DateUtil.DATE_FORMAT_YMDHMS);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    // 下面暂未实践 ----------------------------------------------------------------

    /**
     * 日期相加减天数
     *
     * @param date        如果为Null，则为当前时间
     * @param days        加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return
     * @throws ParseException
     */
    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException
    {
        if (date == null)
        {
            date = new Date();
        }
        if (!includeTime)
        {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }


    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StringUtils.DATE_FORMAT_YMDHMS || StringUtils.DATE_FORMAT_YMD，如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException
    {
        if (StringUtils.isBlank(pattern))
        {
            pattern = DateUtil.DATE_FORMAT_YMD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }

    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     *
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateString(Date dateTime) throws ParseException
    {
        String dateTimeString = DateUtil.dateFormat(dateTime, DateUtil.DATE_FORMAT_YMDHMS);
        return dateTimeString.substring(0, 10);
    }

    /**
     * 当时、分、秒为00:00:00时，将日期时间格式成只有日期的字符串，
     * 当时、分、秒不为00:00:00时，直接返回
     *
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException
    {
        String dateTimeString = DateUtil.dateFormat(dateTime, DateUtil.DATE_FORMAT_YMDHMS);
        if (dateTimeString.endsWith("00:00:00"))
        {
            return dateTimeString.substring(0, 10);
        }
        else
        {
            return dateTimeString;
        }
    }

    /**
     * 将日期时间格式成日期对象，和dateParse互用
     *
     * @param dateTime Date
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours     加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param minutes   加减的分钟
     * @return
     */
    public static Date dateAddMinutes(Date startDate, int minutes)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减秒数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param seconds   加减的秒数
     * @return
     */
    public static Date dateAddSeconds(Date startDate, int seconds)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days      加减的天数
     * @return Date
     */
    public static Date dateAddDays(Date startDate, int days)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return c.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param months    加减的月数
     * @return Date
     */
    public static Date dateAddMonths(Date startDate, int months)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return c.getTime();
    }

    /**
     * 时间加减年数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param years     加减的年数
     * @return Date
     */
    public static Date dateAddYears(Date startDate, int years)
    {
        if (startDate == null)
        {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return c.getTime();
    }

    /**
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
     *
     * @param myDate      时间
     * @param compareDate 要比较的时间
     * @return int
     */
    public static int dateCompare(Date myDate, Date compareDate)
    {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    /**
     * 获取两个时间中最小的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMin(Date date, Date compareDate)
    {
        if (date == null)
        {
            return compareDate;
        }
        if (compareDate == null)
        {
            return date;
        }
        if (1 == dateCompare(date, compareDate))
        {
            return compareDate;
        }
        else if (-1 == dateCompare(date, compareDate))
        {
            return date;
        }
        return date;
    }

    /**
     * 获取两个时间中最大的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMax(Date date, Date compareDate)
    {
        if (date == null)
        {
            return compareDate;
        }
        if (compareDate == null)
        {
            return date;
        }
        if (1 == dateCompare(date, compareDate))
        {
            return date;
        }
        else if (-1 == dateCompare(date, compareDate))
        {
            return compareDate;
        }
        return date;
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException
    {
        Date dateStart = dateParse(dateFormat(startDate, DATE_FORMAT_YMD), DATE_FORMAT_YMD);
        Date dateEnd = dateParse(dateFormat(endDate, DATE_FORMAT_YMD), DATE_FORMAT_YMD);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException
    {
        return dateBetween(startDate, endDate) + 1;
    }

    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     *
     * @param date
     * @return
     */
    public static int getYear(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     *
     * @param date
     * @return
     */
    public static int getDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 根据时间获取当月最大的日期
     * <li>2017-02-13，返回2017-02-28</li>
     * <li>2016-02-13，返回2016-02-29</li>
     * <li>2016-01-11，返回2016-01-31</li>
     *
     * @param date Date
     * @return
     * @throws Exception
     */
    public static Date maxDateOfMonth(Date date) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param date Date
     * @return
     * @throws Exception
     */
    public static Date minDateOfMonth(Date date) throws Exception
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }
}
