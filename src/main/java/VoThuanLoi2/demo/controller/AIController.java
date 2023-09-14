package VoThuanLoi2.demo.controller;

import VoThuanLoi2.demo.services.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/handel-ai")
    public String handelChat(@RequestParam("input") String input){
        return openAIService.openAIServiceCall(input);
    }
}
