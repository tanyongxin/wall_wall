package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.pojo.LoginPojo;
import wall.pojo.UserTabVo;
import wall.service.UserTabService;
import wall.util.Res;
import wall.util.StateCode;

@RestController
public class UserTabController {

    @Autowired
    private UserTabService userTabService;

    @ApiOperation(value = "小程序用户登录接口", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "小程序端的 code",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "wxName", value = "微信用户名",
                    required = true, dataType = "string")
    })
    @RequestMapping(value = "/smallProgramLogin",method = RequestMethod.POST)
    public Res<?> ordinaryAopSmallProgramLogin(@RequestBody LoginPojo loginPojo){
        String code = loginPojo.getCode();
        String wxName = loginPojo.getWxName();
        String avatars = loginPojo.getAvatars();
        UserTabVo userTab = userTabService.smallProgramLogin(code, wxName,avatars);
        System.out.println(userTab);
        return userTab == null ? new Res<>(StateCode.LOGIN_FAILURE) : new Res<>(StateCode.OPERATION_SUCCEED,userTab);
    }


    // 测试完成
    @ApiOperation(value = "修改用户信息接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userTabVo", value = "用户实体类",
                    required = true, dataType = "UserTabVo")
    })
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Res<?> aopCheckLoginUpdateUser(@RequestParam(value = "session_key",required = true)String session_key,@RequestBody  UserTabVo userTabVo){
        userTabService.updateUserTab(userTabVo);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    // 测试完成
    @ApiOperation(value = "根据id查找用户信息", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",
                    required = true, dataType = "long")
    })
    @RequestMapping(value = "/selectUserById/{id}",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectUserById(@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,userTabService.selectUserById(id));
    }


    // 测试完成
    @ApiOperation(value = "根据id查找用户名", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",
                    required = true, dataType = "long")
    })
    @RequestMapping(value = "/selectUserNameById/{id}",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectUserNameById(@PathVariable("id") Long id){
        System.out.println("ordinaryAopSelectUserNameById ... ");
        return new Res<>(StateCode.OPERATION_SUCCEED,userTabService.selectUserNameById(id));
    }


    // 获取一批用户 id，每一批的大小由 size 决定。
    @RequestMapping(value = "/selectUserIds",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectUserIds(@RequestParam("size") int size){
        return new Res<>(StateCode.OPERATION_SUCCEED,userTabService.selectUserIds(size));
    }



}
