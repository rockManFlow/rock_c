package com.kuark.tool.advance.advance20191120;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

/**
 * @author rock
 * @detail 时间类型处理
 * @date 2019/11/26 9:55
 */
public class RockDateUtils {
    /**
     * Calendar.YEAR
     * Calendar.DATE 当前日
     *
     Calendar.DAY_OF_MONTH：日期，和 Calendar.DATE 相同
     Calendar.HOUR：12 小时制的小时数
     Calendar.HOUR_OF_DAY：24小时制的小时数
     Calendar.MINUTE：分钟
     Calendar.SECOND：秒
     Calendar.DAY_OF_WEEK：周几
     */
    public void getCurrentMonth(){
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;////12，当前月，注意加 1
    }

    /**
     * month/day/minute等类似
     * @param date
     * @param num
     */
    public void dateAddYear(Date date,int num){
        if(date==null){
            date=new Date();
        }
        //根据某个特定的时间 date (Date 型) 计算
        Calendar specialDate = Calendar.getInstance();
        specialDate.setTime(date); //注意在此处将 specialDate 的值改为特定日期
        specialDate.add(Calendar.YEAR, num); //特定时间的1年前
    }

    public void compareA2B(Date dateA,Date dateB){
        System.out.println(dateA.before(dateB)); //true，当 dateA 小于 dateB 时，为 true，否则为 false
        System.out.println(dateB.after(dateA)); //true，当 dateB 大于 dateA 时，为 true，否则为 false
        System.out.println(dateA.compareTo(dateB)); //-1，当 dateA 小于 dateB 时，为 -1
        System.out.println(dateB.compareTo(dateA)); //1，当 dateB 大于 dateA 时，为 1
        System.out.println(dateB.compareTo(dateB)); //0，当两个日期相等时，为 0
    }

    //设置时区---防止因为时区的问题，导致时间不一致
    public static String format2Y_M_D_H_M_S(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Africa/Lagos"));
        return format.format(new Date());
    }

    /**
     * jdk1.8 时间日期属性
     */
    public static void dateLearn(){
        //时间日期
        LocalDate date=LocalDate.now();
        System.out.println("这个月的第几天："+date.getDayOfMonth());
        System.out.println(""+date.getDayOfWeek().name());
        // 本地时间
        LocalTime lt = LocalTime.now();

        // 本地日期
        LocalDate ld = LocalDate.now();

        // 本地日期时间
        LocalDateTime ldt = LocalDateTime.now();

        // 创建一个指定的时间
        LocalDateTime ldt2 = LocalDateTime.of(2012, 2, 12, 12, 12, 12);

        // 日期时间转日期或时间
        LocalDate ld2 = ldt.toLocalDate();
        LocalTime lt2 = ldt.toLocalTime();
    }
    /**
     * jdk1.8 时间字符串转换
     */
    public static void parseDate(){
        // 格式化模版
        DateTimeFormatter DATETIME19 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 时间转字符串
        String dtStr = DATETIME19.format(LocalDateTime.now());

        // 字符串转时间
        LocalDateTime ldt = LocalDateTime.parse(dtStr, DATETIME19);
    }

