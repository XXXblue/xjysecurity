package com.xjy.security.controller;

import com.xjy.security.Async.DeferredResultHalder;
import com.xjy.security.Async.MockQueue;
import com.xjy.security.dto.User;
import com.xjy.security.dto.UserQueryCondition;
import com.xjy.security.enums.ResultEnum;
import com.xjy.security.exception.JsonException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1716:12
 * @Description: restful风格的api
 * @Modified By:
 */
@RestController
//资源总缀
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHalder deferredResultHalder;

    @GetMapping
    @ApiOperation(value = "用户查询服务")
//    @RequestParam是后台要求你必须传这个参数，不然就会400
//  获取全部
    public List<User> getUser(UserQueryCondition userQueryCondition){
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("u111");
        user1.setPassword("p111");
        list.add(user1);
        User user2 = new User();
        user2.setUsername("u222");
        user2.setPassword("p222");
        list.add(user2);
        return list;
    }
//这玩意是正则，能防止你传字符串的id
//获取某个
    @GetMapping(value = "/{id:\\d+}")
    public User getUser(@PathVariable("id") String id){
        User user =new User();
        user.setUsername("jfkd");
        return user;
    }
//修改
    @PutMapping(value = "/{id:\\d+}")
    public User updateUser(@RequestBody User user){
        return user;
    }
//创建
    @PostMapping
    public User createUser(@RequestBody User user){
        return user;
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void deleteUser(@PathVariable("id") String id){
        System.out.println("delete");
    }

//    模拟不可预知错误json
    @GetMapping(value = "/500")
    public void testError(){
        int i= 1/0;
    }

    @GetMapping(value = "/501")
    public void testErrorSecond(){
//        模拟可预知错误json
        throw new JsonException(ResultEnum.ONE_TWO_TWO);
    }

//    传统的同步请求处理测试
    @GetMapping(value = "testAsync")
    public String asyncSuccess() {
        log.info("主线程开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("主线程结束");
        return "success";
    }

//    使用callable的异步处理请求测试，这种在实际项目中不推荐，不能支持复杂的业务场景
    @GetMapping("/runnable")
    public Callable<String> asyncSuccessRunnable() {
        log.info("主线程开始");
        Callable<String> result=new Callable<String>(){
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程结束");
                // TODO Auto-generated method stub
                return null;
            }
        } ;
        log.info("主线程结束");
        return result;
    }

//    模拟复杂的业务场景，订单和消息队列
    @GetMapping("/deferredReult")
    public DeferredResult<String> asyncSuccessDeferredReult() {
        log.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<String>();
        deferredResultHalder.getMap().put(orderNumber, result);
//        deferredResultHalder.getMap().get(orderNumber).setResult("place order success");
        log.info("主线程结束");
        return result;
    }
}
