package custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.uw.alice.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_DRAGGING;
import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE;
import static androidx.viewpager.widget.ViewPager.SCROLL_STATE_SETTLING;
import static com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap;

/**
 * MyTabLayout提供了用于显示选项卡的垂直布局。
 *
 * 要显示的选项卡的填充是通过{@link MyTabLayout.Tab}实例完成的。 您可以通过{@link #newTab（）}创建标签。
 * 在这里，您可以分别通过{@link MyTabLayout.Tab＃setText（int）}和{@link MyTabLayout.Tab＃setIcon（int）}更改标签的标签或图标。
 * 要显示选项卡，您需要通过{@link #addTab（MyTabLayout.Tab）}方法之一将其添加到布局中。 例如：
 *
 * <pre>
 * TabLayout tabLayout = ...;
 * tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
 * tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
 * tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
 * </pre>
 *
 * 您应该通过{@link #addOnTabSelectedListener（TabLayout.OnTabSelectedListener）}添加侦听器
 * 更改任何选项卡的选择状态时会收到通知。
 *
 * 您也可以通过使用{@link MyTabItem}将项目添加到布局中的MyTabLayout。 用法示例如下：
 *
 *
 * 如果您将此布局与{@link androidx.viewpager.widget.ViewPager}一起使用，
 * 则可以调用{@link #setupWithViewPager（ViewPager）}将两者链接在一起。
 * 该布局将从{@link PagerAdapter}的页面标题中自动填充。
 *
 * 该视图还支持用作ViewPager装饰的一部分，并且可以像这样在布局资源文件中直接添加到ViewPager中：
 *
 *
 * @see <a href="http://www.google.com/design/spec/components/tabs.html">Tabs</a>
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabPadding
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabPaddingStart
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabPaddingTop
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabPaddingEnd
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabPaddingBottom
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabContentStart
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabBackground
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabMinWidth
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabMaxWidth
 * @attr ref com.google.android.material.R.styleable#TabLayout_tabTextAppearance
 */
public class MyTabLayout extends NestedScrollView {


    private static final int DEF_STYLE_RES = R.style.Widget_Design_TabLayout;

    @Dimension(unit = Dimension.DP)
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;

    @Dimension(unit = Dimension.DP)
    static final int DEFAULT_GAP_TEXT_ICON = 8;

    @Dimension(unit = Dimension.DP)
    private static final int DEFAULT_HEIGHT = 48;

    @Dimension(unit = Dimension.DP)
    private static final int TAB_MIN_WIDTH_MARGIN = 56;

    @Dimension(unit = Dimension.DP)
    private static final int MIN_INDICATOR_WIDTH = 24;

    @Dimension(unit = Dimension.DP)
    static final int FIXED_WRAP_GUTTER_MIN = 16;

    private static final int INVALID_WIDTH = -1;

    private static final int ANIMATION_DURATION = 300;

    private static final Pools.Pool<MyTabLayout.Tab> tabPool = new Pools.SynchronizedPool<>(16);

    private static final String LOG_TAG = "MyTabLayout";

    /**
     * 可滚动选项卡在任何给定时刻显示选项卡的子集，并且可以包含更长的选项卡标签和更多数量的选项卡。
     * 当用户不需要直接比较tab标签时，它们最适合用于在触摸界面中浏览上下文。
     *
     * @see #setTabMode(int)
     * @see #getTabMode()
     */
    public static final int MODE_SCROLLABLE = 0;

    /**
     * 固定选项卡可同时显示所有选项卡，最好与可在选项卡之间快速旋转的内容一起使用。
     * 标签页的最大数量受视图宽度的限制。
     * 固定制表符的宽度相等，基于最宽的制表符标签。
     *
     * @see #setTabMode(int)
     * @see #getTabMode()
     */
    public static final int MODE_FIXED = 1;

    /**
     * 自动调整大小的选项卡的行为类似于带有GRAVITY_CENTER的MODE_FIXED，而这些选项卡适合在Tab布局的内容宽度内。
     * 固定制表符的宽度相等，基于最宽的制表符标签。
     * 一旦选项卡超出视图的宽度，自动调整大小的选项卡就会像MODE_SCROLLABLE一样工作，从而允许动态数量的选项卡，而无需其他布局逻辑。
     *
     * @see #setTabMode(int)
     * @see #getTabMode()
     */
    public static final int MODE_AUTO = 2;


