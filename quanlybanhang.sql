create table Customer(
	cID int primary key auto_increment,
    cName varchar(50),
    cAge int
);

create table `Order`(
	oID int primary key auto_increment,
    cID int,
    oDate date,
    oTotalPrice float,
    foreign key (cID) references Customer(cID)
);

create table Product(
	pID int primary key auto_increment,
    pName varchar(50),
    pPrice float
);

create table OrderDetail(
	oID int not null,
    pID int not null,
    primary key (oID,pID),
    odQTY int,
    Foreign key (oID) references `Order`(oID),
    Foreign key (pID) references Product(pID)
);

insert into customer 
values 	(1,'Minh Quan',10),
		(2,'Ngoc Oanh',20),
        (3,'Hong Ha',50);
        
insert into `order`
values	(1,1,'2006-03-21',null),
		(2,2,'2006-03-23',null),
        (3,1,'2006-03-16',null);
        
insert into product
values	(1,'May giat',3);

insert into product (pName,pPrice)
values	('Tu Lanh',5),
		('Dieu Hoa',7),
        ('Quat',1),
        ('Bep Dien',2);
        
insert into orderdetail
values	(1,1,3),
		(1,3,7),
        (1,4,2),
        (2,1,1),
        (3,1,8),
        (2,5,4),
        (2,3,3);
        
-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select oID, oDate, oTotalPrice
from `order`;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select tb_temp.cID, tb_temp.cName, tb_temp.oID, p.pName, tb_temp.odQTY, p.pPrice
from (
	select c.cID, c.cName, o.oID, od.pID, od.odQTY
    from customer c 
    join `order` o on c.cID = o.cID
    join orderdetail od on od.oID = o.oID
	) as tb_temp
join product p on p.pID = tb_temp.pID;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select c.cName, od.oID
from customer c
left join `order` od on c.cID = od.cID 
having od.oID is null;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice)
select o.oID, o.oDate, sum(od.odQTY * p.pPrice) as total
from `order` o
join orderdetail od on o.oID = od.oID
join product p on od.pID = p.pID
group by o.oID
;