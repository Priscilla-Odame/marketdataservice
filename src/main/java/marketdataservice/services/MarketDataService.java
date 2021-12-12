package marketdataservice.services;

import lombok.extern.slf4j.Slf4j;
import marketdataservice.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class MarketDataService{

    @Autowired
    private RestTemplate restTemplate;


    public List<OrderDto> getExchange1MarketData(){
        String url = "https://exchange.matraining.com/md";
        List<OrderDto> response = restTemplate.getForObject(url, List.class);
        System.out.println(response);

        return response;
    }

    public List<OrderDto> getExchange2MarketData(){
        String url = "https://exchange2.matraining.com/md";
        List<OrderDto> response = restTemplate.getForObject(url, List.class);
        System.out.println(response);

        return response;
    }

}
