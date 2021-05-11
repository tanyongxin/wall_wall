package wall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAspectJAutoProxy
@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = {"wall.dao"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class UserModuleApp {

    public static void main(String[] args) {
        SpringApplication.run(UserModuleApp.class);
    }

}
