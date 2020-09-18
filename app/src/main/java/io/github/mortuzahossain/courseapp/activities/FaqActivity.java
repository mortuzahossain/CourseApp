package io.github.mortuzahossain.courseapp.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.adapter.FaqAdapter;
import io.github.mortuzahossain.courseapp.model.FaqModel;
import io.github.mortuzahossain.courseapp.utils.DialogUtils;

public class FaqActivity extends AppCompatActivity {

    @BindView(R.id.expandedImage) ImageView expandedImage;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar) AppBarLayout appBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.adView) AdView adView;
    @BindView(R.id.error_image) ImageView errorImage;
    @BindView(R.id.error_text) TextView errorText;
    @BindView(R.id.llError) LinearLayout llError;

    private FaqAdapter adapter;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);

        try {

            Glide.with(this)
                    .load("https://image.freepik.com/free-vector/tiny-people-sitting-standing-near-giant-faq_74855-7879.jpg")
                    .into(expandedImage);


            toolbar.setTitle("FAQ");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            progressDialog = DialogUtils.showLoadingDialog(this);
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            CollectionReference reference = firestore.collection("faq");
            Query query = reference.orderBy("title");
            FirestoreRecyclerOptions<FaqModel> options = new FirestoreRecyclerOptions.Builder<FaqModel>().setQuery(query, FaqModel.class).build();

            adapter = new FaqAdapter(options);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

            progressDialog.show();
            query.addSnapshotListener((queryDocumentSnapshots, e) -> {
                progressDialog.dismiss();
                if (queryDocumentSnapshots != null) {
                    if (queryDocumentSnapshots.size() == 0) {
                        llError.setVisibility(View.VISIBLE);
                        errorText.setText("No data available.");
                        errorImage.setImageResource(R.drawable.warning);
                    } else {
                        llError.setVisibility(View.GONE);
                    }
                } else {
                    llError.setVisibility(View.VISIBLE);
                    errorText.setText("Something went wrong.");
                    errorImage.setImageResource(R.drawable.warning);
                }

            });
        } catch (Exception e) {
            llError.setVisibility(View.VISIBLE);
            errorText.setText(e.getLocalizedMessage());
            errorImage.setImageResource(R.drawable.warning);
        }

        // FOR ADMOB
        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        adView.loadAd(adRequest);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }


    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        if (adView != null) {
            adView.pause();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.pause();
        }
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}