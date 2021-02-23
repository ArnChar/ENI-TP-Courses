package fr.eni.courses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.module2tp.R;
import fr.eni.courses.bo.Article;

public class ListeArticlesAdapter extends RecyclerView.Adapter<ListeArticlesAdapter.ViewHolder>
{
    private List<Article> dataset = null;
    private View.OnClickListener articleClickListener = null;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvNom;
        private final TextView tvPrix;
        private final RatingBar rbNote;

        public ViewHolder(@NonNull View itemView, View.OnClickListener articleClickListener)
        {
            super(itemView);
            itemView.setOnClickListener(articleClickListener);
            this.tvNom = itemView.findViewById(R.id.tv_article_nom);
            this.tvPrix = itemView.findViewById(R.id.tv_article_prix);
            this.rbNote = itemView.findViewById(R.id.rb_article_note);
        }
    }

    public ListeArticlesAdapter(List<Article> dataset, View.OnClickListener articleClickListener)
    {
        this.dataset = dataset;
        this.articleClickListener = articleClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listeArticlesElement = inflater.inflate(R.layout.liste_articles_element, parent, false);
        return new ViewHolder(listeArticlesElement, this.articleClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.itemView.setTag(Integer.valueOf(position));

        holder.tvNom.setText(dataset.get(position).getNom());
        holder.tvPrix.setText(String.format("%2.2f",dataset.get(position).getPrix())+" €");
        holder.rbNote.setRating(dataset.get(position).getNote().floatValue());
    }

    @Override
    public int getItemCount()
    {
        return dataset.size();
    }

}
