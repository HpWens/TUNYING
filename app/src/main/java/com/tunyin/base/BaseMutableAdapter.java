package com.tunyin.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tunyin.listener.OnItemClickListener;
import com.tunyin.listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseMutableAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private List<T> dataList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private int position;
    private boolean mShowAnim = true;
    private int mLastPosition = -1;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId(viewType), parent, false);
        return getViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        this.position = position;
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
        if (getDataList().size() > 0)
//            try {
                holder.bindData(getDataList().get(position));
//            } catch (Exception e) {
//                CrashReport.postCatchedException(e);
//            }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    protected abstract int layoutId(int viewType);

    protected abstract BaseViewHolder getViewHolder(View view, int viewType);


    public void setOnItemClickListener(@NonNull OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(@NonNull List<T> dataList) {
        if (dataList == null) return;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void addDataList(@NonNull List<T> dataList) {
        if (dataList != null) {
            int origalCount = this.dataList.size();
            this.dataList.addAll(dataList);
            notifyItemRangeInserted(origalCount, dataList.size());
        } else {
            setDataList(dataList);
        }
    }

    public void addData(@NonNull T data) {
        if (data == null) return;
        this.dataList.add(data);
        notifyItemInserted(dataList.size());
    }

    public void removeData(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        //刷新下标，不然下标就重复
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener == null) return;
        mOnItemClickListener.onItemClick(view, (int) view.getTag());
    }


    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener == null) return true;
        onItemLongClickListener.onItemLongClick(v, (Integer) v.getTag());
        return true;
    }

    public int getPosition() {
        return position;
    }


//    /**
//     * 设置动画
//     *
//     * @param holder
//     */
//    protected void startAnimator(BaseViewHolder holder) {
//        if (!mShowAnim) {
//            return;
//        }
//        int adapterPosition = holder.getAdapterPosition();
//        if (adapterPosition > mLastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recyclerview_anim);
//            holder.itemView.setAnimation(animation);
//            mLastPosition = adapterPosition;
//        } else {
//            AnimHelper.clear(holder.itemView);
//        }
//    }
}
