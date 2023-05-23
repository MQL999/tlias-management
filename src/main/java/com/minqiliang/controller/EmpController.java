package com.minqiliang.controller;

import com.minqiliang.anno.Log;
import com.minqiliang.pojo.Emp;
import com.minqiliang.pojo.PageBean;
import com.minqiliang.pojo.Result;
import com.minqiliang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页条件查询员工
     *
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量或单个删除员工，{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 添加员工
     * @param emp
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info("添加员工,{}",emp);
        empService.add(emp);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息，{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改信息
     * @param emp
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息，新的信息为：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
