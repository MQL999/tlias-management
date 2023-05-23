package com.minqiliang.service.impl;

import com.minqiliang.mapper.DeptMapper;
import com.minqiliang.mapper.EmpMapper;
import com.minqiliang.pojo.Dept;
import com.minqiliang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    /**
     * 查询所有部门
     * @return
     */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * 根据id删除部门
     */
    @Transactional(rollbackFor = Exception.class) // spring事物管理
    @Override
    public void deleteById(Integer id) {
        // 删除部门
        deptMapper.deleteById(id);
        // 根据部门id删除部门里的员工
        empMapper.deleteByDeptID(id);
    }

    /**
     * 添加部门
     * @return
     */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
