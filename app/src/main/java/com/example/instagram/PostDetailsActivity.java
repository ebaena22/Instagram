package com.example.instagram;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instagram.model.Post;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    private Post post;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvTimestamp;
    private TextView tvHandle;
    private TextView tvTopHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        post = getIntent().getParcelableExtra("PARSE_OBJECT_EXTRA");
        Log.d("PostDetailsActivity", String.format("Showing details for '%s'", post.getDescription()));

        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvHandle = findViewById(R.id.tvHandle);
        tvTopHandle = findViewById(R.id.tvTopHandle);

        tvDescription.setText(post.getDescription());

        Date timestamp = post.getCreatedAt();
        DateFormat df = new SimpleDateFormat("MM--dd--yyyy HH:mm:ss");
        String strTimestamp= df.format(timestamp);
        tvTimestamp.setText(strTimestamp);
        tvHandle.setText(post.getHandle());
        tvTopHandle.setText(post.getHandle());

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this)
                    .load(image.getUrl())
                    .into(ivImage);
        }

    }
}
