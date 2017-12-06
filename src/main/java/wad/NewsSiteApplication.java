
package wad;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NewsSiteApplication {
    
    public static void main(String[] args) throws Exception{
        SpringApplication.run(NewsSiteApplication.class, args);
    }
    
    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }
    
}
