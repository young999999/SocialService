package com.py.base.controller;

import com.py.base.pojo.Label;
import com.py.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result updata(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.updata(label);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
}
