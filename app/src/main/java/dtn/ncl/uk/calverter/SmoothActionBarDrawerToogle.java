package dtn.ncl.uk.calverter;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by dtn on 12/05/2016.
 */
public class SmoothActionBarDrawerToogle extends ActionBarDrawerToggle {
    private Runnable runnable;

    public SmoothActionBarDrawerToogle(Activity activity, DrawerLayout drawerLayout, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
        super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    public SmoothActionBarDrawerToogle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, @StringRes int openDrawerContentDescRes, @StringRes int closeDrawerContentDescRes) {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
    }
    @Override
    public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
    }
    @Override
    public void onDrawerStateChanged(int newState) {
        super.onDrawerStateChanged(newState);
        if (runnable != null && newState == DrawerLayout.STATE_IDLE) {
            runnable.run();
            runnable = null;
            Log.d("calverter", "runnable performed on idle..." );
        }
    }

    public void taskOnIdle(Runnable runnable) {
        this.runnable = runnable;
    }
}
