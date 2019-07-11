package com.example.instagram;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instagram.model.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

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
    private ImageView ivLike;
    private TextView tvNumLikes;
    private ImageView ivProfile;


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
        ivLike = findViewById(R.id.ivPostLike);
        tvNumLikes = findViewById(R.id.tvNumLikes);
        ivProfile = findViewById(R.id.ivProfileImageDetails);

        tvDescription.setText(post.getDescription());

        Date timestamp = post.getCreatedAt();
        DateFormat df = new SimpleDateFormat("MM--dd--yyyy HH:mm:ss");
        String strTimestamp= df.format(timestamp);
        tvTimestamp.setText(strTimestamp);
        tvHandle.setText(post.getHandle());
        tvTopHandle.setText(post.getHandle());
        tvNumLikes.setText(post.getNumLikes() + " likes");

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this)
                    .load(image.getUrl())
                    .into(ivImage);
        }

        ParseFile profileImage = post.getUser().getParseFile("profileImage");
        if (profileImage != null) {
            Glide.with(this)
                    .load(profileImage.getUrl())
                    .into(ivProfile);
        }


        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nLikes = post.getNumLikes();
                nLikes++;
                post.setNumLikes(nLikes);

                ivLike.setImageResource(R.drawable.ufi_heart_active);
                ivLike.setColorFilter(Color.RED);

                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            e.printStackTrace();
                        }
                    }
                });
                tvNumLikes.setText(post.getNumLikes() + " likes");
            }
        });
    }
}
