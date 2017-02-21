package com.hm.camerademo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.hm.camerademo.ui.dialog.LoadingDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);
        initData();
        bindEvent();
    }

    /**
     * 绑定布局文件
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定控件事件
     */
    protected abstract void bindEvent();

    protected final void showLoading() {
        showLoading(null);
    }

    protected final void showLoading(String content) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.show(this, content);
        } else {
            if (!TextUtils.isEmpty(content)) {
                loadingDialog.setContent(content);
            }
            loadingDialog.show();
        }
    }

    protected final void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}