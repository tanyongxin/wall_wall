package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.LoveParentMapper;
import wall.dao.LoveSubMapper;
import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import wall.pojo.LoveSub;
import wall.service.BaseService;
import wall.service.LoveService;
import wall.util.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoveServiceImpl extends BaseService implements LoveService {


    @Autowired
    private LoveParentMapper loveParentMapper;

    @Autowired
    private LoveSubMapper loveSubMapper;

    @Autowired
    private IdWorker idWorker;

    @Transactional
    @Override
    public int addLove(LoveParentVo loveParentVo, List<LoveSub> loveSubList) {
        if (loveParentVo.getUserId() == null)
            throw new RuntimeException();
        loveParentVo.setId(idWorker.nextId());
        loveParentVo.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        int res = loveParentMapper.insertSelective(loveParentVo);
        if (res == 1){
            for (LoveSub loveSub : loveSubList) {
                loveSub.setId(idWorker.nextId());
                loveSub.setParentId(loveParentVo.getId());
                loveSubMapper.insertSelective(loveSub);
            }
        }
        return 1;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public Pageable<LoveParentVo> selectByUserId(Pageable<LoveParentVo> parentVoPageable) {
//        if (parentVoPageable.getEntity().getUserId() == null)
//            throw new RuntimeException();
//        parentVoPageable.setFrom((parentVoPageable.getPageNum() - 1) * parentVoPageable.getPageSize());
//        List<LoveParentVo> loveParentVos = loveParentMapper.selectByUserId(parentVoPageable);
//        parentVoPageable.setRes(loveParentVos);
//        Long lastId = 0L;
//        for (LoveParentVo loveParentVo : loveParentVos) {
//            loveParentVo.setLoveSubs(loveSubMapper.selectByParentId(loveParentVo.getId()));
////            loveParentVo.setUserName();
//            lastId = Math.max(lastId,loveParentVo.getId());
//        }
//        if (lastId != 0)
//            parentVoPageable.setLastId(lastId);
//        return parentVoPageable;
//    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<LoveParentVo> selectList(Pageable<LoveParentVo> pageable) {
        pageable.setFrom((pageable.getPageNum() - 1) * pageable.getPageSize());
        List<LoveParentVo> loveParentVos = loveParentMapper.selectByLoveParentVo(pageable);
        pageable.setRes(loveParentVos);
        Long lastId = 0L;
        for (LoveParentVo loveParentVo : loveParentVos) {
            loveParentVo.setLoveSubs(loveSubMapper.selectByParentId(loveParentVo.getId()));
            // todo：需要调用远程接口获取用户名。只有实名才需要
            if (!loveParentVo.getAnonymous()){
                loveParentVo.setUserName(getUserNameByUserId(loveParentVo.getUserId()));
            }
            lastId = Math.max(lastId,loveParentVo.getId());
        }
        if (lastId != 0)
            pageable.setLastId(lastId);
        pageable.setEntity(null);
        return pageable;
    }


}
