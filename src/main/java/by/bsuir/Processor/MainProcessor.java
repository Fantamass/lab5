package by.bsuir.Processor;

public class MainProcessor {
	private int addInt;
	private int resInt;
	private int startInt;
	private int endInt;
	private boolean validation;
	
	public MainProcessor(String addText, String resTest, String startText, String endText ){
		try {
			addInt = Integer.parseInt(addText);
			resInt = Integer.parseInt(resTest);
			startInt = Integer.parseInt(startText);
			endInt = Integer.parseInt(endText);	
		}
		catch(Exception e){
			validation = false;
		}
		validation = true;
	}
	
	public boolean isValid(){
		return validation;
	}
	
	public String process() {
		for(int i = startInt; i <= endInt; ++i)
			if(i + addInt == resInt){
				return "Найденный корень: " + String.valueOf(i);
			}

        return "Невозможно подобраь корень";
	}
}
