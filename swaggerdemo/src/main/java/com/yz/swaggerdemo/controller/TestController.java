package com.yz.swaggerdemo.controller;

import com.yz.swaggerdemo.entity.AjaxResult;
import com.yz.swaggerdemo.entity.UserEntity;
import com.yz.toolscommon.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.yz.swaggerdemo.entity.AjaxResult.error;
import static com.yz.swaggerdemo.entity.AjaxResult.success;


@Api("用户信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController {
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();

    {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public AjaxResult userList() {
        List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
        return success(userList);
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/{userId}")
    public AjaxResult getUser(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            return success(users.get(userId));
        } else {
            return error("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer", dataTypeClass = Integer.class), @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class), @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", dataTypeClass = String.class), @ApiImplicitParam(name = "mobile", value = "用户手机", dataType = "String", dataTypeClass = String.class)})
    @PostMapping("/save")
    public AjaxResult save(UserEntity user) {
        if (StringUtil.isNull(user) || StringUtil.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        return success(users.put(user.getUserId(), user));
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public AjaxResult update(@RequestBody UserEntity user) {
        if (StringUtil.isNull(user) || StringUtil.isNull(user.getUserId())) {
            return error("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId())) {
            return error("用户不存在");
        }
        users.remove(user.getUserId());
        return success(users.put(user.getUserId(), user));
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @DeleteMapping("/{userId}")
    public AjaxResult delete(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            users.remove(userId);
            return success();
        } else {
            return error("用户不存在");
        }
    }
}