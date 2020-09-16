package com.meis.base.mei.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.meis.base.R;
import com.meis.base.mei.MeiCompatActivity;
import com.meis.base.mei.dialog.MeiCompatDialog;
import com.meis.base.mei.fragment.MeiCompatFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * desc:
 * author: ws
 * date: 2018/4/19.
 */

public abstract class BaseFragment extends MeiCompatFragment implements ISupportFragment {

    final SupportFragmentDelegate mDelegate = new SupportFragmentDelegate(this);

    protected FragmentActivity mActivity;

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    public boolean isRegisterEventBus() {
        return false;
    }

    //传递过来的参数Bundle，供子类使用
    protected Bundle args;

    /**
     * 创建fragment的静态方法，方便传递参数
     *
     * @param args 传递的参数
     * @return
     */
    public static <T extends Fragment> T newInstance(Class clazz, Bundle args) {
        T mFragment = null;
        try {
            mFragment = (T) clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mDelegate.onAttach(activity);
        mActivity = mDelegate.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return mDelegate.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    public void onDestroyView() {
        mDelegate.onDestroyView();
        super.onDestroyView();
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mDelegate.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mDelegate.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     *
     * @deprecated Use {@link #post(Runnable)} instead.
     */
    @Override
    public void enqueueAction(Runnable runnable) {
        mDelegate.enqueueAction(runnable);
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    /**
     * Called when the enter-animation end.
     * 入栈动画 结束时,回调
     */
    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        mDelegate.onEnterAnimationEnd(savedInstanceState);
    }


    /**
     * Lazy initial，Called when fragment is first called.
     * <p>
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mDelegate.onLazyInitView(savedInstanceState);
    }

    /**
     * Called when the fragment is visible.
     * 当Fragment对用户可见时回调
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    @Override
    public void onSupportVisible() {
        mDelegate.onSupportVisible();
    }

    /**
     * Called when the fragment is invivible.
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    @Override
    public void onSupportInvisible() {
        mDelegate.onSupportInvisible();
    }

    /**
     * Return true if the fragment has been supportVisible.
     */
    @Override
    final public boolean isSupportVisible() {
        return mDelegate.isSupportVisible();
    }

    /**
     * Set fragment animation with a higher priority than the ISupportActivity
     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * 按返回键触发,前提是SupportActivity的onBackPressed()方法能被调用
     *
     * @return false则继续向上传递, true则消费掉该事件
     */
    @Override
    public boolean onBackPressedSupport() {
        return mDelegate.onBackPressedSupport();
    }

    /**
     * 类似 {@link Activity# setResult(int, Intent)}
     * <p>
     * Similar to {@link Activity# setResult(int, Intent)}
     *
     * @see # startForResult(ISupportFragment, int)
     */
    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        mDelegate.setFragmentResult(resultCode, bundle);
    }

    /**
     * 类似  {@link Activity# onActivityResult(int, int, Intent)}
     * <p>
     * Similar to {@link Activity# onActivityResult(int, int, Intent)}
     *
     * @see # startForResult(ISupportFragment, int)
     */
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        mDelegate.onFragmentResult(requestCode, resultCode, data);
    }

    /**
     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
     * 类似 {@link Activity# onNewIntent(Intent)}
     * <p>
     * Similar to {@link Activity# onNewIntent(Intent)}
     *
     * @param args putNewBundle(Bundle newBundle)
     * @see # start(ISupportFragment, int)
     */
    @Override
    public void onNewBundle(Bundle args) {
        mDelegate.onNewBundle(args);
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see # start(ISupportFragment, int)
     */
    @Override
    public void putNewBundle(Bundle newBundle) {
        mDelegate.putNewBundle(newBundle);
    }


    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput() {
        mDelegate.hideSoftInput();
    }

    /**
     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
     */
    protected void showSoftInput(final View view) {
        mDelegate.showSoftInput(view);
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(ISupportFragment toFragment) {
        if (mActivity != null && mActivity instanceof MeiCompatActivity) {
            if (((MeiCompatActivity) mActivity).canStatusHelper()) {
                mDelegate.loadRootFragment(R.id.base_main, toFragment);
            } else {
                throw new RuntimeException("method canStatusHelper can not return false ");
            }
        } else {
            throw new RuntimeException("mActivity must extends CompatActivity");
        }
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean
            addToBackStack, boolean allowAnim) {
        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment...
            toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    public void startForResult(ISupportFragment toFragment, int requestCode) {
        mDelegate.startForResult(toFragment, requestCode);
    }

    /**
     * Start the target Fragment and pop itself
     */
    public void startWithPop(ISupportFragment toFragment) {
        mDelegate.startWithPop(toFragment);
    }

    /**
     * @see #popTo(Class, boolean)
     * +
     * @see #start(ISupportFragment)
     */
    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean
            includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }


    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        mDelegate.replaceFragment(toFragment, addToBackStack);
    }

    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the child fragment.
     */
    public void popChild() {
        mDelegate.popChild();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     * <p>
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable
            afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable
            afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable,
                popAnim);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable
            afterPopTransactionRunnable) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment,
                afterPopTransactionRunnable);
    }

