package fr.eni.courses.dao.contracts;

public class ArticleContract {

    public static final String TABLE_NAME = "Articles";

    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_DESCRIPTION= "description";
    public static final String COL_URL= "url";
    public static final String COL_NOTE= "note";
    public static final String COL_PRIX= "prix";
    public static final String COL_ACHETE= "achete";

    public static final String[] ALL_COLUMNS = new String[] {
            ArticleContract.COL_ID,
            ArticleContract.COL_NOM,
            ArticleContract.COL_DESCRIPTION,
            ArticleContract.COL_URL,
            ArticleContract.COL_NOTE,
            ArticleContract.COL_PRIX,
            ArticleContract.COL_ACHETE
    };

    public static final String CREATE_TABLE = "CREATE TABLE "+
            TABLE_NAME + " ( "+
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NOM + " TEXT," +
            COL_DESCRIPTION + " TEXT," +
            COL_URL + " TEXT," +
            COL_NOTE + " REAL," +
            COL_PRIX + " REAL," +
            COL_ACHETE + " INTEGER" +
            " );";

    public static final String DROP_TABLE = "DROP " + TABLE_NAME;

}
