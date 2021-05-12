package wall.service;

import wall.pojo.UserTabVo;

public interface UserTabService {


    UserTabVo smallProgramLogin(String code, String wxName);

    int updateUserTab(UserTabVo userTabVo);


    UserTabVo selectUserById(Long id);

    String selectUserNameById(Long id);
}
