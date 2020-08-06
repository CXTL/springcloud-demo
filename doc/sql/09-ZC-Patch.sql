-- 热更脚本

-- ---------------------------------更新人: cxt  更新时间: 2020.06.12 ---------------------------------------------------
-- sys_user 表 新增 head_url 字段
alter table sys_user add column head_url varchar(100) comment '用户头像';


alter table fin_invest
	add should_invest_amount decimal(11,2) null comment '应投资金额';

alter table fin_invest
	add invest_date bigint null comment '投资日期';

alter table fin_asset_record
	add asset_date bigint null comment '收支日期';

	alter table fin_invest modify invest_ratio double null comment '投资比例 %';



