package wall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wall.pojo.BasePojo;
import wall.pojo.UserStub;
import wall.util.Res;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class BaseService<T extends BasePojo> {

    @Autowired
    private UserServiceRpc userServiceRpc;

    private static final LinkedHashMap<Long, UserStub> map = new LinkedHashMap<Long, UserStub>(32,0.75f,true){
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 30;
        }
    }; // 存储用户 id 和用户名的映射

    private static final ReentrantLock lock = new ReentrantLock();


    protected void setUserNameAndAvatars(T t){
        if (t.getUserId() == null || t.getUserId() <= 0)
            return;
        Long userId = t.getUserId();
        UserStub stub = map.get(userId);
        if (stub != null){
            t.setUserName(stub.getWxName());
            t.setAvatars(stub.getAvatars());
        }
        else {
            try {
                lock.lock();
                if (map.containsKey(userId)) {
                    stub = map.get(userId);
                    t.setUserName(stub.getWxName());
                    t.setAvatars(stub.getAvatars());
                    return;
                }
                Res<?> res = userServiceRpc.ordinaryAopSelectUserById(userId);
                if (res == null || res.getState().getCode() != 200 || res.getResult() == null)
                    return;
                UserStub userStub = (UserStub) res.getResult();
                map.put(userId,userStub);
                t.setUserName(userStub.getWxName());
                t.setAvatars(userStub.getAvatars());
            }catch (Throwable throwable){
                System.out.println(Thread.currentThread().getName());
                throwable.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread())
                    lock.unlock();
            }
        }
    }

    protected UserStub getUserStubByUserId(Long userId){
        if (userId == null || userId <= 0)
            return null;
        UserStub stub = map.get(userId);
        if (stub != null)
            return stub;
        try {
            lock.lock();
            if (map.containsKey(userId)) {
                stub = map.get(userId);
                return stub;
            }
            Res<?> res = userServiceRpc.ordinaryAopSelectUserById(userId);
            if (res == null || res.getState().getCode() != 200 || res.getResult() == null)
                return null;
            UserStub userStub = (UserStub) res.getResult();
            map.put(userId,userStub);
            return userStub;
        }catch (Throwable throwable){
//            System.out.println(Thread.currentThread().getName());
            throwable.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
        return null;
    }


}