    /**
     * jdk1.8 时间运算
     */
    public static void calculateDate(){
        LocalDateTime ldt = LocalDateTime.now();
        //获取指定单位的值
        int year = ldt.getYear();
        int day = ldt.getDayOfMonth();
        int week = ldt.getDayOfWeek().getValue();
        int hour = ldt.getHour();

        // 指定时间单位的值
        LocalDateTime ldt2 = ldt.withDayOfMonth(10).withYear(2020);  // 返回的是一个新的对象，需要接收

        // 在现有时间上做加法
        LocalDateTime ldt3 = ldt.plusYears(2).plusMonths(-2);

        // 在现有时间上做减法
        LocalDateTime ldt4 = LocalDateTime.now().minus(-2, ChronoUnit.MONTHS).minusDays(3);

        // 获取一天的开始或结束
        LocalDateTime ldtStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime ldtEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        // 时间是否在指定时间之前
        boolean isBefore = ldt.isBefore(ldt2);

        // 时间是否在指定时间之后
        boolean isAfter = ldt.isAfter(ldt2);

        // 比较两个日期是否相等 重写的equals可以直接比较
        boolean equality = ldt.equals(ldt2);

        // 比较是否是周期性日期，比如 生日 节假日 账单日 等
        MonthDay holiday = MonthDay.of(5, 1); // 五一
        boolean equaty = holiday.equals(MonthDay.from(LocalDateTime.now())); // 今天是否是五一
    }
    /**
     * jdk1.8 间隔计算
     */
    public static void initerCheck(){
        LocalDateTime ldt1 = LocalDateTime.of(2012, 2, 12, 12, 12, 12);
        LocalDateTime ldt2 = LocalDateTime.of(2015, 5, 15, 15, 15, 15);

        // 时间的间隔    Duration 表示时分秒的时间量（累计向上单位的差，即计算实际的总共的差）
        Duration duration = Duration.between(ldt1, ldt2);
        long durnMill = duration.toMillis();   // 计算毫秒差
        long durnMin = duration.toMinutes();   // 计算分钟差
        long durnHour = duration.toHours();    // 计算小时差
        long durnDay = duration.toDays();      // 计算天数差

        // 日期的间隔    Period 表示年月日的时间量（只计算当前单位的差，不累计向上单位的差距）
        Period period = Period.between(ldt1.toLocalDate(), ldt2.toLocalDate());
        long perdDay = period.getDays();        // 只计算当前差，不累计年月差带来的天数差
        long perdMonth = period.getMonths();    // 只计算当前差，不累计年数差带来的月数差
        long perdYear = period.getYears();

        // 计算实际总间隔天数的第二种方法
        long diffEehDay =ldt1.toLocalDate().toEpochDay() - ldt2.toLocalDate().toEpochDay();

        // 计算指定时间单位之差
        long diffChrDay =ChronoUnit.DAYS.between(ldt1, ldt2);    // 日期单位之差
        long diffChrMin =ChronoUnit.MINUTES.between(ldt1, ldt2); // 分钟单位之差
    }

