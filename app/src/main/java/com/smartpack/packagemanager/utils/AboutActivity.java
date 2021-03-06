/*
 * Copyright (C) 2020-2021 sunilpaulmathew <sunil.kde@gmail.com>
 *
 * This file is part of Package Manager, a simple, yet powerful application
 * to manage other application installed on an android device.
 *
 */

package com.smartpack.packagemanager.utils;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.smartpack.packagemanager.BuildConfig;
import com.smartpack.packagemanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on September 22, 2020
 */

public class AboutActivity extends AppCompatActivity {

    private AppCompatImageView mDeveloper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutview);

        AppCompatTextView mAppName = findViewById(R.id.app_title);
        mDeveloper = findViewById(R.id.developer);
        AppCompatTextView mForegroundText = findViewById(R.id.foreground_text);
        mAppName.setText(getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME);
        AppCompatTextView mCancel = findViewById(R.id.cancel_button);
        mCancel.setOnClickListener(v -> {
            super.onBackPressed();
        });
        mDeveloper.setOnClickListener(v -> {
            Utils.launchUrl("https://github.com/sunilpaulmathew", mDeveloper, this);
        });

        String change_log = null;
        try {
            change_log = new JSONObject(Objects.requireNonNull(Utils.readAssetFile(
                    this, "changelogs.json"))).getString("releaseNotes");
        } catch (JSONException ignored) {
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        mAppName.setText(getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME);
        mForegroundText.setText(change_log);
        mAppName.setVisibility(View.VISIBLE);
        mForegroundText.setVisibility(View.VISIBLE);
        mCancel.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}