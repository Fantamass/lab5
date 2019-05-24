package by.bsuir.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.json.*;
import org.springframework.web.bind.annotation.*;
import by.bsuir.Processor.MainProcessor;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

@RestController
public class MainController {

    @PostMapping(value = "/process")
    public String process(@RequestBody String jsonString) {
        
    	JSONObject request = new JSONObject(jsonString);
    	JSONObject response = new JSONObject();
    	
    	MainProcessor lab1 = new MainProcessor(request.getString("addText"), request.getString("resText"),request.getString("startText"), request.getString("endText"));
    	
    	if(!lab1.isValid())
    		response.put("result", "Невозможно подобраь корень");
    	else
    		response.put("result", lab1.process());
    	
        return response.toString();
    }
    
    @PostMapping(value = "/processFunc")
    public List<String> processFunc(@RequestBody String jsonString) {
        Gson input = new Gson();
        Type listType = new TypeToken<List<String>>() {}.getType();

        List<String> list = input.fromJson(jsonString, listType);
        
        return list.stream()
        	.sorted((a, b) -> a.compareTo(b))
        	.collect(Collectors.toList());
    }
}
