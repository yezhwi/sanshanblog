package xyz.sanshan.main.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import xyz.sanshan.main.web.config.webaseconfig.SpringSecuityFilter;
import xyz.sanshan.main.web.config.webaseconfig.WebAppInitializer;

/**
 * 在主程序直接运行需要Tomcat依赖
 *
 * 如果需要调整为传统Spring FrameWork方式工作 只需要将这里的继承类{@link SpringBootServletInitializer}  注释 将在{@link SpringSecuityFilter}与{@link WebAppInitializer}中的注释打开即可
 *
 * 需要注意的是属于Spring-Boot-Actuator的监控功能 没有在传统Spring FrameWork中配置
 *
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, MongoAutoConfiguration.class,ThymeleafAutoConfiguration.class, ElasticsearchAutoConfiguration.class, TransactionAutoConfiguration.class})
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableHystrix
public class MainBootstrap extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainBootstrap.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainBootstrap.class, args);
    }
}