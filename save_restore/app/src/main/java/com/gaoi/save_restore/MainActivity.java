package com.gaoi.save_restore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mCurrentPhotoIndex = 0;
    private int[] mPhotoIds = new int[] { R.drawable.ad,
            R.drawable.ae, R.drawable.af, R.drawable.ag,
            R.drawable.ai, R.drawable.al, R.drawable.am,
            R.drawable.an};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPhoto(mCurrentPhotoIndex);

        // Handle clicks on the 'Next' button.
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCurrentPhotoIndex = (mCurrentPhotoIndex + 1)
                        % mPhotoIds.length;
                showPhoto(mCurrentPhotoIndex);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("photo_index", mCurrentPhotoIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentPhotoIndex = savedInstanceState.getInt("photo_index");
        showPhoto(mCurrentPhotoIndex);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void showPhoto(int photoIndex) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(mPhotoIds[photoIndex]);

        TextView statusText = (TextView) findViewById(R.id.textView);
        statusText.setText(String.format("%d/%d", photoIndex + 1,
                mPhotoIds.length));
    }
}
