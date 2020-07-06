package com.olawaleotubu.biologyquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class QuestionPagerActivity extends AppCompatActivity {
    private static final String EXTRA_QUEST_LIST = "com.olawaleotubu.biologyquiz.quest_list";

    private ViewPager mViewPager;
    private ArrayList<? extends Question> mQuestionList;

    public static Intent newIntent(Context packageContext, List questionList){
        Intent intent = new Intent(packageContext, QuestionPagerActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_QUEST_LIST, (ArrayList<? extends Parcelable>) questionList);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_pager);

        mQuestionList = getIntent().getParcelableArrayListExtra(EXTRA_QUEST_LIST);

        mViewPager = findViewById(R.id.question_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Question  question = mQuestionList.get(position);
                return QuestionFragment.newInstance(question);
            }

            @Override
            public int getCount() {
                return mQuestionList.size();
            }
        });
    }
}
