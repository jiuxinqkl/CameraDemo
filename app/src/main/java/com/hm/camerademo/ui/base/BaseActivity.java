package com.hm.camerademo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.hm.camerademo.ui.dialog.LoadingDialog;

import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();
    private LoadingDialog loadingDialog;

    protected V viewBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBind= DataBindingUtil.setContentView(this,bindLayout());
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
    protected void bindEvent() {

    }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
