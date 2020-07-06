package com.olawaleotubu.biologyquiz;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    private Question mQuestion;
    AppCompatImageView questionImage;
    AppCompatTextView questionText, answerText, answerExplained;
    AppCompatButton nextButton, previousButton;
    RadioGroup radioGroup;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;

    private static final String ARG_QUEST_LIST = "questionList";

    public  static QuestionFragment newInstance(Question mQuestion){
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUEST_LIST, mQuestion);
        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(args);
        return questionFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = getArguments().getParcelable(ARG_QUEST_LIST);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionImage = view.findViewById(R.id.question_image);
        questionText = view.findViewById(R.id.question_text);
        radioGroup = view.findViewById(R.id.options_radios);
        radioButtonA = radioGroup.findViewById(R.id.radio_button_A);
        radioButtonB = radioGroup.findViewById(R.id.radio_button_B);
        radioButtonC = radioGroup.findViewById(R.id.radio_button_C);
        radioButtonD = radioGroup.findViewById(R.id.radio_button_D);
        answerExplained = view.findViewById(R.id.answer_explained_text);

        questionImage.setImageResource(Integer.parseInt(mQuestion.getQuestImg()));
        questionText.setText(mQuestion.getQuestText());
        radioButtonA.setText(mQuestion.getAnswer().getOptA());
        radioButtonB.setText(mQuestion.getAnswer().getOptB());
        radioButtonC.setText(mQuestion.getAnswer().getOptC());
        radioButtonD.setText(mQuestion.getAnswer().getOptD());
        answerExplained.setText(mQuestion.getAnswer().getAnsExpl());
        return view;
    }
}
