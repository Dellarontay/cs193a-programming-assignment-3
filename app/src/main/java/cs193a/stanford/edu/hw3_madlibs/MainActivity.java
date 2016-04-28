package cs193a.stanford.edu.hw3_madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import java.util.*;
import stanford.androidlib.*;

public class MainActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickGetStarted(View view) {
        startActivity(FillInWordsActivity.class);
    }

}
