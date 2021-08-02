package wall.dao;


import org.springframework.stereotype.Repository;
import wall.entity.Pageable;
import wall.pojo.Message;
import wall.pojo.MessageVo;
import java.util.List;

@Repository
public interface MessageMapper {

    int insertSelective(MessageVo record);

    List<MessageVo> selectMessageVos(Pageable<MessageVo, MessageVo> pageable);

    int view(Long id);

    int selectCountByUserId(Long userId);
}
