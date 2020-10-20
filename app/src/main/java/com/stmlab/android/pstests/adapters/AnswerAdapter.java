package com.stmlab.android.pstests.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.activities.MainActivity;
import com.stmlab.android.pstests.fragments.QuestionFragment;
import com.stmlab.android.pstests.helpers.Navigation;
import com.stmlab.android.pstests.models.AnswerModel;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AnswerModel> mAnswerList = new ArrayList<>();
    @Inject
    Navigation mNavigation;
    OnClickItemListener mOnItemClickListener;

    public interface OnClickItemListener {
        void onItemClick(AnswerModel answer);
    }
    public void setupAnswer(ArrayList<AnswerModel> answerList) {
        mAnswerList = answerList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MainActivity.getNavigationComponent().inject(this);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new AnswerHolder(inflater.inflate(R.layout.item_answer, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if ( viewHolder instanceof AnswerHolder ) {
            ((AnswerHolder) viewHolder).bind(mAnswerList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mAnswerList.size();
    }

    public void setOnItemClickListener(OnClickItemListener listener) {
        mOnItemClickListener = listener;
    }

    class AnswerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewAnswer;
        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewAnswer = itemView.findViewById(R.id.textViewQuestion);
            itemView.setOnClickListener(this);
        }

        public void bind(AnswerModel answer) {
            if ( answer.getText().equals("") ) {
                mTextViewAnswer.setText(String.valueOf(answer.getValue()));
            } else {
                mTextViewAnswer.setText(answer.getText());
            }
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if ( position != NO_POSITION ) {
                mOnItemClickListener.onItemClick(mAnswerList.get(position));
            }
//            Fragment questionFragment = new QuestionFragment();
//            mNavigation.nextQuestion(questionFragment);
        }
    }

}
