package marketdataservice.config;

import lombok.extern.slf4j.Slf4j;
import marketdataservice.dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RedisConfig {
    @Value("167.99.202.174")
    private String host;

    @Value("exchange1channel")
    private  String exchange1channel;

    @Value("exchange2channel")
    private String exchange2channel;


    @Bean
    public RedisTemplate<String, OrderDto> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, OrderDto> template = new RedisTemplate<>();
//        template.setDefaultSerializer(new StringRedisSerializer());
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<OrderDto>(OrderDto.class));
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    public RestTemplate restTemplates(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(6379);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    @Primary
    public ChannelTopic exchange1ChannelTopic() {
        return ChannelTopic.of(exchange1channel);
    }

    @Bean
    public ChannelTopic exchange2ChannelTopic() {
        return ChannelTopic.of(exchange2channel);
    }
}


