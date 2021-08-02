package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.ReportSub;
import wall.service.ReportSubService;
import wall.util.Res;
import wall.util.StateCode;


@RequestMapping("/reportSub")
@RestController
public class ReportSubController {


    @Autowired
    private ReportSubService reportSubService;


    // 测试完成
    @ApiOperation(value = "根据用户 id 查询用户的审核任务", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pageable", value = "分页实体类",
                    required = true, dataType = "Pageable")
    })
    @RequestMapping(value = "/selectByUserId/{userId}",method = RequestMethod.POST)
    public Res<?> aopCheckLoginSelectByUserId(@RequestParam(value = "session_key")String session_key, @PathVariable("userId") Long userId,
                                              @RequestBody Pageable<Long, ReportSub> pageable){
        pageable.setEntity(userId);
        return new Res<>(StateCode.OPERATION_SUCCEED,reportSubService.selectByUserId(pageable));
    }


    // 测试完成
    @ApiOperation(value = "根据用户 id 查询用户的审核任务", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "ReportSub 的 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginSelectById(@RequestParam(value = "session_key")String session_key,@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,reportSubService.selectById(id));
    }


    // 测试完成
    @ApiOperation(value = "对举报进行审核", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "auditRes", value = "审核结果",
                    required = true, dataType = "Byte"),
            @ApiImplicitParam(name = "reportSubId", value = "ReportSub 的 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/audit/{reportSubId}/{userId}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginAudit(@RequestParam(value = "session_key")String session_key,
                                     @RequestParam("auditRes") Byte auditRes,@PathVariable("reportSubId") Long reportSubId,@PathVariable("userId") Long userId){
        return new Res<>(StateCode.OPERATION_SUCCEED,reportSubService.audit(reportSubId,userId,auditRes));
    }


}
