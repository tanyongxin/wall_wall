package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.LoveParentMapper;
import wall.dao.LoveSubMapper;
import wall.entity.Pageable;
import wall.pojo.LoveParentVo;
import wall.pojo.LoveSub;
import wall.service.LoveService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoveServiceImpl implements LoveService {


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

    @Transactional(readOnly = true)
    @Override
    public Pageable<LoveParentVo> selectByUserId(Pageable<LoveParentVo> parentVoPageable) {
        if (parentVoPageable.getEntity().getUserId() == null)
            throw new RuntimeException();
        parentVoPageable.setFrom((parentVoPageable.getPageNum() - 1) * parentVoPageable.getPageSize());
        List<LoveParentVo> loveParentVos = loveParentMapper.selectByUserId(parentVoPageable);
        parentVoPageable.setRes(loveParentVos);
        for (LoveParentVo loveParentVo : loveParentVos) {
            loveParentVo.setLoveSubs(loveSubMapper.selectByParentId(loveParentVo.getId()));
            // todo：需要调用远程接口获取用户名
//            loveParentVo.setUserName();
        }
        return parentVoPageable;
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<LoveParentVo> selectList(Pageable<LoveParentVo> parentVoPageable) {
        parentVoPageable.setFrom((parentVoPageable.getPageNum() - 1) * parentVoPageable.getPageSize());
        List<LoveParentVo> loveParentVos = loveParentMapper.selectByLoveParentVo(parentVoPageable);
        parentVoPageable.setRes(loveParentVos);
        for (LoveParentVo loveParentVo : loveParentVos) {
            loveParentVo.setLoveSubs(loveSubMapper.selectByParentId(loveParentVo.getId()));
            // todo：需要调用远程接口获取用户名
//            loveParentVo.setUserName();
        }
        return parentVoPageable;
    }


}
