package wall.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class HttpClientUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>  T httpClientUtils(String conn_url,Class<T> clas) {
        Object o = null;
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
            HttpRequestBase httpRequestBase = null;
            httpRequestBase = new HttpGet(conn_url);
//            else if (requestType != null && requestType.equals("POST")) {
//                httpRequestBase = new HttpPost(conn_url);
//                HttpPost httpPost = (HttpPost) httpRequestBase;
//                List<BasicNameValuePair> list = new ArrayList<>();
//                httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//                if (parameterValue != null && parameterName != null) {
//                    list.add(new BasicNameValuePair(parameterName, mapper.writeValueAsString(parameterValue)));
//                }
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
//                httpPost.setEntity(entity);
//            }
            CloseableHttpResponse response = httpClient.execute(httpRequestBase);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String string = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("返回内容为  -----》" + string);
                if (string != null && !"".equals(string)) {
                    JSONObject jsonObject = JSONUtil.parseObj(string);
                    if (jsonObject.getObj("errmsg") != null){
                        return null;
                    }
                    o = mapper.readValue(string, clas);
                    return (T) o;
                }
            }
            response.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T) o;
    }
}
