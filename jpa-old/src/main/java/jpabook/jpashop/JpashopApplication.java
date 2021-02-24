package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }

    // 엔티티를 노출안시키면 안써도 됨. 지연로딩 무시하게
    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
