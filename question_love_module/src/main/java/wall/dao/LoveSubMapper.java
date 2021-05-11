package wall.dao;


import org.springframework.stereotype.Repository;
import wall.pojo.LoveSub;

import java.util.List;

@Repository
public interface LoveSubMapper {

    int insertSelective(LoveSub record);

    LoveSub selectByPrimaryKey(Long id);

    List<LoveSub> selectByParentId(Long parentId);
}
