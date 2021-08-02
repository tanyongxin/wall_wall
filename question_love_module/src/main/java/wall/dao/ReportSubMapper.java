package wall.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.ReportSub;

import java.util.List;

@Repository
public interface ReportSubMapper {

    int insertSelective(ReportSub record);

    ReportSub selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportSub record);

    List<ReportSub> selectOverdueReportSub(@Param("id") Long id,@Param("currTime") String currTime,@Param("threshold") long threshold,
                                           @Param("pageSize") int pageSize);

    int invalidReportSubByParentId(Long parentId);

    List<ReportSub> selectByParentId(Long parentId);

    List<ReportSub> selectByUserId(Pageable<Long, ReportSub> pageable);

    List<ReportSub> selectAll();

    Long selectMinId();

}
