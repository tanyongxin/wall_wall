package wall.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.QuestionsVo;
import wall.service.QuestionService;
import wall.util.Res;
import wall.util.StateCode;

@RequestMapping("/question")
@RestController
public class QuestionsController {


    @Autowired
    private QuestionService questionService;


    // 测试成功
    @ApiOperation(value = "添加提问接口", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "string"),
            @ApiImplicitParam(name = "questionsVo", value = "提问实体类",
                    required = true, dataType = "QuestionsVo")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res<?> aopCheckLoginAddQuestion(@RequestParam(value = "session_key")String session_key, @RequestBody QuestionsVo questionsVo){
        questionService.addQuestion(questionsVo);
        return new Res<>(StateCode.OPERATION_SUCCEED,questionsVo.getVo_id());
    }


    // 测试成功
    @ApiOperation(value = "根据 id 查询提问详情", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提问实体类",required = true,dataType = "long"),
            @ApiImplicitParam(name = "userId", value = "用户 id ，可以不传，用户登陆后可以传入",required = false,dataType = "long")
    })
    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> ordinaryAopSelectById(@PathVariable("id") Long id,@RequestParam(value = "userId",required = false) Long userId){
        return new Res<>(StateCode.OPERATION_SUCCEED,questionService.selectByPrimaryKey(id,userId));
    }


    // 测试成功
    @ApiOperation(value = "根据条件查询提问", httpMethod = "POST", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 id ，可以不传，用户登陆后可以传入",required = false,dataType = "long"),
            @ApiImplicitParam(name = "pageable", value = "分页实体类",required = true,dataType = "Pageable")
    })
    @RequestMapping(value = "/selectList",method = RequestMethod.POST)
    // 封装级联属性：
    /*
    {
	"entity":{
		"userId":1
	}
     */
    public Res<?> ordinaryAopSelectList(@RequestParam(value = "userId",required = false) Long userId,@RequestBody Pageable<QuestionsVo,QuestionsVo> pageable){
        return new Res<>(StateCode.OPERATION_SUCCEED,questionService.selectQuestionsVos(pageable,userId));
    }


    @ApiOperation(value = "根据 id 和用户 id 删除提问", httpMethod = "GET", response = ResponseEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session_key", value = "用户登陆成功后的 session_key",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户 id",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "提问 id",
                    required = true, dataType = "Long")
    })
    @RequestMapping(value = "/delete/{userId}/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginDeleteQuestion(@RequestParam(value = "session_key")String session_key,@PathVariable("userId") Long userId,@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,questionService.deleteQuestionByUser(userId,id));
    }



}
