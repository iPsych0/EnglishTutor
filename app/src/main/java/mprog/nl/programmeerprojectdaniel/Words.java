package mprog.nl.programmeerprojectdaniel;

import java.util.ArrayList;

/**
 * Created by Jasper school on 6-6-2016.
 */
public class Words {

    private int _id;
    private int _listId;
    private String _dutchWord;
    private String _englishWord;
    private ArrayList<Words> wordsArrayList = new ArrayList<>();

    public Words(){

    }

    public Words(String dutchWord, String englishWord, ArrayList<Words> wordsArrayList) {
        this._dutchWord = dutchWord;
        this._englishWord = englishWord;
        this.wordsArrayList = wordsArrayList;
    }

    public ArrayList<Words> getWordsArrayList() {
        return this.wordsArrayList;
    }

    public int get_listId(){
        return _listId;
    }

    public void set_listId(int _listId){
        this._listId = _listId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_dutchWord() {
        return _dutchWord;
    }

    public void set_dutchWord(String dutchWord) {
        this._dutchWord = dutchWord;
    }

    public String get_englishWord() {
        return _englishWord;
    }

    public void set_englishWord(String englishWord) {
        this._englishWord = englishWord;
    }
}
