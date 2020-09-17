package io.github.mortuzahossain.courseapp.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.widget.PullRefreshLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.adapter.CourseListAdapter;
import io.github.mortuzahossain.courseapp.network.interfaces.CourseInterfaces;
import io.github.mortuzahossain.courseapp.network.model.CourseListResponse;
import io.github.mortuzahossain.courseapp.network.presenter.CoursePresenter;
import io.github.mortuzahossain.courseapp.utils.DialogUtils;
import io.github.mortuzahossain.courseapp.utils.Navigator;

public class CourseListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CourseInterfaces.view {

    @BindView(R.id.toolbar) MaterialToolbar toolbar;
    @BindView(R.id.appBarLayout) AppBarLayout appBarLayout;
    @BindView(R.id.courseList) RecyclerView courseList;
    @BindView(R.id.swipeRefreshLayout) PullRefreshLayout swipeRefreshLayout;
    @BindView(R.id.error_image) ImageView errorImage;
    @BindView(R.id.error_text) TextView errorText;
    @BindView(R.id.btnRetry) MaterialButton btnRetry;
    @BindView(R.id.llError) LinearLayout llError;
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;

    CourseListAdapter adapter;
    List<CourseListResponse> courseListResponses = new ArrayList<>();

    Dialog progressDialog;
    CoursePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        ButterKnife.bind(this);

        progressDialog = DialogUtils.showLoadingDialog(this);
        presenter = new CoursePresenter(this);

        NavigationMenuView navMenuView = (NavigationMenuView) navView.getChildAt(0);
        navView.setNavigationItemSelectedListener(this);
        navMenuView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        navView.setNavigationItemSelectedListener(this);
        navView.setNavigationItemSelectedListener(CourseListActivity.this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));

        presenter.getCourse();
        swipeRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.getCourse());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        new Handler().postDelayed(() -> {
            switch (id) {
                case R.id.about_us:
                    //Navigator.goToActivityForward(this, AboutUsActivity.class);
                    break;
                case R.id.faq:
                    //Navigator.goToActivityForward(this, FaqActivity.class);
                    break;
                case R.id.contact:
                    //Navigator.goToActivityForward(this, ContactUsActivity.class);
                    break;

                default:
                    break;
            }
        }, 200);

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showSuccess(List<CourseListResponse> courseListResponses) {
        llError.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        adapter = new CourseListAdapter(this, courseListResponses);
        courseList.setAdapter(adapter);
        courseList.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFailed(String err) {
        swipeRefreshLayout.setRefreshing(false);
        llError.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorText.setText(err);
        errorImage.setImageResource(R.drawable.warning);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showInternetError() {
        llError.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorText.setText("No Internet Connection");
        errorImage.setImageResource(R.drawable.no_wifi);
    }

    @OnClick(R.id.btnRetry)
    public void onViewClicked() {
        presenter.getCourse();
    }
}