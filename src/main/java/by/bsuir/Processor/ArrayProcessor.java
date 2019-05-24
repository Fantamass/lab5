package by.bsuir.Processor;

import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONArray;

public class ArrayProcessor {
	private JSONArray addInts;
	private JSONArray resInts;
	private JSONArray startInts;
	private JSONArray endInts;
	private int arraysLength;
	
	private JSONArray result;
	private boolean validation;
	private String errorString;
	
	public ArrayProcessor(JSONArray addInts, JSONArray resInts, JSONArray startInts, JSONArray endInts ){
		Set<Integer> lengths = new LinkedHashSet<>();	
		lengths.add(addInts.length());
		lengths.add(resInts.length());
		lengths.add(startInts.length());
		lengths.add(endInts.length());

		if (lengths.size() == 1){
			arraysLength = addInts.length();
			validation = true;
			this.addInts = addInts;
			this.resInts = resInts;
			this.startInts = startInts;
			this.endInts = endInts;
		}
		else {
			validation = false;
			errorString = "Длины массивов не совпадают";
		}
			
	}
	
	public boolean isValid(){
		return validation;
	}
	
	
	public String getError(){
		return errorString;
	}
	
	public String process(String addText, String resTest, String startText, String endText ) {
		try {
			for(int i = Integer.parseInt(startText); i <= Integer.parseInt(endText); ++i)
				if(i + Integer.parseInt(addText) == Integer.parseInt(resTest)){
					return String.valueOf(i);
				}

	        return "Невозможно подобраь корень";
		} catch (Exception e) {
	        return "Указано не число";
		}

	}
	
	public JSONArray processArray() {
		result = new JSONArray();
		for (int i = 0; i < arraysLength; ++i){
			String root = process(addInts.getString(i), resInts.getString(i), startInts.getString(i), endInts.getString(i));
			result.put(root);
		}
		
		return result; 		
	}
}
