select *
from (
         select sum(real_receive_amount) as incomeToday, sum(real_pay_amount) as expenditureToday
         from fin_asset_record
         where account_code = 12
           and create_time between 1588057644000 and 1595920044573
         group by account_code
     ) t;

-- 查询今天的收入支出


select t1.account_code
from (
         select account_code
         from fin_account fa

                  left join (
             select ifnull(sum(account_code), 0)        as account_code,
                    ifnull(sum(real_receive_amount), 0) as incomeToday,
                    ifnull(sum(real_pay_amount), 0)     as expenditureToday
             from fin_asset_record
             where account_code = 12
               and create_time between 1588057644000 and 1587798444000
         ) t on fa.account_code = t.account_code
     ) t1


         left join
     (
         -- 查询昨天的收入支出
         select account_code,
                ifnull(sum(real_receive_amount), 0) as incomeYesterday,
                ifnull(sum(real_pay_amount), 0)     as expenditureYesterday
         from fin_asset_record
         where account_code = 12
           and create_time between 1588057644000 and 1595920044573
     ) t2 on t1.account_code = t2.account_code;



select account_code,
       ifnull(sum(real_receive_amount), 0) as incomeToday,
       ifnull(sum(real_pay_amount), 0)     as expenditureToday
from fin_asset_record
where account_code = 12
  and create_time between 1588057644000 and 1587798444000

union all

-- 查询昨天的收入支出
select account_code,
       ifnull(sum(real_receive_amount), 0) as incomeYesterday,
       ifnull(sum(real_pay_amount), 0)     as expenditureYesterday
from fin_asset_record
where account_code = 12
  and create_time between 1588057644000 and 1595920044573;


-- 查询时间内总余额
select account_code, balance_after
from fin_asset_record
where id in (
    select max(id) id
    from fin_asset_record
    where create_time between 1595952000000 and 1596038400000
    group by account_code
);

-- 查询时间内总收入总支出
select fa.account_code,
       ifnull(real_receive_amount, 0) real_receive_amount,
       ifnull(real_pay_amount, 0)     real_pay_amount
from fin_account fa
         left join (
    select account_code, sum(real_receive_amount) real_receive_amount, sum(real_pay_amount) real_pay_amount
    from fin_asset_record
    where create_time between 1595952000000 and 1596038400000
    group by account_code
) t on fa.account_code = t.account_code
where fa.is_deleted = 0
;


select account_code, sum(real_receive_amount) real_receive_amount, sum(real_pay_amount) real_pay_amount
from fin_asset_record
where create_time between 1595952000000 and 1596038400000
group by account_code;



select t1.account_code, real_receive_amount, real_pay_amount, balance_after
from (
         select account_code, balance_after
         from fin_asset_record
         where id in (
             select max(id) id
             from fin_asset_record
             where create_time between 1595952000000 and 1596038400000
             group by account_code
         )
     ) t1

         left join (
    select account_code, sum(real_receive_amount) real_receive_amount, sum(real_pay_amount) real_pay_amount
    from fin_asset_record
    where create_time between 1595952000000 and 1596038400000
    group by account_code
) t2
                   on t1.account_code = t2.account_code;


-- 分组查询时间内的总金额 总收入 总支出
select fa.account_code,
       ifnull(total_income, 0)      total_income,
       ifnull(total_expenditure, 0) total_expenditure,
       ifnull(total_asset, 0)       total_asset
from fin_account fa
         left join (
    select t1.account_code, total_income, total_expenditure, total_asset
    from (
             select account_code, balance_after as total_asset
             from fin_asset_record
             where id in (
                 select max(id) id
                 from fin_asset_record
                 where create_time between 1595952000000 and 1596038400000
                 group by account_code
             )
         ) t1

             left join (
        select account_code, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
        from fin_asset_record
        where create_time between 1595952000000 and 1596038400000
        group by account_code
    ) t2
                       on t1.account_code = t2.account_code
) t on fa.account_code = t.account_code
where fa.is_deleted = 0
;

select ifnull(sum(real_receive_amount), 0) as incomeToday,
       ifnull(sum(real_pay_amount), 0)     as expenditureToday
