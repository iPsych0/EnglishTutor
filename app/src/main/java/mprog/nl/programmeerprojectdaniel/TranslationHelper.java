package mprog.nl.programmeerprojectdaniel;

public class TranslationHelper {

    private String translation;
    private String wordType;

    public TranslationHelper(){

    }

    public TranslationHelper(String translation, String wordType) {
        this.translation = translation;
        this.wordType = wordType;
    }

    public String get_translation() { return translation; }

    public void set_translation(String translation){ this.translation = translation; }

    public String get_wordType() {
        return wordType;
    }

    public void set_wordType(String wordType) {
        this.wordType = wordType;
    }
}