    public void popToChild(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable
            afterPopTransactionRunnable, int popAnim) {
        mDelegate.popToChild(targetFragmentClass, includeTargetFragment,
                afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getFragmentManager());
    }

    public ISupportFragment getTopChildFragment() {
        return SupportHelper.getTopFragment(getChildFragmentManager());
    }

    /**
     * @return 位于当前Fragment的前一个Fragment
     */
    public ISupportFragment getPreFragment() {
        return SupportHelper.getPreFragment(this);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getFragmentManager(), fragmentClass);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
    }

    public void setToolBarCenterTitle(String title) {
        View centerTitle = getToolbarView().findViewById(R.id.tv_center_title);
        if (null != centerTitle && centerTitle instanceof TextView) {
            centerTitle.setVisibility(View.VISIBLE);
            ((TextView) centerTitle).setText(title);
        }
    }

    public void autoFillToolBarLeftIcon() {
        getToolbarView().setNavigationIcon(R.mipmap.ic_white_back);
        getToolbarView().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    public void removeFragment(Class fragmentClss) {
        Fragment fragment = getChildFragmentManager().findFragmentByTag(fragmentClss.getName());
        if (fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }

    public void hideFragment(int fragmentId) {
        Fragment fragment = getChildFragmentManager().findFragmentById(fragmentId);
        if (fragment != null) {
            hideFragment(fragment);
        }
    }

    public void hideFragment(Fragment fragment) {
        if (fragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .hide(fragment).commitAllowingStateLoss();
        }
    }

    public void showFragment(int container, Fragment fragment) {
        showFragment(container, fragment, false);
    }

    public void showFragment(int container, Fragment fragment, boolean hideOther) {
        if (!isAdded()) return;
        if (fragment.isAdded()) {
            getChildFragmentManager().beginTransaction()
                    .show(fragment).commitAllowingStateLoss();
        } else {
            getChildFragmentManager().beginTransaction()
                    .add(container, fragment, fragment.getClass().getName()).commitAllowingStateLoss();
        }
        if (hideOther) {
            @SuppressLint("RestrictedApi")
            List<Fragment> fragmentList = getChildFragmentManager().getFragments();
            if (fragmentList == null) return;
            for (int i = fragmentList.size() - 1; i >= 0; i--) {
                Fragment frag = fragmentList.get(i);
                if (frag != null && !frag.getClass().getName().equals(fragment.getClass().getName())) {
                    Object obj = frag.getTag();
                    if (obj == null || !obj.toString().equals("publish"))
                        hideFragment(frag);
                }
            }
        }
    }

    public boolean isAddedFragment(String name) {
        @SuppressLint("RestrictedApi")
        List<Fragment> fragmentList = getChildFragmentManager().getFragments();
        if (fragmentList == null || fragmentList.isEmpty()) return false;
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            if (fragmentList.get(i) != null && fragmentList.get(i).getClass() != null && fragmentList.get(i).getClass
                    ().getSimpleName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param baseDialog
     */
    public void showDialog(MeiCompatDialog baseDialog) {
        getActivity().getSupportFragmentManager().beginTransaction().add(baseDialog, "dialog_" + baseDialog.getClass
                ().getSimpleName()).commitAllowingStateLoss();
    }

    public String getNonEmpty(String ch) {
        return TextUtils.isEmpty(ch) ? "" : ch;
    }

    @Override
    public int getResLayoutId() {
        return getLayoutId();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();
}