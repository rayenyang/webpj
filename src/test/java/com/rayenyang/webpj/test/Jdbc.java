package com.rayenyang.webpj.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author rayenyang
 *         Date:    2018/4/12
 */
public class Jdbc {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmnt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webpj", "root", "1234");
            conn.setAutoCommit(false);
            // 设置隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            pstmnt = conn.prepareStatement("INSERT INTO t_user(user_name) VALUES (?)");
            pstmnt.setString(1, "jdbc");
            pstmnt.execute();
            throw new RuntimeException("hahh");
        } catch (Exception e) {
            e.printStackTrace();
           /* try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }*/
        } finally {
            if (pstmnt != null) {
                try {
                    pstmnt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
    }
}
