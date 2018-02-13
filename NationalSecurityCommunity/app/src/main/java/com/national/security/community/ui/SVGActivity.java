package com.national.security.community.ui;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.national.security.community.R;

public class SVGActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        PathView pathView = findViewById(R.id.pathView);
        //        final Path path = makeConvexArrow(50, 100);
//        pathView.setPath(path);
        pathView.setFillAfter(true);
        pathView.useNaturalColors();

        pathView.setOnClickListener(v -> pathView.getPathAnimator().
                delay(100).
                duration(1500).
                interpolator(new AccelerateDecelerateInterpolator()).
                start());
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
    }
}
