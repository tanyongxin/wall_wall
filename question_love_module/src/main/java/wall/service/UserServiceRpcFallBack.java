package wall.service;

import wall.util.Res;



public class UserServiceRpcFallBack implements UserServiceRpc {
    @Override
    public Res<?> ordinaryAopSelectUserNameById(Long id) {
        return null;
    }
}
