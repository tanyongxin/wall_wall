package wall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wall.dao.UserTabMapper;
import wall.pojo.IdentityEnum;
import wall.pojo.UserTabVo;
import wall.service.UserTabService;
import wall.util.ConstantUtil;
import wall.util.HttpClientUtils;
import wall.util.IdWorker;
import wall.util.WXLoginUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class UserTabServiceImpl implements UserTabService {



    Logger logger = LoggerFactory.getLogger(UserTabServiceImpl.class);

    @Autowired
    private UserTabMapper userTabMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Value("${APPID}")
    private String APPID;

    @Value("${SECRET}")
    private String SECRET;

    /**
     * 小程序的登陆接口
     * @param code
     * @param wxName
     * @return
     */
    @Transactional
    @Override
    public UserTabVo smallProgramLogin(String code, String wxName,String avatars) {
        WXLoginUtil wxLoginUtil = HttpClientUtils.
                httpClientUtils("https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code", WXLoginUtil.class);
        if (wxLoginUtil == null)
            return null;
        String oppen_id = wxLoginUtil.getOpenid();
        String session_key = wxLoginUtil.getSession_key();
        UserTabVo userTab = userTabMapper.selectUserTabByOppen_id(oppen_id);
        if (userTab == null){
            userTab = new UserTabVo();
            userTab.setId(idWorker.nextId())
                    .setOppenId(oppen_id)
                    .setWxName(wxName)
                    .setIdentity(IdentityEnum.STUDENTS)
                    .setWxNum(ConstantUtil.UNKNOWN)
                    .setSex(ConstantUtil.UNKNOWN).setAvatars(avatars);
            userTab.setVo_join_time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            int res = userTabMapper.insertSelective(userTab);
            if (res != 1)
                throw new RuntimeException();
//            logger.info("xxxx");
        }
        // 如果用户的微信名改了，数据库中也要修改
        if (!userTab.getWxName().equals(wxName) || !userTab.getAvatars().equals(avatars)){
            userTab.setWxName(wxName);
            userTab.setAvatars(avatars);
            updateUserTab(userTab);
        }
        userTab.setSession_key(session_key);
        logger.info("userTab : " + userTab);
        // 设置 30 分钟的超时时间
//        redisTemplate.opsForValue().set(session_key,oppen_id,30, TimeUnit.MINUTES);
        return userTab;
    }

    @Transactional
    @Override
    public int updateUserTab(UserTabVo userTabVo) {
        if (userTabVo.getId() == null)
            throw new RuntimeException();
        return userTabMapper.updateByPrimaryKeySelective(userTabVo);
    }

    @Transactional(readOnly = true)
    @Override
    public UserTabVo selectUserById(Long id) {
        return userTabMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public String selectUserNameById(Long id) {
        return userTabMapper.selectUserNameById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> selectUserIds(int size) {
        Long maxId = userTabMapper.selectMaxId();
        Long minId = userTabMapper.selectMinId();
        Long random = userTabMapper.selectRandom(maxId, minId);
        return userTabMapper.selectUserIds(size,random);
    }

}
