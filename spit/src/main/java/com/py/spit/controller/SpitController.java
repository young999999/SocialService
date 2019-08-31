package com.py.spit.controller;

import com.py.spit.pojo.Spit;
import com.py.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{spitid}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitid){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitid));
    }

    @RequestMapping(method =RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{spitid}",method =RequestMethod.PUT)
    public Result update(@PathVariable String spitid, @RequestBody Spit spit){
        spit.set_id(spitid);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }
    @RequestMapping(value = "/{spitid}",method =RequestMethod.DELETE)
    public Result delete(@PathVariable String spitid){
        spitService.deleteById(spitid);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "//comment/{parentid}/{page}/{size}",method =RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageData=spitService.findByParentid(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }
    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        // 判断当前用户是否已经点赞，但是现在没有做验证，暂时先把userid写死
        String userid = "111111";
        // 判断当前用户是否已经点赞
        if (redisTemplate.opsForValue().get("thumbup_spit_" + userid) != null) {
            // 已经点赞了
            return new Result(false, StatusCode.REPERROR, "不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_spit_" + userid, 1);
        return new Result(true, StatusCode.OK, "点赞成功");







        //可重复点赞
        /*spitService.thumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");*/
    }

}
