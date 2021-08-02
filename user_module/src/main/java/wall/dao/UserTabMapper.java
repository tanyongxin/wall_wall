package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.pojo.UserTabVo;

import java.util.List;

@Repository
public interface UserTabMapper {


    int insertSelective(UserTabVo record);

    UserTabVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTabVo record);

    UserTabVo selectUserTabByOppen_id(String oppen_id);

    String selectUserNameById(Long id);

    Long selectMaxId();

    Long selectMinId();

    List<Long> selectUserIds(@Param("size") int size,@Param("id") Long id);

    Long selectRandom(@Param("maxId") Long maxId,@Param("minId") Long minId);

}
