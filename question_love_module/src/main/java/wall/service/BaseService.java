package wall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wall.util.Res;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class BaseService {

    @Autowired
    private UserServiceRpc userServiceRpc;

    private static final LinkedHashMap<Long,String> IDNAME = new LinkedHashMap<>(); // 存储用户 id 和用户名的映射

    private static final ReentrantLock lock = new ReentrantLock();

    // 根据用户 id 获取对应的用户名
    protected String getUserNameByUserId(Long userId) {
        if (IDNAME.containsKey(userId))
            return IDNAME.get(userId);
        try {
            lock.lock();
            if (IDNAME.containsKey(userId))
                return IDNAME.get(userId);
            Res<?> res = userServiceRpc.ordinaryAopSelectUserNameById(userId);
            if (res.getState().getCode() == 200){
                String name = (String) res.getResult();
                IDNAME.put(userId,name);
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
        return IDNAME.get(userId);
    }

}
