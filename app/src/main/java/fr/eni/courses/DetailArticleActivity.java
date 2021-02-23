package fr.eni.courses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

import fr.eni.courses.bo.Article;
import fr.eni.module2tp.R;

public class DetailArticleActivity extends AppCompatActivity {

    private static final String TAG = "MYTAG";

    public static final String INTENT_ARTICLE = "article";

    private ProgressBar pb_det_art_image = null;
    private ImageView iv_det_art_image = null;

    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        Toolbar toolbar = findViewById(R.id.tb_det_art);
        setSupportActionBar(toolbar);

        this.article = getIntent().getParcelableExtra(INTENT_ARTICLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadViewFromArticle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_article, menu);
        return true;
    }

    private void loadViewFromArticle()
    {
        TextView tvNom = findViewById(R.id.tv_det_art_nom);
        TextView tvDescription = findViewById(R.id.tv_det_art_description);
        TextView tvPrix = findViewById(R.id.tv_det_art_prix);
        RatingBar rbRate = findViewById(R.id.rb_det_art_note);

        tvNom.setText(this.article.getNom());
        tvDescription.setText(this.article.getDescription());
        tvPrix.setText(String.valueOf(this.article.getPrix())+" €");
        rbRate.setRating(this.article.getNote().floatValue());

        pb_det_art_image = findViewById(R.id.pb_det_art_image);
        pb_det_art_image.setVisibility(View.GONE);

        iv_det_art_image = findViewById(R.id.iv_det_art_image);

        LoadImageAsync loadImageAsync = new LoadImageAsync();
        loadImageAsync.execute("");
    }

    // EDIT ARTICLE

    public void onClickSaisieArticle(MenuItem item) {
        Intent intent = new Intent(this, SaisieArticleActivity.class);
        intent.putExtra(SaisieArticleActivity.INTENT_ARTICLE, article);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            article = data.getParcelableExtra(INTENT_ARTICLE);
            loadViewFromArticle();
        }
    }


    // OTHERS ACTIONS

    public void onClickPlanete(View view)
    {
        Log.d(TAG, "Lancement de l'activité InfoUrlActivity");
        Intent intentInfoUrl = new Intent(this, InfoUrlActivity.class);
        intentInfoUrl.putExtra("article", this.article);
        startActivity(intentInfoUrl);
    }

    public void onClickAcheter(View view)
    {
        this.article.setAchete(!this.article.isAchete());
        Log.d( TAG, "Article acheté: " + (this.article.isAchete() ? "Oui" : "Non") );
    }


    // IMAGE LOADING

    private class LoadImageAsync extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv_det_art_image.setVisibility(View.GONE);
            pb_det_art_image.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null;
            try {
                URL url = new URL(article.getUrl());
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                bmp = null;
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null) {
                iv_det_art_image.setImageBitmap(bitmap);
                pb_det_art_image.setVisibility(View.GONE);
                iv_det_art_image.setVisibility(View.VISIBLE);
            }
        }
    }
}