package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.UserShareMapper;
import wall.entity.Pageable;
import wall.pojo.UserShare;
import wall.service.UserShareService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserShareServiceImpl implements UserShareService {

    @Autowired
    private UserShareMapper userShareMapper;

    @Autowired
    private IdWorker idWorker;

    @Transactional
    @Override
    public int insertSelective(UserShare record) {
        if (record.getPage() == null || record.getRequestUrl() == null || record.getUserId() == null)
            return 0;
        record.setId(idWorker.nextId())
                .setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return userShareMapper.insertSelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Pageable<Long, UserShare> selectByUserId(Pageable<Long, UserShare> pageable) {
        pageable.setFrom((pageable.getPageNum() - 1) * pageable.getPageSize());
        List<UserShare> userShares = userShareMapper.selectByUserId(pageable);
        pageable.setEntity(null);
        pageable.setRes(userShares);
        return pageable;
    }

    @Transactional(readOnly = true)
    @Override
    public UserShare selectByPrimaryKey(Long id) {
        return userShareMapper.selectByPrimaryKey(id);
    }


    @Transactional
    @Override
    public int updateCodeUrlById(String codeUrl, Long id) {
        return userShareMapper.updateCodeUrlById(codeUrl,id);
    }
}
