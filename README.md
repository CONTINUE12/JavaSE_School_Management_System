# JavaSE_School_Management_System

#### 介绍
基于AWT和Swing及MYSQL开发的学校教务系统

一 功能分析

1. 学生：查询学生信息、查询成绩
2. 教师：查询教师信息、录入、修改、删除学生成绩
3. 管理员：添加、修改、删除学生账号，添加、修改、删除教师账号

二.开发环境

   Windows 10，IntelliJ IDEA 2020.2，mysql5.5
	
三.项目结构设计

1. main目录：db.DBConnentor为数据库的连接，login.Login、login.UsingExit分别为用户的登录和退出
2. student目录：StudentManage为学生系统主界面，GetStudent为查询学生信息，GetGrade为查询成绩- 
3. teacher目录：TeacherManage为教师系统主界面，GetTeacher为查询教师信息，AddGrade、DeleteGrade、SetGrade分别为录入、
               删除、修改学生成绩
4. manager目录：student.AddStudent、student.DeleteStudent、student.SetStudent分别为添加、删除、修改学生信息
	       teacher.AddTeacher、teacher.DeleteTeacher、teacher.SetTeacher分别为添加、删除、修改教师信息

四.数据库student_manage设计：

1. 数据表students：学号id、姓名name、密码password、性别sex、生日birthday、班级clas、学院school
2. 数据表teachers：编号id、姓名name、密码password、性别sex、生日birth、职称state、学院school
3. 数据表grades：学号sid、语文chinese、数学math
4. 数据表managers：编号id、姓名name、密码password 