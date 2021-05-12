package wall.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wall.util.Res;

@Primary
@FeignClient(value = "user_module",fallback = UserServiceRpcFallBack.class)
public interface UserServiceRpc {

    @RequestMapping(value = "/user/selectUserNameById/{id}",method = RequestMethod.GET)
    Res<?> ordinaryAopSelectUserNameById(@PathVariable("id") Long id);

}
