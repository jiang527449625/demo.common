//package com.demo.common.config;
//
//import com.demo.common.mybatis.MySqlInterceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Configuration
//@AutoConfigureAfter(PageHelperAutoConfiguration.class)
//public class MyBatisConfig {
//
//    @Autowired
//    private List<SqlSessionFactory> sqlSessionFactoryList;
//
//    @PostConstruct
//    public void addMySqlInterceptor() {
//        MySqlInterceptor interceptor = new MySqlInterceptor();
//        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
//
//            // 添加自定义属性
////            Properties properties = new Properties();
////            properties.setProperty("prop1", "value1");
////            interceptor.setProperties(properties);
//            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
//
//        }
//    }
//
//}
