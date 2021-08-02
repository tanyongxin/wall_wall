package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.pojo.Feedback;
import wall.service.FeedbackService;
import wall.util.Res;
import wall.util.StateCode;


@RequestMapping("/feedback")
@RestController
public class FeedbackController {


    @Autowired
    private FeedbackService feedbackService;


    @ApiOperation(value = "添加回馈接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "feedback", value = "反馈实体类",
                    required = true, dataType = "Feedback")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res<?> aopCheckLoginAdd(@RequestParam(value = "session_key")String session_key, @RequestBody Feedback feedback){
        feedbackService.insertSelective(feedback);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectById(@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,feedbackService.selectByPrimaryKey(id));
    }

}
