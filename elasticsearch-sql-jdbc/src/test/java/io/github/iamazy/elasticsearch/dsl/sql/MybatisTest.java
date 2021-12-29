package io.github.iamazy.elasticsearch.dsl.sql;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MybatisTest {



    @Test
    public void testConn(){
        Configuration configuration = new Configuration();
        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = factory.openSession();
        System.out.println(1);
    }

}
