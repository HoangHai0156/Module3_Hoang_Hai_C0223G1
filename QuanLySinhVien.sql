
create table class(
	ClassID int primary key auto_increment,
    ClassName varchar(60) not null,
    StartDate date not null,
    `Status` bit
);

create table Student(
	StudentID int primary key auto_increment,
    StudentName varchar(60) not null,
    Address varchar(50),
    Phone varchar(20),
    `Status` bit,
    ClassID int not null
);

create table `Subject`(
	SubID int primary key auto_increment,
    SubName varchar(30) not null,
    Credit tinyint not null default 1 check (credit >= 1),
    `Status` bit default 1
);

create table Mark(
	MarkID int primary key auto_increment,
    SubID int not null,
    StudentID int not null,
    Mark float default 0 check (mark between 0 and 100),
    ExamTimes tinyint default 1
);

alter table Mark add foreign key (SubID) references `Subject`(SubID);

alter table Mark add foreign key (StudentID) references Student(StudentID);

insert into class(ClassID,ClassName,StartDate,`Status`)
values (1,'A1','2008-12-20',1);
insert into class(ClassID,ClassName,StartDate,`Status`)
values (2,'A2','2008-12-22',1);
insert into class(ClassID,ClassName,StartDate,`Status`)
values (3,'B3',current_date(),0);

insert into student values (1,'Hung','Ha Nội','0912113113',1,1),
(2,'Hoa','Hai Phòng','',1,1),
(3,'Manh','HCM','0123123123',0,2);

insert into `subject` 
values 	(1,'CF',5,1),
		(2,'C',6,1),
        (3,'HDJ',5,1),
        (4,'RDBMS',10,1);
        
insert into mark 
values	(1,1,1,8,1),
		(2,1,2,10,2),
        (3,2,1,12,1);

select * from student
where `status` = true;

select * from `subject`
where Credit < 10;

select s.StudentID, s.StudentName, c.ClassName from student s
join class c on s.ClassID = c.ClassID
where c.ClassName = 'A1';

select m.StudentID, stu.StudentName, sub.SubName, m.Mark from `subject` sub
join mark m on sub.SubID = m.SubID join student stu on stu.StudentID = m.StudentID
where sub.SubName = 'CF';

-- Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’
select * from student
where StudentName like 'H%';

-- Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.
select * from class
where month(StartDate) = 12;

-- Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.
select * from `subject`
where credit between 3 and 5;

-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2. UPDATE `quanlysinhvien`.`student` SET `ClassID` = '2' WHERE (`StudentID` = '1');
update student
set ClassID = 2 where (StudentName = 'Hung');

-- Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
select * 
from (
	select stu.StudentName, sub.SubName, m.Mark
    from student stu join mark m on stu.StudentID = m.StudentID join `subject` sub on sub.SubID = m.SubID
    order by stu.StudentName
) as tb_temp
order by tb_temp.Mark desc;

-- Hiển thị số lượng sinh viên ở từng nơi
select Address, count(Address) as soLuongSV
from student
group by Address;

-- Tính điểm trung bình các môn học của mỗi học viên
select m.StudentID, stu.StudentName, avg(mark) as DTB
from mark m
join student stu on stu.StudentID = m.StudentID
group by StudentID;

-- Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 15
select m.StudentID, stu.StudentName, avg(mark) as DTB
from mark m
join student stu on stu.StudentID = m.StudentID
group by StudentID
having DTB > 15;

-- Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
select m.StudentID, stu.StudentName, avg(mark) as DTB
from mark m
join student stu on stu.StudentID = m.StudentID
group by StudentID
having avg(mark) >= (select max(avg1) from (select avg(mark) as avg1 from mark m1 group by m1.StudentID) as tb_temp )
;

-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select *
from `subject` s 
group by s.SubID
having s.Credit >= all (select s2.Credit as cr from `subject` s2);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select s.SubID, s.SubName, s.Credit, s.`Status`, max(m.Mark)
from mark m
join `subject` s on s.SubID = m.SubID
group by m.SubID
having max(m.Mark) >= all (select max(m1.Mark) from mark m1 group by m1.SubID)
;

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
create view student_mark as
select stu.StudentID, stu.StudentName, stu.Address, stu.Phone, stu.`Status`, stu.ClassID, avg(m.Mark)
from mark m 
join student stu on m.StudentID = stu.StudentID
group by m.StudentID
order by avg(m.Mark) desc
;

select * from student_mark;