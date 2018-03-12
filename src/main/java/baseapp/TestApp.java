package baseapp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.google.gson.Gson;

@SpringBootApplication
@MapperScan("baseapp.dao")
public class TestApp
{
    @Bean
    public SqlSessionFactory sqlSessionFactory (DataSource dataSource,
            ApplicationContext applicationContext) throws Exception
    {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean ();
        sessionFactory.setDataSource (dataSource);
        sessionFactory.setMapperLocations (
                applicationContext.getResources ("classpath:/mapper/**/*.xml"));
        return sessionFactory.getObject ();
    }
    
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public Gson jsonSerializer() { return new Gson(); }

    public static void main (String[] args)
    {
        SpringApplication.run (TestApp.class, args);
    }
}
