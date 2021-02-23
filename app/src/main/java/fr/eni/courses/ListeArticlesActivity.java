package fr.eni.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.eni.courses.adapter.ListeArticlesAdapter;
import fr.eni.courses.bo.Article;
import fr.eni.courses.bo.Configuration;
import fr.eni.courses.dao.ArticleDAO;
import fr.eni.module2tp.R;

public class ListeArticlesActivity extends AppCompatActivity {

    public static final String TAG = "MYTAG";

    private List<Article> listeArticles = null;
    private RecyclerView rvListe = null;


    private View.OnClickListener elementClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            int position = ((Integer)view.getTag()).intValue();

            Intent intent = new Intent(ListeArticlesActivity.this, DetailArticleActivity.class);
            intent.putExtra("article", listeArticles.get(position));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_articles);

        Configuration.getInstance().load(this);

//        initDatabaseArticles();

        rvListe = findViewById(R.id.rv_liste_articles);

        Toolbar toolbar = findViewById(R.id.tb_liste_articles);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"ListeArticleActivity.onResume");
        Log.v(TAG,"tri par prix : "+Configuration.getInstance().isTri_par_prix());
        listeArticles = new ArticleDAO(this).get(Configuration.getInstance().isTri_par_prix());
        for(Article a : listeArticles) {
            Log.v(TAG,a.getId() + " " + a.getPrix());
        }
        rvListe.setAdapter(new ListeArticlesAdapter(listeArticles, elementClickListener));
    }


    // EVENTS

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste_articles, menu);
        return true;
    }

    public void onCLickAjouter(MenuItem item) {
        Intent intent = new Intent(this, SaisieArticleActivity.class);
        startActivity(intent);
    }

    public void onClickConfiguration(MenuItem item)
    {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }


    // INIT

    private void initDatabaseArticles()
    {
        listeArticles = new ArrayList<Article>();
        Log.i(TAG, "creation liste articles");
        for(int i=0; i<5; i++) {
            listeArticles.add(new Article(
                    "Pain au chocolat "+i,
                    "Une viennoiserie au beurre et au chocolat",
                    "https://tfkcc.com/wp-content/uploads/2019/06/Pain-au-Chocolat-M-02.jpg",
                    getRandomNumberInRange(0,500)/100.0, getRandomNumberInRange(0,1000)/100.0, true));

            listeArticles.add(new Article(
                    "Tarte aux pommes "+i,
                    "Une tarte à pâte feuilletée, aux pommes et à la compote de pomme",
                    "https://www.auxdelicesdupalais.net/wp-content/uploads/2019/03/DSC01764.jpg",
                    getRandomNumberInRange(0,500)/100.0, getRandomNumberInRange(0,1000)/100.0, false));

            listeArticles.add(new Article(
                    "Charlotte aux fraises "+i,
                    "Gâteau de boudoirs garni aux fraises et à la chantilly",
                    "https://www.johaprato.com/files/torta_charlotte_foto.jpg",
                    getRandomNumberInRange(0,500)/100.0, getRandomNumberInRange(0,1000)/100.0, false));
        }
        ArticleDAO dao = new ArticleDAO(this);
        Log.i(TAG, "insertion liste articles");
        for(Article article : listeArticles) {
            dao.insert(article);
        }
        Log.i(TAG, "insertion liste articles terminee");
    }

    private static int getRandomNumberInRange(int min, int max)
    {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}