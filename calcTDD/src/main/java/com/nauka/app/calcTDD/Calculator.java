package com.nauka.app.calcTDD;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;

public class Calculator {
	


	public double calc(String input) throws NegativesNotAllowedException{

		double result = 0;

		if (input.equals("")) {
			return result;
		} else if (input.contains("//")) {
			
			//where exemplary input is "//[;]\n1;2"
			char individualSeparator = input.charAt(3);
			input = input.substring(6);
			
			String inputParameters[] = replaceSeparator(input, individualSeparator).split(",");
			result = add(inputParameters);
			
			return result;
			
		} else if (!input.contains(",") && !input.contains("\n")) {
			
			result = Double.valueOf(input);
			if(result < 0){
				throw new NegativesNotAllowedException("Nie wolno stosować liczby ujemnej, podałeś: " + result);
			}
			return result;
		} else if(input.contains(",") || input.contains("\n")){ 
			
			String inputParameters[] = replaceSeparator(input, '\n').split(",");
			result = add(inputParameters);
		}
		return result;
	}


	private static double add(String[] inputParameters) throws NegativesNotAllowedException {
		
		double result = 0;
			
			for (String param : inputParameters) {
				
				if(Double.valueOf(param) < 0){
					throw new NegativesNotAllowedException("Nie wolno stosować liczby ujemnej, podałeś: " + Double.valueOf(param));
				}
				result = result + Double.valueOf(param);
			}
		return result;
	}
	
	private static String replaceSeparator(String input, char separator) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(input);
		
		for (int i = 0; i < stringBuilder.length(); i++) {
			
			if(stringBuilder.charAt(i) == separator){
				stringBuilder.replace(i, i+1, ",");		
			}
		}
		return stringBuilder.toString();
	}

}
