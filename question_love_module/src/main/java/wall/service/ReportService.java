package wall.service;

import wall.entity.Pageable;
import wall.pojo.ReportVo;

import java.util.List;

public interface ReportService {

    int insertSelective(ReportVo record);

    // 测试完成
    int updateByPrimaryKeySelective(ReportVo record);

    ReportVo selectByPrimaryKey(Long id);

    // 根据用户 id 查询该用户提交过的举报
    Pageable<Long, ReportVo> selectReportVosByUserId(Pageable<Long,ReportVo> pageable);

    // 测试完成
    List<ReportVo> selectReportVosByRange(Long reportVoId, int size);
}
