package org.lmcgee.technicaltest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.lmcgee.technicaltest.config.PropertiesConfig;
import org.lmcgee.technicaltest.exception.JsonException;
import org.lmcgee.technicaltest.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetUsersServiceImpl implements GetUsersService {

    private PropertiesConfig propertiesConfig;
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public GetUsersServiceImpl(PropertiesConfig propertiesConfig, ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.propertiesConfig = propertiesConfig;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getUsers() {
        String url = propertiesConfig.getAllUsersEndpoint();
        Map<String, Double> boundaryMap = this.calculateLocationBoundaries();

        List<User> userList = Arrays.stream(restTemplate.getForObject(url, User[].class))
                .filter(u -> u.getLatitude() <= boundaryMap.get("maxLatitude") &&
                             u.getLatitude() >= boundaryMap.get("minLatitude") &&
                             u.getLongitude() <= boundaryMap.get("maxLongitude") &&
                             u.getLongitude() >= boundaryMap.get("minLongitude"))
                .collect(Collectors.toList());

        log.info("Hit endpoint: {} Returning user list size: {}", url, userList.size());
        return this.convertJsonMessage(userList);
    }

    @Override
    public String convertJsonMessage(List<User> userList) {
        try {
            return objectMapper.writeValueAsString(userList);
        } catch(JsonProcessingException e) {
            throw new JsonException("User list could not be converted to a string");
        }
    }

    private Map<String, Double> calculateLocationBoundaries() {
        Map<String, Double> boundaryMap = new HashMap<>();
        double londonLatitude = 51.5098;
        double londonLongitude = -0.1180;
        //1.0 degree of latitude = 69 miles
        boundaryMap.put("maxLatitude", (londonLatitude + ((1.0 / 69) * 60)));
        boundaryMap.put("minLatitude", (londonLatitude - ((1.0 / 69) * 60)));
        //1.0 degree of longitude = 54.6 miles
        boundaryMap.put("maxLongitude", (londonLongitude + ((1.0 / 54.6) * 60)));
        boundaryMap.put("minLongitude", (londonLongitude - ((1.0 / 54.6) * 60)));

        return boundaryMap;
    }
}
