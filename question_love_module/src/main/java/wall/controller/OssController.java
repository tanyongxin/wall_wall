package wall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wall.pojo.oss.OssCallbackResult;
import wall.pojo.oss.OssPolicyResult;
import wall.service.OssService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/aliyun/oss")
public class OssController {


    @Autowired
    private OssService ossService;

    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public OssPolicyResult policy(@RequestParam("type") byte type) {
        OssPolicyResult result = null;
        try {
            result =  ossService.policy(type);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = null;
        try {
            ossCallbackResult = ossService.callback(request);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return ossCallbackResult;
    }
}
