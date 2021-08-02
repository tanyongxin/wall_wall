package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.ReportVo;
import wall.service.ReportService;
import wall.util.Res;
import wall.util.StateCode;


@RequestMapping("/report")
@RestController
public class ReportController {


    @Autowired
    private ReportService reportService;



    // 测试完成
    @ApiOperation(value = "添加举报接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "reportVo", value = "举报实体类",
                    required = true, dataType = "ReportVo")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res<?> aopCheckLoginAddReport(@RequestParam(value = "session_key")String session_key, @RequestBody ReportVo reportVo){
        reportService.insertSelective(reportVo);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


    // 测试完成
//    @ApiOperation(value = "修改举报接口", httpMethod = "POST", response = ResponseEntity.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
//                    required = true, dataType = "string"),
//            @ApiImplicitParam(name = "reportVo", value = "举报实体类",
//                    required = true, dataType = "ReportVo")
//    })
//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    public Res<?> aopCheckLoginUpdate(@RequestParam(value = "session_key")String session_key, @RequestBody ReportVo reportVo){
//        reportService.updateByPrimaryKeySelective(reportVo);
//        return new Res<>(StateCode.OPERATION_SUCCEED);
//    }


    // 测试完成
    @ApiOperation(value = "根据 id 查询举报详情接口", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "举报 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginSelectById(@RequestParam(value = "session_key")String session_key,@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,reportService.selectByPrimaryKey(id));
    }


    // 测试完成
    @ApiOperation(value = "根据用户 id 查询举报接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pageable", value = "分页实体类",
                    required = true, dataType = "Pageable")
    })
    @RequestMapping(value = "/selectByUserId/{userId}",method = RequestMethod.POST)
    public Res<?> aopCheckLoginSelectByUserId(@RequestParam(value = "session_key")String session_key,@PathVariable("userId") Long userId,@RequestBody Pageable<Long,ReportVo> pageable){
        pageable.setEntity(userId);
        return new Res<>(StateCode.OPERATION_SUCCEED,reportService.selectReportVosByUserId(pageable));
    }

}
