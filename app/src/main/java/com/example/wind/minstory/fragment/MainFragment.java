package com.example.wind.minstory.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.wind.minstory.ContentActivity;
import com.example.wind.minstory.R;
import com.example.wind.minstory.adapter.MainAdapter;
import com.example.wind.minstory.base.LoadMoreListView;
import com.example.wind.minstory.base.RefreshAndLoadMoreView;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.content.MainContentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/4/17.
 */
public class MainFragment extends Fragment{
    private RefreshAndLoadMoreView refreshAndLoadMoreView;
//    private ConvenientBanner convenientBanner;
    private LoadMoreListView loadMoreListView;
    private MainAdapter mainAdapter;
    private MainContentData content;
    private List<MainContent> mainContents;
    public static int index = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        refreshAndLoadMoreView = (RefreshAndLoadMoreView) view.findViewById(R.id.rl_fragment_main);
        loadMoreListView = (LoadMoreListView) view.findViewById(R.id.load_fragment_main);



        refreshAndLoadMoreView.setLoadMoreListView(loadMoreListView);
        loadMoreListView.setRefreshAndLoadMoreView(refreshAndLoadMoreView);


        //首页的数据
        content = new MainContentData();
        mainContents = new ArrayList<>();
        for (int i = 0; i <content.getTitles().length;i++){
            MainContent mainContent = new MainContent();
            mainContent.setTitle(content.getTitles()[i]);
            mainContent.setImg(content.getImgs()[i]);
            mainContent.setIntro(content.getIntros()[i]);
            mainContents.add(mainContent);
        }

        mainAdapter = new MainAdapter(getActivity(),mainContents, R.layout.item_main,loadMoreListView);


        loadMoreListView.setAdapter(mainAdapter);



        loadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position-1;
                startActivity(new Intent(getActivity(), ContentActivity.class));

            }
        });
        //底部加载更多
        loadMoreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                for (int i = 0; i <2;i++){
                    MainContent mainContent = new MainContent();
                    mainContent.setTitle(content.getTitles()[i]);
                    mainContent.setImg(content.getImgs()[i]);
                    mainContent.setIntro(content.getIntros()[i]);
                    mainContents.add(mainContent);
                }


                loadMoreListView.isNextData(true);
                mainAdapter.notifyDataSetChanged();

                loadMoreListView.onLoadComplete();


            }
        });
        //下拉加载事件
        refreshAndLoadMoreView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("aa",loadMoreListView.isLoading()+"");
                refreshAndLoadMoreView.setRefreshing(false);


            }
        });

        return view;
    }



    //轮播图图片数据
    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }


    }

}
