package cs193a.stanford.edu.hw3_madlibs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;
import stanford.androidlib.*;

public class ShowStoryActivity extends SimpleActivity {
    // TODO: finish me!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);
        String story = getStringExtra("storyText");
        TextView textview = (TextView) findViewById(R.id.Madlib_story);
        textview.setText(story);
    }

    public void onMakeClick(View view) {
        startActivity(MainActivity.class);
    }
}
