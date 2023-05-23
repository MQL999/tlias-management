package com.minqiliang.service;

import com.minqiliang.pojo.Emp;
import com.minqiliang.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {

    /**
     * 分页条件查询员工
     *
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                   LocalDate begin, LocalDate end);


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

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改信息
     * @param emp
     */
    void update(Emp emp);
}
