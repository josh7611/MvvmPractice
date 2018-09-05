package com.oohaweb.listuser.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.oohaweb.listuser.R;
import com.oohaweb.listuser.view_model.UserViewModel;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        UserViewModel.getInstance().getUserDetail(userId).observe(this, userDetail -> {
            if (null != userDetail) {
                ((TextView) findViewById(R.id.name)).setText(userDetail.getName());
                ((TextView) findViewById(R.id.bio)).setText(userDetail.getBio());
                ((TextView) findViewById(R.id.login)).setText(userDetail.getLogin());
                ((TextView) findViewById(R.id.location)).setText(userDetail.getLocation());
                ((TextView) findViewById(R.id.blog)).setText(userDetail.getBlog());

                if (!userDetail.isSite_admin()) {
                    findViewById(R.id.site_admin).setVisibility(View.GONE);
                }

                Picasso.with(this)
                        .load(userDetail.getAvatar_url())
                        .transform(new CropCircleTransformation())
                        .into(((ImageView) findViewById(R.id.avatar_view)));
            }
        });

        Button btn = findViewById(R.id.btn_cancel);
        btn.setOnClickListener((View v) ->
                onBackPressed()
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
