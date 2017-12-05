package com.xiaocai.listLinearLayout;

import android.app.Application;

import com.xiaocai.listLinearLayout.util.AppUtils;

/**
 * @author xiaokun
 * @date 2017/12/5
 */

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        AppUtils.init(this);
    }
}
