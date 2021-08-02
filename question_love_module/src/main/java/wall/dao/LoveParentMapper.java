package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import java.util.List;

@Repository
public interface LoveParentMapper {

    int insertSelective(LoveParentVo record);

    LoveParentVo selectByPrimaryKey(Long id);

    // 根据 LoveParentVo 对象中封装的条件进行查询
    List<LoveParentVo> selectByLoveParentVo(Pageable<LoveParentVo,LoveParentVo> parentVoPageable);

    int updateByPrimaryKeySelective(LoveParentVo record);

    int updateLikeNumberById(@Param("id") Long id,@Param("count") Integer count);

    int deleteLove(Long targetId);

    int deleteLoveByUser(@Param("userId") Long userId, @Param("id") Long id);

    List<Long> selectLovesIdByRange(@Param("loveParentId") long loveParentId, @Param("pageSize") int pageSize);
}
