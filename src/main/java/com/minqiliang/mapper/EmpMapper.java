package com.minqiliang.mapper;

import com.minqiliang.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数,不使用分页插件
     * @return
     */
    /*@Select("select count(*) from emp")
    Long count();*/

    /**
     * 分页查询，不使用分页插件
     * @param start
     * @param pageSize
     * @return
     */
    /*@Select("select * from emp limit #{start},#{pageSize} ;")
    List<Emp> page(Integer start, Integer pageSize);*/

    /**
     * 查询所有信息，使用插件
     *
     * @return
     */
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    void delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     * @return
     */
    void add(Emp emp);

    @Select("select * from emp where id = #{id};")
    Emp getById(Integer id);

    /**
     * 修改信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据部门id删除员工
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptID(Integer deptId);
}
