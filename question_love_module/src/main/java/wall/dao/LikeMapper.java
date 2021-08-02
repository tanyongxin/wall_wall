package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.LikeVo;

import java.util.List;

@Repository
public interface LikeMapper {


    int insertSelective(LikeVo record);

    // 取消点赞
    int cancelLike(@Param("id") Long id,@Param("userId") Long userId);

    // 点赞
    int like(@Param("id") Long id,@Param("userId") Long userId);

    LikeVo selectIsLikeByUerIdAndTypeAndTargetIId(@Param("userId") Long userId,@Param("type") Byte type,@Param("targetId") Long targetId);

    // 根据用户 id 查询用户点赞过的帖子
    List<LikeVo> selectLikeVosByUserId(Pageable<Long,LikeVo> pageable);

    Integer selectLikeCountByTypeAndTargetIId(@Param("type") Byte type, @Param("targetId") Long targetId);
}
