package com.uw.alice.ui.navigationD.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.uw.alice.R;
import com.uw.alice.common.Constant;
import com.uw.alice.ui.navigationD.test.fragment.TestFragment1;

/**
 * 向 FragmentTransaction 添加更改的顺序无关紧要，不过：
 *
 * 每个事务都是您想要同时执行的一组更改。您可以使用 add()、remove() 和 replace() 等方法，为给定事务设置您想要执行的所有更改。
 * 然后，如要将事务应用到 Activity，您必须调用 commit()。
 * 如果您要向同一容器添加多个片段，则您添加片段的顺序将决定它们在视图层次结构中出现的顺序。
 * 如果您没有在执行删除片段的事务时调用 addToBackStack()，则事务提交时该片段会被销毁，用户将无法回退到该片段。
 * 不过，如果您在删除片段时调用 addToBackStack()，则系统会停止该片段，并随后在用户回退时将其恢复。
 *
 * 提示：对于每个片段事务，您都可通过在提交前调用 setTransition() 来应用过渡动画。
 *
 * 调用 commit() 不会立即执行事务，而是在 Activity 的界面线程（“主”线程）可执行该操作时，再安排该事务在线程上运行。
 * 不过，如有必要，您也可以从界面线程调用 executePendingTransactions()，以立即执行 commit() 提交的事务。
 * 通常不必这样做，除非其他线程中的作业依赖该事务。
 *
 * 注意：您只能在 Activity 保存其状态（当用户离开 Activity）之前使用 commit() 提交事务。
 * 如果您试图在该时间点后提交，则会引发异常。这是因为如需恢复 Activity，则提交后的状态可能会丢失。
 * 对于丢失提交无关紧要的情况，请使用 commitAllowingStateLoss()。
 *
 *
 *  注意：如果您的 Fragment 中需要 Context 对象，则可以调用 getContext()。
 *  但请注意，只有在该片段附加到 Activity 时才需调用 getContext()。
 *  如果尚未附加该片段，或者其在生命周期结束期间已分离，则 getContext() 返回 null。
 */
public class FragmentContainerActivity extends AppCompatActivity {

    private int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        if (getIntent() != null){
            tag = getIntent().getIntExtra(Constant.ARG_Flag,0);

            //返回用于与与此活动关联的片段进行交互的FragmentManager。
            getSupportFragmentManager()
                    //对与此FragmentManager关联的片段启动一系列编辑操作。
                    .beginTransaction()
                    .add(R.id.fragment_container, getFragment())  //添加一个片段，指定要添加的片段以及将其插入哪个视图
                    //将事务添加到片段事务返回栈。该返回栈由 Activity 管理，允许用户通过按返回按钮返回上一片段状态。
                    //.addToBackStack(null)
                    .commit(); //事务应用到 Activity，必须调用 commit()
        }

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, getFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

    }

    private Fragment getFragment() {
        switch (tag) {
            case 1:
                return TestFragment1.newInstance();
        }
        return TestFragment1.newInstance();
    }


}
