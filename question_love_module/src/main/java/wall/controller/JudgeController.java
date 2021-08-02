package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.pojo.JudgeVo;
import wall.service.JudgeService;
import wall.util.Res;
import wall.util.StateCode;

// 评审员 controller
@RestController
@RequestMapping("/judge")
public class JudgeController {


    @Autowired
    private JudgeService judgeService;


    // 测试完成
    @ApiOperation(value = "申请成为评审员接口", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/apply/{userId}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginApply(@RequestParam(value = "session_key")String session_key, @PathVariable("userId") Long userId){
        JudgeVo judgeVo = new JudgeVo();
        judgeVo.setUserId(userId);
        judgeService.insertSelective(judgeVo);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    // 测试完成
//    @ApiOperation(value = "根据用户 id 查询评审相关信息", httpMethod = "GET", response = ResponseEntity.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
//                    required = true, dataType = "string"),
//            @ApiImplicitParam(name = "userId", value = "用户 id",
//                    required = true, dataType = "Long")
//    })
//    @RequestMapping(value = "/selectByUserId/{userId}",method = RequestMethod.GET)
//    public Res<?> aopCheckLoginSelectByUserId(@RequestParam(value = "session_key")String session_key, @PathVariable("userId") Long userId){
//        return new Res<>(StateCode.OPERATION_SUCCEED,judgeService.selectByPrimaryKey(userId,false));
//    }


//    @ApiOperation(value = "修改评审员信息接口", httpMethod = "POST", response = ResponseEntity.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
//                    required = true, dataType = "string"),
//            @ApiImplicitParam(name = "judgeVo", value = "评审员实体类",
//                    required = true, dataType = "JudgeVo")
//    })
//    @RequestMapping(value = "/updateByUserId",method = RequestMethod.POST)
//    public Res<?> aopCheckLoginUpdateByUserId(@RequestParam(value = "session_key")String session_key, @RequestBody JudgeVo judgeVo){
//        judgeService.updateByPrimaryKeySelective(judgeVo);
//        return new Res<>(StateCode.OPERATION_SUCCEED);
//    }


    //  测试完成
    @RequestMapping(value = "/agree/{userId}",method = RequestMethod.GET)
    public Res<?> agree(@PathVariable("userId") Long userId){
        judgeService.agree(userId);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    //  测试完成
    @RequestMapping(value = "/disagree/{userId}",method = RequestMethod.GET)
    public Res<?> disagree(@PathVariable("userId") Long userId,@RequestParam("reason") String reason){
        judgeService.disagree(userId,reason);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }



}
