package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import wall.service.LoveService;
import wall.util.Res;
import wall.util.StateCode;

@RequestMapping("/love")
@RestController
public class LoveController {


    @Autowired
    private LoveService loveService;

    // 测试成功
    @ApiOperation(value = "添加表白接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "loveParentVo", value = "表白主表实体类",
                    required = true, dataType = "LoveParentVo"),
            @ApiImplicitParam(name = "loveParentVo", value = "表白子表列表",
                    required = true, dataType = "LoveParentVo")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    // 封装集合类型的参数：
    /*
    {
	"userId":1,
	"loveSubs":[
		{
		"type":0,
		"image":"bbb"
		},
		{
		"type":1,
		"content":"bbb"
		}

	]
}
     */
    public Res<?> aopCheckLoginAddLove(@RequestParam(value = "session_key")String session_key,
                                       @RequestBody LoveParentVo loveParentVo) { // @RequestBody 注解是如何实现的
        loveService.addLove(loveParentVo, loveParentVo.getLoveSubs());
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


//    @ApiOperation(value = "根据用户 id 查询表白列表", httpMethod = "POST", response = ResponseEntity.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "parentVoPageable", value = "分页实体",
//                    required = true, dataType = "Pageable")
//    })
//    @RequestMapping(value = "/selectByUserId",method = RequestMethod.POST)
//    public Res<?> ordinaryAopSelectByUserId(@RequestBody Pageable<LoveParentVo> parentVoPageable ){
//        return new Res<>(StateCode.OPERATION_SUCCEED,loveService.selectByUserId(parentVoPageable));
//    }


    // 测试成功
    @ApiOperation(value = "根据条件查询表白列表", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentVoPageable", value = "分页实体",
                    required = true, dataType = "Pageable")
    })
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
    public Res<?> ordinaryAopSelectList(@RequestBody Pageable<LoveParentVo> pageable){
        return new Res<>(StateCode.OPERATION_SUCCEED,loveService.selectList(pageable));
    }


}
