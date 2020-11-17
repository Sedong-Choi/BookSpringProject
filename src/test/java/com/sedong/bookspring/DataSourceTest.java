package com.sedong.bookspring;


import com.bookspring.config.RootContext;

import com.bookspring.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContext.class, WebConfig.class})
// WebContext.class 등록을 하면 error가 난다 조심!
public class DataSourceTest {

    @Autowired
    private DataSource ds;
    // ContextDataSource.class 만들고 @Import를 이용하여 RootContext에 등록해야한다.
    //이걸로 많은 시간 보냈다.

    @Test
    public void testConnection() throws Exception{

        try(Connection con = ds.getConnection()){
            System.out.println(con);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
