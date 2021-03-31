package com.uw.alice.ui.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uw.alice.R;
import com.uw.alice.databinding.FragmentMyBinding;
import com.uw.alice.ui.others.idiom.CorpusOfIdiomsActivity;
import com.uw.alice.ui.others.joke.GraphicJokesActivity;
import com.uw.alice.ui.others.model.TaoModelsActivity;
import com.uw.alice.ui.others.wallpaper.WallpaperHorizontalPageActivity;
import com.uw.alice.ui.others.wallpaper.BingWallpaperListActivity;
import com.uw.alice.ui.others.wallpaper.WallpaperVerticalPageActivity;
import com.uw.alice.ui.others.test.TestWidgetActivity;

public class MyFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MyFragment";
    private FragmentMyBinding mBinding;
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  MyFragment onCreate");
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  MyFragment onCreateView");
        //View root = inflater.inflate(R.layout.fragment_my, container, false);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        mBinding.setClickListener(this);

        mContext = getContext();
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationD));  //设置系统状态栏颜色


        return mBinding.getRoot();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.cv_queryMobilePhoneNumberHome:
                startActivity(new Intent(mContext, QueryPhoneNumberActivity.class));
                break;

            case R.id.cv_inspirationalQuotations:
                startActivity(new Intent(mContext, InspirationalQuotationsActivity.class));
                break;

            case R.id.cv_queryTaoModels:
                startActivity(new Intent(mContext, TaoModelsActivity.class));
                break;

            case R.id.cv_corpus_of_idioms:
                startActivity(new Intent(mContext, CorpusOfIdiomsActivity.class));
                break;

            case R.id.cv_dailyWallpaper:
                CharSequence[] items = {"横向滑动浏览壁纸", "垂直滑动浏览壁纸", "列表滑动浏览壁纸"};
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
                builder.setTitle("请选择浏览方式");
                builder.setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            startActivity(new Intent(mContext, WallpaperHorizontalPageActivity.class));
                            break;

                        case 1:
                            startActivity(new Intent(mContext, WallpaperVerticalPageActivity.class));
                            break;

                        case 2:
                            startActivity(new Intent(mContext, BingWallpaperListActivity.class));
                            break;
                    }
                });
                builder.create().show();
                break;

            case R.id.cv_intelligent_chat_robot:
                startActivity(new Intent(mContext, IntelligentChatRobotActivity.class));
                break;

            case R.id.card_joke:
                startActivity(new Intent(mContext, GraphicJokesActivity.class));
                break;

            case R.id.card_view_testWidget:
                startActivity(new Intent(mContext, TestWidgetActivity.class));
                break;

            case R.id.card_exit:
                /**
                 * 当您的活动完成并应该结束时调用此函数。ActivityResult将通过onActivityResult（）传播回启动您的人。
                 * finish(DONT_FINISH_TASK_WITH_ACTIVITY);
                 *
                 * 活动完成时任务未完成
                 * DONT_FINISH_TASK_WITH_ACTIVITY
                 *
                 * 完成当前活动并指定是否删除与此活动关联的任务。
                 * finish(int finishTask)
                 */
                //requireActivity().finish();
                /**
                 * 当您的活动完成并且应该关闭，并且作为完成任务的根活动的一部分，应该完全删除任务时，调用此函数。
                 * finish(FINISH_TASK_WITH_ROOT_ACTIVITY);
                 *
                 * 如果完成活动是任务的根，则任务完成。为了保留过去的行为，任务也会从最近的事件中删除。
                 * FINISH_TASK_WITH_ROOT_ACTIVITY
                 *
                 */
                requireActivity().finishAndRemoveTask();
                break;

        }
    }



}
