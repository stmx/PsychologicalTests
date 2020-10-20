package com.stmlab.android.pstests.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stmlab.android.pstests.R;
import com.stmlab.android.pstests.models.TestModel;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

public class TestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<TestModel> mTestList = new ArrayList<>();
    ArrayList<TestModel> mSourceList = new ArrayList<>();
    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setupTestsList(ArrayList<TestModel> testList) {
        mSourceList.clear();
        mSourceList.addAll(testList);
        filterList("");
    }

    public void filterList(String query) {
        mTestList.clear();
        for (TestModel test : mSourceList) {
            if ( test.getTitle().toLowerCase().contains(query.toLowerCase()) ) {
                mTestList.add(test);
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_test, viewGroup, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if ( viewHolder instanceof TestHolder ) {
            ViewCompat.setTransitionName(viewHolder.itemView, mTestList.get(i).getDescription());
            ((TestHolder) viewHolder).bind(mTestList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mTestList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(TestModel model);
    }

    public class TestHolder extends RecyclerView.ViewHolder {
        TextView mTextViewTitleTest;
        TextView mTextViewTestSize;
        public TestHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitleTest = itemView.findViewById(R.id.textViewTitleTest);
            mTextViewTestSize = itemView.findViewById(R.id.textViewNumberQuestion);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if ( position != NO_POSITION ) {
                    mOnItemClickListener.onItemClick(mTestList.get(position));
                }
            });
        }

        public void bind(TestModel test) {
            ViewCompat.setTransitionName(mTextViewTestSize, String.valueOf(test.getTestSize()));
            mTextViewTitleTest.setText(test.getTitle());
            mTextViewTestSize.setText(String.valueOf(test.getTestSize()));
        }
    }

}
