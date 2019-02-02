package com.algoworks.residemenu;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.algoworks.residemenu.fragment.CenteredTextFragment;
import com.algoworks.residemenu.menu.DrawerAdapter;
import com.algoworks.residemenu.menu.DrawerItem;
import com.algoworks.residemenu.menu.SimpleItem;
import com.algoworks.residemenu.slidingrootnav.SlidingRootNav;
import com.algoworks.residemenu.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

/**
 * Created by yarolegovich on 25.03.2017.
 */

public class SampleActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_1 = 4;
    private static final int POS_2 = 5;
    private static final int POS_3 = 6;
    private static final int POS_4 = 7;


    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    CoordinatorLayout layout;
    AppBarLayout appbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.activity_main);
        appbar = findViewById(R.id.appbar);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();


        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                createItemFor(POS_CART)));


        adapter.setListener(this);


        RecyclerView recSlideMenu = findViewById(R.id.rec_slide_menu);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.shape_slide_line_divider);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.shape_slide_line_divider);


        recSlideMenu.setNestedScrollingEnabled(false);
        recSlideMenu.setLayoutManager(new GridLayoutManager(this, 2));
        //recSlideMenu.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 2));
        recSlideMenu.setAdapter(adapter);

    }


    @Override
    public void onItemSelected(int position) {

        slidingRootNav.closeMenu();
        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorTextView))
                .withTextTint(color(R.color.colorTextView))
                .withSelectedIconTint(color(R.color.colorWhite))
                .withSelectedTextTint(color(R.color.colorWhite));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }
}
