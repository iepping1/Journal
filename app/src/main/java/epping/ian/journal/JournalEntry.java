package epping.ian.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    // create variables for all journals
    private String title, content, mood, timestamp;
    private int id;

    public JournalEntry(int id, String title, String content, String mood, String timestamp) {
        id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }
    public JournalEntry(String title, String content, String mood, String timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    // get the values of each journal
    public String getTitle() {
        return title;
    }

    public String getTime() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public int getId() { return id; }

    // set the title, moodd, time and identity of each journal entry
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String timestamp) { this.timestamp = timestamp; }

    public void setMood(String mood) {
        this.mood = mood;
    }

    //public void setId(int id) { this.id = id; }
}
