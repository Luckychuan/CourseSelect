package com.example.luckychuan.courseselect.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckychuan.courseselect.R;
import com.example.luckychuan.courseselect.bean.CourseInfoJson;
import com.example.luckychuan.courseselect.presenter.SelectCoursePresenter;
import com.example.luckychuan.courseselect.util.Constant;
import com.example.luckychuan.courseselect.view.SelectCourseView;

/**
 * Created by Luckychuan on 2017/11/29.
 */

public class CourseInfoFragment extends BaseFragment implements SelectCourseView {

    private View mView;
    private SelectCoursePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("course_id");
        mView = view;
        mPresenter = new SelectCoursePresenter(this);
        mPresenter.attach();
        mPresenter.requestCourseInfo(LoginActivity.getUserKey(), id);
    }


    @Override
    public void showCourseInfo(CourseInfoJson courseInfoJson) {
        CourseInfoJson.Data info = courseInfoJson.getData();
        TextView keChengBianHao = (TextView) mView.findViewById(R.id.keChengBianHao);
        TextView keChengMingCheng = (TextView) mView.findViewById(R.id.keChengMingCheng);
        TextView xueFen = (TextView) mView.findViewById(R.id.xueFen);
        TextView yunDongXiangMu = (TextView) mView.findViewById(R.id.yunDongXiangMu);
        TextView renKeJiaoShi = (TextView) mView.findViewById(R.id.renKeJiaoShi);
        TextView zhiGongHao = (TextView) mView.findViewById(R.id.zhiGongHao);
        TextView shangKeZhouCi = (TextView) mView.findViewById(R.id.shangKeZhouCi);
        TextView xingQi = (TextView) mView.findViewById(R.id.xingQi);
        TextView jieCi = (TextView) mView.findViewById(R.id.jieCi);
        TextView xiaoQu = (TextView) mView.findViewById(R.id.xiaoQu);
        TextView rongLiang = (TextView) mView.findViewById(R.id.rongLiang);
        TextView jiBie = (TextView) mView.findViewById(R.id.jiBie);
        TextView keChengLeiXing = (TextView) mView.findViewById(R.id.keChengLeiXing);
        TextView xingBie = (TextView) mView.findViewById(R.id.xingBie);
        TextView xueNian = (TextView) mView.findViewById(R.id.xueNian);
        TextView xueQi = (TextView) mView.findViewById(R.id.xueQi);

        keChengBianHao.setText(keChengBianHao.getText() + info.getId());
        keChengMingCheng.setText(keChengMingCheng.getText() + info.getName());
        xueFen.setText(xueFen.getText() + "" + info.getCredit());
        yunDongXiangMu.setText(yunDongXiangMu.getText() + info.getSportName());
        renKeJiaoShi.setText(renKeJiaoShi.getText() + info.getTeacher());
        zhiGongHao.setText(zhiGongHao.getText() + info.getZhiGongHao());
        shangKeZhouCi.setText(shangKeZhouCi.getText() + "" + info.getDuration()+"周");
        xingQi.setText(xingQi.getText() + Constant.WEEKS[info.getWeek()]);
        jieCi.setText(jieCi.getText() +"" +info.getTime() + "、" + (info.getTime()+1) + "节");
        xiaoQu.setText(xiaoQu.getText() + info.getCampus());
        rongLiang.setText(rongLiang.getText() + "" + info.getSize() + "人");
        jiBie.setText(jiBie.getText() + "" + info.getLevel());
        keChengLeiXing.setText(keChengLeiXing.getText() + info.getType());
        xingBie.setText(xingBie.getText() + info.getSex());
        xueNian.setText(xueNian.getText() + "" + info.getYear());
        xueQi.setText(xueQi.getText() + ""+info.getTerm());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
