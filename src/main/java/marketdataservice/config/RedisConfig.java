package marketdataservice.config;

import marketdataservice.dto.MarketDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.ArrayList;


@Configuration
public class RedisConfig {
    @Value("exchange1channel")
    private  String exchange1channel;

    @Value("exchange2channel")
    private String exchange2channel;


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }


    @Bean
    public RedisTemplate<String, ArrayList<MarketDto>> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, ArrayList<MarketDto>> template = new RedisTemplate<String, ArrayList<MarketDto>>();
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(ArrayList.class));
        template.setConnectionFactory(lettuceConnectionFactory());
        return template;
    }

    @Bean
//    @Primary
    public ChannelTopic exchange1ChannelTopic() {
        return ChannelTopic.of(exchange1channel);
    }

    @Bean
    public ChannelTopic exchange2ChannelTopic() {
        return ChannelTopic.of(exchange2channel);
    }


}


