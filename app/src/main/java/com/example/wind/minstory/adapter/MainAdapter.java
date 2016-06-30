package com.example.wind.minstory.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.wind.minstory.ContentActivity;
import com.example.wind.minstory.R;
import com.example.wind.minstory.content.MainContent;
import com.example.wind.minstory.view.IOSTaoBaoDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 2016/4/17.
 */
public class MainAdapter extends BaseAdapter implements OnItemClickListener {
    private SparseBooleanArray sp_collect = new SparseBooleanArray();
    private SparseBooleanArray sp_download = new SparseBooleanArray();
    private LayoutInflater mInflater;
    private Context mContext;
    private List<MainContent> mDatas;
    private int mLayoutId;
    private View view;
    public MainAdapter(Context context,List<MainContent> list, int layoutId,View view) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = list;
        this.mLayoutId = layoutId;
        this.view = view;
        for (int i = 0;i < list.size();i++){
            sp_collect.put(i,false);
            sp_download.put(i,false);
        }


    }

    @Override
    public int getCount() {
        return mDatas.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ConvenientbannerHolder convenientbannerHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            if (type == 0) {

                convenientbannerHolder = new ConvenientbannerHolder();
                convertView = mInflater.inflate(R.layout.convenientbanner, null);
                convenientbannerHolder.convenientBanner = (ConvenientBanner) convertView.findViewById(R.id.convenientBanner);


                convertView.setTag(convenientbannerHolder);
            } else {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_main, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img_item_main);
                holder.title = (TextView)convertView.findViewById(R.id.tv_item_main_title);
                holder.intro = (TextView)convertView.findViewById(R.id.tv_item_main_intro);
                holder.share = (ImageView)convertView.findViewById(R.id.main_share);
                holder.collect = (ImageView)convertView.findViewById(R.id.main_collect);
                holder.download = (ImageView)convertView.findViewById(R.id.main_download);
                convertView.setTag(holder);
            }
        } else {
            if (type == 0) {
                convenientbannerHolder = (ConvenientbannerHolder) convertView.getTag();
            } else {
                holder = (ViewHolder) convertView.getTag();

            }
        }

        if (type == 0) {
            convenientbannerHolder.convenientBanner.startTurning(2000);
            List<Integer> list = new ArrayList<>();
            list.add(R.mipmap.t1);
            list.add(R.mipmap.t2);
            list.add(R.mipmap.t3);

            convenientbannerHolder.convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                @Override
                public LocalImageHolderView createHolder() {
                    return new LocalImageHolderView();
                }
            }, list).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    .setOnItemClickListener(this);

        }else {
            holder.img.setImageResource(mDatas.get(position - 1).getImg());
            holder.title.setText(mDatas.get(position - 1).getTitle());
            holder.intro.setText(mDatas.get(position - 1).getIntro());
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IOSTaoBaoDialog dialog = new IOSTaoBaoDialog(mContext, (View) view.getParent());
                    dialog.show();
                }
            });
            if (sp_collect.get(position)){
                holder.collect.setImageResource(R.mipmap.star_selected);
            }else {
                holder.collect.setImageResource(R.mipmap.star);
            }
            if (sp_download.get(position)){
                holder.download.setImageResource(R.mipmap.dl_paused_i);
            }else {
                holder.download.setImageResource(R.mipmap.download_m);
            }
            final ViewHolder finalHolder = holder;
            holder.collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sp_collect.get(position)){
                        finalHolder.collect.setImageResource(R.mipmap.star);
                        sp_collect.put(position,false);
                    }else {
                        finalHolder.collect.setImageResource(R.mipmap.star_selected);
                        sp_collect.put(position,true);
                    }

                }
            });
            holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sp_download.get(position)){
                        finalHolder.download.setImageResource(R.mipmap.download_m);
                        sp_download.put(position,false);
                    }else {
                        finalHolder.download.setImageResource(R.mipmap.dl_paused_i);
                        sp_download.put(position,true);
                    }
                }
            });

        }

        return convertView;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.setClass(mContext,ContentActivity.class);
        mContext.startActivity(intent);

    }

    final class ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView intro;
        private ImageView share;
        private ImageView collect;
        private ImageView download;
    }

    final class ConvenientbannerHolder {
        private ConvenientBanner convenientBanner;
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
