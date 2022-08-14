package com.example.demoMyBatis.mapper;

import com.example.demoMyBatis.dao.Person;
import com.example.demoMyBatis.dao.PersonQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    int countByExample(PersonQuery example);

    int deleteByExample(PersonQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonQuery example);

    Person selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonQuery example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonQuery example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}