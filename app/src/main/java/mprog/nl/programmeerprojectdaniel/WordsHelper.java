package mprog.nl.programmeerprojectdaniel;

public class WordsHelper {

    private String dutchWord;
    private String englishWord;

    public WordsHelper(){

    }

    public WordsHelper(String dutchWord, String englishWord) {
        this.dutchWord = dutchWord;
        this.englishWord = englishWord;
    }

    public String get_dutchWord() { return dutchWord; }

    public void set_dutchWord(String translation){ this.dutchWord = dutchWord; }

    public String get_englishWord() {
        return englishWord;
    }

    public void set_englishWord(String wordType) {
        this.englishWord = englishWord;
    }
}
