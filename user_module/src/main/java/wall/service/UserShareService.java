package wall.service;

import wall.entity.Pageable;
import wall.pojo.UserShare;

public interface UserShareService {

    int insertSelective(UserShare record);

    // 查询用户的分享
    Pageable<Long, UserShare> selectByUserId(Pageable<Long,UserShare> pageable);


    UserShare selectByPrimaryKey(Long id);

    int updateCodeUrlById(String codeUrl,Long id);

}
