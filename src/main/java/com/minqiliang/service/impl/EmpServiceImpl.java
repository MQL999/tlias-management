package com.minqiliang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.minqiliang.mapper.EmpMapper;
import com.minqiliang.pojo.Emp;
import com.minqiliang.pojo.PageBean;
import com.minqiliang.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 不使用插件
     * @param page
     * @param pageSize
     * @return
     */
   /* @Override
    public PageBean page(Integer page, Integer pageSize) {
        Long count = empMapper.count();
        List<Emp> empList = empMapper.page((page - 1) * pageSize, pageSize);
        PageBean pageBean = new PageBean(count,empList);
        return pageBean;
    }*/

    /**
     * 分页查询使用插件
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDate begin, LocalDate end) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * 添加员工
     * @param emp
     * @return
     */
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改信息
     * @param emp
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }
}
