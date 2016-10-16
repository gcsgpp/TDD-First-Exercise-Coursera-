import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {
	private List<String> listWords = new ArrayList<String>(0);

	public boolean startWithNumber(String originalWord) {
		Pattern p = Pattern.compile("^\\d");
		Matcher m = p.matcher(originalWord);
		return m.find();
	}

	public boolean hasSpecialCharacters(String originalWord) {
		Pattern p = Pattern.compile("\\W");
		Matcher m = p.matcher(originalWord);
		return m.find();
	}
	
	public List<String> converterCamelCase(String original){
		if(checkInconsistency(original))
			return listWords;
		String[] letters = original.split("");
		for(int i = 0; i < letters.length; i++){
			String nextLetter = new String("");
			
			if(i != (letters.length - 1))
				nextLetter = letters[i + 1];
			
			separateWords(letters[i], nextLetter);
		}
		return listWords;
	}
	
	private void separateWords(String letter, String nextLetter){
		if(letterIsLower(letter) && (letterIsUpper(nextLetter) || letterIsNumber(nextLetter))){
			addLastWord(letter.toLowerCase());
			addNewWord("");
		}else if((letterIsUpper(letter) && letterIsUpper(nextLetter)) || 
				 (letterIsNumber(letter) && letterIsNumber(nextLetter))){
			addLastWord(letter);
		}else if(letterIsUpper(letter) && letterIsLower(nextLetter)){
			addNewWord(letter.toLowerCase());
		}else
			addLastWord(letter);
	}

	private boolean letterIsLower(String letter) {
		return letter.matches("[a-z]");
	}

	private void addNewWord(String word) {
		if(listWords.size() > 0 && listWords.get(listWords.size() - 1).equals(""))
			addLastWord(word);
		else
			setWord(word);
	}
	
	private void setWord(String word){
		listWords.add(word);
	}

	private boolean letterIsUpper(String letter) {
		return letter.matches("[A-Z]");
	}
	
	private boolean letterIsNumber(String letter){
		return letter.matches("[0-9]");
	}
	
	private boolean checkInconsistency(String wholeWord){
		if(startWithNumber(wholeWord) || hasSpecialCharacters(wholeWord))
			return true;
		else
			return false;
	}

	private void addLastWord(String word) {
		int sizeListWords = listWords.size();
		if(sizeListWords > 0){
			String lastWord = listWords.get(sizeListWords - 1);
			lastWord += word;
			listWords.set(sizeListWords - 1, lastWord);
		}else
			setWord(word);
	}

}
