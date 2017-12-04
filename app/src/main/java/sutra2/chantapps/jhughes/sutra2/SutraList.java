package sutra2.chantapps.jhughes.sutra2;

import java.util.ArrayList;

/**
 * Created by jhughes on 03/12/17.
 */

final class SutraList {
    protected FullscreenActivity context;
    private ArrayList<Sutra> sutras;
    private static SutraList instance = null;
    private DatabaseAccess dbAccess;
    protected SutraList(FullscreenActivity _context) {
        context = _context;
        ArrayList<Sutra> sutras = DatabaseAccess.getInstance(context).getSutras();
        // Exists only to defeat instantiation.
    }
    public static SutraList getInstance(FullscreenActivity context) {
        if(instance == null) {
           instance = new SutraList(context);
        }
        return instance;
    }


    public ArrayList<Sutra> all() {
        return sutras;
    }

    public Sutra getSutra(int id) {
        return sutras.get(id);
    }


}
