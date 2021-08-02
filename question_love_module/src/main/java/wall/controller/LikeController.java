package wall.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.LikeVo;
import wall.service.LikeService;
import wall.util.Res;
import wall.util.StateCode;

@RequestMapping("/like")
@RestController
public class LikeController {



    @Autowired
    private LikeService likeService;

    // 测试完成
    @ApiOperation(value = "点赞接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "likeVo", value = "点赞实体类",
                    required = true, dataType = "LikeVo")
    })
    @RequestMapping(value = "/add/{userId}",method = RequestMethod.POST)
    // userId 的值为被点赞的帖子所属用户的 id
    public Res<?> aopCheckLoginAddLike(@RequestParam(value = "session_key")String session_key,@PathVariable("userId") Long userId, @RequestBody LikeVo likeVo){
        likeService.insertSelective(likeVo,userId);
        return new Res<>(StateCode.OPERATION_SUCCEED,likeVo.getVo_id());
    }

    @RequestMapping(value = "/like/{id}/{userId}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginLike(@RequestParam(value = "session_key")String session_key,@PathVariable("id") Long id,@PathVariable("userId") Long userId){
        likeService.like(id, userId);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


    // 测试完成
    @ApiOperation(value = "取消点赞接口", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "主键 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/cancel/{id}/{userId}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginCancelLike(@RequestParam(value = "session_key")String session_key,@PathVariable("id") Long id,@PathVariable("userId") Long userId){
        likeService.cancelLike(id,userId);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    // 测试完成
    @ApiOperation(value = "查询用户对某个帖子的点赞情况", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "类型",
                    required = true, dataType = "Byte"),
            @ApiImplicitParam(name = "targetId", value = "帖子 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/selectIsLike/{userId}/{type}/{targetId}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginSelectIsLike(@RequestParam(value = "session_key")String session_key
            ,@PathVariable("userId") Long userId,@PathVariable("type") Byte type,@PathVariable("targetId") Long targetId) {
        return new Res<>(StateCode.OPERATION_SUCCEED,likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, type, targetId));
    }


    // 测试完成
    @ApiOperation(value = "查询用户点赞过的帖子", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "targetId", value = "分页实体类",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/selectLikeVosByUserId",method = RequestMethod.POST)
    public Res<?> aopCheckLoginSelectLikeVosByUserId(@RequestParam(value = "session_key")String session_key, @RequestParam("userId") Long userId
            , @RequestBody Pageable<Long,LikeVo> pageable) {
        pageable.setEntity(userId);
        return new Res<>(StateCode.OPERATION_SUCCEED,likeService.selectLikeVosByUserId(pageable));
    }

}
