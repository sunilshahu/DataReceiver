package com.sunilshahu.datareceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DataReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receiver);
        //get the image view
        ImageView picView = (ImageView)findViewById(R.id.picture);
        //get the text view
        TextView txtView = (TextView)findViewById(R.id.txt);
        //get the received intent
        Intent receivedIntent = getIntent();
        String receivedAction = receivedIntent.getAction();
        String receivedType = receivedIntent.getType();
        //make sure it's an action and type we can handle
        if(receivedAction.equals(Intent.ACTION_SEND)){
            //content is being shared
            if(receivedType.startsWith("text/")){
                //handle sent text
                String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
                //check we have a string
                if (receivedText != null) {
                    //set the text
                    txtView.setText(receivedText);
                    Uri receivedUri = (Uri)receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                    //check we have a uri
                    if (receivedUri != null) {
                        //set the picture
                        //RESAMPLE YOUR IMAGE DATA BEFORE DISPLAYING
                        picView.setImageURI(receivedUri);//just for demonstration
                    }
                }
            }
            else if(receivedType.startsWith("image/")){
                //handle sent image
                picView.setVisibility(View.GONE);
            }
        }
        else if(receivedAction.equals(Intent.ACTION_MAIN)){
            //app has been launched directly, not from share list
            txtView.setText("Nothing has been shared!");
        }
    }
}
