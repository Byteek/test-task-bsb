package by.pohodsky.bsbtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableSwagger2
public class SampleSimpleApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SampleSimpleApplication.class, args);
    }

}
