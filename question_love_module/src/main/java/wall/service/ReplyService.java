package wall.service;

import wall.entity.Pageable;
import wall.pojo.ReplyVo;
import java.util.List;

public interface ReplyService {

    int insertSelective(ReplyVo record,Long userId);

    ReplyVo selectByPrimaryKey(Long id,Long userId);

    List<ReplyVo> selectByTypeAndTargetIdLimit5( byte type,  Long targetId,Long userId);

    int updateByPrimaryKeySelective(ReplyVo record);

    Pageable<ReplyVo,ReplyVo> selectList(Pageable<ReplyVo,ReplyVo> pageable,Long userId);

    int deleteReply(Long targetId);

    int deleteReplyByUser(Long userId, Long id);

    List<Long> selectReplysIdByRange(long replyId, int pageSize);

    int updateLikeNumberById(Long id, Integer count);
}
