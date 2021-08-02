package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.UserShare;

import java.util.List;

@Repository
public interface UserShareMapper {

    int insertSelective(UserShare record);

    // 查询用户的分享
    List<UserShare> selectByUserId(Pageable<Long,UserShare> pageable);


    UserShare selectByPrimaryKey(Long id);

    int updateCodeUrlById(@Param("codeUrl") String codeUrl, @Param("id") Long id);
}
