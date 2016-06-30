package com.example.wind.minstory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wind.minstory.R;
import com.example.wind.minstory.content.MainContent;

import java.util.List;

/**
 * Created by wind on 2016/5/27.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<MainContent> mDatas;
    private LayoutInflater mInflater;


    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView
    public RecyclerAdapter(Context context,List<MainContent> mDatas){

        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);


    }

    //点击事件
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemViewHolder itemViewHolder;
        FootViewHolder footViewHolder;
        if (viewType == TYPE_ITEM){
            itemViewHolder = new ItemViewHolder(mInflater.inflate(R.layout.item_content,
                    parent,false));
            return itemViewHolder;
        }else if (viewType == TYPE_FOOTER){
            footViewHolder = new FootViewHolder(mInflater.inflate(R.layout.item_more,parent,false));

            return footViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ItemViewHolder) {
            ((ItemViewHolder)holder).item_title.setText(mDatas.get(position).getTitle());
            ((ItemViewHolder)holder).item_content.setText(mDatas.get(position).getIntro());
            ((ItemViewHolder)holder).img.setImageResource(mDatas.get(position).getImg());

            holder.itemView.setTag(position);
        }else if(holder instanceof FootViewHolder){

        }


//        holder.tv.setText(mDatas.get(position));
//
//        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    //布局控制
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    //下拉添加数据
    public void addItem(List<MainContent> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mDatas);
        mDatas.removeAll(mDatas);
        mDatas.addAll(newDatas);
        notifyDataSetChanged();
    }
    //上拉添加数据
    public void loadAddItem(List<MainContent> newDatas){
        mDatas.addAll(newDatas);
        notifyDataSetChanged();

    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
     class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView item_title;
        private TextView item_content;
        private ImageView img;
        public ItemViewHolder(View view){
            super(view);
            item_title = (TextView)view.findViewById(R.id.tv_item_main_title);
            item_content = (TextView)view.findViewById(R.id.tv_item_main_intro);
            img = (ImageView)view.findViewById(R.id.img_item_main);
        }
    }

    /**
     * 底部FootView布局
     */
    class FootViewHolder extends  RecyclerView.ViewHolder{
        public FootViewHolder(View view) {
            super(view);

        }
    }



}
