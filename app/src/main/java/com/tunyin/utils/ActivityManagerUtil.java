package com.tunyin.utils;

import android.app.Activity;

import java.util.Iterator;
import java.util.Stack;

/**
 * public class BaseActivity extends AppCompatActivity {
 * private ActivityManagerUtil activityManagerUtil;
 * protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_base);
 * activityManagerUtil = ActivityManagerUtil.getInstance();
 * activityManagerUtil.pushOneActivity(this);
 * }
 * protected void onDestroy() {
 * super.onDestroy();
 * activityManagerUtil.popOneActivity(this);
 * }
 * }
 * Created by WuXiaolong
 * on 2016/5/19.
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class ActivityManagerUtil {
    //activity栈
    private Stack<Activity> activityStack = new Stack<>();

    private ActivityManagerUtil() {
    }

    private static class SingletonHolder {
        private static final ActivityManagerUtil INSTANCE = new ActivityManagerUtil();
    }

    public static ActivityManagerUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 把一个activity压入栈中
     *
     * @param activity activity
     */
    public synchronized void pushOneActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 从栈堆里移除一个activity
     *
     * @param activity activity
     */
    public synchronized void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return 栈顶的activity
     */
    public synchronized Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束指定的Activity
     *
     * @param activity activity
     */
    public synchronized void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param cls 指定的Activity
     */
    public synchronized void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        Activity tempActivity;
        while (iterator.hasNext()) {
            tempActivity = iterator.next();
            if (tempActivity.getClass().equals(cls)) {
                iterator.remove();
                tempActivity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param classes 指定的Activitys
     */
    public synchronized void finishActivity(Class<?>... classes) {
        Iterator<Activity> iterator;
        Activity tempActivity;
        for (Class cls : classes) {
            iterator = activityStack.iterator();
            while (iterator.hasNext()) {
                tempActivity = iterator.next();
                if (tempActivity.getClass().equals(cls)) {
                    iterator.remove();
                    tempActivity.finish();
                }
            }
        }
    }

    /**
     * 结束所有activity
     */
    public synchronized void finishAllActivity() {
        try {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出应用程序
     */
    public synchronized void appExit() {
        try {
            finishAllActivity();
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
