package ru.denivip.android.videoview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        VideoView video = (VideoView) findViewById(R.id.videoView);
        
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(video);
        controller.setAnchorView(video);
        
        video.setMediaController(controller);
        video.setVideoURI(Uri.parse("http://www.denivip.ru/trailer.mp4"));
        video.start();
    }
}