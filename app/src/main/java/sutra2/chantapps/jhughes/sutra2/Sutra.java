package sutra2.chantapps.jhughes.sutra2;

/**
 * Created by jhughes on 03/12/17.
 */

public class Sutra {
    private String phonetic;
    private String devanagari;
    private int id;
    private int pada;

    protected Sutra(int id, String phonetic, String devanagari, int pada){
        this.id = id;
        this.phonetic = phonetic;
        this.devanagari = devanagari;
        this.pada = pada;
    }
    protected String getPhonetic(){
        return this.phonetic;
    }
}
