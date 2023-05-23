package com.minqiliang.service;

import com.minqiliang.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    void deleteById(Integer id);

    /**
     * 添加部门
     * @return
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    void update(Dept dept);
}
