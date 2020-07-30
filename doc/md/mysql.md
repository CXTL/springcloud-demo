# mysql

## mysql不同时间粒度下的分组统计



我们在做项目或者数据分析时，经常遇到这样的需求：统计不同时间粒度下的数据分布情况，例如，每一天中每个小时网站的访问量，某路口每半个小时通过的车辆数量等。对于此类的问题，一个sql简单的查询就能实现，故特此记录下，方便以后使用。
在MySQL中，我的表为：track
数据结构如下所示：
![表track的数据结构示意](https://img-blog.csdnimg.cn/20181105163738204.png)

### 按天统计



```sql
SELECT DATE(TimeStart) AS date, COUNT(*) AS num
FROM track
WHERE Flag = 0 AND Duration >= 300 
GROUP BY date
ORDER BY date;
12345
```

### 按小时统计

```sql
SELECT DATE_FORMAT(TimeStart, '%Y-%m-%d %H:00:00') AS time, COUNT(*) AS num
FROM track
WHERE Flag = 0 AND Duration >= 300
GROUP BY time
ORDER BY time;
12345
```

结果如下：
![一小时结果](https://img-blog.csdnimg.cn/20181105164437450.png)

### 按半小时统计

```sql
SELECT time, COUNT( * ) AS num 
FROM
	(
	SELECT Duration,
		DATE_FORMAT(
			concat( date( TimeStart ), ' ', HOUR ( TimeStart ), ':', floor( MINUTE ( TimeStart ) / 30 ) * 30 ),
			'%Y-%m-%d %H:%i' 
		) AS time 
	FROM tarck
	WHERE Flag = 0  AND Duration >= 300 
	) a 
GROUP BY DATE_FORMAT( time, '%Y-%m-%d %H:%i' ) 
ORDER BY time;
12345678910111213
```

结果如下：
![半小时查询结果](https://img-blog.csdnimg.cn/20181105164102966.png)

### 按N分钟统计

将上面的SQL语句稍微修改下，就可以实现按任意N分钟为时间片的分组统计，如按10分钟统计，先上代码：

```sql
SELECT time, COUNT( * ) AS num 
FROM
	(
	SELECT Duration,
		DATE_FORMAT(
			concat( date( TimeStart ), ' ', HOUR ( TimeStart ), ':', floor( MINUTE ( TimeStart ) / 10 ) * 10 ),
			'%Y-%m-%d %H:%i' 
		) AS time 
	FROM tarck
	WHERE Flag = 0  AND Duration >= 300 
	) a 
GROUP BY DATE_FORMAT( time, '%Y-%m-%d %H:%i' ) 
ORDER BY time;
12345678910111213
```

基本思路：
将datetime类型的时间转化为相应时间片的时间，例如将‘2017-03-01 01:08:19’ 转化为‘2017-03-01 01:00:00’，然后group by即可。

### 按分钟统计

将按小时统计的SQL语句稍微修改下，就可以实现按分钟统计

```sql
SELECT DATE_FORMAT(TimeStart, '%Y-%m-%d %H:%i:00') AS time, COUNT(*) AS num
FROM track 
WHERE Flag = 0 AND Duration >= 300
GROUP BY time
ORDER BY time;
12345
```

DATE_FORMAT功能强大，可以根据format字符串格式化date值，参考下面链接
http://www.w3school.com.cn/sql/func_date_format.asp

## MySQL 日期和毫秒数的转换

### 日期转秒数

```sql
select UNIX_TIMESTAMP('2011-05-31 23:59:59');1
```

### 秒数转日期

```sql
select from_unixtime(1306771200);  
```

### 需要毫秒数相应乘除1000即可

```sql
SELECT NOW();
select from_unixtime(1399537700);  
select UNIX_TIMESTAMP(NOW()) * 1000;123
```

**需要注意的是：** `select UNIX_TIMESTAMP(NOW())` 输出的是**秒**。

### 参数格式化

``````mysql
SELECT DATE_FORMAT(NOW(),'%Y-%m-%d %H:%i:%s'); 
``````

## mysql查询时间段每月的每天

### 时间段内每天

``````mysql
select a.Date
from (
         select curdate() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY as Date
         from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a
                  cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b
                  cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c
     ) a
where unix_timestamp(a.Date)*1000 between 1595865600000 and 1596038400000
order by a.Date;
``````

### 每天每小时

``````mysql
SELECT   DATE_FORMAT( DATE_SUB( DATE_FORMAT( from_unixtime(1595952000000 / 1000),'%Y-%m-%d'),INTERVAL ( -(@i:=@i+1) ) HOUR ) ,'%Y-%m-%d %H:00:00') AS 'time'
FROM (
         SELECT a  FROM
             (SELECT '1' AS a UNION SELECT '2' UNION SELECT '3' UNION SELECT '4'   ) AS a
                 JOIN ( SELECT  '1' UNION SELECT '2' UNION SELECT '3' UNION SELECT '4' UNION SELECT '5' UNION SELECT '6' ) AS b
                      ON 1
     ) AS b  ,(SELECT @i:=-1)  AS i;
``````

## MySQL 为日期增加一个时间间隔：date_add()

`````mysql
set @dt = now();
select date_add(@dt, interval 1 day);   - 加1天
select date_add(@dt, interval 1 hour);   -加1小时
select date_add(@dt, interval 1 minute);    - 加1分钟
select date_add(@dt, interval 1 second); -加1秒
select date_add(@dt, interval 1 microsecond);-加1毫秒
select date_add(@dt, interval 1 week);-加1周
select date_add(@dt, interval 1 month);-加1月
select date_add(@dt, interval 1 quarter);-加1季
select date_add(@dt, interval 1 year);-加1年
`````

## 1、字符串的拼接

### 1.1 CONCAT(s1,s2，...)函数

返回连接参数产生的字符串，一个或多个待拼接的内容，任意一个为NULL则返回值为NULL。

```sql
SELECT CONCAT('现在的时间：',NOW());  -- 输出结果：现在的时间：2019-01-17 11:27:58
```

### 1.2 CONCAT_WS(x,s1,s2,...)函数

返回多个字符串拼接之后的字符串，每个字符串之间有一个x。

```sql
SELECT CONCAT_WS(';','pan_junbiao的博客','KevinPan','pan_junbiao'); -- 输出结果：pan_junbiao的博客;KevinPan;pan_junbiao
```

 

## 2、字符串的截取

### 2.1 SUBSTRING(s,n,len)、MID(s,n,len)函数

两个函数作用相同，从字符串s中返回一个第n个字符开始、长度为len的字符串。

```sql
SELECT SUBSTRING('您好，欢迎访问pan_junbiao的博客',8,14);  -- 输出结果：pan_junbiao的博客



SELECT MID('您好，欢迎访问pan_junbiao的博客',8,14);        -- 输出结果：pan_junbiao的博客
```

### 2.2 LEFT(s,n)、RIGHT(s,n)函数

前者返回字符串s从最左边开始的n个字符，后者返回字符串s从最右边开始的n个字符。

```sql
SELECT LEFT('您好，欢迎访问pan_junbiao的博客',7);   -- 输出结果：您好，欢迎访问



SELECT RIGHT('您好，欢迎访问pan_junbiao的博客',14); -- 输出结果：pan_junbiao的博客
```

 

## 3、字符串的替换

### 3.1 INSERT(s1,x,len,s2)函数

返回字符串s1，其子字符串起始于位置x，被字符串s2取代len个字符。

```sql
SELECT INSERT('您好，欢迎访问阿标的博客',8,2,'pan_junbiao');  -- 输出结果：您好，欢迎访问pan_junbiao的博客
```

### 3.2 REPLACE(s,s1,s2)函数

返回一个字符串，用字符串s2替代字符串s中所有的字符串s1。

```sql
SELECT REPLACE('您好，欢迎访问阿标的博客','阿标','pan_junbiao'); -- 输出结果：您好，欢迎访问pan_junbiao的博客
```

 

## 4、字符串的查询位置

### 4.1 LOCATE(str1,str)、POSITION(str1 IN str)、INSTR(str,str1)函数

三个函数作用相同，返回子字符串str1在字符串str中的开始位置（从第几个字符开始）。

```sql
SELECT LOCATE('pan_junbiao','您好，欢迎访问pan_junbiao的博客');       -- 输出结果：8



SELECT POSITION('pan_junbiao' IN '您好，欢迎访问pan_junbiao的博客');  -- 输出结果：8



SELECT INSTR('您好，欢迎访问pan_junbiao的博客','pan_junbiao');        -- 输出结果：8
```

### 4.2 FIELD(s,s1,s2,...)函数

返回第一个与字符串s匹配的字符串的位置。

```sql
SELECT FIELD('pan_junbiao','pan_junbiao的博客','KevinPan','阿标','pan_junbiao');  -- 输出结果：4
```