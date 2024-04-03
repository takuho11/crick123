package cn.topcheer.pms2;

import org.mybatis.spring.annotation.MapperScan;
import org.springblade.core.launch.BladeApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan({"cn.topcheer"})
@MapperScan({"cn.topcheer.**.mapper.**"})
@EnableTransactionManagement
@EnableConfigurationProperties
@Slf4j
public class Pms2Application  implements CommandLineRunner{

    
    public static void main(String[] args) {

//         SpringApplication.run(Pms2Application.class);
        BladeApplication.run("Pms2Application", Pms2Application.class, args);
        log.info(
            "\n\n"+
            "-------------------------------------------------------\n\n"+
            "       app  start finish!\n\n"+
            "-------------------------------------------------------"+
            "\n\n");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
    @Autowired
    private Environment env;


    	@Autowired
    private ApplicationContext appContext;


    @Override
    public void run(String... args) throws Exception {

        // System.out.println("===== Active Profiles =====");
        // for (String profile : env.getActiveProfiles()) {
        //     System.out.println(profile);
        // }

        // String[] beans = appContext.getBeanDefinitionNames();
        // Arrays.sort(beans);
        // for (String bean : beans) 
        // {
        //     System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
        // }

        // System.out.println("===== Loaded Config Files =====");
        // env.get
        // for (String propertySourceName : env.getPropertySources().toString().split(",")) {
        //     System.out.println(propertySourceName.trim());
        // }
    }

    
}
