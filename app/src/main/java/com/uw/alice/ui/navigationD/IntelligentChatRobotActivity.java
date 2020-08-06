package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;;

import com.uw.alice.R;
import com.uw.alice.data.model.Chat;
import com.uw.alice.data.model.SingleChat;
import com.uw.alice.databinding.ActivityIntelligentChatRobotBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class IntelligentChatRobotActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "IntelligentChatRobotAct";
    private Context mContext;
    private ActivityIntelligentChatRobotBinding mBinding;

    private List<SingleChat> mChatList = new ArrayList<>();
    private MyChatWithRobotListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intelligent_chat_robot);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_intelligent_chat_robot);
        mBinding.setClicklistener(this);
        mContext = IntelligentChatRobotActivity.this;

        //输入框监听
        mBinding.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (mBinding.etInput.getText().toString().trim().isEmpty()){
                    //Toast.makeText(mContext, "不准为空！", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.VISIBLE);
                    mBinding.btAllowSend.setVisibility(View.GONE);
                }else{
                    //Toast.makeText(mContext, "不是空", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.GONE);
                    mBinding.btAllowSend.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mBinding.etInput.getText().toString().trim().isEmpty()){
                    //Toast.makeText(mContext, "不准为空！", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.VISIBLE);
                    mBinding.btAllowSend.setVisibility(View.GONE);
                }else{
                    //Toast.makeText(mContext, "不是空", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.GONE);
                    mBinding.btAllowSend.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mBinding.etInput.getText().toString().trim().isEmpty()){
                    //Toast.makeText(mContext, "不准为空！", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.VISIBLE);
                    mBinding.btAllowSend.setVisibility(View.GONE);
                }else{
                   // Toast.makeText(mContext, "不是空", Toast.LENGTH_SHORT).show();
                    mBinding.ivLimitSend.setVisibility(View.GONE);
                    mBinding.btAllowSend.setVisibility(View.VISIBLE);
                }
            }
        });

        //初始化聊天界面
        initData();

        //初始化我的聊天列表
        mAdapter = new MyChatWithRobotListAdapter(mChatList);
        mBinding.myChatRecycleList.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.myChatRecycleList.setAdapter(mAdapter);


    }

    private void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_back:
                finish();
            break;

            case R.id.bt_allow_send:
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.btAllowSend.setEnabled(false);
                mChatList.add(new SingleChat(mBinding.etInput.getText().toString(),1));
                //开始平滑滚动到适配器位置。定位到当前最后一条数据 让消息列表时刻保持在底部
                mBinding.myChatRecycleList.smoothScrollToPosition(mAdapter.getItemCount()-1);
                //请求对话
                chat();
                break;

        }
    }

    private void chat() {

        Observer<Chat> chatObserver = new Observer<Chat>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Chat chat) {
                //成功发送消息后 清空输入框
                mBinding.etInput.setText("");
                //得到回复后  隐藏加载框 允许点击发送
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.btAllowSend.setEnabled(true);
                if (chat.getResult()==0){
                    mChatList.add(new SingleChat(chat.getContent(),2));
                    //聊天列表随着消息内容的增加，滚动到最后一条。
                    //开始平滑滚动到适配器位置。定位到当前最后一条数据 让消息列表时刻保持在底部
                    mBinding.myChatRecycleList.smoothScrollToPosition(mAdapter.getItemCount()-1);
                   // mBinding.myChatRecycleList.scrollToPosition(mChatList.size());
                }else{
                    Toast.makeText(mContext, chat.getContent(), Toast.LENGTH_SHORT).show();
                }

                //点击编辑框弹出软键盘会遮挡消息列表    以下代码作用
                //顶起消息列表底部视图 使消息列表在软键盘上方
                mBinding.myChatRecycleList.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    //参数
                    //View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom
                    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                        // bottom < oldBottom
                        if (i3 < i7){
                            mBinding.myChatRecycleList.post(new Runnable() {
                                @Override
                                public void run() {
                                   if (mAdapter.getItemCount() > 0){
                                       mBinding.myChatRecycleList.smoothScrollToPosition(mAdapter.getItemCount()-1);
                                   }
                                }
                            });
                        }
                    }
                });


            }

            @Override
            public void onError(Throwable e) {
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.btAllowSend.setEnabled(true);
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().chatWithRobot(chatObserver,"free",mBinding.etInput.getText().toString().trim());

    }


}
