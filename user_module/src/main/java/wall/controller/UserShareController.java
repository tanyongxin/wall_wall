package wall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wall.entity.Pageable;
import wall.pojo.UserShare;
import wall.service.UserShareService;
import wall.util.Res;
import wall.util.StateCode;


@RequestMapping("/share")
@RestController
public class UserShareController {


    @Autowired
    private UserShareService userShareService;



    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Res<?> aopCheckLoginAdd(@RequestParam(value = "session_key",required = true)String session_key, @RequestBody UserShare userShare){
        userShareService.insertSelective(userShare);
        return new Res<>(StateCode.OPERATION_SUCCEED);
    }


    @RequestMapping(value = "/selectByUserId/{userId}",method = RequestMethod.POST)
    public Res<?> aopCheckLoginSelectByUserId(@RequestParam(value = "session_key",required = true)String session_key,
                                              @PathVariable("userId") Long userId, @RequestBody Pageable<Long,UserShare> pageable){
        pageable.setEntity(userId);
        return new Res<>(StateCode.OPERATION_SUCCEED,userShareService.selectByUserId(pageable));
    }


    @RequestMapping(value = "/selectById/{id}",method = RequestMethod.GET)
    public Res<?> aopCheckLoginSelectById(@RequestParam(value = "session_key",required = true)String session_key,@PathVariable("id") Long id){
        return new Res<>(StateCode.OPERATION_SUCCEED,userShareService.selectByPrimaryKey(id));
    }


}
