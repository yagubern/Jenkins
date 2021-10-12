package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class TaskRestController {

    @GetMapping(path="/api/rate/{code}/{date}", produces = "application/json")
    public ResponseEntity<String> getMessage(@PathVariable("code") String code,
                                     @PathVariable("date") String date) {

        String url = String.format("http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s", date.replaceAll("-","/"));

        RestTemplate restTemplate = new RestTemplate();

        Valutes valutes = restTemplate.getForObject(url, Valutes.class);

        String rate = "";

        assert valutes != null;
        for(Valute valute : valutes.getValutes()) {
            if (valute.getCharCode().equals(code)) {
                rate = valute.getValue();
            }
        }

        if ( rate.isEmpty() ) {
            return ResponseEntity.badRequest().body("{\"response\":\"Bad request\"}");
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("rate", rate);
        resultMap.put("date", date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"response\":\"Internal server error\"}");
        }

        return ResponseEntity.ok(json);
    }

    @GetMapping(path="/api/rate/{code}",produces = "application/json")
    ResponseEntity<String> getMessage(@PathVariable("code") String code) {

        String url = "http://www.cbr.ru/scripts/XML_daily.asp";

        RestTemplate restTemplate = new RestTemplate();

        Valutes valutes = restTemplate.getForObject(url, Valutes.class);

        String rate = "";

        DateTimeFormatter initialFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        assert valutes != null;
        LocalDate dateTime = LocalDate.parse(valutes.getDate(), initialFormatter);

        for(Valute valute : valutes.getValutes()) {
            if (valute.getCharCode().equals(code)) {
                rate = valute.getValue();
            }
        }

        if ( rate.isEmpty() ) {
            return ResponseEntity.badRequest().body("{\"response\":\"Bad request\"}");
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("rate", rate);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateTime.format(outputFormatter);

        resultMap.put("date", date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"response\":\"Internal server error\"}");
        }

        return ResponseEntity.ok(json);
    }
}