package wall.dao;

import org.springframework.stereotype.Repository;
import wall.pojo.School;

@Repository
public interface SchoolMapper {


    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

}
