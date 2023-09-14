package VoThuanLoi2.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {
    private final RestTemplate restTemplate;

    @Value(("${apiKey}"))
    private String apiKey;
    @Value(("${modelID}"))
    private String modelID;
    @Value(("${url}"))
    private String url;

    @Autowired
    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String openAIServiceCall(String input){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        String requestBody = "{\"model\": \"" + modelID
                + "\", \"messages\": [{\"role\": \"user\", \"content\": \""
                + input + "\"}]}";
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        String response =  restTemplate.postForObject(url, request, String.class);

        return response;
    }
}
