package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.Message;
import wall.pojo.MessageVo;
import wall.service.MessageService;
import wall.util.Res;
import wall.util.StateCode;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageService messageService;


    @ApiOperation(value = "根据条件查询自己的消息", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "pageable", value = "分页实体类，可以传入 type 和 userId 进行查询",
                    required = true, dataType = "Pageable")
    })
    @RequestMapping(value = "/selectMessageVos",method = RequestMethod.POST)
    public Res<?> aopCheckLoginSelectMessageVos(@RequestParam(value = "session_key")String session_key, @RequestBody Pageable<MessageVo, MessageVo> pageable){
        return new Res<>(StateCode.OPERATION_SUCCEED,messageService.selectMessageVos(pageable));
    }

//    @ApiOperation(value = "根据时间查询消息", httpMethod = "GET", response = ResponseEntity.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
//                    required = true, dataType = "string"),
//            @ApiImplicitParam(name = "pageable", value = "分页实体类，可以传入 type 和 userId 进行查询",
//                    required = true, dataType = "Pageable")
//    })
//    @RequestMapping(value = "/selectMessageVosByTime",method = RequestMethod.GET)
//    public Res<?> aopCheckLoginSelectMessageVosByTime(@RequestParam(value = "session_key")String session_key,){
//
//    }

    // 查看了某条消息
    @ApiOperation(value = "将某条消息置为已读", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "消息的 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginView(@RequestParam(value = "session_key")String session_key, @PathVariable("id") Long id){
        messageService.view(id);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public Res<?> aopCheckLoginHasNew(@RequestParam(value = "session_key")String session_key, @RequestParam("userId")Long userId){
        if (userId == null)
            return new Res<>(StateCode.INVALIDPARAMETER);
        return new Res<>(StateCode.OPERATION_SUCCEED,messageService.selectCountByUserId(userId));
    }
}
