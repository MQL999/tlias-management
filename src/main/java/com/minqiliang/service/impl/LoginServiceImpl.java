package com.minqiliang.service.impl;

import com.minqiliang.mapper.LoginMapper;
import com.minqiliang.pojo.Emp;
import com.minqiliang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Emp login(Emp emp) {
        return loginMapper.getByUsernameAndPassword(emp);
    }
}
