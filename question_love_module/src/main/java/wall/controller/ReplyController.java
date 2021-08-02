package wall.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.ReplyVo;
import wall.service.ReplyService;
import wall.util.Res;
import wall.util.StateCode;

@RequestMapping("/reply")
@RestController
public class ReplyController {


    @Autowired
    private ReplyService replyService;

    // 测试完成
    @ApiOperation(value = "添加回复接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "replyVo", value = "回复实体类",
                    required = true, dataType = "ReplyVo")
    })
    @RequestMapping(value = "/add/{userId}",method = RequestMethod.POST)
    // userId 的值为被回复的帖子所属的用户的 id
    public Res<?> aopCheckLoginAddReply(@RequestParam(value = "session_key")String session_key,@PathVariable("userId") Long userId, @RequestBody ReplyVo replyVo ) {
        replyService.insertSelective(replyVo,userId);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    // 测试完成
    @ApiOperation(value = "修改回复接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "replyVo", value = "回复实体类",
                    required = true, dataType = "ReplyVo")
    })
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Res<?> aopCheckLoginUpdate(@RequestParam(value = "session_key")String session_key, @RequestBody ReplyVo replyVo ){
        replyService.updateByPrimaryKeySelective(replyVo);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


    // 测试完成
    @ApiOperation(value = "查询回复列表接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 id ，可以不传，用户登陆后可以传入",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pageable", value = "分页实体类",
                    required = true, dataType = "Pageable")
    })
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
    public Res<?> ordinaryAopSelectList(@RequestParam(value = "userId",required = false) Long userId,@RequestBody  Pageable<ReplyVo,ReplyVo> pageable){
        return new Res<>(StateCode.OPERATION_SUCCEED,replyService.selectList(pageable,userId));
    }


    @ApiOperation(value = "根据 id 和用户 id 删除回复", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "回复 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/delete/{userId}/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginDeleteReply(@RequestParam(value = "session_key")String session_key,@PathVariable("userId") Long userId,@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,replyService.deleteReplyByUser(userId,id));
    }

}
