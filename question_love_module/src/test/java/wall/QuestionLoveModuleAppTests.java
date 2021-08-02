package wall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import wall.pojo.JudgeVo;
import wall.pojo.ReportSub;
import wall.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionLoveModuleAppTests {



//    @Autowired
//    private ReportService reportService;
//
//    @Autowired
//    private ReportSubService reportSubService;
//
//    @Autowired
//    private JudgeService judgeService;
//
//
//    @Autowired
//    private LoveService loveService;
//
//    @Autowired
//    private QuestionService questionService;
//
//    @Autowired
//    private ReplyService replyService;
//
//    @Autowired
//    private LikeService likeService;


//    @Autowired
//    private StringRedisTemplate redisTemplate;


    @Test
    public void contextLoads() throws IOException {

//        loveService.deleteLoveByUser(154015068114259968L,154055451695259648L);
//        questionService.deleteQuestionByUser(154015068114259968L,156522465680564224L);
//        replyService.deleteReplyByUser(1L,147854989216321536L);


//        List<Long> ids = loveService.selectLovesIdByRange(154055451695259648L,10);
//        for (Long id : ids) {
//            Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte)0,id);
//            loveService.updateLikeNumberById(id,count);
//        }
//        ids = questionService.selectQuestionsIdByRange(156522465680564224L,10);
//        for (Long id : ids) {
//            Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte) 1, id);
//            questionService.updateLikeNumberById(id,count);
//        }
//
//        ids = replyService.selectReplysIdByRange(147854989216321536L,10);
//        for (Long id : ids) {
//            Integer count = likeService.selectLikeCountByTypeAndTargetIId((byte) 2, id);
//            replyService.updateLikeNumberById(id,count);
//        }


//        System.out.println(reportService.selectReportVosByRange(0L, 10));
//        System.out.println(reportSubService.selectByParentId(149749791058956288L));
//        ReportSub reportSub = new ReportSub();
//        reportSub.setUserId(1L);
//        reportSub.setParentId(149749791058956288L);
//        reportSubService.insertSelective(reportSub);
//        reportSubService.invalidReportSubByParentId(149749791058956288L);

//        System.out.println(reportSubService.selectAll());
//        System.out.println(reportSubService.selectOverdueReportSub(0L,
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 259200, 10));

//        ReportSub reportSub = new ReportSub();
//        reportSub.setId(2L);
//        reportSub.setAuditRes((byte)1);
//        reportSubService.updateByPrimaryKeySelective(reportSub);

//        System.out.println(judgeService.selectJudgeVosByRange(0L, 10));
//        JudgeVo judgeVo = new JudgeVo();
//        judgeVo.setUserId(2L);
//        judgeVo.setComplete(1);
//        judgeService.updateByPrimaryKeySelective(judgeVo);

//        System.out.println(judgeService.selectByPrimaryKey(2L, false));

    }




}
