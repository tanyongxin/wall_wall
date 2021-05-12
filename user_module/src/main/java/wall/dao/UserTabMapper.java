package wall.dao;

import org.springframework.stereotype.Repository;
import wall.pojo.UserTabVo;

@Repository
public interface UserTabMapper {


    int insertSelective(UserTabVo record);

    UserTabVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTabVo record);

    UserTabVo selectUserTabByOppen_id(String oppen_id);

    String selectUserNameById(Long id);
}
