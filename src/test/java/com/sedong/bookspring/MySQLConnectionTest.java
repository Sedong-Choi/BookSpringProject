package com.sedong.bookspring;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/book_ex";
    private static final String USER= "sedong";
    private static final String PW = "sedong";

    @Test
    public void testConnection() throws Exception{

        Class.forName(DRIVER);
        try(Connection con = DriverManager.getConnection(URL,USER,PW)){
            System.out.println(con);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
