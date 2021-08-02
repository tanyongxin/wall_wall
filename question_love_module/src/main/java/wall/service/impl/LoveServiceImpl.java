package wall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.LoveParentMapper;
import wall.dao.LoveSubMapper;
import wall.entity.Pageable;
import wall.pojo.LikeVo;
import wall.pojo.LoveParentVo;
import wall.pojo.LoveSub;
import wall.pojo.UserStub;
import wall.service.BaseService;
import wall.service.LikeService;
import wall.service.LoveService;
import wall.util.IdWorker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoveServiceImpl extends BaseService<LoveParentVo> implements LoveService {


    @Autowired
    private LoveParentMapper loveParentMapper;

    @Autowired
    private LoveSubMapper loveSubMapper;

    @Autowired
    private LikeService likeService;


    @Autowired
    private IdWorker idWorker;

    Logger logger = LoggerFactory.getLogger(LoveServiceImpl.class);

    @Transactional
    @Override
    public int addLove(LoveParentVo loveParentVo, List<LoveSub> loveSubList) {
        if (loveParentVo.getUserId() == null)
            return 0;
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
    public Pageable<LoveParentVo,LoveParentVo> selectList(Long userId,Pageable<LoveParentVo,LoveParentVo> pageable) {
        List<LoveParentVo> loveParentVos = loveParentMapper.selectByLoveParentVo(pageable);
        pageable.setRes(loveParentVos);
        for (LoveParentVo loveParentVo : loveParentVos) {
            loveParentVo.setLoveSubs(loveSubMapper.selectByParentId(loveParentVo.getId()));
            // todo：需要调用远程接口获取用户名。只有实名才需要
            if (!loveParentVo.getIsAnonymous()){
                setUserNameAndAvatars(loveParentVo);
            }
            if (userId != null){
                LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 0, loveParentVo.getId());
                loveParentVo.setLike(likeVo);
            }
        }
        pageable.setEntity(null);
        return pageable;
    }

    @Transactional(readOnly = true)
    @Override
    public LoveParentVo selectLoveParentVoById(Long id,Long userId) {
        LoveParentVo loveParentVo = loveParentMapper.selectByPrimaryKey(id);
        if (loveParentVo == null)
            return null;
        if (!loveParentVo.getIsAnonymous()){
            setUserNameAndAvatars(loveParentVo);
        }
        List<LoveSub> loveSubs = loveSubMapper.selectByParentId(id);
        loveParentVo.setLoveSubs(loveSubs);
        if (userId != null){
            LikeVo likeVo = likeService.selectIsLikeByUerIdAndTypeAndTargetIId(userId, (byte) 0, id);
            loveParentVo.setLike(likeVo);
        }
        return loveParentVo;
    }

    @Transactional
    @Override
    public int deleteLove(Long targetId) {
        logger.info("deleteLove，id is " + targetId);
        return loveParentMapper.deleteLove(targetId);
    }

    @Transactional
    @Override
    public int deleteLoveByUser(Long userId, Long id) {
        return loveParentMapper.deleteLoveByUser(userId,id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> selectLovesIdByRange(long loveParentId, int pageSize) {
        return loveParentMapper.selectLovesIdByRange(loveParentId,pageSize);
    }

    @Transactional
    @Override
    public int updateLikeNumberById(Long id, Integer count) {
        return loveParentMapper.updateLikeNumberById(id,count);
    }

}
