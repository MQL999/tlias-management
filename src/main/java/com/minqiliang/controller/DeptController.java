package com.minqiliang.controller;

import com.minqiliang.anno.Log;
import com.minqiliang.pojo.Dept;
import com.minqiliang.pojo.Result;
import com.minqiliang.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /**
     * 根据id删除部门
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除部门");
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门");
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询部门");
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result updata(@RequestBody Dept dept){
        log.info("修改部门信息");
        deptService.update(dept);
        return Result.success();
    }
}
