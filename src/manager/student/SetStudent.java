package manager.student;

//实现学生信息的修改功能

import main.db.DBConnentor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetStudent extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L; //类序列化号，用于跨版本编译

    JLabel JL = new JLabel("修改学生信息",JLabel.CENTER); //设置标签对象

    JLabel JLnumber = new JLabel("学号："); //创建标签及文本框对象
    JTextField JTnumber = new JTextField();

    JLabel JLname = new JLabel("姓名：");
    JTextField JTname = new JTextField();

    JLabel JLsex = new JLabel("性别：");
    ButtonGroup BG = new ButtonGroup();
    JRadioButton JRB1 = new JRadioButton("男");
    JRadioButton JRB2 = new JRadioButton("女");

    JLabel JLbirthday = new JLabel("生日：");
    JTextField JTbirthday = new JTextField();

    JLabel JLclass = new JLabel("班级：");
    JTextField JTclass =new JTextField();

    JLabel JLxueyuan = new JLabel("学院：");
    JTextField JTxueyuan = new JTextField();

    JButton JBget = new JButton("查询");
    JButton JBset = new JButton("修改");
    JButton JBexit = new JButton("退出");

    String sql = "";

    //构造方法
    public SetStudent()
    {
        this.setTitle("修改学生信息");
        this.setLayout(null);

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,19));
        JL.setBounds(150,30,200,40);
        this.add(JL);

        JLnumber.setBounds(100,80,100,20); //设置标签的位置
        this.add(JLnumber);
        JTnumber.setBounds(200,80,80,20);
        this.add(JTnumber);

        JLname.setBounds(100,160,60,20);
        this.add(JLname);
        JTname.setBounds(200,160,80,20);
        this.add(JTname);

        JLsex.setBounds(100,200,100,20);
        this.add(JLsex);
        JRB1.setBounds(200,200,40,20);
        JRB2.setBounds(300,200,40,20);
        this.add(JRB1);
        this.add(JRB2);
        BG.add(JRB1); //添加单选按钮到ButtonGroup组件
        BG.add(JRB2);

        JLclass.setBounds(100,240,80,20);
        this.add(JLclass);
        JTclass.setBounds(200,240,80,20);
        this.add(JTclass);

        JLbirthday.setBounds(100,280,60,20);
        this.add(JLbirthday);
        JTbirthday.setBounds(200,280,80,20);
        this.add(JTbirthday);

        JLxueyuan.setBounds(100,320,60,20);
        this.add(JLxueyuan);
        JTxueyuan.setBounds(200,320,80,20);
        this.add(JTxueyuan);

        JBget.setBounds(80,120,90,20); //设置按钮的位置
        this.add(JBget);
        JBget.addActionListener(this); //给按钮添加监听器

        JBset.setBounds(190,120,90,20);
        this.add(JBset);
        JBset.addActionListener(this);

        JBexit.setBounds(300,120,90,20);
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

        if(e.getSource() == JBget)
        {
            int inumber = Integer.parseInt(JTnumber.getText()); //将文本传给字符串inumber
            sql = "select * from students where Id = '" + inumber + "'"; //检索出Id=inumber的学生的所有信息

            try{
                Statement stm = DBConnentor.getConn().createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if(rs.next())
                {
                    String name = rs.getString(2);
                    JTname.setText(name); //将字符串name显示在文本框中

                    String sex = rs.getString(4);
                    if(sex.equals("男")){
                        JRB1.setSelected(true);
                    }else{
                        JRB2.setSelected(true);
                    }

                    String clas = rs.getString(6);
                    JTclass.setText(clas);

                    String birthday = rs.getString(5);
                    JTbirthday.setText(birthday);

                    String xueyuan = rs.getString(7);
                    JTxueyuan.setText(xueyuan);
                }else{
                    JOptionPane.showMessageDialog(null,"此用户不存在！");
                }
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }

        if(e.getSource() == JBset)
        {
            int inumber = Integer.parseInt(JTnumber.getText()); //将文本传给字符串inumber

            String sname = JTname.getText();
            String sclass = JTclass.getText();
            String ssex = "女";
            if(JRB1.isSelected()){
                ssex = "男";
            }
            String sbirthday = JTbirthday.getText();
            String sxueyuan = JTxueyuan.getText();
            sql = "select * from students where Id = '" + inumber + "'"; //检索出Id=inumber的学生的所有信息

            try{
                Statement stm = DBConnentor.getConn().createStatement();
                ResultSet rs = stm.executeQuery(sql);
                if(rs.next())
                {
                    sql = "update students set name ='" + sname + "',clas='" + sclass +"',sex='" + ssex +
                            "',school='" + sxueyuan + "',birthday='" + sbirthday + "'where Id='" + inumber + "'";
                    int n = stm.executeUpdate(sql); //对数据库进行更新
                    if(n > 0)
                        JOptionPane.showMessageDialog(null,"修改成功！");
                    else
                        JOptionPane.showMessageDialog(null,"修改失败！");
                }else{
                    JOptionPane.showMessageDialog(null,"此用户不存在！");
                }
            }catch(Exception ee){
                ee.printStackTrace();
            }
        }

        if(e.getSource() == JBexit)
        {
            setVisible(false); //设置窗口不可见
        }
    }

    public static void main(String[] args) {
        new SetStudent();
    }
}
