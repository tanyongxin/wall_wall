package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.pojo.School;
import wall.service.SchoolService;
import wall.util.Res;
import wall.util.StateCode;

@RestController
public class SchoolController {


    @Autowired
    private SchoolService schoolService;


    @ApiOperation(value = "添加学校信息接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "school", value = "学校信息",
                    required = true, dataType = "School")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res<?> ordinaryAopAddSchool(@RequestBody School school){
        schoolService.addSchool(school);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


    @ApiOperation(value = "根据学校id查询学校信息", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校id",
                    required = true, dataType = "int")
    })
    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectById(@PathVariable("id") int id){
        return new Res<>(StateCode.OPERATION_SUCCEED,schoolService.selectSchoolById(id));
    }


    @ApiOperation(value = "根据学校id修改学校信息", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "school", value = "学校信息",
                    required = true, dataType = "School")
    })
    @RequestMapping(value = "/updateSchool",method = RequestMethod.POST)
    public Res<?> ordinaryAopUpdateSchool(@RequestBody School school){
        schoolService.updateSchool(school);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }









}
