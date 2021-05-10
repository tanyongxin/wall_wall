package wall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAspectJAutoProxy
@EnableFeignClients
@EnableTransactionManagement
//@MapperScan
@SpringCloudApplication
public class UserModuleApp {

    public static void main(String[] args) {
        SpringApplication.run(UserModuleApp.class);
    }

}
