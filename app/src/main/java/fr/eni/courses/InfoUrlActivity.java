package fr.eni.courses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import fr.eni.courses.bo.Article;
import fr.eni.module2tp.R;

public class InfoUrlActivity extends AppCompatActivity {

    private static final String TAG = "LOGTAG";

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);

        article = getIntent().getParcelableExtra("article");
        Log.i(TAG,article.toString());
    }
}