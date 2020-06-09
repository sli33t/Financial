package cn.itcast.page;


import cn.itcast.page.service.FeignLoanService;
import cn.itcast.page.service.FiegnLoan2Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = {FeignLoanService.class, FiegnLoan2Service.class})
public class PageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PageApplication.class, args);
    }
}
