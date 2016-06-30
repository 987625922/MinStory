package com.example.wind.minstory.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.content.ContentData;

public class ContentFragment extends Fragment {
    private CardView cardView;
    private TextView content;
    private ImageView img;
    private TextView title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        cardView = (CardView)rootView.findViewById(R.id.card_contentfragment);
        content = (TextView)rootView.findViewById(R.id.content);
        img = (ImageView)rootView.findViewById(R.id.img_content);
        title = (TextView)rootView.findViewById(R.id.title);
        ContentData contentData = new ContentData();
        int index = MainFragment.index;
        if (index>contentData.content.size()-1){
            index = index % contentData.content.size();
            title.setText(contentData.title.get(index));
            content.setText(contentData.content.get(index));
            img.setImageResource(contentData.img.get(index));
        }else {
            title.setText(contentData.title.get(MainFragment.index));
            content.setText(contentData.content.get(MainFragment.index));
            img.setImageResource(contentData.img.get(MainFragment.index));
        }
        Animator animator = AnimatorInflater.loadAnimator(getContext(),R.anim.content_anim);
        animator.setTarget(cardView);
        animator.start();
        return rootView;
    }
}
