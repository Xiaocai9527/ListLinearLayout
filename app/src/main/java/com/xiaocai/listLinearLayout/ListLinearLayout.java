package com.xiaocai.listLinearLayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.List;

/**
 * @author xiaokun
 * @date 2017/11/13
 */

public class ListLinearLayout extends LinearLayout
{
    /**
     * 默认每行列数
     */
    private int mColumn = 4;
    /**
     * 默认适配器
     */
    private LinearLayoutAdapter mAdapter;
    /**
     * 默认方向
     */
    private int oriention = VERTICAL;
    /**
     * 默认行间距3dp
     */
    private int lineSpacing = DimenUtils.dpToPxInt(3);
    /**
     * LinearLayout的最大宽度
     */
    private int MAX_WIDTH = 0;

    private int paddingLeft = 0;
    private int paddingRight = 0;

    public ListLinearLayout(Context context)
    {
        super(context);
    }

    public ListLinearLayout(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setAdapter(LinearLayoutAdapter adapter)
    {
        this.mAdapter = adapter;
        setOrientation(oriention);
        inflateView();
    }

    /**
     * 填充view
     */
    private void inflateView()
    {
        List data = mAdapter.getData();
        if (data == null)
        {
            return;
        }
        this.removeAllViews();
        if (MAX_WIDTH == 0)
        {
            addView(new View(getContext()));
            return;
        }

        int size = data.size();
        int rowCount = size / mColumn + (size % mColumn > 0 ? 1 : 0);
        for (int i = 0; i < rowCount; i++)
        {
            LinearLayout linearLayout = new LinearLayout(getContext());
            LayoutParams params = new LayoutParams(MAX_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(params);
            if (i != 0)
            {
                linearLayout.setPadding(0, lineSpacing, 0, 0);
            }
            addView(linearLayout);
            //算出最后一行的列数
            int currentColumn = size % mColumn == 0 ? mColumn : (size % mColumn);
            if (i != rowCount - 1)
            {
                currentColumn = mColumn;
            }
            /**
             * 计算当前子view对应data中的position
             * i是当前行数 currentColumn
             */
            for (int j = 0; j < currentColumn; j++)
            {
                int itemWidth = (MAX_WIDTH - paddingLeft - paddingRight - 3 * lineSpacing) / 4;
                LayoutParams layoutParams = new LayoutParams(itemWidth, ViewGroup.LayoutParams.MATCH_PARENT);
                if (j == 0)
                {
                    layoutParams.setMargins(paddingLeft, 0, 0, 0);
                } else if (j == 3)
                {
                    layoutParams.setMargins(lineSpacing, 0, paddingRight, 0);
                } else
                {
                    layoutParams.setMargins(lineSpacing, 0, 0, 0);
                }
                int position = i * mColumn + currentColumn;

                View view = mAdapter.getView(position - 1);
                view.setLayoutParams(layoutParams);
                linearLayout.addView(view);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (MAX_WIDTH == 0)
        {
            int width = measureWidth(widthMeasureSpec);
            if (width > 0)
            {

                MAX_WIDTH = width;
                inflateView();
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 测出宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec)
    {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY)
        {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST)
        {
            result = Math.min(result, size);
        }
        return result;
    }

    public int getmColumn()
    {
        return mColumn;
    }

    public void setmColumn(int mColumn)
    {
        this.mColumn = mColumn;
    }

    public int getLineSpacing()
    {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing)
    {
        this.lineSpacing = lineSpacing;
    }

    public int getOriention()
    {
        return oriention;
    }

    public void setOriention(int oriention)
    {
        this.oriention = oriention;
    }

    @Override
    public int getPaddingLeft()
    {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft)
    {
        this.paddingLeft = paddingLeft;
    }

    @Override
    public int getPaddingRight()
    {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight)
    {
        this.paddingRight = paddingRight;
    }
}
