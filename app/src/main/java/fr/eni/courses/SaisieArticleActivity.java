package fr.eni.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import fr.eni.courses.bo.Article;
import fr.eni.courses.dao.ArticleDAO;
import fr.eni.module2tp.R;

public class SaisieArticleActivity extends AppCompatActivity {

    private static final String TAG = "MYTAG";

    public static final String INTENT_ARTICLE = "article";

    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_article);

        this.article = getIntent().getParcelableExtra(INTENT_ARTICLE);
        if( article!=null ) {
            loadViewFromArticle();
        }
    }

    public void onClickEnregistrer(View view) {
        ArticleDAO dao = new ArticleDAO(this);

        if( article==null ) {
            // Creation
            article = new Article();
            updateArticleFromView();
            dao.insert(article);
        } else {
            // Mise à jour
            updateArticleFromView();
            dao.update(article);
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra(DetailArticleActivity.INTENT_ARTICLE, article);
        setResult(RESULT_OK, returnIntent);
        finish();
    }


    private void loadViewFromArticle()
    {
        EditText etNom = findViewById(R.id.et_sais_art_nom);
        EditText etDescription = findViewById(R.id.et_sais_art_description);
        EditText etUrl = findViewById(R.id.et_sais_art_url);
        EditText etPrix = findViewById(R.id.et_sais_art_prix);
        RatingBar rbNote = findViewById(R.id.rb_sais_art_note);

        etNom.setText(this.article.getNom());
        etDescription.setText(this.article.getDescription());
        etUrl.setText(this.article.getUrl());
        etPrix.setText(String.valueOf(this.article.getPrix()));
        rbNote.setRating(this.article.getNote().floatValue());
    }

    private void updateArticleFromView()
    {
        EditText etNom = findViewById(R.id.et_sais_art_nom);
        EditText etDescription = findViewById(R.id.et_sais_art_description);
        EditText etUrl = findViewById(R.id.et_sais_art_url);
        EditText etPrix = findViewById(R.id.et_sais_art_prix);
        RatingBar rbNote = findViewById(R.id.rb_sais_art_note);

        article.setNom(etNom.getText().toString());
        article.setDescription(etDescription.getText().toString());
        article.setUrl(etUrl.getText().toString());
        article.setPrix(Double.parseDouble(etPrix.getText().toString()));
        article.setNote(Float.valueOf(rbNote.getRating()).doubleValue());
    }
}