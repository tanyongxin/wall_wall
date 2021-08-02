package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.FeedbackMapper;
import wall.pojo.Feedback;
import wall.service.FeedbackService;
import wall.util.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private IdWorker idWorker;


    @Transactional
    @Override
    public int insertSelective(Feedback record) {
        if (record.getUserId() == null || record.getSuggest() == null)
            return 0;
        record.setId(idWorker.nextId());
        record.setVo_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return feedbackMapper.insertSelective(record);
    }

    @Transactional(readOnly = true)
    @Override
    public Feedback selectByPrimaryKey(Long id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }
}
