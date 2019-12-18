package com.plate.portalsite.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionTest {

    public static void main(String[] args) {

        String Url="jdbc:mysql://localhost:3306/portalsite?serverTimezone=UTC";
        String User="root";
        String Password="";
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库链接
            Connection con = DriverManager.getConnection(Url, User, Password);
            //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from  m_item ");
            //4.处理数据库的返回结果(使用ResultSet类)
            while (rs.next()) {
                System.out.println("username" + rs.getString("title"));
            }
            //关闭资源
            rs.close();
            st.close();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
