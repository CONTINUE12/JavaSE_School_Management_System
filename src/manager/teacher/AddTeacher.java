package manager.teacher;

//实现添加教师信息功能

import main.db.DBConnentor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddTeacher extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L; //类序列化号，用于跨版本编译

    JLabel JL = new JLabel("添加基本信息",JLabel.CENTER); //设置标签对象

    JLabel JLnumber = new JLabel("教师编号："); //创建标签及文本框对象
    JTextField JTnumber = new JTextField();

    JLabel JLname = new JLabel("姓名：");
    JTextField JTname = new JTextField();

    JLabel JLsex = new JLabel("性别：");
    ButtonGroup BG = new ButtonGroup();
    JRadioButton JRB1 = new JRadioButton("男");
    JRadioButton JRB2 = new JRadioButton("女");

    JLabel JLbirthday = new JLabel("生日：");
    JTextField JTbirthday = new JTextField();

    JLabel JLstate = new JLabel("职称：");
    JTextField JTstate =new JTextField();

    JLabel JLxueyuan = new JLabel("学院：");
    JTextField JTxueyuan = new JTextField();

    JButton JBadd = new JButton("添加");
    JButton JBnext = new JButton("重置");
    JButton JBexit = new JButton("退出");

    String sql = "";

    //构造方法
    public AddTeacher()
    {
        this.setTitle("添加教师信息");
        this.setLayout(null);

        JL.setForeground(Color.red);
        JL.setFont(new java.awt.Font("宋体",Font.PLAIN,19));
        JL.setBounds(150,30,200,40);
        this.add(JL);

        JLnumber.setBounds(100,80,100,20); //设置标签的位置
        this.add(JLnumber);
        JTnumber.setBounds(200,80,100,20);
        this.add(JTnumber);

        JLname.setBounds(100,120,60,20);
        this.add(JLname);
        JTname.setBounds(200,120,80,20);
        this.add(JTname);

        JLsex.setBounds(100,160,100,20);
        this.add(JLsex);
        JRB1.setBounds(200,160,40,20);
        JRB2.setBounds(300,160,40,20);
        this.add(JRB1);
        this.add(JRB2);
        BG.add(JRB1); //添加单选按钮到ButtonGroup组件
        BG.add(JRB2);

        JLbirthday.setBounds(100,200,80,20);
        this.add(JLbirthday);
        JTbirthday.setBounds(200,200,80,20);
        this.add(JTbirthday);

        JLstate.setBounds(100,240,60,20);
        this.add(JLstate);
        JTstate.setBounds(200,240,80,20);
        this.add(JTstate);

        JLxueyuan.setBounds(100,280,60,20);
        this.add(JLxueyuan);
        JTxueyuan.setBounds(200,280,80,20);
        this.add(JTxueyuan);

        JBadd.setBounds(80,320,90,20); //设置按钮的位置
        this.add(JBadd);
        JBadd.addActionListener(this); //给按钮添加监听器

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

        if(e.getSource() == JBadd) //处理添加事件
        {
            int snumber = Integer.parseInt(JTnumber.getText()); //将文本传给字符串snumber
            String sname = JTname.getText();
            String ssex = "女";
            if(JRB1.isSelected()){
                ssex = "男";
            }
            String sbirthday = JTbirthday.getText();
            String sstate = JTstate.getText();
            String sxueyuan = JTxueyuan.getText();

            sql = "select * from teachers where Id = '" + snumber + "'";

            try{
                Statement stm = DBConnentor.getConn().createStatement();
                ResultSet rs = stm.executeQuery(sql);

                if(rs.next()){ //判断结果是否存在
                    JOptionPane.showMessageDialog(null,"该账号已经存在!");
                }
                else{
                    //插入一组数据
                    sql = "insert into teachers values('" + snumber + "','" + sname + "','','" + ssex + "','" +
                            sstate + "','" + sxueyuan + "','" + sbirthday + "')";
                    int i = stm.executeUpdate(sql); //对数据库进行更新
                    if(i > 0){
                        JOptionPane.showMessageDialog(null,"添加成功！");
                        System.out.println(i + " teacher added");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"添加失败！");
                    }
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
            JTbirthday.setText(null);
            JTstate.setText(null);
            JTxueyuan.setText(null);
        }

        if(e.getSource() == JBexit)
        {
            setVisible(false); //设置窗口不可见
        }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}
