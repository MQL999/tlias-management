package com.minqiliang.mapper;

import com.minqiliang.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from emp where username = #{username} and password = #{password};")
    Emp getByUsernameAndPassword(Emp emp);
}
