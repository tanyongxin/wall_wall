package wall.service;

import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import wall.pojo.LoveSub;

import java.util.List;

public interface LoveService {



    int addLove(LoveParentVo loveParentVo, List<LoveSub> loveSubList);

//    Pageable<LoveParentVo> selectByUserId(Pageable<LoveParentVo> parentVoPageable);

    Pageable<LoveParentVo> selectList(Pageable<LoveParentVo> pageable);
}
