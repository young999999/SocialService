package com.py.base.dao;

import com.py.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//继承JpaRepository来完成对数据库的操作
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> { }
