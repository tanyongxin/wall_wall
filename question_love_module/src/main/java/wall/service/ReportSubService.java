package wall.service;

import wall.entity.Pageable;
import wall.pojo.ReportSub;
import java.util.List;


public interface ReportSubService {

    // 测试完成
    int insertSelective(ReportSub record);

    // 测试完成
    int updateByPrimaryKeySelective(ReportSub record);

    // 查询过期的审核。测试完成
    List<ReportSub> selectOverdueReportSub(Long id,String currTime,long threshold,int pageSize);

    // 让 parentId 相同的审核任务失效掉。测试完成
    int invalidReportSubByParentId(Long parentId);

    // 根据 parentId 查询审核任务。测试完成
    List<ReportSub> selectByParentId(Long parentId);

    // 下面的两个方法由 controller 层调用。测试完成
    Pageable<Long, ReportSub> selectByUserId(Pageable<Long, ReportSub> pageable);

    // 测试完成
    String audit(Long reportSubId, Long userId,Byte auditRes);

    // 测试完成
    ReportSub selectById(Long id);

    // 下面的接口仅供测试
    List<ReportSub> selectAll();

    Long selectMinId();
}
