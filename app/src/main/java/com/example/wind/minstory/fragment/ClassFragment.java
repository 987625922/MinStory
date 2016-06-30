package com.example.wind.minstory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.wind.minstory.AttentionActivity2;
import com.example.wind.minstory.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wind on 2016/4/17.
 */
public class ClassFragment extends Fragment {
    private GridView gridView;
    private String[] storyClass = {"全部","精选童话","安徒生童话","格林童话","希腊神话",
            "豪夫童话","法国童话","伊索寓言","世界名著","影响世界的100本书"
            ,"中国","外国"};
    private int[] photo = {R.mipmap.quanbu,R.mipmap.jinxuangushi,R.mipmap.antushengtonghua,R.mipmap.gelintonghua,
            R.mipmap.xilashenghua,R.mipmap.haofutonghua,R.mipmap.faguotonghua,
            R.mipmap.yishuo,R.mipmap.shijiemingzhu,R.mipmap.shu100,R.mipmap.chinastory,R.mipmap.waiguostory
    };
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        gridView = (GridView)view.findViewById(R.id.gridview_main);
        List<HashMap<String, Object>> gridDatas = new ArrayList<>();


        for (int i = 0 ; i < photo.length ; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("img", photo[i]);
            map.put("class", storyClass[i]);
            gridDatas.add(map);
        }
        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
                gridDatas,
                R.layout.grid_item_main,
                new String[]{"img","class"},
                new int[]{R.id.gridview_main_img,R.id.gridview_main_tv});
       gridView.setAdapter(saImageItems);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getActivity(), AttentionActivity.class));
                Intent intent = new Intent();
                intent.setClass(getActivity(), AttentionActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);


            }
        });



        return view;
    }

}
