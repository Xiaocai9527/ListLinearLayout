package com.xiaocai.listLinearLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xiaocai.listLinearLayout.entity.TestData;
import com.xiaocai.listLinearLayout.listlinear.LinearLayoutAdapter;
import com.xiaocai.listLinearLayout.listlinear.ListLinearLayout;
import com.xiaocai.listLinearLayout.loadingview.LoadingDialog;
import com.xiaocai.listLinearLayout.util.AppUtils;
import com.xiaocai.listLinearLayout.util.DimenUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String CLICK_POSITION = "click_position";
    private ListLinearLayout lineLayout;
    private MyAdapter adapter;
    private SharedPreferences mPref;
    private String dataStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPref = getSharedPreferences("data", MODE_PRIVATE);
        initView();
        initData();
    }

    private void initView()
    {
        lineLayout = (ListLinearLayout) findViewById(R.id.linear_layout);
        lineLayout.setPaddingLeft(DimenUtils.dpToPxInt(10));
        lineLayout.setPaddingRight(DimenUtils.dpToPxInt(10));
        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mPref.edit().putInt(CLICK_POSITION, -1).commit();
                final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this, "加载中...");
                loadingDialog.show();
                AppUtils.runOnUIDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        initData();
                        loadingDialog.dismiss();
                    }
                }, 1500);
            }
        });
    }

    private void initData()
    {
        if (dataStr.equals(""))
        {
            dataStr = data2;
        } else if (dataStr.equals(data2))
        {
            dataStr = data1;
        } else if (dataStr.equals(data1))
        {
            dataStr = data2;
        }
        TestData testData = new Gson().fromJson(dataStr, TestData.class);
        List<TestData.DataBean> data = testData.getData();
        adapter = new MyAdapter(R.layout.item_test_linear_layout, data);
        lineLayout.setAdapter(adapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    class MyAdapter extends LinearLayoutAdapter<TestData.DataBean>
    {

        public MyAdapter(int itemLayoutId, List<TestData.DataBean> data)
        {
            super(itemLayoutId, data);
        }

        @Override
        public View getView(final int position)
        {
            View view = View.inflate(AppUtils.getAppContext(), getmItemLayoutId(), null);
            TextView btn = (TextView) view.findViewById(R.id.text_view);
//            View view = holder.getItemView();
//            TextView btn = holder.getView(R.id.text_view);
            btn.setText(getData().get(position).getName());
            int clickPosition = mPref.getInt(CLICK_POSITION, -1);
            if (clickPosition != -1 && clickPosition == position)
            {
                view.setBackgroundColor(Color.parseColor("#8C8C8C"));
            }
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mPref.edit().putInt(CLICK_POSITION, position).commit();
                    refresh();
                    Toast.makeText(AppUtils.getAppContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }
    }

    private void refresh()
    {
        TestData testData = new Gson().fromJson(data2, TestData.class);
        List<TestData.DataBean> data = testData.getData();
        adapter.setNewData(data);
        lineLayout.setAdapter(adapter);
    }

    private String data = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String data1 = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private String data2 = "{\n" +
            "  \"Data\": [\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"依迅\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
