//package marketdataservice.services;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class Publisher {
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final ChannelTopic topic;
//
//    public Publisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic){
//        this.redisTemplate = redisTemplate;
//        this.topic = topic;
//    }
//
//    public void publish(String message){
//        this.redisTemplate.convertAndSend(topic.getTopic(), message);
//    }
//}
