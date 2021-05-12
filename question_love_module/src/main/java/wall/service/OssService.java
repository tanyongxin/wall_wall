package wall.service;

import wall.pojo.oss.OssCallbackResult;
import wall.pojo.oss.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

// OSS业务接口
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy(byte type);

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
