package me.talksy.android;

import android.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class ChatActivity extends AppCompatActivity {

    static final String EXTRA_CONTACT_IMAGE = "";
    static final String EXTRA_CONTACT_NAME = "";
    static final String EXTRA_CONTACT_NUMBER = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        String name = null;
        if (extras != null) {
            name = extras.getString("EXTRA_CONTACT_NAME");
        }





        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        /*actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | actionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.mipmap.ic_launcher);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.END
                | Gravity.CENTER_VERTICAL);
        layoutParams.rightMargin = 20;
        imageView.setLayoutParams(layoutParams);
        setTitle(name);
        actionBar.setCustomView(imageView);*/

        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setIcon(R.mipmap.ic_launcher);

        setTitle(name);

        setContentView(R.layout.chat_activity);




    }


/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}
