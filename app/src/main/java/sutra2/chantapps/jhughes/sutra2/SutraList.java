package sutra2.chantapps.jhughes.sutra2;

import java.util.ArrayList;

/**
 * Created by jhughes on 03/12/17.
 */

final class SutraList {
    private FullscreenActivity context;
    private ArrayList<Sutra> sutras;
    private static SutraList instance = null;
    private DatabaseAccess dbAccess;
    private SutraList(FullscreenActivity _context) {
        context = _context;
        sutras = DatabaseAccess.getInstance(context).getSutras();
    }

    protected static SutraList getInstance(FullscreenActivity context) {
        if(instance == null) {
           instance = new SutraList(context);
        }
        return instance;
    }

    public ArrayList<Sutra> all() {
      return sutras;
    }

    protected Sutra getSutra(int id) {
      return sutras.get(id);
    }

    protected int chapterStartForChapterNumber(FullscreenActivity context, int padaNum) {
        return DatabaseAccess.getInstance(context).getChapterStartId(padaNum);
    }

}
