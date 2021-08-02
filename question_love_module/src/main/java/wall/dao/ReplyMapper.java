package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.ReplyVo;

import java.util.List;

@Repository
public interface ReplyMapper {

    int insertSelective(ReplyVo record);

    ReplyVo selectByPrimaryKey(Long id);

    List<ReplyVo> selectByTypeAndTargetId(@Param("type") byte type,@Param("targetId") Long targetId);

    int updateByPrimaryKeySelective(ReplyVo record);

    List<ReplyVo> selectList(Pageable<ReplyVo,ReplyVo> pageable);

    int updateLikeNumberById(@Param("id") Long id,@Param("count")Integer count);

    int deleteReply(Long targetId);

    // 返回 targetId 下面的回复数量
    int count(Long targetId);

    int deleteReplyByUser(@Param("userId") Long userId,@Param("id") Long id);

    List<Long> selectReplysIdByRange(@Param("replyId") long replyId, @Param("pageSize") int pageSize);
}
