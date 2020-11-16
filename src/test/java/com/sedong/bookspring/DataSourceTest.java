package com.sedong.bookspring;


import com.sedong.bookspring.config.RootContext;
import com.sedong.bookspring.config.WebConfig;
import com.sedong.bookspring.config.WebContext;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContext.class,WebConfig.class})
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
