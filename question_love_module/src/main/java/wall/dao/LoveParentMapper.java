package wall.dao;

import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.LoveParentVo;

import java.util.List;

@Repository
public interface LoveParentMapper {

    int insertSelective(LoveParentVo record);

    LoveParentVo selectByPrimaryKey(Long id);

    // 根据用户 id 查询
    List<LoveParentVo> selectByUserId(Pageable<LoveParentVo> parentVoPageable);

    // 根据 LoveParentVo 对象中封装的条件进行查询
    List<LoveParentVo> selectByLoveParentVo(Pageable<LoveParentVo> parentVoPageable);

    int updateByPrimaryKeySelective(LoveParentVo record);

}
