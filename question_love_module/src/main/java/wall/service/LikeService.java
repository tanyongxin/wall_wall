package wall.service;

import wall.entity.Pageable;
import wall.pojo.LikeVo;

import java.util.List;

public interface LikeService {

    int insertSelective(LikeVo record,Long userId);

    int cancelLike(Long id,Long userId);

    int like(Long id,Long userId);

    LikeVo selectIsLikeByUerIdAndTypeAndTargetIId(Long userId, Byte type, Long targetId);

    Pageable<Long,LikeVo> selectLikeVosByUserId(Pageable<Long,LikeVo> pageable);

    /**
     * 统计 （type，targetId） 所对应的帖子的点赞数
     * @param type
     * @param targetId
     * @return
     */
    Integer selectLikeCountByTypeAndTargetIId(Byte type, Long targetId);
}