    /**
     * jdk1.8 时间戳、瞬时点、Date、本地时间、转换
     */
    public static void parseCheck(){
        // 时间戳
        long timestamp = System.currentTimeMillis();

        // 瞬时点
        Instant instant = Instant.now();

        // Date
        Date  date =  new Date();

        // 时间戳 转 瞬时点
        Instant instant2 = Instant.ofEpochMilli(timestamp);

        // 瞬时点 转 时间戳
        long timestamp2 = instant.toEpochMilli();

        // Date 转 瞬时点
        Instant instant3 = date.toInstant();

        // 瞬时点 转 Date
        Date date2 = Date.from(instant);

        // 瞬时点 转 本地时间
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // 本地时间 转 时间戳
        long timestamp3 = ldt.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long timestamp4 = ldt.toInstant(ZoneOffset.of("+08:00")).toEpochMilli();
        long timestamp5 = ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * jdk1.8 时区时间
     */
    public static void qDate(){
        // 时区ID的集合
        Set<String> zoneSet = ZoneId.getAvailableZoneIds();

        // 默认时区
        ZoneId zoneId = ZoneId.systemDefault();

        // 时区时间
        LocalDateTime cur = LocalDateTime.now();                                    // 本地默认时间 2019-04-29T14:45:07.156
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));    // 时区当前时间 2019-04-28T23:45:07.156
        OffsetDateTime odt = OffsetDateTime.now(ZoneId.of("America/Los_Angeles"));  // 带偏移量时间 2019-04-28T23:45:07.156-07:00
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));    // 带时区的时间 2019-04-28T23:45:07.156-07:00[America/Los_Angeles]
        LocalDateTime ldto = odt.toLocalDateTime();                                 // 转本地类时间 2019-04-28T23:45:07.156
        LocalDateTime ldtz = zdt.toLocalDateTime();                                 // 转本地类时间 2019-04-28T23:45:07.156

        // 时钟 类似时间戳
        Clock clockDefault = Clock.systemDefaultZone();               //系统默认
        Clock clockUtc = Clock.systemUTC();                           // UTC
        Clock c1ockZone = Clock.system(ZoneId.of("+08:00"));          //指定时区
        Clock clockRegion = Clock.system(ZoneId.of("Asia/Shanghai")); //指定区域
        long timestamp = clockDefault.millis();                       //获取时间戳，等于System.currentTimeMillis()
    }

    public static void dateCheck(){
        //日期格式使用YYYY-MM-dd  再年尾的时候就会出现问题，需要使用yyyy-MM-dd
        LocalDate date=LocalDate.of(2019,12,31);
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String formatStr = dateTimeFormatter.format(date);
        System.out.println("formatStr="+formatStr);//formatStr=2020-12-31
    }

    /**
     * Date转LocalDate
     * @param input
     * @return
     */
    public static LocalDate Data2LocalDate(Date input){
        Instant instant = input.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * Date转LocalDateTime
     * @param input
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date input){
        Instant instant = input.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * 明天
     */
    public static LocalDate nowDateAddOneDay(){
        LocalDate now = LocalDate.now();
        return now.plusDays(1);
    }

    /**
     * 下月第一天
     */
    public static LocalDate firstDayOfNextMonth(){
        LocalDate now = LocalDate.now();
        LocalDate dayOfNextMonth=now.with(TemporalAdjusters.firstDayOfNextMonth());
        return dayOfNextMonth;
    }

    /**
     * 下周的第一天
     */
    public static LocalDate nowWeekAddNextWeekDay(){
        LocalDate date = LocalDate.now().plusWeeks(1);
        return date.with(DayOfWeek.MONDAY);
    }
    /**
     * LocalDate转Date
     * @param input
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime input){
        return Date.from(input.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 当前减去指定天数
     */
    public static LocalDateTime nowDateSubtractAssignDay(int differDay){
        LocalDateTime now = LocalDateTime.now();
        return now.minusDays(differDay);
    }

    /**
     * 相差天数 end-start
     * @param start
     * @param end
     * @return
     */
    public static Long differDay(LocalDateTime start,LocalDateTime end){
        return (end.toLocalDate().toEpochDay() - start.toLocalDate().toEpochDay());
    }

    public static void test(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("");
        formatter.parse("");
        //是线程安全的，因为在其中进行转换时会新建一个具体转换对象
        //LocalDate 都会返回新对象
    }

    public static void main(String[] args) throws ParseException {
        long st=System.currentTimeMillis()/1000;
        Date date=timeStemp2Date(st);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String s = format.format(date);
        System.out.println("utc="+s);

        SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s2 = format2.format(date);
        System.out.println("local="+s2);
//        LocalDate localDate=LocalDate.now();
//        //根据字符串取日期
//        LocalDate parse = LocalDate.parse("2020-02-10");//yyyy-MM-dd
//        // 取本月第1天：
//        LocalDate firstDayOfThisMonth = localDate.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
//        // 取本月第2天：
//        LocalDate secondDayOfThisMonth = localDate.withDayOfMonth(2); // 2014-12-02
//        // 取本月最后一天，再也不用计算是28，29，30还是31：
//        LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
//        // 取下一天：
//        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
//        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
//        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
    }

    /**
     * 到秒时间戳转换成日期
     * @param timeStemps
     * @return
     */
    public static Date timeStemp2Date(long timeStemps){
        Date date=new Date(timeStemps*1000);
        return date;
    }

    /**
     * 日期转utc时间串
     * @param date
     * @return
     */
    public static String date2UtcDayStr(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(date);
    }
}
