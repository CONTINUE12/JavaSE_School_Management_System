package manager.student;

//实现学生信息的删除功能

import main.db.DBConnentor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteStudent extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L; //类序列化号，用于跨版本编译

    JLabel JL = new JLabel("删除基本信息",JLabel.CENTER); //设置标签对象

    JLabel JLnumber = new JLabel("学号："); //创建标签及文本框对象
    JTextField JTnumber = new JTextField();

    JLabel JLname = new JLabel("姓名：");
    JTextField JTname = new JTextField();

    JButton JBdelete = new JButton("删除");
    JButton JBnext = new JButton("重置");
    JButton JBexit = new JButton("退出");

    String sql = "";

    //构造方法
    public DeleteStudent()
    {
        this.setTitle("删除学生信息");
        this.setLayout(null);

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,19));
        JL.setBounds(150,30,200,40);
        this.add(JL);

        JLnumber.setBounds(100,120,100,20); //设置标签的位置
        this.add(JLnumber);
        JTnumber.setBounds(200,120,80,20);
        this.add(JTnumber);

        JLname.setBounds(100,160,60,20);
        this.add(JLname);
        JTname.setBounds(200,160,80,20);
        this.add(JTname);

        JBdelete.setBounds(80,320,90,20); //设置按钮的位置
        this.add(JBdelete);
        JBdelete.addActionListener(this); //给按钮添加监听器

        JBnext.setBounds(190,320,90,20);
        this.add(JBnext);
        JBnext.addActionListener(this);

        JBexit.setBounds(300,320,90,20);
        this.add(JBexit);
        JBexit.addActionListener(this);

        this.setBounds(710,340,500,400);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //事件响应方法
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == JBdelete) //处理添加事件
        {
            int inumber = Integer.parseInt(JTnumber.getText()); //将文本传给字符串inumber

            sql = "select * from students where Id = '" + inumber + "'"; //检索出Id=inumber的学生的所有信息

            try{
                Statement stm = DBConnentor.getConn().createStatement();
                ResultSet rs = stm.executeQuery(sql);

                if(rs.next()){ //判断结果是否存在
                    JTname.setText(rs.getString(2));
                    sql = "delete from students where Id = '" + inumber + "'";
                    int n = stm.executeUpdate(sql); //对数据库进行更新
                    if(n > 0)
                        JOptionPane.showMessageDialog(null,"删除成功！");
                    else
                        JOptionPane.showMessageDialog(null,"删除失败！");
                }
                else{
                    JOptionPane.showMessageDialog(null,"此用户不存在！");
                }
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }

        if(e.getSource() == JBnext) //处理重置事件
        {
            //设置文本的text值为null
            JTnumber.setText(null);
            JTname.setText(null);
        }

        if(e.getSource() == JBexit)
        {
            setVisible(false); //设置窗口不可见
        }
    }

    public static void main(String[] args) {
        new DeleteStudent(); //实例化一个对象
    }

}
