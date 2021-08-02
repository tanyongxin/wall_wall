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
import wall.pojo.LoveParentVo;
import wall.pojo.Report;
import wall.pojo.ReportSub;
import wall.pojo.ReportVo;
import wall.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "wall.service")
@EnableTransactionManagement
@MapperScan(basePackages = {"wall.dao"})
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
@EnableCircuitBreaker
public class QuestionLoveModuleApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(QuestionLoveModuleApp.class);
//        LoveService service = context.getBean(LoveService.class);
//        LoveParentVo loveParentVo = service.selectLoveParentVoById(147548942144507904L, null);
//        long currTime = System.currentTimeMillis();
//        System.out.println(loveParentVo.getTime());
//        System.out.println(currTime / 1000);
//        System.out.println( ((currTime / 1000) - loveParentVo.getTime()) >= 259200 );

//        String currTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        ReportSubService reportService = context.getBean(ReportSubService.class);
//        ReportSub reportSub = new ReportSub();
//        reportSub.setUserId(1L);
//        reportSub.setParentId(149749791058956288L);
//        reportService.insertSelective(reportSub);
//        reportService.invalidReportSubByParentId(149749791058956288L);
//        System.out.println(reportService.selectOverdueReportSub(0L, currTime,
//                259200, 10));

//        ReportService reportService = context.getBean(ReportService.class);
//        ReportVo report = new ReportVo();
//        report.setDescribe("测试 1");
//        report.setImage("测试 2");
//        report.setType((byte)0);
//        report.setTargetId(147571795334139904L);
//        report.setUserId(1L);
//        reportService.insertSelective(report);
//
//        ReportSubService subService = context.getBean(ReportSubService.class);
//        for (int i = 0; i < 3; i++) {
//            ReportSub reportSub = new ReportSub();
//            reportSub.setParentId(152104050836512768L);
//            reportSub.setUserId((long)(i + 1));
//            reportSub.setVo_time("2021-05-22 09:45:21");
//            subService.insertSelective(reportSub);
//        }

//        LoveService loveService = context.getBean(LoveService.class);
//        loveService.deleteLove(147548942144507904L);
//
//        QuestionService questionService = context.getBean(QuestionService.class);
//        questionService.deleteQuestion(147567340874240000L);
//
//        ReplyService replyService = context.getBean(ReplyService.class);
//        replyService.deleteReply(147854989216321536L);

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

        // 就是对生成的api文档的一个描述
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("表白提问模块接口")
                    .description("文档描述")
                    .termsOfServiceUrl("相关url")
                    .version("版本：1.0")
                    .build();
        }
    }
}
