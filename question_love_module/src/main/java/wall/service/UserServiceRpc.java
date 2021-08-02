package wall.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wall.pojo.UserStub;
import wall.util.Res;

@Primary
@FeignClient(value = "user-module" ,url = "localhost:8084/user",fallback = UserServiceRpcFallBack.class) // 可以直接指定 url 进行访问，不需要服务注册与发现中心
public interface UserServiceRpc {

    @RequestMapping(value = "/selectUserNameById/{id}",method = RequestMethod.GET)
    Res<?> ordinaryAopSelectUserNameById(@PathVariable("id") Long id);


    @RequestMapping(value = "/selectUserIds",method = RequestMethod.GET)
    Res<?> ordinaryAopSelectUserIds(@RequestParam("size") int size);

    // 返回值可以不和远程接口一致
    @RequestMapping(value = "/selectUserById/{id}",method = RequestMethod.GET)
    Res<UserStub> ordinaryAopSelectUserById(@PathVariable("id") Long id);

}
