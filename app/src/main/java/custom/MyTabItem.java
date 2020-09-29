package custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.TintTypedArray;

import com.google.android.material.tabs.TabLayout;
import com.uw.alice.R;

/**
 * TabItem是一个特殊的“视图”，它允许您为布局中的{@link TabLayout}声明选项卡项。
 * 该视图实际上并没有添加到TabLayout中，它只是一个虚拟对象，允许设置选项卡项目的文本，图标和自定义布局。
 * 有关如何使用它的更多信息，请参见TabLayout。
 *
 * @attr ref com.google.android.material.R.styleable#TabItem_android_icon
 * @attr ref com.google.android.material.R.styleable#TabItem_android_text
 * @attr ref com.google.android.material.R.styleable#TabItem_android_layout
 * @see TabLayout
 */
public class MyTabItem extends View {

    //TODO(b/76413401): make package private after the widget migration
    public final CharSequence text;
    //TODO(b/76413401): make package private after the widget migration
    public final Drawable icon;
    //TODO(b/76413401): make package private after the widget migration
    public final int customLayout;

    public MyTabItem(Context context) {
        this(context, null);
    }

    @SuppressLint("RestrictedApi")
    public MyTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        @SuppressLint("RestrictedApi") final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.TabItem);
        text = a.getText(R.styleable.TabItem_android_text);
        icon = a.getDrawable(R.styleable.TabItem_android_icon);
        customLayout = a.getResourceId(R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }


}
