package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("Swagger-User类测试")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="根据Id查询方法测试",notes="根据传参返回对应的值")
    @GetMapping("/searchById")
    public User searchById(String id){
       return userService.findById(id);
    }

    @ApiOperation(value="根据User查询方法测试",notes="根据传参返回对应的值")
    @GetMapping("/searchByUser")
    public List<User> searchByUser(User user){
        return userService.findByUser(user);
    }

    @ApiOperation(value="根据User增加方法测试",notes="根据传参返回对应的值")
    @PostMapping("/addUser")
    public User addUser(User user){
        return userService.addUser(user);
    }

    @ApiOperation(value="根据Id删除方法测试",notes="根据传参返回对应的值")
    @DeleteMapping("/deleteUser")
    public String delete(String id){
        userService.deleteUser(id);
        return "删除成功！！！";
    }

    @ApiOperation(value="根据User修改方法测试",notes="根据传参返回对应的值")
    @PutMapping("/modifyUser")
    public User modifyUser(User user){
        return userService.modifyUser(user);
    }

}



