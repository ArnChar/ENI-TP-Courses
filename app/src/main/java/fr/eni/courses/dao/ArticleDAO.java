package fr.eni.courses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.eni.courses.bo.Article;
import fr.eni.courses.dao.contracts.ArticleContract;

public class ArticleDAO
{
    private SQLiteDatabase db;

    public ArticleDAO(Context context)
    {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * INSERT
     * @param article à insérer
     * @return ID de l'article inséré
     */

    public long insert(Article article)
    {
        return db.insert(ArticleContract.TABLE_NAME, null, createCVFromArticle(article));
    }

    private ContentValues createCVFromArticle(Article article)
    {
        ContentValues cv = new ContentValues();
        cv.put(ArticleContract.COL_NOM, article.getNom());
        cv.put(ArticleContract.COL_DESCRIPTION, article.getDescription());
        cv.put(ArticleContract.COL_URL, article.getUrl());
        cv.put(ArticleContract.COL_NOTE, article.getNote());
        cv.put(ArticleContract.COL_PRIX, article.getPrix());
        cv.put(ArticleContract.COL_ACHETE, article.isAchete());
        return cv;
    }


    /**
     * GET unitaire
     * @param id de l'article à rechercher
     * @return Article trouvé
     */
    public Article get(long id)
    {
        Article resultat = null;
        Cursor cur = db.query(
                ArticleContract.TABLE_NAME,
                ArticleContract.ALL_COLUMNS,
                ArticleContract.COL_ID+"=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if( cur.moveToNext() ) {
            resultat = createArticleFromCursor(cur);
        }
        return resultat;
    }

    private Article createArticleFromCursor(Cursor cursor)
    {
        Article resultat = new Article();
        resultat.setId(cursor.getLong(cursor.getColumnIndex(ArticleContract.COL_ID)));
        resultat.setNom(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_NOM)));
        resultat.setDescription(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_DESCRIPTION)));
        resultat.setUrl(cursor.getString(cursor.getColumnIndex(ArticleContract.COL_URL)));
        resultat.setNote(cursor.getDouble(cursor.getColumnIndex(ArticleContract.COL_NOTE)));
        resultat.setPrix(cursor.getDouble(cursor.getColumnIndex(ArticleContract.COL_PRIX)));
        resultat.setAchete(cursor.getInt(cursor.getColumnIndex(ArticleContract.COL_ACHETE))==1);
        return resultat;
    }

    /**
     * GET liste de tous les articles
     * @return Liste des Article
     */
    public List<Article> get(boolean triParPrix)
    {
        Cursor cur = db.query(
                ArticleContract.TABLE_NAME,
                ArticleContract.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                triParPrix ? ArticleContract.COL_PRIX+" ASC" : null,
                null
        );
        List<Article> resultat = new ArrayList<>();
        while( cur.moveToNext() ) {
            resultat.add( createArticleFromCursor(cur) );
        }
        return resultat;
    }

    /**
     * UPDATE un article
     * @param article
     * @return vrai si article modifié
     */

    public boolean update(Article article)
    {
        return db.update(
                ArticleContract.TABLE_NAME,
                createCVFromArticle(article),
                ArticleContract.COL_ID+"=?",
                new String[] {String.valueOf(article.getId())}
        ) > 0;
    }


}
