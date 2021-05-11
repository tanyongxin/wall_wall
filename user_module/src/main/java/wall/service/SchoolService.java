package wall.service;


import org.springframework.stereotype.Service;
import wall.pojo.School;

public interface SchoolService {


    int addSchool(School school);

    School selectSchoolById(int id);

    int updateSchool(School school);
}
