package wall.service;

import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import wall.pojo.LoveSub;

import java.util.List;

public interface LoveService {

    int addLove(LoveParentVo loveParentVo, List<LoveSub> loveSubList);

    Pageable<LoveParentVo,LoveParentVo> selectList(Long userId,Pageable<LoveParentVo,LoveParentVo> pageable);

    LoveParentVo selectLoveParentVoById(Long id,Long userId);

    // 将对应的表白的 isValid 字段置为 false
    int deleteLove(Long targetId);

    int deleteLoveByUser(Long userId, Long id); // 用户删除自己发表过的表白

    List<Long> selectLovesIdByRange(long loveParentId, int pageSize);

    int updateLikeNumberById(Long id,Integer count);

}
