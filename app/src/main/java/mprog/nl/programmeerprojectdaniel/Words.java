package mprog.nl.programmeerprojectdaniel;

import java.util.ArrayList;

/**
 * Created by Jasper school on 6-6-2016.
 */
public class Words {

    private int _id;
    private String _dutchWord;
    private String _englishWord;
    private String _listName;

    public Words(){

    }

    public Words(int _id, String listName, String dutchWord, String englishWord) {
        this._id = _id;
        this._listName = listName;
        this._dutchWord = dutchWord;
        this._englishWord = englishWord;
    }

    public String get_listName() { return _listName; }

    public void set_listName(String listName){ this._listName = listName; }


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
