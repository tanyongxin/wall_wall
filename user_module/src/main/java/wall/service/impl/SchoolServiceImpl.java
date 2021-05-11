package wall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.SchoolMapper;
import wall.pojo.School;
import wall.service.SchoolService;


@Service
public class SchoolServiceImpl implements SchoolService {


    @Autowired
    private SchoolMapper schoolMapper;

    @Transactional
    @Override
    public int addSchool(School school) {
        return schoolMapper.insertSelective(school);
    }

    @Transactional(readOnly = true)
    @Override
    public School selectSchoolById(int id) {
        return schoolMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateSchool(School school) {
        if (school.getId() == null)
            throw new RuntimeException();
        return schoolMapper.updateByPrimaryKeySelective(school);
    }
}
