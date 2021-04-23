package com.ry_050.myapp.yuyuejianche.yuyuexuzhi;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.ry_050.myapp.BaseFragment;
import com.ry_050.myapp.R;

public class yuyuexuzhiFragment extends BaseFragment {
    private WebView webView;

    @Override
    protected int setlayout() {
        return R.layout.yuyuexuzhi_fragment;
    }

    @Override
    protected void initView() {
        inittoolbar("预约须知");
        webView=findViewById(R.id.wv);
        String ba="<p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">1.</span><span style=\\\\\\\"font-size:16px;\\\\\\\">至少提前两周预约</span><span style=\\\\\\\"font-size:16px;\\\\\\\">，<span style=\\\\\\\"font-size:100%;\\\\\\\">预</span>约确定以支付定金为准，定金20元(不可退)；拍照排期以收到定金顺序为准。</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\\\\\\\"></span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">2.检车余款于拍照当日结清：支付宝／微信／现金均可。</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:none;\\\\\\\"> </span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">3.如遇个人原因临时变更，请于原定拍摄时间提前<span style=\\\\\\\"font-size:100%;\\\\\\\">至少48小时</span>与我联系更改，感谢理解；预约当天无特殊理由取消，订单作废，再次预约重付20元定金。</span></p>";
        webView.loadDataWithBaseURL("http://192.168.148.15:10002/userinfo/carNotice/grt",ba,"text/html","utf-8",null);
    }



}