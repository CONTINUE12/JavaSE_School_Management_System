package main.db;

//实现连接数据库的公共类
import java.sql.*;

public class DBConnentor {

    static int count = 0;
    private static Connection conn = null;
    public static Connection getConn(){
        try{
            Class.forName("Com.mysql.jdbc.Driver"); //加载数据库驱动
        }catch(ClassNotFoundException e){

        }

        String url = "jdbc:mysql://localhost:3306/student_manage?useSSL=false&useUnicode=true&characterEncoding=gb2312";
        String user = "root"; //用户名
        String password = ""; //密码

        try{
            conn = DriverManager.getConnection(url,user,password); //连接数据库
            if(conn != null && count == 0){
                System.out.println("数据库连接成功");
                count++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return conn; //返回Connection对象
    }
}
