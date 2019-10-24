#### 判断是否是每个月的最后一天

```
//根据字符串格式进行解析
public static boolean isLastDayOfMonth(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        //表示索引从第几个开始解析字符串
        Date strtodate = formatter.parse(date, pos);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strtodate);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
```



#### 时间类型格式校验方法


    //根据字符串格式进行解析转换
    public  boolean isValidDate(String datestr) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) formatter.parse(datestr);
            return datestr.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }


#### 字符串转时间

```
public static Date StringToDate(String strdata){
		Date date1=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1=sdf.parse(strdata);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
```

#### 时间转字符串

```
public static String  DateToString(Date date){
	SimpleDateFormat simdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String str=simdate.format(date);
	return str;
}
```



#### 时间格式判断

输入数据格式yyyyMM

```
public static boolean isValidDateYYYYMM(String str) {
        int strLength = str.length();
        if (strLength != 6) {
            return false;
        }
        if (!BaseUtil.isNum(str)) {
            return false;
        }
        String year = str.substring(0, 4);
        String month = str.substring(4, 6);

        if (!month.substring(0, 1).equals("0")) {
            if (Integer.parseInt(month) > 12) {
                return false;
            }
        }
        if (month.substring(0, 1).equals("0")) {
            if (Integer.parseInt(month.substring(1, 2)) == 0) {
                return false;
            }
        }
        return true;
    }
```



#### 将日期格式化为指定格式的字串

```
public static String format(Date date, String pattern) {
		if (null == date)
			return null;
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}
	public static String format(Object date, String pattern) {
		if (null == date || !(date instanceof java.util.Date))
			return null;
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format((Date) date);
	}
```

####  判断两个时间是否在同一个yue

```
 public static boolean equals(Date date1, Date date2) {
         
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);
        System.out.println(year1 + "  " + month1);
        System.out.println(year2 + "  " + month2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
         
    } 
```







#### 传入时间增加 N月

```
    /**
     * 传入时间增加 N月
     * @param date
     * @param n
     * @return
     */
    public static Date addNMONTH(Date date,Integer n){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, n);
        Date time = rightNow.getTime();
        return time;
    }

```

