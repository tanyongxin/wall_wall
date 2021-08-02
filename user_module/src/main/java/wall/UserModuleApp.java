package wall;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wall.dao.UserTabMapper;
import wall.service.UserTabService;


@EnableAspectJAutoProxy
@EnableFeignClients
@EnableTransactionManagement
@MapperScan(basePackages = {"wall.dao"})
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@EnableCircuitBreaker
public class UserModuleApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserModuleApp.class);
//        UserTabMapper mapper = context.getBean(UserTabMapper.class);
//        System.out.println(mapper.selectUserTabByOppen_id("o7IDc4v4Xsp3p4bWAa7S63TG71LI").getId());
//        UserTabService service = context.getBean(UserTabService.class);
//        System.out.println(service.smallProgramLogin("053cjYll2RB5974zv2ll2uqB6t4cjYl3", "猫巷").getId());
    }


    @EnableSwagger2
    @Configuration
    class Swagger2 {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()  //RequestHandlerSelectors.any()，所有的路劲都去扫描
                    //这个扫描包的意思一样，固定路劲就去相应的路劲扫描
                    .apis(RequestHandlerSelectors.basePackage("wall.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        //就是对生成的api文档的一个描述
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("用户模块接口")
                    .description("文档描述")
                    .termsOfServiceUrl("相关url")
                    .version("版本：1.0")
                    .build();
        }
    }

}
