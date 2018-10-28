# 微信点餐数据库


```sql
-- 类目
drop table if exists product_category;
create table product_category (
	category_id varchar(32) not null primary key comment "分类id",
	category_name varchar(64) not null comment "类目名字",
	category_type int not null comment "类目编号",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment "类目表";
ALTER TABLE product_category ADD INDEX(`category_type`) COMMENT 'for category_type';


-- 商品
drop table if exists product_info;
create table product_info(
	product_id varchar(32) not null PRIMARY KEY  comment "商品id", 
	product_name varchar(64) not null comment "商品名称",
	product_price decimal(8,2) not null comment "单价",
	product_stock int not null comment "库存",
	product_description varchar(64) comment "描述",
	product_icon varchar(32) not null comment "小图",
	product_status tinyint(3) DEFAULT "0" COMMENT "商品状态,0正常1下架",
	category_type int not null comment "类目编号",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment "商品表";

-- 订单
drop table if exists order_master;
create table order_master (
	order_id varchar(32) not null PRIMARY KEY comment "订单id",
	buyer_id varchar(32) not null comment "买家id",
	buyer_name varchar(32) not null comment "买家名字",
	buyer_phone varchar(32) not null comment "买家电话",
	buyer_address varchar(128) not null comment "买家地址",
	buyer_openid varchar(64) not null comment "买家微信openid",
	order_amount decimal(8,2) not null comment "订单总金额",
	order_status tinyint(3) not null default 0 comment "订单状态，默认0新下单",
	pay_status tinyint(3) not null default 0 comment "支付状态，默认0未支付",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间",
	order_scote decimal(8,2) default 0 comment "订单积分",
	approve_state varchar(32) default "check" comment "订单积分,approved 已审批,check 未审核"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "订单表";

-- 订单商品
drop table if exists order_detail;
create table order_detail (
	detail_id varchar(32) not null PRIMARY KEY comment "订单详情id",
	order_id varchar(32) not null comment "订单id",
	product_id varchar(32) not null comment "商品id",
	product_name varchar(64) not null comment "商品名称",
	product_price decimal(8,2) not null comment "商品价格",
	product_quantity int not null comment "商品数量",
	product_icon varchar(512) comment "商品小图",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "订单详情表";

-- 卖家(登录后台使用, 卖家登录之后可能直接采用微信扫码登录，不使用账号密码)
drop table if exists seller_info;
create table seller_info (
    id varchar(32) not null primary key ,
    username varchar(32) not null comment "用户名称",
    password varchar(32) not null comment "用户密码",
    openid varchar(64) not null comment "微信openid",
    create_time timestamp not null default current_timestamp comment "创建时间",
    update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
) comment '卖家信息表';

-- 买家信息
drop table if exists customer_info;
create table customer_info (
	buyer_id varchar(32) not null PRIMARY KEY comment "买家id",
	buyer_name varchar(32) not null comment "买家名字",
	buyer_phone varchar(32) not null comment "买家电话",
	buyer_address varchar(128) not null comment "买家地址",
	buyer_openid varchar(64) not null comment "买家微信openid",
	buyer_scote varchar(64) not null comment "买家积分",
	used_scote varchar(64) not null comment "买家已用积分",
	buyer_password varchar(64) not null comment "买家密码",
	login_count decimal(6,2) not null comment "买家登录次数",
	login_timet timestamp  comment "买家最后一次登录时间",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "买家信息";

-- 商品兑换记录
drop table if exists scote_exchange;
create table scote_exchange (
	exchange_id varchar(32) not null PRIMARY KEY comment "兑换id",
	buyer_id varchar(32) not null comment "买家id",
	buyer_name varchar(32) not null comment "买家名字",
	buyer_phone varchar(32) not null comment "买家电话",
	product_id varchar(32) not null comment "商品id",
	product_name varchar(64) not null comment "商品名称",
	used_scote varchar(64) not null comment "买家使用积分",
	if_used varchar(32) default "N" not null comment "买家是否已使用，Y是已使用，N未使用",
	schedule_time varchar(32) comment "预定使用时间",
	used_time varchar(32)  comment "真实使用时间",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "积分兑换";

-- 优惠券
drop table if exists coupon_info;
create table coupon_info (
	coupon_id varchar(32) not null PRIMARY KEY comment "优惠券id",
	coupon_code varchar(32) not null comment "优惠券code",
	coupon_name varchar(32) not null comment "优惠券名称",
	coupon_price decimal(8,2) not null comment "优惠券价格",
	buyer_id varchar(32) not null comment "买家id",
	buyer_name varchar(32) not null comment "买家名字",
	buyer_phone varchar(32) not null comment "买家电话",
	if_used varchar(32) default "N" not null comment "是否已使用，Y是已使用，N未使用",
	end_time timestamp comment "有效结束时间",
	begin_time timestamp default current_timestamp comment "有效开始时间",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "积分兑换";

-- 参数配置
drop table if exists parameter_config;
create table parameter_config (
	config_id varchar(32) not null PRIMARY KEY comment "参数配置id",
	config_key varchar(32) not null comment "参数key",
	config_name varchar(32) not null comment "参数name",
	create_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间"
)ENGINE=InnoDB DEFAULT CHARSET=utf8  comment "参数配置";
	
-- 产品类
INSERT INTO product_category(category_id,category_name,category_type,create_time,update_time)
VALUE("1","颈椎类","1",sysdate(),sysdate());
INSERT INTO product_category(category_id,category_name,category_type,create_time,update_time)
VALUE("2","推拿类","2",sysdate(),sysdate());
INSERT INTO product_category(category_id,category_name,category_type,create_time,update_time)
VALUE("3","温通仪","3",sysdate(),sysdate());

-- 产品信息
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type,create_time,update_time)
VALUE(1,"戴脖子",120,1000,"戴脖子","",0,1,sysdate(),sysdate());
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type,create_time,update_time)
VALUE(2,"戴腰杆",180,1000,"戴腰杆","",0,1,sysdate(),sysdate());
INSERT INTO product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type,create_time,update_time)
VALUE(3,"戴手",60,1000,"戴手","",0,1,sysdate(),sysdate());

INSERT INTO seller_info(id,username,password,openid,create_time,update_time)
VALUE(1,"ylx","123456","dsssade",sysdate(),sysdate());

```

