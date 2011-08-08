package ru.denivip.android.videoview;

import ru.denivip.android.video.MediaController;
import ru.denivip.android.video.VideoView;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

public class Main extends Activity {
	
	private static final String TEST_STREAM_LOCAL = "http://local/PR243467.mp4";
	
	private static final String TEST_STREAM_REMOTE = "http://www.denivip.ru/trailer.mp4";
	
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
        video.setVideoURI(Uri.parse(TEST_STREAM_LOCAL));
        video.start();
    }
}
