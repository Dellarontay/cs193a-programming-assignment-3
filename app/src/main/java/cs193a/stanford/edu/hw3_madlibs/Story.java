/*
 * CS 193A, Marty Stepp
 * This class represents a Mad Libs story that comes from a text file.
 * You can construct it and pass an input stream or Scanner to read the story text.
 * After constructing it, you can ask it for each placeholder by calling
 *  getPlaceholder(i), then filling in that placeholder by calling setPlaceholder(i, string).
 * You can get the story's text by calling its toString method.
 * A Story is Serializable, so it can be packed into an Intent as "extra" data.
 */

package cs193a.stanford.edu.hw3_madlibs;

import java.io.*;
import java.util.*;

public class Story implements Serializable {
    private String text = "";                                // text of the story
    private List<String> placeholders = new ArrayList<>();   // list of placeholders to fill in
    private boolean htmlMode = false;                        // set true to surround placeholders with <b></b> tags

    /** constructs a new empty Story */
    public Story() {
        // empty
    }

    /** constructs a new Story reading its text from the given input stream */
    public Story(InputStream stream) {
        read(stream);
    }

    /** constructs a new Story reading its text from the given Scanner */
    public Story(Scanner input) {
        read(input);
    }

    /** returns the placeholder text at the given index */
    public String getPlaceholder(int index) {
        return placeholders.get(index);
    }

    /** returns total number of placeholders in the story */
    public int getPlaceholderCount() {
        return placeholders.size();
    }

    /** reads initial story text from the given input stream */
    public void read(InputStream stream) {
        read(new Scanner(stream));
    }

    /** reads initial story text from the given Scanner */
    public void read(Scanner input) {
        while (input.hasNext()) {
            String word = input.next();
            if (word.startsWith("<") && word.endsWith(">")) {
                // a placeholder; replace with e.g. "<0>" so I can find/replace it easily later
                // (make them bold so that they stand out!)
                text += " <" + placeholders.size() + ">";

                // "<plural-noun>" becomes "plural noun"
                String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                placeholders.add(placeholder);
            } else {
                // a regular word; just concatenate
                if (!text.isEmpty()) {
                    text += " ";
                }
                text += word;
            }
        }
    }

    /**
     * sets whether this story will be outputted in HTML mode with B tags around placeholders
     * (default false)
     */
    public void setHtmlMode(boolean value) {
        htmlMode = value;
    }

    /** sets the placeholder at the given index to be replaced by the given text */
    public void setPlaceholder(int index, String text) {
        placeholders.set(index, text);
    }

    /** returns story text, including any placeholders filled in */
    public String toString() {
        String storyText = text;

        // fill in the placeholders
        for (int i = 0; i < placeholders.size(); i++) {
            String replacement = placeholders.get(i);
            if (htmlMode) {
                replacement = "<b>" + replacement + "</b>";
            }
            storyText = storyText.replace("<" + i + ">", replacement);
        }

        // a few whitespace substitutions to make it look better
        storyText = storyText.replace(" .", ".");
        storyText = storyText.replace(" ,", ",");
        storyText = storyText.replace(" ?", "?");
        storyText = storyText.replace(" !", "!");

        return storyText;
    }
}
