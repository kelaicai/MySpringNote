package com.example.demoMyBatis;

import com.example.demoMyBatis.dao.Person;
import com.example.demoMyBatis.mapper.PersonMapper;
import com.example.demoMyBatis.mapper.PersonMapperCustom;
import com.example.demoMyBatis.mapper.TableMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MapperScan("com.example.demoMyBatis.mapper")
@Slf4j
@Component
@SpringBootApplication
public class DemoMyBatisApplication {

//	@Autowired
//	private PersonMapper personMapper;

	private String CREATE_SQL = "Create table if not exists person2 ("+
			"        `id`       int(0)      NOT NULL AUTO_INCREMENT COMMENT '主键'," +
			"        `name` varchar(255)      NULL DEFAULT NULL COMMENT '用户名'," +
			"        `age` int(10) NULL DEFAULT NULL COMMENT '年龄'," +
			"        PRIMARY KEY (`id`)\n" +
			"        ) ENGINE = InnoDB\n" +
			"        AUTO_INCREMENT = 9\n" +
			"        CHARACTER SET = utf8mb4 COMMENT ='用于测试的用户表';";

	@Autowired
	private PersonMapperCustom personMapperCustom;

	//@Resource
	@Autowired
	private TableMapper tableMapper;

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(DemoMyBatisApplication.class, args);
		DemoMyBatisApplication app = context.getBean(DemoMyBatisApplication.class);
		app.run();
		app.testDB();
	}

	public void run(){
		log.info("hello");
	}

	public void testDB(){
		Person p1 = new Person(1,"xiaoming",1);
		//personMapperCustom.insert(p1);


		for(int i = 0 ;i<3;i++) {
			try {
				personMapperCustom.selfInsert("person2", p1.getId(), p1.getName(), p1.getAge());
			} catch (Exception e) {
				log.error("table not exists:{}", e);
				if (e.getMessage().indexOf("doesn't exist") != -1) {
					log.info("create table");
					//tableMapper.createTable("person2");
					try{
						int result  =   tableMapper.createTableFromSql(CREATE_SQL);
						log.info("result:{}", result);
					}catch (Exception e2){
						log.error("create table ex:{}",e2);
					}
					continue;
				}
			}
			log.info("obj:{}",p1);
		}


		//Person person = personMapper.selectByPrimaryKey(1);
		Map<String,Object> par =  new HashMap<>();
		par.put("name","xiaoming");
		par.put("age",new Integer(1));
		List<Person> person = personMapperCustom.selectPersonnameAndAge("xiaoming",1);
		log.info("{}",person);


		personMapperCustom.deleteByPrimaryKey(1);
	}
}
