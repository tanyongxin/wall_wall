package wall.service;

import wall.pojo.UserTabVo;

import java.util.List;

public interface UserTabService {


    UserTabVo smallProgramLogin(String code, String wxName,String avatars);

    int updateUserTab(UserTabVo userTabVo);


    UserTabVo selectUserById(Long id);

    String selectUserNameById(Long id);

    List<Long> selectUserIds(int size);
}
