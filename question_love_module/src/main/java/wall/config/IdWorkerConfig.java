package wall.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wall.util.IdWorker;

@Configuration
public class IdWorkerConfig {

    @Value("${workerId}")
    private long workerId;

    @Value("${datacenterId}")
    private long datacenterId;

    @Value("${sequence}")
    private long sequence;

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker(workerId,datacenterId,sequence);
    }

}
