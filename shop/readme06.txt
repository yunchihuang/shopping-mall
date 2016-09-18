任务一：完成会员的地址管理
1. 创建表：address
-- ----
-- 会员地址表
-- ----
create table address(
  id               integer           primary key   auto_increment,
  contact          varchar(32)       not null      comment '收货人',
  mobile           varchar(32)       not null      comment '联系电话',
  street           varchar(255)      not null      comment '详细地址',
  zipcode          varchar(32)       default null  comment '邮编',
  mbr_id           integer           not null      comment '所属会员',
  default_value    tinyint(1)        default false comment '是否为所属会员的默认收货地址'
);

-- 数据
INSERT INTO address(contact, mobile, street, zipcode, mbr_id, default_value) VALUES 
 ('张三', '13902025678', '天津·西青区·津静路26号', '300384', 1, 1), 
 ('李四', '13702204321', '天津市滨海高新区华苑产业区海泰华科三路1号华鼎智地3号楼4层', '300384', 1, 0), 
 ('王5', '022-87188666', '天津市南开区华苑路26号', '300384', 1, 0);
 
 2. 创建实体类：Address.java
 
 3. 创建业务逻辑类：AddressService.java
 
 4. 功能一：获取当前会员的地址列表
 /member/address/list---> AddressListServlet-->AddressService
 5. 功能二：新增地址
 /member/address/add ---> AddressAddServlet-->AddressService--->存储到数据库 
 6. 功能三：删除地址
 
 7. 功能四：编辑地址信息
 
 8. 功能五：设置默认地址
 
 