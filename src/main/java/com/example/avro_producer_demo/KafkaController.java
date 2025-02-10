package com.example.avro_producer_demo;

import com.example.avro_producer_demo.User;
import com.example.avro_producer_demo.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String name, @RequestParam int age) {
        User user = User.newBuilder()
                .setName(name)
                .setAge(age)
                .build();

        kafkaProducerService.sendMessage("demo_avro", user.getName(), user);
        return "Message sent successfully";
    }

    @PostMapping("/sendJson")
    public String sendJsonMessage(@RequestParam String name, @RequestParam int age) {
        String jsonMessage = "{\"name\":\"" + name + "\",\"age\":" + age + "}";
        kafkaProducerService.sendJsonMessage("demo_json", name, jsonMessage);
        return "Message sent successfully";
    }
}
