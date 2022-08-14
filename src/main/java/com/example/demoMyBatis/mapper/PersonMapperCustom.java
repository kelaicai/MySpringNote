package com.example.demoMyBatis.mapper;

import com.example.demoMyBatis.dao.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PersonMapperCustom extends PersonMapper{

    @Select("select * from person where name = #{name}")
    public List<Person> findByName(String name);

    @Select("select * from person where name = #{name} and age = #{age}")
    public List<Person> selectPersonnameAndAge(String name,Integer age);

    @Insert("insert into ${table} (id,name,age) values (#{id},#{name},#{age}) ")
    public void selfInsert(String table,Integer id,String name,Integer age);
}
