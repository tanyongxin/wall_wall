package wall.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.ReportVo;

import java.util.List;

@Repository
public interface ReportMapper {

    int insertSelective(ReportVo record);

    int updateByPrimaryKeySelective(ReportVo record);

    ReportVo selectByPrimaryKey(Long id);

    // 根据用户 id 查询该用户提交过的举报
    List<ReportVo> selectReportVosByUserId(Pageable<Long,ReportVo> pageable);

    // 查找主键值大于 id 的 ReportVo 记录，取 size 条
    List<ReportVo> selectReportVosByRange(@Param("id") Long id, @Param("size") int size);

    Long selectMinId();
}
