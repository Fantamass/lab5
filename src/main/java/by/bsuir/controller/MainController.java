package by.bsuir.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.json.*;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import by.bsuir.Processor.MainProcessor;
import by.bsuir.Processor.ArrayProcessor;;


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
    
    @PostMapping(value = "/processArray")
    public String processArray(@RequestBody String jsonString) {
        
    	JSONObject request = new JSONObject(jsonString);
    	JSONObject response = new JSONObject();
    	
    	ArrayProcessor lab1 = new ArrayProcessor(request.getJSONArray("addText"), request.getJSONArray("resText"),request.getJSONArray("startText"), request.getJSONArray("endText"));
    	
    	if(!lab1.isValid())
    		response.put("result", lab1.getError());
    	else
    		response.put("result", lab1.processArray());
    	
        return response.toString();
    }
    
    @PostMapping(value = "/processSort")
    public List<String> processSort(@RequestBody String jsonString) {
        Gson input = new Gson();
        Type listType = new TypeToken<List<String>>() {}.getType();

        List<String> list = input.fromJson(jsonString, listType);
        
        return list.stream()
        	.sorted((a, b) -> a.compareTo(b))
        	.collect(Collectors.toList());
    }
}
