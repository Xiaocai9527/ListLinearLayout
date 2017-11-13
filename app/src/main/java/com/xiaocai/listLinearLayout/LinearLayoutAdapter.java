package com.xiaocai.listLinearLayout;

import android.view.View;

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


    public LinearLayoutAdapter(int itemLayoutId)
    {
        this.mItemLayoutId = itemLayoutId;
    }

    public LinearLayoutAdapter(int itemLayoutId, List<T> data)
    {
        this.mItemLayoutId = itemLayoutId;
        this.mDatas = data;
    }

    /**
     * 模仿listview的baseAdapter助攻的getView方法
     *
     * @param position
     * @return
     */
    public abstract View getView(int position);

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
}
