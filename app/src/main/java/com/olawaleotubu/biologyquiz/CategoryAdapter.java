package com.olawaleotubu.biologyquiz;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;
    private Context context;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView mCardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category, parent, false);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView= cardView.findViewById(R.id.cat_image);
        imageView.setImageResource(category.getCatImgId());
        AppCompatTextView textView = cardView.findViewById(R.id.cat_title);
        textView.setText(category.getCatitle());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView view) {
            super(view);
            cardView = view;
        }
    }
}
