package cs193a.stanford.edu.hw3_madlibs;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.Scanner;
import java.io.File;
import stanford.androidlib.*;

public class FillInWordsActivity extends SimpleActivity {
    // TODO: finish me!

    private static final String[] filenames = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt",
                                        "madlib3_clothes.txt", "madlib4_dance.txt"};
    private Story story;
    private int cur_index = 0;
    private int totalPlaceholders = 0;
    private int placeholdersLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_words);
        Scanner scan = SimpleIO.with(this).openInternalFileScanner(R.raw.madlib0_simple);
        story = new Story(scan);
        totalPlaceholders = story.getPlaceholderCount();
        placeholdersLeft = totalPlaceholders;
        TextView wordsLeftView = (TextView)findViewById(R.id.words_left);
        wordsLeftView.setText(totalPlaceholders + " word(s) left.");

        TextView placeholderType = (TextView) findViewById(R.id.current_word);
        placeholderType.setText("Please type a/n " + story.getPlaceholder(cur_index));
    }


    public void onOkClick(View view) {

            EditText edit = (EditText) findViewById(R.id.fill_field);
            String text = edit.getText().toString();
            story.setPlaceholder(cur_index,text);
            cur_index++;
            placeholdersLeft--;
            if(cur_index >= totalPlaceholders){
                String storyText = story.toString();
                startActivity(ShowStoryActivity.class, "storyText", storyText);
            }else{
                TextView wordsLeftView = (TextView)findViewById(R.id.words_left);
                wordsLeftView.setText(placeholdersLeft + " word(s) left.");

                TextView placeholderType = (TextView) findViewById(R.id.current_word);
                placeholderType.setText("Please type a/n " + story.getPlaceholder(cur_index));
            }

    }
}