    /** @hide */
    @RestrictTo(LIBRARY_GROUP)
    @IntDef(value = {MODE_SCROLLABLE, MODE_FIXED, MODE_AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {}

    /**
     * 如果使用{@link MyTabLayout.Tab＃setText（CharSequence）}实例化标签，
     * 并且设置了此模式，将保存文本并将其用于内容描述，但不会创建可见标签。
     *
     * @see MyTabLayout.Tab#setTabLabelVisibility(int)
     */
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;

    /**
     * 默认情况下设置此模式。
     * 如果使用{@link MyTabLayout.Tab＃setText（CharSequence）}实例化选项卡，则将创建可见标签。
     *
     * @see MyTabLayout.Tab#setTabLabelVisibility(int)
     */
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;

    /** @hide */
    @IntDef(value = {TAB_LABEL_VISIBILITY_UNLABELED, TAB_LABEL_VISIBILITY_LABELED})
    public @interface LabelVisibility {}

    /**
     * Gravity用于尽可能多地填充{@link MyTabLayout}。此选项仅在宽度小于600dp的非横向屏幕上与{@link #MODE_FIXED}一起使用时才生效。
     *
     * @see #setTabGravity(int)
     * @see #getTabGravity()
     */
    public static final int GRAVITY_FILL = 0;

    /**
     * Gravity用于在{@link MyTabLayout}的中心布置选项卡。
     *
     * @see #setTabGravity(int)
     * @see #getTabGravity()
     */
    public static final int GRAVITY_CENTER = 1;

    /**
     *  Gravity用来布置与{@link MyTabLayout}的开头对齐的标签。
     *
     * @see #setTabGravity(int)
     * @see #getTabGravity()
     */
    public static final int GRAVITY_START = 1 << 1;

    /** @hide */
    @RestrictTo(LIBRARY_GROUP)
    @IntDef(
            flag = true,
            value = {GRAVITY_FILL, GRAVITY_CENTER, GRAVITY_START})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {}

    /**
     * 指示器重力，用于将标签选择指示器对准{@link MyTabLayout}的底部。
     * 仅当通过自定义指示器可绘制对象的固有高度（首选），
     * 通过{@code tabIndicatorHeight}属性（不推荐使用）
     * 或通过{@link #setSelectedTabIndicatorHeight（int）}（不推荐使用）
     * 设置指示器高度时，此设置才会生效。
     * 否则，该指示符将不会显示。
     * 这是默认值。
     *
     * @see #setSelectedTabIndicatorGravity(int)
     * @see #getTabIndicatorGravity()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorGravity
     */
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;

    /**
     * 指标重力，用于将标签选择指标与{@link MyTabLayout}的中心对齐。
     * 仅当通过自定义指示器可绘制对象的固有高度（首选），
     * 通过{@code tabIndicatorHeight}属性（不推荐使用）
     * 或通过{@link #setSelectedTabIndicatorHeight（int）}（不推荐使用）
     * 设置指示器高度时，此设置才会生效。
     * 否则，该指示符将不会显示。
     *
     * @see #setSelectedTabIndicatorGravity(int)
     * @see #getTabIndicatorGravity()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorGravity
     */
    public static final int INDICATOR_GRAVITY_CENTER = 1;

    /**
     * 指示器重力，用于将标签选择指示器对准{@link MyTabLayout}的顶部。
     * 仅当通过自定义指示器可绘制对象的固有高度（首选），
     * 通过{@code tabIndicatorHeight}属性（不推荐使用）
     * 或通过{@link #setSelectedTabIndicatorHeight（int）}（不推荐使用）
     * 设置指示器高度时，此设置才会生效。
     * 否则，该指示符将不会显示。
     *
     * @see #setSelectedTabIndicatorGravity(int)
     * @see #getTabIndicatorGravity()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorGravity
     */
    public static final int INDICATOR_GRAVITY_TOP = 2;

    /**
     * 指示器重力用于在{@link MyTabLayout}的整个高度和宽度上拉伸选项卡选择指示器。
     * 如果设置，这将忽略{@code tabIndicatorHeight}和指示器可绘制对象的固有高度。
     *
     * @see #setSelectedTabIndicatorGravity(int)
     * @see #getTabIndicatorGravity()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorGravity
     */
    public static final int INDICATOR_GRAVITY_STRETCH = 3;

    /** @hide */
    @RestrictTo(LIBRARY_GROUP)
    @IntDef(
            value = {
                    INDICATOR_GRAVITY_BOTTOM,
                    INDICATOR_GRAVITY_CENTER,
                    INDICATOR_GRAVITY_TOP,
                    INDICATOR_GRAVITY_STRETCH
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {}

    /** 选项卡的选择状态更改时调用的回调接口。 */
    public interface OnTabSelectedListener extends MyTabLayout.BaseOnTabSelectedListener<MyTabLayout.Tab> {}

    /**
     * 选项卡的选择状态更改时调用的回调接口。
     *
     * @deprecated Use {@link MyTabLayout.OnTabSelectedListener} instead.
     */
    @Deprecated
    public interface BaseOnTabSelectedListener<T extends MyTabLayout.Tab> {
        /**
         * 当选项卡进入选定状态时调用。
         *
         * @param tab 选择的标签
         */
        public void onTabSelected(T tab);

        /**
         * 当选项卡退出选定状态时调用。
         *
         * @param tab 未选择的标签
         */
        public void onTabUnselected(T tab);

        /**
         * 当用户再次选择已经选择的选项卡时调用。
         * 某些应用程序可以使用此操作返回到类别的顶级。
         *
         * @param tab 重新选择的标签。
         */
        public void onTabReselected(T tab);
    }

    private final ArrayList<MyTabLayout.Tab> tabs = new ArrayList<>();
    @Nullable
    private MyTabLayout.Tab selectedTab;

    private final RectF tabViewContentBounds = new RectF();

    @NonNull
    final MyTabLayout.SlidingTabIndicator slidingTabIndicator;

    int tabPaddingStart;
    int tabPaddingTop;
    int tabPaddingEnd;
    int tabPaddingBottom;

    int tabTextAppearance;
    ColorStateList tabTextColors;
    ColorStateList tabIconTint;
    ColorStateList tabRippleColorStateList;
    @Nullable
    Drawable tabSelectedIndicator;

    android.graphics.PorterDuff.Mode tabIconTintMode;
    float tabTextSize;
    float tabTextMultiLineSize;

    final int tabBackgroundResId;

    int tabMaxWidth = Integer.MAX_VALUE;
    private final int requestedTabMinWidth;
    private final int requestedTabMaxWidth;
    private final int scrollableTabMinWidth;

    private int contentInsetStart;

    @MyTabLayout.TabGravity
    int tabGravity;
    int tabIndicatorAnimationDuration;
    @MyTabLayout.TabIndicatorGravity
    int tabIndicatorGravity;
    @MyTabLayout.Mode
    int mode;
    boolean inlineLabel;
    boolean tabIndicatorFullWidth;
    boolean unboundedRipple;

    @Nullable private MyTabLayout.BaseOnTabSelectedListener selectedListener;

    private final ArrayList<MyTabLayout.BaseOnTabSelectedListener> selectedListeners = new ArrayList<>();
    @Nullable private MyTabLayout.BaseOnTabSelectedListener currentVpSelectedListener;

    private ValueAnimator scrollAnimator;

    @Nullable ViewPager viewPager;
    @Nullable private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private MyTabLayout.TabLayoutOnPageChangeListener pageChangeListener;
    private MyTabLayout.AdapterChangeListener adapterChangeListener;
    private boolean setupViewPagerImplicitly;

    // 我们用作简单的RecyclerBin的游泳池
    private final Pools.Pool<MyTabLayout.TabView> tabViewPool = new Pools.SimplePool<>(12);

    public MyTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.tabStyle);
    }

    @SuppressLint("RestrictedApi")
    public MyTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        // 确保我们使用正确主题的上下文，而不是传入的上下文。
        context = getContext();

        // 禁用滚动条
        //setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        // 添加TabStrip
        slidingTabIndicator = new MyTabLayout.SlidingTabIndicator(context);
        super.addView(
                slidingTabIndicator,
                0,
                new /*Horizontal*/NestedScrollView.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        TypedArray a =
                ThemeEnforcement.obtainStyledAttributes(
                        context,
                        attrs,
                        R.styleable.TabLayout,
                        defStyleAttr,
                        DEF_STYLE_RES,
                        R.styleable.TabLayout_tabTextAppearance);

        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable background = (ColorDrawable) getBackground();
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(background.getColor()));
            materialShapeDrawable.initializeElevationOverlay(context);
            materialShapeDrawable.setElevation(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, materialShapeDrawable);
        }

        slidingTabIndicator.setSelectedIndicatorHeight(
                a.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
        slidingTabIndicator.setSelectedIndicatorColor(
                a.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
        setSelectedTabIndicator(
                MaterialResources.getDrawable(context, a, R.styleable.TabLayout_tabIndicator));
        setSelectedTabIndicatorGravity(
                a.getInt(R.styleable.TabLayout_tabIndicatorGravity, INDICATOR_GRAVITY_BOTTOM));
        setTabIndicatorFullWidth(a.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));

        tabPaddingStart =
                tabPaddingTop =
                        tabPaddingEnd =
                                tabPaddingBottom = a.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        tabPaddingStart =
                a.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, tabPaddingStart);
        tabPaddingTop = a.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, tabPaddingTop);
        tabPaddingEnd = a.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, tabPaddingEnd);
        tabPaddingBottom =
                a.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, tabPaddingBottom);

        tabTextAppearance =
                a.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);

        // Text colors/sizes come from the text appearance first
        final TypedArray ta =
                context.obtainStyledAttributes(
                        tabTextAppearance, androidx.appcompat.R.styleable.TextAppearance);
        try {
            tabTextSize =
                    ta.getDimensionPixelSize(
                            androidx.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            tabTextColors =
                    MaterialResources.getColorStateList(
                            context,
                            ta,
                            androidx.appcompat.R.styleable.TextAppearance_android_textColor);
        } finally {
            ta.recycle();
        }

        if (a.hasValue(R.styleable.TabLayout_tabTextColor)) {
            // If we have an explicit text color set, use it instead
            // 如果我们有明确的文字颜色设置，请改用它
            tabTextColors = MaterialResources.getColorStateList(context, a, R.styleable.TabLayout_tabTextColor);
        }

        if (a.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
            // We have an explicit selected text color set, so we need to make merge it with the
            // current colors. This is exposed so that developers can use theme attributes to set
            // this (theme attrs in ColorStateLists are Lollipop+)
            //我们有一个明确的选定文本颜色集，因此我们需要将其与当前颜色合并。
            // 这是公开的，以便开发人员可以使用主题属性进行设置（ColorStateLists中的主题属性为Lollipop +）
            final int selected = a.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0);
            tabTextColors = createColorStateList(tabTextColors.getDefaultColor(), selected);
        }

        tabIconTint =
                MaterialResources.getColorStateList(context, a, R.styleable.TabLayout_tabIconTint);
        tabIconTintMode =
                ViewUtils.parseTintMode(a.getInt(R.styleable.TabLayout_tabIconTintMode, -1), null);

        tabRippleColorStateList =
                MaterialResources.getColorStateList(context, a, R.styleable.TabLayout_tabRippleColor);

        tabIndicatorAnimationDuration =
                a.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, ANIMATION_DURATION);

        requestedTabMinWidth =
                a.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, INVALID_WIDTH);
        requestedTabMaxWidth =
                a.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, INVALID_WIDTH);
        tabBackgroundResId = a.getResourceId(R.styleable.TabLayout_tabBackground, 0);
        contentInsetStart = a.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
        // noinspection WrongConstant
        // 无检查WrongConstant
        mode = a.getInt(R.styleable.TabLayout_tabMode, MODE_FIXED);
        tabGravity = a.getInt(R.styleable.TabLayout_tabGravity, GRAVITY_FILL);
        inlineLabel = a.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
        unboundedRipple = a.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
        a.recycle();

        // TODO add attr for these
        final Resources res = getResources();
        tabTextMultiLineSize = res.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
        scrollableTabMinWidth = res.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);

        // Now apply the tab mode and gravity
        // 现在应用tab mode模式和gravity
        applyModeAndGravity();
    }

    /**
     * 为当前选定的标签设置标签指示符的颜色。
     *
     * @param color Indicator使用的颜色
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorColor
     */
    public void setSelectedTabIndicatorColor(@ColorInt int color) {
        slidingTabIndicator.setSelectedIndicatorColor(color);
    }

    /**
     * Sets the tab indicator's height for the currently selected tab.
     *
     * @deprecated If possible, set the intrinsic height directly on a custom indicator drawable
     *     passed to {@link #setSelectedTabIndicator(Drawable)}.
     * @param height height to use for the indicator in pixels
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorHeight
     */
    @Deprecated
    public void setSelectedTabIndicatorHeight(int height) {
        slidingTabIndicator.setSelectedIndicatorHeight(height);
    }

    /**
     * Set the scroll position of the tabs. This is useful for when the tabs are being displayed as
     * part of a scrolling container such as {@link androidx.viewpager.widget.ViewPager}.
     *
     * <p>Calling this method does not update the selected tab, it is only used for drawing purposes.
     *
     * @param position current scroll position
     * @param positionOffset Value from [0, 1) indicating the offset from {@code position}.
     * @param updateSelectedText Whether to update the text's selected state.
     * @see #setScrollPosition(int, float, boolean, boolean)
     */
    public void setScrollPosition(int position, float positionOffset, boolean updateSelectedText) {
        setScrollPosition(position, positionOffset, updateSelectedText, true);
    }

    /**
     * Set the scroll position of the tabs. This is useful for when the tabs are being displayed as
     * part of a scrolling container such as {@link androidx.viewpager.widget.ViewPager}.
     *
     * <p>Calling this method does not update the selected tab, it is only used for drawing purposes.
     *
     * @param position current scroll position
     * @param positionOffset Value from [0, 1) indicating the offset from {@code position}.
     * @param updateSelectedText Whether to update the text's selected state.
     * @param updateIndicatorPosition Whether to set the indicator to the given position and offset.
     * @see #setScrollPosition(int, float, boolean)
     */
    public void setScrollPosition(
            int position,
            float positionOffset,
            boolean updateSelectedText,
            boolean updateIndicatorPosition) {
        final int roundedPosition = Math.round(position + positionOffset);
        if (roundedPosition < 0 || roundedPosition >= slidingTabIndicator.getChildCount()) {
            return;
        }

        // Set the indicator position, if enabled
        if (updateIndicatorPosition) {
            slidingTabIndicator.setIndicatorPositionFromTabPosition(position, positionOffset);
        }

        // Now update the scroll position, canceling any running animation
        if (scrollAnimator != null && scrollAnimator.isRunning()) {
            scrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(position, positionOffset), 0);

        // Update the 'selected state' view as we scroll, if enabled
        if (updateSelectedText) {
            setSelectedTabView(roundedPosition);
        }
    }

    /**
     * Add a tab to this layout. The tab will be added at the end of the list. If this is the first
     * tab to be added it will become the selected tab.
     *
     * @param tab Tab to add
     */
    public void addTab(@NonNull MyTabLayout.Tab tab) {
        addTab(tab, tabs.isEmpty());
    }

    /**
     * Add a tab to this layout. The tab will be inserted at <code>position</code>. If this is the
     * first tab to be added it will become the selected tab.
     *
     * @param tab The tab to add
     * @param position The new position of the tab
     */
    public void addTab(@NonNull MyTabLayout.Tab tab, int position) {
        addTab(tab, position, tabs.isEmpty());
    }

    /**
     * Add a tab to this layout. The tab will be added at the end of the list.
     *
     * @param tab Tab to add
     * @param setSelected True if the added tab should become the selected tab.
     */
    public void addTab(@NonNull MyTabLayout.Tab tab, boolean setSelected) {
        addTab(tab, tabs.size(), setSelected);
    }

    /**
     * Add a tab to this layout. The tab will be inserted at <code>position</code>.
     *
     * @param tab The tab to add
     * @param position The new position of the tab
     * @param setSelected True if the added tab should become the selected tab.
     */
    public void addTab(@NonNull MyTabLayout.Tab tab, int position, boolean setSelected) {
        if (tab.parent != this) {
            throw new IllegalArgumentException("Tab belongs to a different MyTabLayout.");
        }
        configureTab(tab, position);
        addTabView(tab);

        if (setSelected) {
            tab.select();
        }
    }

    private void addTabFromItemView(@NonNull MyTabItem item) {
        final MyTabLayout.Tab tab = newTab();
        if (item.text != null) {
            tab.setText(item.text);
        }
        if (item.icon != null) {
            tab.setIcon(item.icon);
        }
        if (item.customLayout != 0) {
            tab.setCustomView(item.customLayout);
        }
        if (!TextUtils.isEmpty(item.getContentDescription())) {
            tab.setContentDescription(item.getContentDescription());
        }
        addTab(tab);
    }

    /**
     * @deprecated Use {@link #addOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)} and {@link
     *    #removeOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}.
     */
    @Deprecated
    public void setOnTabSelectedListener(@Nullable MyTabLayout.OnTabSelectedListener listener) {
        setOnTabSelectedListener((MyTabLayout.BaseOnTabSelectedListener) listener);
    }

    /**
     * @deprecated Use {@link #addOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)} and {@link
     *     #removeOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}.
     */
    @Deprecated
    public void setOnTabSelectedListener(@Nullable MyTabLayout.BaseOnTabSelectedListener listener) {
        // The logic in this method emulates what we had before support for multiple
        // registered listeners.
        if (selectedListener != null) {
            removeOnTabSelectedListener(selectedListener);
        }
        // Update the deprecated field so that we can remove the passed listener the next
        // time we're called
        selectedListener = listener;
        if (listener != null) {
            addOnTabSelectedListener(listener);
        }
    }

    /**
     * Add a {@link MyTabLayout.OnTabSelectedListener} that will be invoked when tab selection changes.
     *
     * <p>Components that add a listener should take care to remove it when finished via {@link
     * #removeOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}.
     *
     * @param listener listener to add
     */
    public void addOnTabSelectedListener(@NonNull MyTabLayout.OnTabSelectedListener listener) {
        addOnTabSelectedListener((MyTabLayout.BaseOnTabSelectedListener) listener);
    }

    /**
     * Add a {@link MyTabLayout.BaseOnTabSelectedListener} that will be invoked when tab selection
     * changes.
     *
     * <p>Components that add a listener should take care to remove it when finished via {@link
     * #removeOnTabSelectedListener(MyTabLayout.BaseOnTabSelectedListener)}.
     *
     * @param listener listener to add
     * @deprecated use {@link #addOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}
     */
    @Deprecated
    public void addOnTabSelectedListener(@Nullable MyTabLayout.BaseOnTabSelectedListener listener) {
        if (!selectedListeners.contains(listener)) {
            selectedListeners.add(listener);
        }
    }

    /**
     * Remove the given {@link MyTabLayout.OnTabSelectedListener} that was previously added via {@link
     * #addOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}.
     *
     * @param listener listener to remove
     */
    public void removeOnTabSelectedListener(@NonNull MyTabLayout.OnTabSelectedListener listener) {
        removeOnTabSelectedListener((MyTabLayout.BaseOnTabSelectedListener) listener);
    }

    /**
     * Remove the given {@link MyTabLayout.BaseOnTabSelectedListener} that was previously added via
     * {@link #addOnTabSelectedListener(MyTabLayout.BaseOnTabSelectedListener)}.
     *
     * @param listener listener to remove
     * @deprecated use {@link #removeOnTabSelectedListener(MyTabLayout.OnTabSelectedListener)}
     */
    @Deprecated
    public void removeOnTabSelectedListener(@Nullable MyTabLayout.BaseOnTabSelectedListener listener) {
        selectedListeners.remove(listener);
    }

    /** Remove all previously added {@link MyTabLayout.OnTabSelectedListener}s. */
    public void clearOnTabSelectedListeners() {
        selectedListeners.clear();
    }

    /**
     * Create and return a new {@link MyTabLayout.Tab}. You need to manually add this using {@link #addTab(MyTabLayout.Tab)}
     * or a related method.
     *
     * @return A new Tab
     * @see #addTab(MyTabLayout.Tab)
     */
    @NonNull
    public MyTabLayout.Tab newTab() {
        MyTabLayout.Tab tab = createTabFromPool();
        tab.parent = this;
        tab.view = createTabView(tab);
        return tab;
    }

    // TODO(b/76413401): remove this method and just create the final field after the widget migration
    protected MyTabLayout.Tab createTabFromPool() {
        MyTabLayout.Tab tab = tabPool.acquire();
        if (tab == null) {
            tab = new MyTabLayout.Tab();
        }
        return tab;
    }

    // TODO(b/76413401): remove this method and just create the final field after the widget migration
    protected boolean releaseFromTabPool(MyTabLayout.Tab tab) {
        return tabPool.release(tab);
    }

    /**
     * Returns the number of tabs currently registered with the action bar.
     *
     * @return Tab count
     */
    public int getTabCount() {
        return tabs.size();
    }

    /** Returns the tab at the specified index. */
    @Nullable
    public MyTabLayout.Tab getTabAt(int index) {
        return (index < 0 || index >= getTabCount()) ? null : tabs.get(index);
    }

    /**
     * Returns the position of the current selected tab.
     *
     * @return selected tab position, or {@code -1} if there isn't a selected tab.
     */
    public int getSelectedTabPosition() {
        return selectedTab != null ? selectedTab.getPosition() : -1;
    }

    /**
     * Remove a tab from the layout. If the removed tab was selected it will be deselected and another
     * tab will be selected if present.
     *
     * @param tab The tab to remove
     */
    public void removeTab(@NonNull MyTabLayout.Tab tab) {
        if (tab.parent != this) {
            throw new IllegalArgumentException("Tab does not belong to this MyTabLayout.");
        }

        removeTabAt(tab.getPosition());
    }

    /**
     * Remove a tab from the layout. If the removed tab was selected it will be deselected and another
     * tab will be selected if present.
     *
     * @param position Position of the tab to remove
     */
    public void removeTabAt(int position) {
        final int selectedTabPosition = selectedTab != null ? selectedTab.getPosition() : 0;
        removeTabViewAt(position);

        final MyTabLayout.Tab removedTab = tabs.remove(position);
        if (removedTab != null) {
            removedTab.reset();
            releaseFromTabPool(removedTab);
        }

        final int newTabCount = tabs.size();
        for (int i = position; i < newTabCount; i++) {
            tabs.get(i).setPosition(i);
        }

        if (selectedTabPosition == position) {
            selectTab(tabs.isEmpty() ? null : tabs.get(Math.max(0, position - 1)));
        }
    }

    /** Remove all tabs from the action bar and deselect the current tab. */
    public void removeAllTabs() {
        // Remove all the views
        for (int i = slidingTabIndicator.getChildCount() - 1; i >= 0; i--) {
            removeTabViewAt(i);
        }

        for (final Iterator<MyTabLayout.Tab> i = tabs.iterator(); i.hasNext(); ) {
            final MyTabLayout.Tab tab = i.next();
            i.remove();
            tab.reset();
            releaseFromTabPool(tab);
        }

        selectedTab = null;
    }

    /**
     * 在此布局中设置选项卡的行为模式。 有效的输入选项是：
     *
     * <ul>
     *   <li>{@link #MODE_FIXED}: 固定选项卡可同时显示所有选项卡，最好与可在选项卡之间快速旋转的内容一起使用。
     *   <li>{@link #MODE_SCROLLABLE}: 可滚动选项卡在任何给定时刻显示选项卡的子集，并且可以包含更长的选项卡标签和更多数量的选项卡。
     *   当用户不需要直接比较标签标签时，它们最适合用于在触摸界面中浏览上下文。
     *   此模式通常用于{@link androidx.viewpager.widget.ViewPager}.
     * </ul>
     *
     * @param mode one of {@link #MODE_FIXED} or {@link #MODE_SCROLLABLE}.
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabMode
     */
    public void setTabMode(@MyTabLayout.Mode int mode) {
        if (mode != this.mode) {
            this.mode = mode;
            applyModeAndGravity();
        }
    }

    /**
     * 返回当前使用的模式 {@link MyTabLayout}.
     *
     * @see #setTabMode(int)
     */
    @MyTabLayout.Mode
    public int getTabMode() {
        return mode;
    }

    /**
     * 设置布局标签时要使用的重力。
     *
     * @param gravity one of {@link #GRAVITY_CENTER} or {@link #GRAVITY_FILL}.
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabGravity
     */
    public void setTabGravity(@MyTabLayout.TabGravity int gravity) {
        if (tabGravity != gravity) {
            tabGravity = gravity;
            applyModeAndGravity();
        }
    }

    /**
     * 用于布置标签的当前重力。
     *
     * @return one of {@link #GRAVITY_CENTER} or {@link #GRAVITY_FILL}.
     */
    @MyTabLayout.TabGravity
    public int getTabGravity() {
        return tabGravity;
    }

    /**
     * 在{@link MyTabLayout}中设置用于对齐选项卡选择指示器的指示器重力。
     * 您必须通过自定义指标可绘制对象的固有高度（首选），{@code tabIndicatorHeight}属性（不建议使用）
     * 或{@link #setSelectedTabIndicatorHeight（int）}（不建议使用）来设置指标高度。
     * 否则，除非将重力设置为{@link #INDICATOR_GRAVITY_STRETCH}，
     * 否则指示器将不会显示，在这种情况下，指示器将忽略指示器高度，并在{@link MyTabLayout}的整个高度和宽度上延伸。
     * 如果未设置，则默认为{@link #INDICATOR_GRAVITY_BOTTOM}。
     *
     * @param indicatorGravity one of {@link #INDICATOR_GRAVITY_BOTTOM}, {@link
     *     #INDICATOR_GRAVITY_CENTER}, {@link #INDICATOR_GRAVITY_TOP}, or {@link
     *     #INDICATOR_GRAVITY_STRETCH}
     * @attr ref com.google.android.material.R.styleable#MyTabLayout_tabIndicatorGravity
     */
    public void setSelectedTabIndicatorGravity(@MyTabLayout.TabIndicatorGravity int indicatorGravity) {
        if (tabIndicatorGravity != indicatorGravity) {
            tabIndicatorGravity = indicatorGravity;
            ViewCompat.postInvalidateOnAnimation(slidingTabIndicator);
        }
    }

    /**
     * 获取用于在{@link MyTabLayout}中对齐选项卡选择指示器的当前指示器重力。
     *
     * @return one of {@link #INDICATOR_GRAVITY_BOTTOM}, {@link #INDICATOR_GRAVITY_CENTER}, {@link
     *     #INDICATOR_GRAVITY_TOP}, or {@link #INDICATOR_GRAVITY_STRETCH}
     */
    @MyTabLayout.TabIndicatorGravity
    public int getTabIndicatorGravity() {
        return tabIndicatorGravity;
    }

    /**
     * 启用或禁用选项以使选项卡选择指示器适合选项卡项目的整个宽度，而不适合选项卡项目的内容。
     *
     * 默认为true。
     * 如果设置为false并且选项卡项具有文本标签，则选择指示符的宽度将设置为文本标签的宽度。
     * 如果选项卡项没有文本标签，但是有图标，则选择指示器的宽度将设置为该图标。
     * 如果选项卡项都不具备这些选项，或者如果计算出的宽度小于最小宽度值，则选择指示符宽度将设置为最小宽度值。
     *
     * @param tabIndicatorFullWidth  选择指示器的宽度是否适合选项卡项目的整个宽度
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorFullWidth
     * @see #isTabIndicatorFullWidth()
     */
    public void setTabIndicatorFullWidth(boolean tabIndicatorFullWidth) {
        this.tabIndicatorFullWidth = tabIndicatorFullWidth;
        ViewCompat.postInvalidateOnAnimation(slidingTabIndicator);
    }

    /**
     * 获取选择指示器宽度是否适合选项卡项目的整个宽度，或适合选项卡项目的内容。
     *
     * @return whether or not selection indicator width is fit to the full width of the tab item
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabIndicatorFullWidth
     * @see #setTabIndicatorFullWidth(boolean)
     */
    public boolean isTabIndicatorFullWidth() {
        return tabIndicatorFullWidth;
    }

    /**
     * 设置选项卡标签是否将与选项卡图标一起内嵌显示，还是将其显示在选项卡图标下方
     *
     * @see #isInlineLabel()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabInlineLabel
     */
    public void setInlineLabel(boolean inline) {
        if (inlineLabel != inline) {
            inlineLabel = inline;
            for (int i = 0; i < slidingTabIndicator.getChildCount(); i++) {
                View child = slidingTabIndicator.getChildAt(i);
                if (child instanceof MyTabLayout.TabView) {
                    ((MyTabLayout.TabView) child).updateOrientation();
                }
            }
            applyModeAndGravity();
        }
    }

    /**
     * 设置选项卡标签是否将与选项卡图标一起内嵌显示，还是将其显示在选项卡图标下方。
     *
     * @param inlineResourceId Resource ID for boolean inline flag
     * @see #isInlineLabel()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabInlineLabel
     */
    public void setInlineLabelResource(@BoolRes int inlineResourceId) {
        setInlineLabel(getResources().getBoolean(inlineResourceId));
    }

    /**
     * 返回tab标签是与标签图标一起内嵌显示，还是在标签图标下方显示。
     *
     * @see #setInlineLabel(boolean)
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabInlineLabel
     */
    public boolean isInlineLabel() {
        return inlineLabel;
    }

    /**
     * 设置此{@link MyTabLayout}是否具有无限波纹效果，或者波纹是否将绑定到选项卡项目的大小。
     *
     * 默认为false。
     *
     * @see #hasUnboundedRipple()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabUnboundedRipple
     */
    public void setUnboundedRipple(boolean unboundedRipple) {
        if (this.unboundedRipple != unboundedRipple) {
            this.unboundedRipple = unboundedRipple;
            for (int i = 0; i < slidingTabIndicator.getChildCount(); i++) {
                View child = slidingTabIndicator.getChildAt(i);
                if (child instanceof MyTabLayout.TabView) {
                    ((MyTabLayout.TabView) child).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    /**
     * 设置此{@link MyTabLayout}是否具有无限波纹效果，或者波纹是否将绑定到选项卡项目的大小。
     * 默认为false。
     *
     * @param unboundedRippleResourceId Resource ID for boolean unbounded ripple value
     * @see #hasUnboundedRipple()
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabUnboundedRipple
     */
    public void setUnboundedRippleResource(@BoolRes int unboundedRippleResourceId) {
        setUnboundedRipple(getResources().getBoolean(unboundedRippleResourceId));
    }

    /**
     * 返回此{@link MyTabLayout}是否具有无限的波纹效果，或者波纹是否绑定到选项卡项目的大小。
     *
     * @see #setUnboundedRipple(boolean)
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabUnboundedRipple
     */
    public boolean hasUnboundedRipple() {
        return unboundedRipple;
    }

    /**
     * 设置选项卡使用的不同状态（正常，选定）的文本颜色。
     *
     * @see #getTabTextColors()
     */
    public void setTabTextColors(@Nullable ColorStateList textColor) {
        if (tabTextColors != textColor) {
            tabTextColors = textColor;
            updateAllTabs();
        }
    }

    /** 获取用于选项卡的不同状态（正常，选定）的文本颜色。 */
    @Nullable
    public ColorStateList getTabTextColors() {
        return tabTextColors;
    }

    /**
     * 设置选项卡使用的不同状态（正常，选定）的文本颜色。
     *
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabTextColor
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabSelectedTextColor
     */
    public void setTabTextColors(int normalColor, int selectedColor) {
        setTabTextColors(createColorStateList(normalColor, selectedColor));
    }

    /**
     * 设置选项卡使用的不同状态（正常，选定）的图标色调。
     *
     * @see #getTabIconTint()
     */
    public void setTabIconTint(@Nullable ColorStateList iconTint) {
        if (tabIconTint != iconTint) {
            tabIconTint = iconTint;
            updateAllTabs();
        }
    }

    /**
     * 设置用于选项卡的不同状态（正常，选定）的图标着色资源。
     *
     * @param iconTintResourceId A color resource to use as icon tint.
     * @see #getTabIconTint()
     */
    public void setTabIconTintResource(@ColorRes int iconTintResourceId) {
        setTabIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    /** 获取用于选项卡的不同状态（正常，选定）的图标色调。 */
    @Nullable
    public ColorStateList getTabIconTint() {
        return tabIconTint;
    }

    /**
     * 返回此TabLayout的波纹颜色。
     *
     * @return the color (or ColorStateList) used for the ripple
     * @see #setTabRippleColor(ColorStateList)
     */
    @Nullable
    public ColorStateList getTabRippleColor() {
        return tabRippleColorStateList;
    }

    /**
     * 设置此TabLayout的波纹颜色。
     *
     * 在具有KitKat或更低版本的设备上运行时(Android4.4及以下版本)，我们将此颜色绘制为填充的覆盖层而不是波纹。
     *
     * @param color color (or ColorStateList) to use for the ripple
     * @attr ref com.google.android.material.R.styleable#TabLayout_tabRippleColor
     * @see #getTabRippleColor()
     */
    public void setTabRippleColor(@Nullable ColorStateList color) {
        if (tabRippleColorStateList != color) {
            tabRippleColorStateList = color;
            for (int i = 0; i < slidingTabIndicator.getChildCount(); i++) {
                View child = slidingTabIndicator.getChildAt(i);
                if (child instanceof MyTabLayout.TabView) {
                    ((MyTabLayout.TabView) child).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    /**
     * 设置此TabLayout的波纹颜色资源。
     *
     * 在具有KitKat或更低版本的设备上运行时(Android4.4及以下版本)，我们将此颜色绘制为填充的覆盖层而不是波纹。
     *
     * @param tabRippleColorResourceId A color resource to use as ripple color.
     * @see #getTabRippleColor()
     */
    public void setTabRippleColorResource(@ColorRes int tabRippleColorResourceId) {
        setTabRippleColor(AppCompatResources.getColorStateList(getContext(), tabRippleColorResourceId));
    }

    /**
     * 返回为此TabLayout可绘制的选择指示器。
     *
     * @return The drawable used as the tab selection indicator, if set.
     * @see #setSelectedTabIndicator(Drawable)
     * @see #setSelectedTabIndicator(int)
     */
    @Nullable
    public Drawable getTabSelectedIndicator() {
        return tabSelectedIndicator;
    }

    /**
     * 设置此TabLayout的选择指示器。 默认情况下，这是选项卡底部的一行。
     * 如果通过TabLayout的样式或通过{@link #setSelectedTabIndicatorColor（int）}指定了{@code tabIndicatorColor}，则选择指示器将使用该颜色进行着色。
     * 否则，它将使用可绘制对象中指定的颜色。
     *
     * @param tabSelectedIndicator A drawable to use as the selected tab indicator.
     * @see #setSelectedTabIndicatorColor(int)
     * @see #setSelectedTabIndicator(int)
     */
    public void setSelectedTabIndicator(@Nullable Drawable tabSelectedIndicator) {
        if (this.tabSelectedIndicator != tabSelectedIndicator) {
            this.tabSelectedIndicator = tabSelectedIndicator;
            ViewCompat.postInvalidateOnAnimation(slidingTabIndicator);
        }
    }

    /**
     * 设置可绘制资源以用作此TabLayout的选择指示器。
     * 默认情况下，这是选项卡底部的一行。
     * 如果通过TabLayout的样式或通过{@link #setSelectedTabIndicatorColor（int）}指定了{@code tabIndicatorColor} 选择指示器将使用该颜色进行着色。
     * 否则，它将使用可绘制对象中指定的颜色。
     *
     * @param tabSelectedIndicatorResourceId A drawable resource to use as the selected tab indicator.
     * @see #setSelectedTabIndicatorColor(int)
     * @see #setSelectedTabIndicator(Drawable)
     */
    public void setSelectedTabIndicator(@DrawableRes int tabSelectedIndicatorResourceId) {
        if (tabSelectedIndicatorResourceId != 0) {
            setSelectedTabIndicator(
                    AppCompatResources.getDrawable(getContext(), tabSelectedIndicatorResourceId));
        } else {
            setSelectedTabIndicator(null);
        }
    }

    /**
     * 一站式设置 {@link MyTabLayout} 与 {@link ViewPager}.
     *
     * 这与在启用自动刷新的情况下调用{@link #setupWithViewPager（ViewPager，boolean）}相同。
     *
     * @param viewPager 要链接到的ViewPager，或{@code null}清除以前的任何链接
     */
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    /**
     *  一站式设置 {@link MyTabLayout} 与 {@link ViewPager}.
     *
     * 此方法会将给定的ViewPager和此TabLayout链接在一起，以便一个中的更改自动反映在另一个中。
     * 这包括滚动状态更改和单击。
     * 从ViewPager适配器的页面标题中将填充此布局中显示的选项卡。
     *
     * 如果{@code autoRefresh}为{@code true}，则{@link PagerAdapter}中的任何更改都将触发此布局，以从适配器的标题重新填充自身。
     *
     * 如果给定的ViewPager不为空，则需要已经设置了{@link PagerAdapter}。
     *
     * @param viewPager 要链接到的ViewPager，或{@code null}清除以前的任何链接
     * @param autoRefresh 如果给定ViewPager的内容更改，则此布局是否应刷新其内容
     */
    public void setupWithViewPager(@Nullable final ViewPager viewPager, boolean autoRefresh) {
        setupWithViewPager(viewPager, autoRefresh, false);
    }

    private void setupWithViewPager(@Nullable final ViewPager viewPager, boolean autoRefresh, boolean implicitSetup) {
        if (this.viewPager != null) {
            // If we've already been setup with a ViewPager, remove us from it
            //如果我们已经使用ViewPager进行了设置，请将其从其中删除
            if (pageChangeListener != null) {
                this.viewPager.removeOnPageChangeListener(pageChangeListener);
            }
            if (adapterChangeListener != null) {
                this.viewPager.removeOnAdapterChangeListener(adapterChangeListener);
            }
        }

        if (currentVpSelectedListener != null) {
            // If we already have a tab selected listener for the ViewPager, remove it
            //如果我们已经为ViewPager选择了一个标签页侦听器，请将其删除
            removeOnTabSelectedListener(currentVpSelectedListener);
            currentVpSelectedListener = null;
        }

        if (viewPager != null) {
            this.viewPager = viewPager;

            // Add our custom OnPageChangeListener to the ViewPager
            //将自定义的OnPageChangeListener添加到ViewPager
            if (pageChangeListener == null) {
                pageChangeListener = new MyTabLayout.TabLayoutOnPageChangeListener(this);
            }
            pageChangeListener.reset();
            viewPager.addOnPageChangeListener(pageChangeListener);

            // Now we'll add a tab selected listener to set ViewPager's current item
            //现在，我们将添加一个标签页选定的侦听器，以设置ViewPager的当前项目
            currentVpSelectedListener = new MyTabLayout.ViewPagerOnTabSelectedListener(viewPager);
            addOnTabSelectedListener(currentVpSelectedListener);

            final PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                // Now we'll populate ourselves from the pager adapter, adding an observer if
                // autoRefresh is enabled
                //现在，我们将从寻呼机适配器填充自己，如果启用了autoRefresh，则添加观察者
                setPagerAdapter(adapter, autoRefresh);
            }

            // Add a listener so that we're notified of any adapter changes
            //添加一个侦听器，以便通知我们任何适配器更改
            if (adapterChangeListener == null) {
                adapterChangeListener = new MyTabLayout.AdapterChangeListener();
            }
            adapterChangeListener.setAutoRefresh(autoRefresh);
            viewPager.addOnAdapterChangeListener(adapterChangeListener);

            // Now update the scroll position to match the ViewPager's current item
            //现在更新滚动位置以匹配ViewPager当前项目
            setScrollPosition(viewPager.getCurrentItem(), 0f, true);
        } else {
            // We've been given a null ViewPager so we need to clear out the internal state,
            // listeners and observers
            //为我们提供了一个空的ViewPager，因此我们需要清除内部状态，侦听器和观察者
            this.viewPager = null;
            setPagerAdapter(null, false);
        }

        setupViewPagerImplicitly = implicitSetup;
    }

    /**
     * @deprecated {@link #setupWithViewPager（ViewPager）}将TabLayout和ViewPager链接在一起。
     * 使用该方法时，更改{@link PagerAdapter}时，TabLayout将自动更新。
     */
    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable final PagerAdapter adapter) {
        setPagerAdapter(adapter, false);
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        // Only delay the pressed state if the tabs can scroll
        //仅在标签滚动时才延迟按下状态
        return getTabScrollRange() > 0;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        MaterialShapeUtils.setParentAbsoluteElevation(this);

        if (viewPager == null) {
            // If we don't have a ViewPager already, check if our parent is a ViewPager to
            // setup with it automatically
            //如果我们还没有ViewPager，请检查您的父母是否是要自动为其设置的ViewPager
            final ViewParent vp = getParent();
            if (vp instanceof ViewPager) {
                // If we have a ViewPager parent and we've been added as part of its decor, let's
                // assume that we should automatically setup to display any titles
                //如果我们有一个ViewPager父级，并且已经将其添加为装饰的一部分，则假定我们应该自动设置为显示任何标题
                setupWithViewPager((ViewPager) vp, true, true);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (setupViewPagerImplicitly) {
            // If we've been setup with a ViewPager implicitly, let's clear out any listeners, etc
            //如果我们已经隐式地设置了ViewPager，那么让我们清除所有侦听器，等等
            setupWithViewPager(null);
            setupViewPagerImplicitly = false;
        }
    }

    private int getTabScrollRange() {
        return Math.max(
                0, slidingTabIndicator.getWidth() - getWidth() - getPaddingLeft() - getPaddingRight());
    }

    void setPagerAdapter(@Nullable final PagerAdapter adapter, final boolean addObserver) {
        if (pagerAdapter != null && pagerAdapterObserver != null) {
            // If we already have a PagerAdapter, unregister our observer
            //如果我们已经有了PagerAdapter，请取消注册我们的观察者
            pagerAdapter.unregisterDataSetObserver(pagerAdapterObserver);
        }

        pagerAdapter = adapter;

        if (addObserver && adapter != null) {
            // Register our observer on the new adapter
            //在新适配器上注册我们的观察者
            if (pagerAdapterObserver == null) {
                pagerAdapterObserver = new MyTabLayout.PagerAdapterObserver();
            }
            adapter.registerDataSetObserver(pagerAdapterObserver);
        }

        // Finally make sure we reflect the new adapter
        //最后确保我们反映了新的适配器
        populateFromPagerAdapter();
    }

    void populateFromPagerAdapter() {
        removeAllTabs();

        if (pagerAdapter != null) {
            final int adapterCount = pagerAdapter.getCount();
            for (int i = 0; i < adapterCount; i++) {
                addTab(newTab().setText(pagerAdapter.getPageTitle(i)), false);
            }

            // Make sure we reflect the currently set ViewPager item
            //确保我们反映了当前设置的ViewPager项
            if (viewPager != null && adapterCount > 0) {
                final int curItem = viewPager.getCurrentItem();
                if (curItem != getSelectedTabPosition() && curItem < getTabCount()) {
                    selectTab(getTabAt(curItem));
                }
            }
        }
    }

    private void updateAllTabs() {
        for (int i = 0, z = tabs.size(); i < z; i++) {
            tabs.get(i).updateView();
        }
    }

    @NonNull
    private MyTabLayout.TabView createTabView(@NonNull final MyTabLayout.Tab tab) {
        MyTabLayout.TabView tabView = tabViewPool != null ? tabViewPool.acquire() : null;
        if (tabView == null) {
            tabView = new MyTabLayout.TabView(getContext());
        }
        tabView.setTab(tab);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab.contentDesc)) {
            tabView.setContentDescription(tab.text);
        } else {
            tabView.setContentDescription(tab.contentDesc);
        }
        return tabView;
    }

    private void configureTab(@NonNull MyTabLayout.Tab tab, int position) {
        tab.setPosition(position);
        tabs.add(position, tab);

        final int count = tabs.size();
        for (int i = position + 1; i < count; i++) {
            tabs.get(i).setPosition(i);
        }
    }

    private void addTabView(@NonNull MyTabLayout.Tab tab) {
        final MyTabLayout.TabView tabView = tab.view;
        tabView.setSelected(false);
        tabView.setActivated(false);
        slidingTabIndicator.addView(tabView, tab.getPosition(), createLayoutParamsForTabs());
    }

    @Override
    public void addView(View child) {
        addViewInternal(child);
    }

    @Override
    public void addView(View child, int index) {
        addViewInternal(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        addViewInternal(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        addViewInternal(child);
    }

    private void addViewInternal(final View child) {
        if (child instanceof MyTabItem) {
            addTabFromItemView((MyTabItem) child);
        } else {
            throw new IllegalArgumentException("Only MyTabItem instances can be added to MyTabLayout");
        }
    }

    @NonNull
    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        updateTabViewLayoutParams(lp);
        return lp;
    }

    private void updateTabViewLayoutParams(@NonNull LinearLayout.LayoutParams lp) {
        if (mode == MODE_FIXED && tabGravity == GRAVITY_FILL) {
            lp.width = 0;
            lp.weight = 1;
        } else {
            lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            lp.weight = 0;
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setElevation(float elevation) {
        super.setElevation(elevation);

        MaterialShapeUtils.setElevation(this, elevation);
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        AccessibilityNodeInfoCompat infoCompat = AccessibilityNodeInfoCompat.wrap(info);
        infoCompat.setCollectionInfo(
                AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(
                        /* rowCount= */ 1,
                        /* columnCount= */ getTabCount(),
                        /* hierarchical= */ false,
                        /* selectionMode = */ AccessibilityNodeInfoCompat.CollectionInfoCompat.SELECTION_MODE_SINGLE));
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        // Draw tab background layer for each tab item
        for (int i = 0; i < slidingTabIndicator.getChildCount(); i++) {
            View tabView = slidingTabIndicator.getChildAt(i);
            if (tabView instanceof MyTabLayout.TabView) {
                ((MyTabLayout.TabView) tabView).drawBackground(canvas);
            }
        }

        super.onDraw(canvas);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //如果我们有一个MeasureSpec可以确定高度，请尝试使用默认高度
        @SuppressLint("RestrictedApi") final int idealHeight = Math.round(ViewUtils.dpToPx(getContext(), getDefaultHeight()));
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                if (getChildCount() == 1 && MeasureSpec.getSize(heightMeasureSpec) >= idealHeight) {
                    getChildAt(0).setMinimumHeight(idealHeight);
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                heightMeasureSpec =
                        MeasureSpec.makeMeasureSpec(
                                idealHeight + getPaddingTop() + getPaddingBottom(), MeasureSpec.EXACTLY);
                break;
            default:
                break;
        }

        final int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED) {
            // 如果我们没有未指定的宽度规格，请使用给定的大小来计算最大制表符宽度
            tabMaxWidth = requestedTabMaxWidth > 0
                            ? requestedTabMaxWidth
                            : (int) (specWidth - ViewUtils.dpToPx(getContext(), TAB_MIN_WIDTH_MARGIN));
        }

        // 现在，使用（可能是）修改后的高度规格进行超级测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 1) {
            // 如果我们处于固定模式，则需要确保标签条与我们的宽度相同，这样我们就不会滚动
            final View child = getChildAt(0);
            boolean remeasure = false;

            switch (mode) {
                case MODE_AUTO:
                case MODE_SCROLLABLE:
                    // 如果孩子比我们小，我们只需要调整孩子的大小。
                    // 这类似于fillViewport
                    remeasure = child.getMeasuredWidth() < getMeasuredWidth();
                    break;
                case MODE_FIXED:
                    // 调整子项的大小，使其不会滚动
                    remeasure = child.getMeasuredWidth() != getMeasuredWidth();
                    break;
            }

            if (remeasure) {
                // 将widthSpec设置为恰好是我们的测量宽度，以重新测量孩子
                int childHeightMeasureSpec =
                        getChildMeasureSpec(
                                heightMeasureSpec,
                                getPaddingTop() + getPaddingBottom(),
                                child.getLayoutParams().height);

                int childWidthMeasureSpec =
                        MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }

    private void removeTabViewAt(int position) {
        final MyTabLayout.TabView view = (MyTabLayout.TabView) slidingTabIndicator.getChildAt(position);
        slidingTabIndicator.removeViewAt(position);
        if (view != null) {
            view.reset();
            tabViewPool.release(view);
        }
        requestLayout();
    }

    private void animateToTab(int newPosition) {
        if (newPosition == MyTabLayout.Tab.INVALID_POSITION) {
            return;
        }

        if (getWindowToken() == null
                || !ViewCompat.isLaidOut(this)
                || slidingTabIndicator.childrenNeedLayout()) {
            // If we don't have a window token, or we haven't been laid out yet just draw the new
            // position now
            //如果我们没有窗口标记，或者尚未布局，请立即绘制新位置
            setScrollPosition(newPosition, 0f, true);
            return;
        }

        final int startScrollX = getScrollX();
        final int targetScrollX = calculateScrollXForTab(newPosition, 0);

        if (startScrollX != targetScrollX) {
            ensureScrollAnimator();

            scrollAnimator.setIntValues(startScrollX, targetScrollX);
            scrollAnimator.start();
        }

        // Now animate the indicator
        slidingTabIndicator.animateIndicatorToPosition(newPosition, tabIndicatorAnimationDuration);
    }

    private void ensureScrollAnimator() {
        if (scrollAnimator == null) {
            scrollAnimator = new ValueAnimator();
            scrollAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            scrollAnimator.setDuration(tabIndicatorAnimationDuration);
            scrollAnimator.addUpdateListener(
                    new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(@NonNull ValueAnimator animator) {
                            scrollTo((int) animator.getAnimatedValue(), 0);
                        }
                    });
        }
    }

    void setScrollAnimatorListener(ValueAnimator.AnimatorListener listener) {
        ensureScrollAnimator();
        scrollAnimator.addListener(listener);
    }

    /**
     * 添加选定标签时调用。
     * 在TabLayout中取消选择所有其他选项卡。
     *
     * @param position Position of the selected tab.
     */
    private void setSelectedTabView(int position) {
        final int tabCount = slidingTabIndicator.getChildCount();
        if (position < tabCount) {
            for (int i = 0; i < tabCount; i++) {
                final View child = slidingTabIndicator.getChildAt(i);
                child.setSelected(i == position);
                child.setActivated(i == position);
            }
        }
    }

    /**
     * 选择给定的标签。
     *
     * @param tab The tab to select, or {@code null} to select none.
     * @see #selectTab(MyTabLayout.Tab, boolean)
     */
    public void selectTab(@Nullable MyTabLayout.Tab tab) {
        selectTab(tab, true);
    }

    /**
     * 选择给定的标签。
     * 如果重新选择了当前标签页，无论{@code updateIndicator}的值如何，它将始终为选定的标签页制作动画。
     *
     * @param tab The tab to select, or {@code null} to select none.
     * @param updateIndicator Whether to animate to the selected tab.
     * @see #selectTab(MyTabLayout.Tab)
     */
    public void selectTab(@Nullable final MyTabLayout.Tab tab, boolean updateIndicator) {
        final MyTabLayout.Tab currentTab = selectedTab;

        if (currentTab == tab) {
            if (currentTab != null) {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
            }
        } else {
            final int newPosition = tab != null ? tab.getPosition() : MyTabLayout.Tab.INVALID_POSITION;
            if (updateIndicator) {
                if ((currentTab == null || currentTab.getPosition() == MyTabLayout.Tab.INVALID_POSITION)
                        && newPosition != MyTabLayout.Tab.INVALID_POSITION) {
                    // If we don't currently have a tab, just draw the indicator
                    setScrollPosition(newPosition, 0f, true);
                } else {
                    animateToTab(newPosition);
                }
                if (newPosition != MyTabLayout.Tab.INVALID_POSITION) {
                    setSelectedTabView(newPosition);
                }
            }
            // Setting selectedTab before dispatching 'tab unselected' events, so that currentTab's state
            // will be interpreted as unselected
            selectedTab = tab;
            if (currentTab != null) {
                dispatchTabUnselected(currentTab);
            }
            if (tab != null) {
                dispatchTabSelected(tab);
            }
        }
    }

    private void dispatchTabSelected(@NonNull final MyTabLayout.Tab tab) {
        for (int i = selectedListeners.size() - 1; i >= 0; i--) {
            selectedListeners.get(i).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(@NonNull final MyTabLayout.Tab tab) {
        for (int i = selectedListeners.size() - 1; i >= 0; i--) {
            selectedListeners.get(i).onTabUnselected(tab);
        }
    }

    private void dispatchTabReselected(@NonNull final MyTabLayout.Tab tab) {
        for (int i = selectedListeners.size() - 1; i >= 0; i--) {
            selectedListeners.get(i).onTabReselected(tab);
        }
    }

    private int calculateScrollXForTab(int position, float positionOffset) {
        if (mode == MODE_SCROLLABLE || mode == MODE_AUTO) {
            final View selectedChild = slidingTabIndicator.getChildAt(position);
            final View nextChild =
                    position + 1 < slidingTabIndicator.getChildCount()
                            ? slidingTabIndicator.getChildAt(position + 1)
                            : null;
            final int selectedWidth = selectedChild != null ? selectedChild.getWidth() : 0;
            final int nextWidth = nextChild != null ? nextChild.getWidth() : 0;

            // base scroll amount: places center of tab in center of parent
            int scrollBase = selectedChild.getLeft() + (selectedWidth / 2) - (getWidth() / 2);
            // offset amount: fraction of the distance between centers of tabs
            int scrollOffset = (int) ((selectedWidth + nextWidth) * 0.5f * positionOffset);

            return (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_LTR)
                    ? scrollBase + scrollOffset
                    : scrollBase - scrollOffset;
        }
        return 0;
    }

    private void applyModeAndGravity() {
        int paddingStart = 0;
        if (mode == MODE_SCROLLABLE || mode == MODE_AUTO) {
            // If we're scrollable, or fixed at start, inset using padding
            paddingStart = Math.max(0, contentInsetStart - tabPaddingStart);
        }
        ViewCompat.setPaddingRelative(slidingTabIndicator, paddingStart, 0, 0, 0);

        switch (mode) {
            case MODE_AUTO:
            case MODE_FIXED:
                if (tabGravity == GRAVITY_START) {
                    Log.w(
                            LOG_TAG,
                            "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be"
                                    + " used instead");
                }
                slidingTabIndicator.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case MODE_SCROLLABLE:
                applyGravityForModeScrollable(tabGravity);
                break;
        }

        updateTabViews(true);
    }

    private void applyGravityForModeScrollable(int tabGravity) {
        switch (tabGravity) {
            case GRAVITY_CENTER:
                slidingTabIndicator.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case GRAVITY_FILL:
                Log.w(
                        LOG_TAG,
                        "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used"
                                + " instead");
                // Fall through
            case GRAVITY_START:
                slidingTabIndicator.setGravity(GravityCompat.START);
                break;
            default:
                break;
        }
    }

    void updateTabViews(final boolean requestLayout) {
        for (int i = 0; i < slidingTabIndicator.getChildCount(); i++) {
            View child = slidingTabIndicator.getChildAt(i);
            child.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) child.getLayoutParams());
            if (requestLayout) {
                child.requestLayout();
            }
        }
    }

    /** 此布局中的一个选项卡。 实例可以通过{@link #newTab()}创建  */
    // TODO(b/76413401): make class final after the widget migration is finished
    public static class Tab {

        /**
         * An invalid position for a tab.
         *
         * @see #getPosition()
         */
        public static final int INVALID_POSITION = -1;

        @Nullable private Object tag;
        @Nullable private Drawable icon;
        @Nullable private CharSequence text;
        // This represents the content description that has been explicitly set on the Tab or TabItem
        // in XML or through #setContentDescription. If the content description is empty, text should
        // be used as the content description instead, but contentDesc should remain empty.
        @Nullable private CharSequence contentDesc;
        private int position = INVALID_POSITION;
        @Nullable private View customView;
        private @MyTabLayout.LabelVisibility
        int labelVisibilityMode = TAB_LABEL_VISIBILITY_LABELED;

        // TODO(b/76413401): make package private after the widget migration is finished
        @Nullable public MyTabLayout parent;
        // TODO(b/76413401): make package private after the widget migration is finished
        @NonNull public MyTabLayout.TabView view;

        // TODO(b/76413401): make package private constructor after the widget migration is finished
        public Tab() {
            // Private constructor
        }

        /** @return This Tab's tag object. */
        @Nullable
        public Object getTag() {
            return tag;
        }

        /**
         * Give this Tab an arbitrary object to hold for later use.
         *
         * @param tag Object to store
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setTag(@Nullable Object tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 返回用于此选项卡的自定义视图。
         *
         * @see #setCustomView(View)
         * @see #setCustomView(int)
         */
        @Nullable
        public View getCustomView() {
            return customView;
        }

        /**
         * 设置要用于此选项卡的自定义视图。
         *
         * 如果提供的视图包含ID为{@link android.R.id＃text1}的{@link TextView}，则将使用为{@link #setText（CharSequence）}赋予的值进行更新。
         * 同样，如果此布局包含ID为{@link android.R.id＃icon}的{@link ImageView}，则将使用给定的{@link #setIcon（Drawable）}值对其进行更新。
         *
         * @param view Custom view to be used as a tab.
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setCustomView(@Nullable View view) {
            customView = view;
            updateView();
            return this;
        }

        /**
         * Set a custom view to be used for this tab.
         *
         * <p>If the inflated layout contains a {@link TextView} with an ID of {@link
         * android.R.id#text1} then that will be updated with the value given to {@link
         * #setText(CharSequence)}. Similarly, if this layout contains an {@link ImageView} with ID
         * {@link android.R.id#icon} then it will be updated with the value given to {@link
         * #setIcon(Drawable)}.
         *
         * @param resId A layout resource to inflate and use as a custom tab view
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setCustomView(@LayoutRes int resId) {
            final LayoutInflater inflater = LayoutInflater.from(view.getContext());
            return setCustomView(inflater.inflate(resId, view, false));
        }

        /**
         * Return the icon associated with this tab.
         *
         * @return The tab's icon
         */
        @Nullable
        public Drawable getIcon() {
            return icon;
        }

        /**
         * Return the current position of this tab in the action bar.
         *
         * @return Current position, or {@link #INVALID_POSITION} if this tab is not currently in the
         *     action bar.
         */
        public int getPosition() {
            return position;
        }

        void setPosition(int position) {
            this.position = position;
        }

        /**
         * Return the text of this tab.
         *
         * @return The tab's text
         */
        @Nullable
        public CharSequence getText() {
            return text;
        }

        /**
         * Set the icon displayed on this tab.
         *
         * @param icon The drawable to use as an icon
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setIcon(@Nullable Drawable icon) {
            this.icon = icon;
            if ((parent.tabGravity == GRAVITY_CENTER) || parent.mode == MODE_AUTO) {
                parent.updateTabViews(true);
            }
            updateView();
            if (BadgeUtils.USE_COMPAT_PARENT
                    && view.hasBadgeDrawable()
                    && view.badgeDrawable.isVisible()) {
                // Invalidate the TabView if icon visibility has changed and a badge is displayed.
                view.invalidate();
            }
            return this;
        }

        /**
         * Set the icon displayed on this tab.
         *
         * @param resId A resource ID referring to the icon that should be displayed
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setIcon(@DrawableRes int resId) {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a MyTabLayout");
            }
            return setIcon(AppCompatResources.getDrawable(parent.getContext(), resId));
        }

        /**
         * Set the text displayed on this tab. Text may be truncated if there is not room to display the
         * entire string.
         *
         * @param text The text to display
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setText(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(contentDesc) && !TextUtils.isEmpty(text)) {
                // If no content description has been set, use the text as the content description of the
                // TabView. If the text is null, don't update the content description.
                view.setContentDescription(text);
            }

            this.text = text;
            updateView();
            return this;
        }

        /**
         * Set the text displayed on this tab. Text may be truncated if there is not room to display the
         * entire string.
         *
         * @param resId A resource ID referring to the text that should be displayed
         * @return The current instance for call chaining
         */
        @NonNull
        public MyTabLayout.Tab setText(@StringRes int resId) {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a MyTabLayout");
            }
            return setText(parent.getResources().getText(resId));
        }

        /**
         * Creates an instance of {@link BadgeDrawable} if none exists. Initializes (if needed) and
         * returns the associated instance of {@link BadgeDrawable}.
         *
         * @return an instance of BadgeDrawable associated with {@code Tab}.
         */
        @NonNull
        public BadgeDrawable getOrCreateBadge() {
            return view.getOrCreateBadge();
        }

        /**
         * Removes the {@link BadgeDrawable}. Do nothing if none exists. Consider changing the
         * visibility of the {@link BadgeDrawable} if you only want to hide it temporarily.
         */
        public void removeBadge() {
            view.removeBadge();
        }

        /**
         * Returns an instance of {@link BadgeDrawable} associated with this tab, null if none was
         * initialized.
         */
        @Nullable
        public BadgeDrawable getBadge() {
            return view.getBadge();
        }

        /**
         * Sets the visibility mode for the Labels in this Tab. The valid input options are:
         *
         * <ul>
         *   <li>{@link #TAB_LABEL_VISIBILITY_UNLABELED}: Tabs will appear without labels regardless of
         *       whether text is set.
         *   <li>{@link #TAB_LABEL_VISIBILITY_LABELED}: Tabs will appear labeled if text is set.
         * </ul>
         *
         * @param mode one of {@link #TAB_LABEL_VISIBILITY_UNLABELED} or {@link
         *     #TAB_LABEL_VISIBILITY_LABELED}.
         * @return The current instance for call chaining.
         */
        @NonNull
        public MyTabLayout.Tab setTabLabelVisibility(@MyTabLayout.LabelVisibility int mode) {
            this.labelVisibilityMode = mode;
            if ((parent.tabGravity == GRAVITY_CENTER) || parent.mode == MODE_AUTO) {
                parent.updateTabViews(true);
            }
            this.updateView();
            if (BadgeUtils.USE_COMPAT_PARENT
                    && view.hasBadgeDrawable()
                    && view.badgeDrawable.isVisible()) {
                // Invalidate the TabView if label visibility has changed and a badge is displayed.
                view.invalidate();
            }
            return this;
        }

        /**
         * Gets the visibility mode for the Labels in this Tab.
         *
         * @return the label visibility mode, one of {@link #TAB_LABEL_VISIBILITY_UNLABELED} or {@link
         *     #TAB_LABEL_VISIBILITY_LABELED}.
         * @see #setTabLabelVisibility(int)
         */
        @MyTabLayout.LabelVisibility
        public int getTabLabelVisibility() {
            return this.labelVisibilityMode;
        }

        /** Select this tab. Only valid if the tab has been added to the action bar. */
        public void select() {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a MyTabLayout");
            }
            parent.selectTab(this);
        }

        /** Returns true if this tab is currently selected. */
        public boolean isSelected() {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a MyTabLayout");
            }
            return parent.getSelectedTabPosition() == position;
        }

        /**
         * Set a description of this tab's content for use in accessibility support. If no content
         * description is provided the title will be used.
         *
         * @param resId A resource ID referring to the description text
         * @return The current instance for call chaining
         * @see #setContentDescription(CharSequence)
         * @see #getContentDescription()
         */
        @NonNull
        public MyTabLayout.Tab setContentDescription(@StringRes int resId) {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a MyTabLayout");
            }
            return setContentDescription(parent.getResources().getText(resId));
        }

        /**
         * Set a description of this tab's content for use in accessibility support. If no content
         * description is provided the title will be used.
         *
         * @param contentDesc Description of this tab's content
         * @return The current instance for call chaining
         * @see #setContentDescription(int)
         * @see #getContentDescription()
         */
        @NonNull
        public MyTabLayout.Tab setContentDescription(@Nullable CharSequence contentDesc) {
            this.contentDesc = contentDesc;
            updateView();
            return this;
        }

        /**
         * Gets a brief description of this tab's content for use in accessibility support.
         *
         * @return Description of this tab's content
         * @see #setContentDescription(CharSequence)
         * @see #setContentDescription(int)
         */
        @Nullable
        public CharSequence getContentDescription() {
            // This returns the view's content description instead of contentDesc because if the title
            // is used as a replacement for the content description, contentDesc will be empty.
            return (view == null) ? null : view.getContentDescription();
        }

        void updateView() {
            if (view != null) {
                view.update();
            }
        }

        void reset() {
            parent = null;
            view = null;
            tag = null;
            icon = null;
            text = null;
            contentDesc = null;
            position = INVALID_POSITION;
            customView = null;
        }
    }

    /** A {@link LinearLayout} containing {@link MyTabLayout.Tab} instances for use with {@link MyTabLayout}. */
    public final class TabView extends LinearLayout {
        private MyTabLayout.Tab tab;
        private TextView textView;
        private ImageView iconView;
        @Nullable private View badgeAnchorView;
        @Nullable private BadgeDrawable badgeDrawable;

        @Nullable private View customView;
        @Nullable private TextView customTextView;
        @Nullable private ImageView customIconView;
        @Nullable private Drawable baseBackgroundDrawable;

        private int defaultMaxLines = 2;

        public TabView(@NonNull Context context) {
            super(context);
            updateBackgroundDrawable(context);
            ViewCompat.setPaddingRelative(
                    this, tabPaddingStart, tabPaddingTop, tabPaddingEnd, tabPaddingBottom);
            setGravity(Gravity.CENTER);
            setOrientation(inlineLabel ? HORIZONTAL : VERTICAL);
            setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), PointerIconCompat.TYPE_HAND));
        }

        private void updateBackgroundDrawable(Context context) {
            if (tabBackgroundResId != 0) {
                baseBackgroundDrawable = AppCompatResources.getDrawable(context, tabBackgroundResId);
                if (baseBackgroundDrawable != null && baseBackgroundDrawable.isStateful()) {
                    baseBackgroundDrawable.setState(getDrawableState());
                }
            } else {
                baseBackgroundDrawable = null;
            }

            Drawable background;
            Drawable contentDrawable = new GradientDrawable();
            ((GradientDrawable) contentDrawable).setColor(Color.TRANSPARENT);

            if (tabRippleColorStateList != null) {
                GradientDrawable maskDrawable = new GradientDrawable();
                // TODO: Find a workaround for this. Currently on certain devices/versions,
                // LayerDrawable will draw a black background underneath any layer with a non-opaque color,
                // (e.g. ripple) unless we set the shape to be something that's not a perfect rectangle.
                maskDrawable.setCornerRadius(0.00001F);
                maskDrawable.setColor(Color.WHITE);

                @SuppressLint("RestrictedApi") ColorStateList rippleColor =
                        RippleUtils.convertToRippleDrawableColor(tabRippleColorStateList);

                // TODO: Add support to RippleUtils.compositeRippleColorStateList for different ripple color
                // for selected items vs non-selected items
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    background =
                            new RippleDrawable(
                                    rippleColor,
                                    unboundedRipple ? null : contentDrawable,
                                    unboundedRipple ? null : maskDrawable);
                } else {
                    Drawable rippleDrawable = DrawableCompat.wrap(maskDrawable);
                    DrawableCompat.setTintList(rippleDrawable, rippleColor);
                    background = new LayerDrawable(new Drawable[] {contentDrawable, rippleDrawable});
                }
            } else {
                background = contentDrawable;
            }
            ViewCompat.setBackground(this, background);
            MyTabLayout.this.invalidate();
        }

        /**
         * 将由tabBackground属性指定的背景可绘制对象绘制到提供的画布上。
         * 此方法会将背景绘制到此TabView的整个边界。
         * 我们提供了一种单独的方法来绘制此背景，而不仅仅是在TabView上设置此背景，以便我们可以控制何时绘制该背景。
         * 这使我们能够在TabLayout选择指示器下方绘制选项卡背景，然后在选择指示器顶部绘制TabLayout内容（图标+标签）
         *
         * @param canvas canvas to draw the background on
         */
        private void drawBackground(@NonNull Canvas canvas) {
            if (baseBackgroundDrawable != null) {
                baseBackgroundDrawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                baseBackgroundDrawable.draw(canvas);
            }
        }

        @Override
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            boolean changed = false;
            int[] state = getDrawableState();
            if (baseBackgroundDrawable != null && baseBackgroundDrawable.isStateful()) {
                changed |= baseBackgroundDrawable.setState(state);
            }

            if (changed) {
                invalidate();
                MyTabLayout.this.invalidate(); // Invalidate MyTabLayout, which draws mBaseBackgroundDrawable
            }
        }

        @Override
        public boolean performClick() {
            final boolean handled = super.performClick();

            if (tab != null) {
                if (!handled) {
                    playSoundEffect(SoundEffectConstants.CLICK);
                }
                tab.select();
                return true;
            } else {
                return handled;
            }
        }

        @Override
        public void setSelected(final boolean selected) {
            final boolean changed = isSelected() != selected;

            super.setSelected(selected);

            if (changed && selected && Build.VERSION.SDK_INT < 16) {
                // Pre-JB we need to manually send the TYPE_VIEW_SELECTED event
                sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
            }

            // Always dispatch this to the child views, regardless of whether the value has
            // changed
            if (textView != null) {
                textView.setSelected(selected);
            }
            if (iconView != null) {
                iconView.setSelected(selected);
            }
            if (customView != null) {
                customView.setSelected(selected);
            }
        }

        @Override
        public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(info);
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                CharSequence customContentDescription = getContentDescription();
                info.setContentDescription(
                        customContentDescription + ", " + badgeDrawable.getContentDescription());
            }
            AccessibilityNodeInfoCompat infoCompat = AccessibilityNodeInfoCompat.wrap(info);
            infoCompat.setCollectionItemInfo(
                    AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(
                            /* rowIndex= */ 0,
                            /* rowSpan= */ 1,
                            /* columnIndex= */ tab.getPosition(),
                            /* columnSpan= */ 1,
                            /* heading= */ false,
                            /* selected= */ isSelected()));
            if (isSelected()) {
                infoCompat.setClickable(false);
                infoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }
            infoCompat.setRoleDescription("Tab");
        }

        @Override
        public void onMeasure(final int origWidthMeasureSpec, final int origHeightMeasureSpec) {
            final int specWidthSize = MeasureSpec.getSize(origWidthMeasureSpec);
            final int specWidthMode = MeasureSpec.getMode(origWidthMeasureSpec);
            final int maxWidth = getTabMaxWidth();

            final int widthMeasureSpec;
            final int heightMeasureSpec = origHeightMeasureSpec;

            if (maxWidth > 0 && (specWidthMode == MeasureSpec.UNSPECIFIED || specWidthSize > maxWidth)) {
                // If we have a max width and a given spec which is either unspecified or
                // larger than the max width, update the width spec using the same mode
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(tabMaxWidth, MeasureSpec.AT_MOST);
            } else {
                // Else, use the original width spec
                widthMeasureSpec = origWidthMeasureSpec;
            }

            // Now lets measure
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // We need to switch the text size based on whether the text is spanning 2 lines or not
            if (textView != null) {
                float textSize = tabTextSize;
                int maxLines = defaultMaxLines;

                if (iconView != null && iconView.getVisibility() == VISIBLE) {
                    // If the icon view is being displayed, we limit the text to 1 line
                    maxLines = 1;
                } else if (textView != null && textView.getLineCount() > 1) {
                    // Otherwise when we have text which wraps we reduce the text size
                    textSize = tabTextMultiLineSize;
                }

                final float curTextSize = textView.getTextSize();
                final int curLineCount = textView.getLineCount();
                final int curMaxLines = TextViewCompat.getMaxLines(textView);

                if (textSize != curTextSize || (curMaxLines >= 0 && maxLines != curMaxLines)) {
                    // We've got a new text size and/or max lines...
                    boolean updateTextView = true;

                    if (mode == MODE_FIXED && textSize > curTextSize && curLineCount == 1) {
                        // If we're in fixed mode, going up in text size and currently have 1 line
                        // then it's very easy to get into an infinite recursion.
                        // To combat that we check to see if the change in text size
                        // will cause a line count change. If so, abort the size change and stick
                        // to the smaller size.
                        final Layout layout = textView.getLayout();
                        if (layout == null
                                || approximateLineWidth(layout, 0, textSize)
                                > getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) {
                            updateTextView = false;
                        }
                    }

                    if (updateTextView) {
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        textView.setMaxLines(maxLines);
                        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                    }
                }
            }
        }

        void setTab(@Nullable final MyTabLayout.Tab tab) {
            if (tab != this.tab) {
                this.tab = tab;
                update();
            }
        }

        void reset() {
            setTab(null);
            setSelected(false);
        }

        final void update() {
            final MyTabLayout.Tab tab = this.tab;
            final View custom = tab != null ? tab.getCustomView() : null;
            if (custom != null) {
                final ViewParent customParent = custom.getParent();
                if (customParent != this) {
                    if (customParent != null) {
                        ((ViewGroup) customParent).removeView(custom);
                    }
                    addView(custom);
                }
                customView = custom;
                if (this.textView != null) {
                    this.textView.setVisibility(GONE);
                }
                if (this.iconView != null) {
                    this.iconView.setVisibility(GONE);
                    this.iconView.setImageDrawable(null);
                }

                customTextView = custom.findViewById(android.R.id.text1);
                if (customTextView != null) {
                    defaultMaxLines = TextViewCompat.getMaxLines(customTextView);
                }
                customIconView = custom.findViewById(android.R.id.icon);
            } else {
                // We do not have a custom view. Remove one if it already exists
                if (customView != null) {
                    removeView(customView);
                    customView = null;
                }
                customTextView = null;
                customIconView = null;
            }

            if (customView == null) {
                // If there isn't a custom view, we'll us our own in-built layouts
                if (this.iconView == null) {
                    inflateAndAddDefaultIconView();
                }
                final Drawable icon =
                        (tab != null && tab.getIcon() != null)
                                ? DrawableCompat.wrap(tab.getIcon()).mutate()
                                : null;
                if (icon != null) {
                    DrawableCompat.setTintList(icon, tabIconTint);
                    if (tabIconTintMode != null) {
                        DrawableCompat.setTintMode(icon, tabIconTintMode);
                    }
                }

                if (this.textView == null) {
                    inflateAndAddDefaultTextView();
                    defaultMaxLines = TextViewCompat.getMaxLines(this.textView);
                }
                TextViewCompat.setTextAppearance(this.textView, tabTextAppearance);
                if (tabTextColors != null) {
                    this.textView.setTextColor(tabTextColors);
                }
                updateTextAndIcon(this.textView, this.iconView);

                tryUpdateBadgeAnchor();
                addOnLayoutChangeListener(iconView);
                addOnLayoutChangeListener(textView);
            } else {
                // Else, we'll see if there is a TextView or ImageView present and update them
                if (customTextView != null || customIconView != null) {
                    updateTextAndIcon(customTextView, customIconView);
                }
            }

            if (tab != null && !TextUtils.isEmpty(tab.contentDesc)) {
                // Only update the TabView's content description from Tab if the Tab's content description
                // has been explicitly set.
                setContentDescription(tab.contentDesc);
            }
            // Finally update our selected state
            setSelected(tab != null && tab.isSelected());
        }

        private void inflateAndAddDefaultIconView() {
            ViewGroup iconViewParent = this;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                iconViewParent = createPreApi18BadgeAnchorRoot();
                addView(iconViewParent, 0);
            }
            this.iconView =
                    (ImageView)
                            LayoutInflater.from(getContext())
                                    .inflate(R.layout.design_layout_tab_icon, iconViewParent, false);
            iconViewParent.addView(iconView, 0);
        }

        private void inflateAndAddDefaultTextView() {
            ViewGroup textViewParent = this;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                textViewParent = createPreApi18BadgeAnchorRoot();
                addView(textViewParent);
            }
            this.textView =
                    (TextView)
                            LayoutInflater.from(getContext())
                                    .inflate(R.layout.design_layout_tab_text, textViewParent, false);
            textViewParent.addView(textView);
        }

        @NonNull
        private FrameLayout createPreApi18BadgeAnchorRoot() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            FrameLayout.LayoutParams layoutparams =
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            frameLayout.setLayoutParams(layoutparams);
            return frameLayout;
        }

        /**
         * Creates an instance of {@link BadgeDrawable} if none exists. Initializes (if needed) and
         * returns the associated instance of {@link BadgeDrawable}.
         *
         * @return an instance of BadgeDrawable associated with {@code Tab}.
         */
        @NonNull
        private BadgeDrawable getOrCreateBadge() {
            // Creates a new instance if one is not already initialized for this TabView.
            if (badgeDrawable == null) {
                badgeDrawable = BadgeDrawable.create(getContext());
            }
            tryUpdateBadgeAnchor();
            if (badgeDrawable == null) {
                throw new IllegalStateException("Unable to create badge");
            }
            return badgeDrawable;
        }

        @Nullable
        private BadgeDrawable getBadge() {
            return badgeDrawable;
        }

        private void removeBadge() {
            if (badgeAnchorView != null) {
                tryRemoveBadgeFromAnchor();
            }
            badgeDrawable = null;
        }

        private void addOnLayoutChangeListener(@Nullable final View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(
                    new OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(
                                View v,
                                int left,
                                int top,
                                int right,
                                int bottom,
                                int oldLeft,
                                int oldTop,
                                int oldRight,
                                int oldBottom) {
                            if (view.getVisibility() == VISIBLE) {
                                tryUpdateBadgeDrawableBounds(view);
                            }
                        }
                    });
        }

        private void tryUpdateBadgeAnchor() {
            if (!hasBadgeDrawable()) {
                return;
            }
            if (customView != null) {
                // TODO(b/123406505): Support badging on custom tab views.
                tryRemoveBadgeFromAnchor();
            } else {
                if (iconView != null && tab != null && tab.getIcon() != null) {
                    if (badgeAnchorView != iconView) {
                        tryRemoveBadgeFromAnchor();
                        // Anchor badge to icon.
                        tryAttachBadgeToAnchor(iconView);
                    } else {
                        tryUpdateBadgeDrawableBounds(iconView);
                    }
                } else if (textView != null
                        && tab != null
                        && tab.getTabLabelVisibility() == TAB_LABEL_VISIBILITY_LABELED) {
                    if (badgeAnchorView != textView) {
                        tryRemoveBadgeFromAnchor();
                        // Anchor badge to label.
                        tryAttachBadgeToAnchor(textView);
                    } else {
                        tryUpdateBadgeDrawableBounds(textView);
                    }
                } else {
                    tryRemoveBadgeFromAnchor();
                }
            }
        }

        @SuppressLint("RestrictedApi")
        private void tryAttachBadgeToAnchor(@Nullable View anchorView) {
            if (!hasBadgeDrawable()) {
                return;
            }
            if (anchorView != null) {
                clipViewToPaddingForBadge(false);
                BadgeUtils.attachBadgeDrawable(
                        badgeDrawable, anchorView, getCustomParentForBadge(anchorView));
                badgeAnchorView = anchorView;
            }
        }

        @SuppressLint("RestrictedApi")
        private void tryRemoveBadgeFromAnchor() {
            if (!hasBadgeDrawable()) {
                return;
            }
            clipViewToPaddingForBadge(true);
            if (badgeAnchorView != null) {
                BadgeUtils.detachBadgeDrawable(
                        badgeDrawable, badgeAnchorView, getCustomParentForBadge(badgeAnchorView));
                badgeAnchorView = null;
            }
        }

        private void clipViewToPaddingForBadge(boolean flag) {
            // Avoid clipping a badge if it's displayed.
            // Clip children / view to padding when no badge is displayed.
            setClipChildren(flag);
            setClipToPadding(flag);
            ViewGroup parent = (ViewGroup) getParent();
            if (parent != null) {
                parent.setClipChildren(flag);
                parent.setClipToPadding(flag);
            }
        }

        final void updateOrientation() {
            setOrientation(inlineLabel ? HORIZONTAL : VERTICAL);
            if (customTextView != null || customIconView != null) {
                updateTextAndIcon(customTextView, customIconView);
            } else {
                updateTextAndIcon(textView, iconView);
            }
        }

        @SuppressLint("RestrictedApi")
        private void updateTextAndIcon(
                @Nullable final TextView textView, @Nullable final ImageView iconView) {
            final Drawable icon =
                    (tab != null && tab.getIcon() != null)
                            ? DrawableCompat.wrap(tab.getIcon()).mutate()
                            : null;
            final CharSequence text = tab != null ? tab.getText() : null;

            if (iconView != null) {
                if (icon != null) {
                    iconView.setImageDrawable(icon);
                    iconView.setVisibility(VISIBLE);
                    setVisibility(VISIBLE);
                } else {
                    iconView.setVisibility(GONE);
                    iconView.setImageDrawable(null);
                }
            }

            final boolean hasText = !TextUtils.isEmpty(text);
            if (textView != null) {
                if (hasText) {
                    textView.setText(text);
                    if (tab.labelVisibilityMode == TAB_LABEL_VISIBILITY_LABELED) {
                        textView.setVisibility(VISIBLE);
                    } else {
                        textView.setVisibility(GONE);
                    }
                    setVisibility(VISIBLE);
                } else {
                    textView.setVisibility(GONE);
                    textView.setText(null);
                }
            }

            if (iconView != null) {
                MarginLayoutParams lp = ((MarginLayoutParams) iconView.getLayoutParams());
                int iconMargin = 0;
                if (hasText && iconView.getVisibility() == VISIBLE) {
                    // If we're showing both text and icon, add some margin bottom to the icon
                    iconMargin = (int) ViewUtils.dpToPx(getContext(), DEFAULT_GAP_TEXT_ICON);
                }
                if (inlineLabel) {
                    if (iconMargin != MarginLayoutParamsCompat.getMarginEnd(lp)) {
                        MarginLayoutParamsCompat.setMarginEnd(lp, iconMargin);
                        lp.bottomMargin = 0;
                        // Calls resolveLayoutParams(), necessary for layout direction
                        iconView.setLayoutParams(lp);
                        iconView.requestLayout();
                    }
                } else {
                    if (iconMargin != lp.bottomMargin) {
                        lp.bottomMargin = iconMargin;
                        MarginLayoutParamsCompat.setMarginEnd(lp, 0);
                        // Calls resolveLayoutParams(), necessary for layout direction
                        iconView.setLayoutParams(lp);
                        iconView.requestLayout();
                    }
                }
            }

            final CharSequence contentDesc = tab != null ? tab.contentDesc : null;
            TooltipCompat.setTooltipText(this, hasText ? null : contentDesc);
        }

        @SuppressLint("RestrictedApi")
        private void tryUpdateBadgeDrawableBounds(@NonNull View anchor) {
            // Check that this view is the badge's current anchor view.
            if (hasBadgeDrawable() && anchor == badgeAnchorView) {
                BadgeUtils.setBadgeDrawableBounds(badgeDrawable, anchor, getCustomParentForBadge(anchor));
            }
        }

        private boolean hasBadgeDrawable() {
            return badgeDrawable != null;
        }

        @Nullable
        private FrameLayout getCustomParentForBadge(@NonNull View anchor) {
            if (anchor != iconView && anchor != textView) {
                return null;
            }
            return BadgeUtils.USE_COMPAT_PARENT ? ((FrameLayout) anchor.getParent()) : null;
        }

        /**
         * Calculates the width of the TabView's content.
         *
         * @return Width of the tab label, if present, or the width of the tab icon, if present. If tabs
         *     is in inline mode, returns the sum of both the icon and tab label widths.
         */
        private int getContentWidth() {
            boolean initialized = false;
            int left = 0;
            int right = 0;

            for (View view : new View[] {textView, iconView, customView}) {
                if (view != null && view.getVisibility() == View.VISIBLE) {
                    left = initialized ? Math.min(left, view.getLeft()) : view.getLeft();
                    right = initialized ? Math.max(right, view.getRight()) : view.getRight();
                    initialized = true;
                }
            }

            return right - left;
        }

        @Nullable
        public MyTabLayout.Tab getTab() {
            return tab;
        }

        /** Approximates a given lines width with the new provided text size. */
        private float approximateLineWidth(@NonNull Layout layout, int line, float textSize) {
            return layout.getLineWidth(line) * (textSize / layout.getPaint().getTextSize());
        }
    }

    class SlidingTabIndicator extends LinearLayout {
        private int selectedIndicatorHeight;
        @NonNull private final Paint selectedIndicatorPaint;
        @NonNull private final GradientDrawable defaultSelectionIndicator;

        int selectedPosition = -1;
        float selectionOffset;

        private int layoutDirection = -1;

        int indicatorLeft = -1;
        int indicatorRight = -1;

        ValueAnimator indicatorAnimator;
        private int animationStartLeft = -1;
        private int animationStartRight = -1;

        SlidingTabIndicator(Context context) {
            super(context);
            setWillNotDraw(false);
            selectedIndicatorPaint = new Paint();
            defaultSelectionIndicator = new GradientDrawable();
        }

        void setSelectedIndicatorColor(int color) {
            if (selectedIndicatorPaint.getColor() != color) {
                selectedIndicatorPaint.setColor(color);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void setSelectedIndicatorHeight(int height) {
            if (selectedIndicatorHeight != height) {
                selectedIndicatorHeight = height;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        boolean childrenNeedLayout() {
            for (int i = 0, z = getChildCount(); i < z; i++) {
                final View child = getChildAt(i);
                if (child.getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void setIndicatorPositionFromTabPosition(int position, float positionOffset) {
            if (indicatorAnimator != null && indicatorAnimator.isRunning()) {
                indicatorAnimator.cancel();
            }

            selectedPosition = position;
            selectionOffset = positionOffset;
            updateIndicatorPosition();
        }

        float getIndicatorPosition() {
            return selectedPosition + selectionOffset;
        }

        @Override
        public void onRtlPropertiesChanged(int layoutDirection) {
            super.onRtlPropertiesChanged(layoutDirection);

            // Android M之前的错误的变通办法，在该错误中，当布局方向更改时，LinearLayout不会自行重新布局
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (this.layoutDirection != layoutDirection) {
                    requestLayout();
                    this.layoutDirection = layoutDirection;
                }
            }
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
                // HorizontalScrollView will first measure use with UNSPECIFIED, and then with
                // EXACTLY. Ignore the first call since anything we do will be overwritten anyway
                // HorizontalScrollView将首先使用Google评估，然后使用EXACTLY进行评估。 忽略第一个电话，因为我们所做的任何事情都会被覆盖
                return;
            }

            // GRAVITY_CENTER will make all tabs the same width as the largest tab, and center them in the
            // SlidingTabIndicator's width (with a "gutter" of padding on either side). If the Tabs do not
            // fit in the SlidingTabIndicator, then fall back to GRAVITY_FILL behavior.
            if ((tabGravity == GRAVITY_CENTER) || mode == MODE_AUTO) {
                final int count = getChildCount();

                // 首先，我们将找到最宽的标签
                int largestTabWidth = 0;
                for (int i = 0, z = count; i < z; i++) {
                    View child = getChildAt(i);
                    if (child.getVisibility() == VISIBLE) {
                        largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
                    }
                }

                if (largestTabWidth <= 0) {
                    // If we don't have a largest child yet, skip until the next measure pass
                    return;
                }

                @SuppressLint("RestrictedApi") final int gutter = (int) ViewUtils.dpToPx(getContext(), FIXED_WRAP_GUTTER_MIN);
                boolean remeasure = false;

                if (largestTabWidth * count <= getMeasuredWidth() - gutter * 2) {
                    // If the tabs fit within our width minus gutters, we will set all tabs to have
                    // the same width
                    for (int i = 0; i < count; i++) {
                        final LinearLayout.LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
                        if (lp.width != largestTabWidth || lp.weight != 0) {
                            lp.width = largestTabWidth;
                            lp.weight = 0;
                            remeasure = true;
                        }
                    }
                } else {
                    // If the tabs will wrap to be larger than the width minus gutters, we need
                    // to switch to GRAVITY_FILL.
                    // TODO (b/129799806): This overrides the user TabGravity setting.
                    tabGravity = GRAVITY_FILL;
                    updateTabViews(false);
                    remeasure = true;
                }

                if (remeasure) {
                    // Now re-measure after our changes
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                }
            }
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);

            if (indicatorAnimator != null && indicatorAnimator.isRunning()) {
                // It's possible that the tabs' layout is modified while the indicator is animating (ex. a
                // new tab is added, or a tab is removed in onTabSelected). This would change the target end
                // position of the indicator, since the tab widths are different. We need to modify the
                // animation's updateListener to pick up the new target positions.
                updateOrRecreateIndicatorAnimation(
                        /* recreateAnimation= */ false, selectedPosition, /* duration= */ -1);
            } else {
                // If we've been layed out, update the indicator position
                updateIndicatorPosition();
            }
        }

        private void updateIndicatorPosition() {
            final View selectedTitle = getChildAt(selectedPosition);
            int left;
            int right;

            if (selectedTitle != null && selectedTitle.getWidth() > 0) {
                left = selectedTitle.getLeft();
                right = selectedTitle.getRight();

                if (!tabIndicatorFullWidth && selectedTitle instanceof MyTabLayout.TabView) {
                    calculateTabViewContentBounds((MyTabLayout.TabView) selectedTitle, tabViewContentBounds);
                    left = (int) tabViewContentBounds.left;
                    right = (int) tabViewContentBounds.right;
                }

                if (selectionOffset > 0f && selectedPosition < getChildCount() - 1) {
                    // Draw the selection partway between the tabs
                    View nextTitle = getChildAt(selectedPosition + 1);
                    int nextTitleLeft = nextTitle.getLeft();
                    int nextTitleRight = nextTitle.getRight();

                    if (!tabIndicatorFullWidth && nextTitle instanceof MyTabLayout.TabView) {
                        calculateTabViewContentBounds((MyTabLayout.TabView) nextTitle, tabViewContentBounds);
                        nextTitleLeft = (int) tabViewContentBounds.left;
                        nextTitleRight = (int) tabViewContentBounds.right;
                    }

                    left = (int) (selectionOffset * nextTitleLeft + (1.0f - selectionOffset) * left);
                    right = (int) (selectionOffset * nextTitleRight + (1.0f - selectionOffset) * right);
                }

            } else {
                left = right = -1;
            }

            setIndicatorPosition(left, right);
        }

        void setIndicatorPosition(int left, int right) {
            if (left != indicatorLeft || right != indicatorRight) {
                // If the indicator's left/right has changed, invalidate
                indicatorLeft = left;
                indicatorRight = right;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        void animateIndicatorToPosition(final int position, int duration) {
            if (indicatorAnimator != null && indicatorAnimator.isRunning()) {
                indicatorAnimator.cancel();
            }

            updateOrRecreateIndicatorAnimation(/* recreateAnimation= */ true, position, duration);
        }

        private void updateOrRecreateIndicatorAnimation(
                boolean recreateAnimation, final int position, int duration) {
            final View targetView = getChildAt(position);
            if (targetView == null) {
                // If we don't have a view, just update the position now and return
                updateIndicatorPosition();
                return;
            }

            int targetLeft = targetView.getLeft();
            int targetRight = targetView.getRight();

            if (!tabIndicatorFullWidth && targetView instanceof MyTabLayout.TabView) {
                calculateTabViewContentBounds((MyTabLayout.TabView) targetView, tabViewContentBounds);
                targetLeft = (int) tabViewContentBounds.left;
                targetRight = (int) tabViewContentBounds.right;
            }

            // Where we want the indicator to end up after the animation finishes.
            final int finalTargetLeft = targetLeft;
            final int finalTargetRight = targetRight;

            // Where the indicator is currently.
            final int startLeft = indicatorLeft;
            final int startRight = indicatorRight;

            // If we're already at the target position, do nothing.
            if (startLeft == finalTargetLeft && startRight == finalTargetRight) {
                return;
            }

            // If we're going to recreate the animation, then we need to update our start positions. If
            // we're not recreating, we reuse the start positions from the original animation.
            if (recreateAnimation) {
                animationStartLeft = startLeft;
                animationStartRight = startRight;
            }

            // Create the update listener with the new target indicator positions. If we're not recreating
            // then animationStartLeft/Right will be the same as when the previous animator was created.
            ValueAnimator.AnimatorUpdateListener updateListener =
                    new ValueAnimator.AnimatorUpdateListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                            final float fraction = valueAnimator.getAnimatedFraction();
                            setIndicatorPosition(
                                    AnimationUtils.lerp(animationStartLeft, finalTargetLeft, fraction),
                                    AnimationUtils.lerp(animationStartRight, finalTargetRight, fraction));
                        }
                    };

            if (recreateAnimation) {
                // Create & start a new indicatorAnimator.
                ValueAnimator animator = indicatorAnimator = new ValueAnimator();
                animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                animator.setDuration(duration);
                animator.setFloatValues(0, 1);
                animator.addUpdateListener(updateListener);
                animator.addListener(
                        new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animator) {
                                selectedPosition = position;
                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                selectedPosition = position;
                                selectionOffset = 0f;
                            }
                        });
                animator.start();
            } else {
                // Reuse the existing animator. Updating the listener only modifies the target positions.
                indicatorAnimator.removeAllUpdateListeners();
                indicatorAnimator.addUpdateListener(updateListener);
            }
        }

        /**
         * Given a {@link MyTabLayout.TabView}, calculate the left and right bounds of its content.
         *
         * <p>If only text label is present, calculates the width of the text label. If only icon is
         * present, calculates the width of the icon. If both are present, the text label bounds take
         * precedence. If both are present and inline mode is enabled, the sum of the bounds of the both
         * the text label and icon are calculated. If neither are present or if the calculated
         * difference between the left and right bounds is less than 24dp, then left and right bounds
         * are adjusted such that the difference between them is equal to 24dp.
         *
         * @param tabView {@link MyTabLayout.TabView} for which to calculate left and right content bounds.
         */
        private void calculateTabViewContentBounds(
                @NonNull MyTabLayout.TabView tabView, @NonNull RectF contentBounds) {
            int tabViewContentWidth = tabView.getContentWidth();
            @SuppressLint("RestrictedApi") int minIndicatorWidth = (int) ViewUtils.dpToPx(getContext(), MIN_INDICATOR_WIDTH);

            if (tabViewContentWidth < minIndicatorWidth) {
                tabViewContentWidth = minIndicatorWidth;
            }

            int tabViewCenter = (tabView.getLeft() + tabView.getRight()) / 2;
            int contentLeftBounds = tabViewCenter - (tabViewContentWidth / 2);
            int contentRightBounds = tabViewCenter + (tabViewContentWidth / 2);

            contentBounds.set(contentLeftBounds, 0, contentRightBounds, 0);
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            int indicatorHeight = 0;
            if (tabSelectedIndicator != null) {
                indicatorHeight = tabSelectedIndicator.getIntrinsicHeight();
            }
            if (selectedIndicatorHeight >= 0) {
                indicatorHeight = selectedIndicatorHeight;
            }

            int indicatorTop = 0;
            int indicatorBottom = 0;

            switch (tabIndicatorGravity) {
                case INDICATOR_GRAVITY_BOTTOM:
                    indicatorTop = getHeight() - indicatorHeight;
                    indicatorBottom = getHeight();
                    break;
                case INDICATOR_GRAVITY_CENTER:
                    indicatorTop = (getHeight() - indicatorHeight) / 2;
                    indicatorBottom = (getHeight() + indicatorHeight) / 2;
                    break;
                case INDICATOR_GRAVITY_TOP:
                    indicatorTop = 0;
                    indicatorBottom = indicatorHeight;
                    break;
                case INDICATOR_GRAVITY_STRETCH:
                    indicatorTop = 0;
                    indicatorBottom = getHeight();
                    break;
                default:
                    break;
            }

            // Draw the selection indicator on top of tab item backgrounds
            if (indicatorLeft >= 0 && indicatorRight > indicatorLeft) {
                Drawable selectedIndicator;
                selectedIndicator =
                        DrawableCompat.wrap(
                                tabSelectedIndicator != null ? tabSelectedIndicator : defaultSelectionIndicator)
                                .mutate();
                selectedIndicator.setBounds(indicatorLeft, indicatorTop, indicatorRight, indicatorBottom);
                if (selectedIndicatorPaint != null) {
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                        // Drawable doesn't implement setTint in API 21
                        selectedIndicator.setColorFilter(
                                selectedIndicatorPaint.getColor(), PorterDuff.Mode.SRC_IN);
                    } else {
                        DrawableCompat.setTint(selectedIndicator, selectedIndicatorPaint.getColor());
                    }
                }
                selectedIndicator.draw(canvas);
            }

            // Draw the tab item contents (icon and label) on top of the background + indicator layers
            super.draw(canvas);
        }
    }

    @NonNull
    private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];
        int i = 0;

        states[i] = SELECTED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        i++;

        return new ColorStateList(states, colors);
    }

    @Dimension(unit = Dimension.DP)
    private int getDefaultHeight() {
        boolean hasIconAndText = false;
        for (int i = 0, count = tabs.size(); i < count; i++) {
            MyTabLayout.Tab tab = tabs.get(i);
            if (tab != null && tab.getIcon() != null && !TextUtils.isEmpty(tab.getText())) {
                hasIconAndText = true;
                break;
            }
        }
        return (hasIconAndText && !inlineLabel) ? DEFAULT_HEIGHT_WITH_TEXT_ICON : DEFAULT_HEIGHT;
    }

    private int getTabMinWidth() {
        if (requestedTabMinWidth != INVALID_WIDTH) {
            // If we have been given a min width, use it
            return requestedTabMinWidth;
        }
        // Else, we'll use the default value
        return (mode == MODE_SCROLLABLE || mode == MODE_AUTO) ? scrollableTabMinWidth : 0;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        // We don't care about the layout params of any views added to us, since we don't actually
        // add them. The only view we add is the SlidingTabStrip, which is done manually.
        // We return the default layout params so that we don't blow up if we're given a TabItem
        // without android:layout_* values.
        return generateDefaultLayoutParams();
    }

    int getTabMaxWidth() {
        return tabMaxWidth;
    }

    /**
     * A {@link ViewPager.OnPageChangeListener} class which contains the necessary calls back to the
     * provided {@link MyTabLayout} so that the tab position is kept in sync.
     *
     * <p>This class stores the provided TabLayout weakly, meaning that you can use {@link
     * ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)
     * addOnPageChangeListener(OnPageChangeListener)} without removing the listener and not cause a
     * leak.
     */
    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @NonNull private final WeakReference<MyTabLayout> tabLayoutRef;
        private int previousScrollState;
        private int scrollState;

        public TabLayoutOnPageChangeListener(MyTabLayout tabLayout) {
            tabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
            previousScrollState = scrollState;
            scrollState = state;
        }

        @Override
        public void onPageScrolled(
                final int position, final float positionOffset, final int positionOffsetPixels) {
            final MyTabLayout tabLayout = tabLayoutRef.get();
            if (tabLayout != null) {
                // Only update the text selection if we're not settling, or we are settling after
                // being dragged
                final boolean updateText =
                        scrollState != SCROLL_STATE_SETTLING || previousScrollState == SCROLL_STATE_DRAGGING;
                // Update the indicator if we're not settling after being idle. This is caused
                // from a setCurrentItem() call and will be handled by an animation from
                // onPageSelected() instead.
                final boolean updateIndicator =
                        !(scrollState == SCROLL_STATE_SETTLING && previousScrollState == SCROLL_STATE_IDLE);
                tabLayout.setScrollPosition(position, positionOffset, updateText, updateIndicator);
            }
        }

        @Override
        public void onPageSelected(final int position) {
            final MyTabLayout tabLayout = tabLayoutRef.get();
            if (tabLayout != null
                    && tabLayout.getSelectedTabPosition() != position
                    && position < tabLayout.getTabCount()) {
                // Select the tab, only updating the indicator if we're not being dragged/settled
                // (since onPageScrolled will handle that).
                final boolean updateIndicator =
                        scrollState == SCROLL_STATE_IDLE
                                || (scrollState == SCROLL_STATE_SETTLING
                                && previousScrollState == SCROLL_STATE_IDLE);
                tabLayout.selectTab(tabLayout.getTabAt(position), updateIndicator);
            }
        }

        void reset() {
            previousScrollState = scrollState = SCROLL_STATE_IDLE;
        }
    }

    /**
     * A {@link MyTabLayout.OnTabSelectedListener} class which contains the necessary calls back to the
     * provided {@link ViewPager} so that the tab position is kept in sync.
     */
    public static class ViewPagerOnTabSelectedListener implements MyTabLayout.OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onTabSelected(@NonNull MyTabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(MyTabLayout.Tab tab) {
            // No-op
        }

        @Override
        public void onTabReselected(MyTabLayout.Tab tab) {
            // No-op
        }
    }

    private class PagerAdapterObserver extends DataSetObserver {
        PagerAdapterObserver() {}

        @Override
        public void onChanged() {
            populateFromPagerAdapter();
        }

        @Override
        public void onInvalidated() {
            populateFromPagerAdapter();
        }
    }

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        private boolean autoRefresh;

        AdapterChangeListener() {}

        @Override
        public void onAdapterChanged(
                @NonNull ViewPager viewPager,
                @Nullable PagerAdapter oldAdapter,
                @Nullable PagerAdapter newAdapter) {
            if (MyTabLayout.this.viewPager == viewPager) {
                setPagerAdapter(newAdapter, autoRefresh);
            }
        }

        void setAutoRefresh(boolean autoRefresh) {
            this.autoRefresh = autoRefresh;
        }
    }


}
