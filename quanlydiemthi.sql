create database QuanLyDiemThi;
create table hocsinh(
	MaHS varchar(20) primary key,
    TenHS varchar(50),
    NgaySinh datetime,
    Lop varchar(20),
    GT varchar(20)
);

create table monhoc(
	MaMH varchar(20) primary key,
    TenMH varchar(50)
);

create table bangdiem(
	MaHS varchar(20),
    MaMH varchar(20),
    DiemThi float,
    NgayKT datetime,
    primary key (MaHS,MaMH),
    foreign key (MaHS) references hocsinh(MaHS),
    foreign key (MaMH) references monhoc(MaMH)
);

create table giaovien(
	MaGV varchar(20) primary key,
    TenGV varchar(50),
    sdt varchar(10)
);

alter table monhoc
	add MaGV varchar(20);
    
alter table monhoc add constraint fk_magv foreign key (MaGV) references giaovien(MaGV);
