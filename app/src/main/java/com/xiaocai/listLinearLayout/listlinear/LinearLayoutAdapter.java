package com.xiaocai.listLinearLayout.listlinear;

import android.view.View;

import com.xiaocai.listLinearLayout.util.AppUtils;

import java.util.List;

/**
 * @author xiaokun
 * @date 2017/11/13
 */

public abstract class LinearLayoutAdapter<T>
{
    /**
     * 数据源
     */
    private List<T> mDatas;
    /**
     * item的xml布局
     */
    private int mItemLayoutId;
    private View itemView;
    private ViewHolder holder;

    public LinearLayoutAdapter(int itemLayoutId)
    {
        this.mItemLayoutId = itemLayoutId;
    }

    public LinearLayoutAdapter(int itemLayoutId, List<T> data)
    {
        this.mItemLayoutId = itemLayoutId;
        this.mDatas = data;
        itemView = View.inflate(AppUtils.getAppContext(), mItemLayoutId, null);
        holder = new ViewHolder(itemView);
    }

    /**
     * 模仿listview的baseAdapter助攻的getView方法
     *
     * @param position
     * @return
     */
    public abstract View getView(int position);

    public void setNewData(List<T> data)
    {
        mDatas.clear();
        mDatas = data;
    }

    public List<T> getData()
    {
        return mDatas;
    }

    public void setData(List<T> mDatas)
    {
        this.mDatas = mDatas;
    }

    public int getmItemLayoutId()
    {
        return mItemLayoutId;
    }

    public void setmItemLayoutId(int mItemLayoutId)
    {
        this.mItemLayoutId = mItemLayoutId;
    }

    public ViewHolder getHolder()
    {
        return holder;
    }

    public void setHolder(ViewHolder holder)
    {
        this.holder = holder;
    }
}