from fin_asset_record
where account_code = 12
  and create_time between 1595952000000 and 1596038400000
group by account_code;

-- 查询当天资产记录
select sum(total_income) total_income, sum(total_expenditure) total_expenditure, sum(total_asset) total_asset, sum(total_profit) total_profit
from (
         select ifnull(total_income, 0)      total_income,
                ifnull(total_expenditure, 0) total_expenditure,
                ifnull(total_asset, 0)       total_asset,
                ifnull(total_income - total_expenditure, 0)       total_profit

         from fin_account fa
                  left join (
             select t1.account_code, total_income, total_expenditure, total_asset
             from (
                      select account_code, balance_after as total_asset
                      from fin_asset_record
                      where id in (
                          select max(id) id
                          from fin_asset_record
                          where create_time between 1595952000000 and 1596038400000
                          group by account_code
                      )
                  ) t1

                      left join (
                 select account_code, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
                 from fin_asset_record
                 where create_time between 1595952000000 and 1596038400000
                 group by account_code
             ) t2
                                on t1.account_code = t2.account_code
         ) t on fa.account_code = t.account_code
         where fa.is_deleted = 0
           and fa.account_code = 12
     ) t;


-- 按小时统计当天的 资产 收入 支出
SELECT DATE_FORMAT(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') AS time, COUNT(*) AS num
FROM fin_asset_record
WHERE create_time between 1595952000000 and 1596038400000
GROUP BY time
ORDER BY time;

select id, from_unixtime(create_time / 1000)
from fin_asset_record
WHERE create_time between 1595952000000 and 1596038400000;

select id, create_time
from fin_asset_record
WHERE create_time between 1595952000000 and 1596038400000;


SELECT time, COUNT(*) AS num
FROM (
         SELECT id,
                DATE_FORMAT(
                        concat(date(from_unixtime(create_time / 1000)), ' ', HOUR(from_unixtime(create_time / 1000)),
                               ':', floor(MINUTE(from_unixtime(create_time / 1000)) / 10) * 10),
                        '%Y-%m-%d %H:%i'
                    ) AS time
         FROM fin_asset_record
         WHERE create_time between 1595952000000 and 1596038400000
     ) a
GROUP BY DATE_FORMAT(time, '%Y-%m-%d %H:%i')
ORDER BY time;

-- 当天每小时
SELECT DATE_FORMAT(
               DATE_SUB(DATE_FORMAT(from_unixtime(1595952000000 / 1000), '%Y-%m-%d'), INTERVAL (-(@i := @i + 1)) HOUR),
               '%Y-%m-%d %H:00:00') AS 'time'
FROM (
         SELECT a
         FROM (SELECT '1' AS a UNION SELECT '2' UNION SELECT '3' UNION SELECT '4') AS a
                  JOIN (SELECT '1'
                        UNION
                        SELECT '2'
                        UNION
                        SELECT '3'
                        UNION
                        SELECT '4'
                        UNION
                        SELECT '5'
                        UNION
                        SELECT '6') AS b
                       ON 1
     ) AS b,
     (SELECT @i := -1) AS i;



select account_code, balance_after as total_asset
from fin_asset_record
where id in (
    select max(id) id
    from fin_asset_record
    where create_time between 1595952000000 and 1596038400000
    group by account_code
);


--  sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
-- 按小时查询当天的原始数据
select *
from (
         select id,
                account_code,
                date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour,
                real_receive_amount,
                real_pay_amount,
                balance_after
         from fin_asset_record
     ) t
group by account_code
;


-- 查询每小时总资产最后一条数据
--  sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
-- 按小时查询当天的原始数据

-- 按帐套查询当天每小时最后一条数据总资产
select account_code,
       date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
       date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:59:59') end_hour,
       balance_after
from fin_asset_record
where id in (
    select max(id)
    from (
             select id,
                    account_code,
                    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
             from fin_asset_record
         ) t
    group by account_code, format_hour
)
;

-- 按帐套查询当天每小时总收入总支出
select account_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
from (
         select account_code,
                real_receive_amount,
                real_pay_amount,
                date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
         from fin_asset_record
     ) t
group by account_code, format_hour;


-- 查询时间段内每小时总资产 总收入 总利润
select *
from (
         select account_code,
                date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
                balance_after
         from fin_asset_record
         where id in (
             select max(id)
             from (
                      select id,
                             account_code,
                             date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                      from fin_asset_record
                  ) t
             group by account_code, format_hour
         )
     ) t1
         left join (
    select account_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
    from (
             select account_code,
                    real_receive_amount,
                    real_pay_amount,
                    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
             from fin_asset_record
         ) t
    group by account_code, format_hour
) t2 on t1.account_code = t2.account_code and t1.start_hour = t2.format_hour
;

SELECT INSERT('2020-07-29 00:00:00',15,19,'59:59');

select
       hour_time as start_time,
       INSERT(hour_time,15,19,'59:59') as end_time,
       ifnull(total_asset,0) as total_asset,
       ifnull(total_expenditure,0) as total_expenditure,
       ifnull(total_income,0) as total_income,
       ifnull(total_profit,0) as total_profit

from (
                  SELECT   DATE_FORMAT( DATE_SUB( DATE_FORMAT( from_unixtime(1595952000000 / 1000),'%Y-%m-%d'),INTERVAL ( -(@i:=@i+1) ) HOUR ) ,'%Y-%m-%d %H:00:00') AS hour_time
                  FROM (
                           SELECT a  FROM
                               (SELECT '1' AS a UNION SELECT '2' UNION SELECT '3' UNION SELECT '4'   ) AS a
                                   JOIN ( SELECT  '1' UNION SELECT '2' UNION SELECT '3' UNION SELECT '4' UNION SELECT '5' UNION SELECT '6' ) AS b
                                        ON 1
                       ) AS b  ,(SELECT @i:=-1)  AS i

                  ) data_hour

left join (

    select
        start_hour,
        sum(balance_after) total_asset,
        sum(total_expenditure) total_expenditure,
        sum(total_income) total_income,
        sum(total_income)-sum(total_expenditure) total_profit
    from (
             -- 查询总资产
             select account_code,
                    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
                    balance_after
             from fin_asset_record
             where
                     account_code = 12 and
                 date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-07-29 00:00:00' and '2020-07-30 00:00:00'
               and id in (
                 select max(id)
                 from (
                          select id,
                                 account_code,
                                 date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                          from fin_asset_record
                          where  account_code = 12 and
                              date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-07-29 00:00:00' and '2020-07-30 00:00:00'
                      ) t

                 group by account_code, format_hour

             )
         ) t1
             left join (
        -- 查询总收入 总支出
        select account_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
        from (
                 select account_code,
                        real_receive_amount,
                        real_pay_amount,
                        date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                 from fin_asset_record
                 where  account_code = 12 and
                     date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-07-29 00:00:00' and '2020-07-30 00:00:00'
             ) t
        group by account_code, format_hour
    ) t2 on t1.account_code = t2.account_code and t1.start_hour = t2.format_hour
    group by start_hour

    ) orgin_data on data_hour.hour_time = orgin_data.start_hour

;


-- 查询时间段内每小时总资产 总收入 总利润
select
     start_hour,
    ifnull(sum(balance_after),0) as total_asset,
    ifnull(sum(total_expenditure),0) as total_expenditure,
    ifnull(sum(total_income),0) as total_income,
    ifnull(sum(total_income)-sum(total_expenditure),0) as total_profit
from (
    -- 查询总资产
         select account_code,
                date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
                balance_after
         from fin_asset_record
         where
              -- account_code = 12 and
             date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
               and id in (
             select max(id)
             from (
                      select id,
                             account_code,
                             date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                      from fin_asset_record
                 where
                       -- account_code = 12 and
                       date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                  ) t

             group by account_code, format_hour

         )
     ) t1
         left join (
     -- 查询总收入 总支出
    select account_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
    from (
             select account_code,
                    real_receive_amount,
                    real_pay_amount,
                    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
             from fin_asset_record
             where
                   -- account_code = 12 and
                   date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
         ) t
    group by account_code, format_hour
) t2 on t1.account_code = t2.account_code and t1.start_hour = t2.format_hour
group by start_hour
;


-- 按天查询当天的原始数据
select
       account_code,
       date_format(from_unixtime(create_time / 1000), '%Y-%m-%d 00:00:00') format_hour,
       real_receive_amount,
       real_pay_amount,
       balance_after
from fin_asset_record
order by  create_time
;


-- 按天查询当天的原始数据
select date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') format_hour,
       real_receive_amount,
       real_pay_amount,
       balance_after
from fin_asset_record where account_code =12
order by  create_time
;


SELECT DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s');


-- 时间段内每天
select a.Date
from (
         select curdate() - INTERVAL (a.a + (10 * b.a) + (100 * c.a)) DAY as Date
         from (select 0 as a
               union all
               select 1
               union all
               select 2
               union all
               select 3
               union all
               select 4
               union all
               select 5
               union all
               select 6
               union all
               select 7
               union all
               select 8
               union all
               select 9) as a
                  cross join (select 0 as a
                              union all
                              select 1
                              union all
                              select 2
                              union all
                              select 3
                              union all
                              select 4
                              union all
                              select 5
                              union all
                              select 6
                              union all
                              select 7
                              union all
                              select 8
                              union all
                              select 9) as b
                  cross join (select 0 as a
                              union all
                              select 1
                              union all
                              select 2
                              union all
                              select 3
                              union all
                              select 4
                              union all
                              select 5
                              union all
                              select 6
                              union all
                              select 7
                              union all
                              select 8
                              union all
                              select 9) as c
     ) a
where unix_timestamp(a.Date) * 1000 between 1595865600000 and 1596038400000
order by a.Date;


-- 查询当天资产变动记录
select
    start_time,
    ifnull(sum(total_asset),0) as total_asset,
    ifnull(sum(total_expenditure),0) as total_expenditure,
    ifnull(sum(total_income),0) as total_income,
    ifnull(sum(total_profit),0) as total_profit

from (

                  select
                      account_code,
                      date_format(from_unixtime(start_time / 1000), '%Y-%m-%d %H:%i:%s') start_time,
                      total_asset,
                      total_income,
                      total_expenditure,
                      total_profit
                  from report_asset
                  where  account_code = 12
                    and date_format(from_unixtime(start_time / 1000), '%Y-%m-%d %H:%i:%s') = '2020-07-28 00:00:00'
                  ) t
group by start_time



;


truncate table fin_account;
truncate table fin_asset;
truncate table fin_asset_record;
truncate table fin_invest;
truncate table fin_subject;

update sys_role set create_time = 1596192129010 where id != 0;


select a.Date
from (
         select curdate() - INTERVAL (a.a + (10 * b.a) + (100 * c.a) + (1000 * d.a) ) DAY as Date
         from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a
                  cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b
                  cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c
                  cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as d
     ) a
where a.Date between '2010-01-20' and '2010-01-24' ;


create procedure getBetweenTime(
    in startTime date,
    in endTime date
)
BEGIN
    DECLARE tmpTime date;


    set tmpTime = startTime;
    while tmpTime <= endTime do

        end while;

end;


select from_unixtime(1595865600000/1000);


select date_add(from_unixtime(1595865600000/1000), interval 1 hour);



-- 查询总支出 总收入
select account_code,subject_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
from (
         select account_code,
                real_receive_amount,
                real_pay_amount,
                subject_code,
                date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
         from fin_asset_record
         where
             -- account_code = 12 and
             date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
     ) t
group by account_code,  subject_code,format_hour;

-- 查询总资产
select account_code,subject_code,
       date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
       balance_after
from fin_asset_record
where
  -- account_code = 12 and
    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
  and id in (
    select max(id)
    from (
             select id,
                    account_code,
                    subject_code,
                    date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
             from fin_asset_record
             where
                 -- account_code = 12 and
                 date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
         ) t

    group by account_code, subject_code,format_hour);




select
       t1.account_code,
       t1.subject_code,
       start_hour,
       sum(total_income) total_income,
       sum(total_expenditure) total_expenditure,
       sum(total_income)-sum(total_expenditure) total_profit,
       sum(total_asset) total_asset
from (
            -- 查询总收入 总支出
                  select account_code,subject_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
                  from (
                           select account_code,
                                  real_receive_amount,
                                  real_pay_amount,
                                  subject_code,
                                  date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                           from fin_asset_record
                           where
                               -- account_code = 12 and
                               date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                       ) t
                  group by account_code,  subject_code,format_hour


) t1
left join
    (
            -- 查询时间段内总金额
        select account_code,subject_code,
               date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
               balance_after as total_asset
        from fin_asset_record
        where
          -- account_code = 12 and
            date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
          and id in (
            select max(id)
            from (
                     select id,
                            account_code,
                            subject_code,
                            date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                     from fin_asset_record
                     where
                         -- account_code = 12 and
                         date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                 ) t

            group by account_code, subject_code,format_hour)

    ) t2 on t1.subject_code = t2.subject_code and t1.account_code = t2.account_code

# where t1.account_code = '002'
group by account_code,subject_code,start_hour
;



select
    hour_time as start_time,
    INSERT(hour_time,15,19,'59:59') as end_time,
    ifnull(total_asset,0) as total_asset,
    ifnull(total_expenditure,0) as total_expenditure,
    ifnull(total_income,0) as total_income,
    ifnull(total_profit,0) as total_profit
from (
         SELECT   DATE_FORMAT( DATE_SUB( DATE_FORMAT( from_unixtime(1596536449423 / 1000),'%Y-%m-%d'),INTERVAL ( -(@i:=@i+1) ) HOUR ) ,'%Y-%m-%d %H:00:00') AS hour_time
         FROM (
                  SELECT a  FROM
                      (SELECT '1' AS a UNION SELECT '2' UNION SELECT '3' UNION SELECT '4'   ) AS a
                          JOIN ( SELECT  '1' UNION SELECT '2' UNION SELECT '3' UNION SELECT '4' UNION SELECT '5' UNION SELECT '6' ) AS b
                               ON 1
              ) AS b  ,(SELECT @i:=-1)  AS i

     ) data_hour

         left join
    (
        select
            start_hour,
            sum(total_income) total_income,
            sum(total_expenditure) total_expenditure,
            sum(total_income)-sum(total_expenditure) total_profit,
            sum(total_asset) total_asset
        from (
                 -- 查询总收入 总支出
                 select account_code,subject_code, format_hour, sum(real_receive_amount) total_income, sum(real_pay_amount) total_expenditure
                 from (
                          select account_code,
                                 real_receive_amount,
                                 real_pay_amount,
                                 subject_code,
                                 date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                          from fin_asset_record
                          where
                              -- account_code = 12 and
                              date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between  '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                      ) t
                 group by account_code,  subject_code,format_hour


             ) t1
                 left join
             (
                 -- 查询时间段内总金额
                 select account_code,subject_code,
                        date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') start_hour,
                        balance_after as total_asset
                 from fin_asset_record
                 where
                   -- account_code = 12 and
                     date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                   and id in (
                     select max(id)
                     from (
                              select id,
                                     account_code,
                                     subject_code,
                                     date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:00:00') format_hour
                              from fin_asset_record
                              where
                                  -- account_code = 12 and
                                  date_format(from_unixtime(create_time / 1000), '%Y-%m-%d %H:%i:%s') between   '2020-08-04 00:00:00' and '2020-08-05 00:00:00'
                          ) t

                     group by account_code, subject_code,format_hour)

             ) t2 on t1.subject_code = t2.subject_code and t1.account_code = t2.account_code

# where t1.account_code = '002'
        group by start_hour
    ) origin_data
on origin_data.start_hour = data_hour.hour_time
;



SELECT   DATE_FORMAT( DATE_SUB( DATE_FORMAT( from_unixtime(1595952000000 / 1000),'%Y-%m-%d'),INTERVAL ( -(@i:=@i+1) ) HOUR ) ,'%Y-%m-%d %H:00:00') AS hour_time;


