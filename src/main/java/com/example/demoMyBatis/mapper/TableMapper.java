package com.example.demoMyBatis.mapper;


public interface TableMapper {
    void createTable(String table);
    int createTableFromSql(String sql);
}
