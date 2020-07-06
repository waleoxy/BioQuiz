package com.olawaleotubu.biologyquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Question> mQuestionList;

    RecyclerView mCatRecyclerView;
    private Category category;
    private List<Category> categories;
    CategoryAdapter mCategoryAdapter;

    private FirebaseFirestore mFirestrore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new ArrayList<>();
        category = new Category(R.string.cat_ecology, R.drawable.ecology);
        categories.add(category);
        category = new Category(R.string.cat_cell, R.drawable.cell);
        categories.add(category);
        category = new Category(R.string.cat_coordition, R.drawable.coordination);
        categories.add(category);
        category = new Category(R.string.cat_genetics, R.drawable.genetics);
        categories.add(category);
        category = new Category(R.string.cat_plant_biology, R.drawable.plantbiology);
        categories.add(category);
        category = new Category(R.string.cat_system, R.drawable.bodysystem);
        categories.add(category);
        category = new Category(R.string.cat_reproduction, R.drawable.green);
        categories.add(category);
        category = new Category(R.string.cat_nutrition, R.drawable.green);
        categories.add(category);

        mCatRecyclerView = findViewById(R.id.cat_recyclerview);
        mCatRecyclerView.setHasFixedSize(true);
        mCategoryAdapter = new CategoryAdapter(this, categories);
        mCatRecyclerView.setAdapter(mCategoryAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mCatRecyclerView.setLayoutManager(layoutManager);

        mFirestrore = FirebaseFirestore.getInstance();

        mQuestionList = new ArrayList<>();

        mCatRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener
                (this, mCatRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
               category = categories.get(position);
               String cat = String.valueOf(category.getCatitle());
               CollectionReference mCollectionReference = mFirestrore.collection("QuestionDb").document(cat).collection("Question");
               mCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful()){
                           for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                               Question question = documentSnapshot.toObject(Question.class);
                               mQuestionList.add(question);
                           }
                           Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                       }
                   }
               });
               Intent intent = QuestionPagerActivity.newIntent(MainActivity.this, mQuestionList);
               startActivity(intent);

                Toast.makeText(MainActivity.this, category.getCatitle(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView,
                                         final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                  View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                  if (child != null && clickListener != null){
                      clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                  }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }
}
