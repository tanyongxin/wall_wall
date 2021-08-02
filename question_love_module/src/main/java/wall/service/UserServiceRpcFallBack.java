package wall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wall.pojo.UserStub;
import wall.util.Res;


@Component
public class UserServiceRpcFallBack implements UserServiceRpc {


    Logger logger = LoggerFactory.getLogger(UserServiceRpcFallBack.class);


    @Override
    public Res<?> ordinaryAopSelectUserNameById(Long id) {
        logger.error("ordinaryAopSelectUserNameById " + id);
        return null;
    }

    @Override
    public Res<?> ordinaryAopSelectUserIds( int size) {
        logger.error("ordinaryAopSelectUserIds " + size);
        return null;
    }

    @Override
    public Res<UserStub> ordinaryAopSelectUserById(Long id) {
        logger.error("ordinaryAopSelectUserById " + id);
        return null;
    }


}
