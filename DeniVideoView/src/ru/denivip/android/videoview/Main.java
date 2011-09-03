package ru.denivip.android.videoview;

import ru.denivip.android.video.MediaController;
import ru.denivip.android.video.VideoView;
import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class Main extends Activity {

	private static final String TEST_STREAM_LOCAL = "http://local/PR243467.mp4";

	private static final String TEST_STREAM_REMOTE = "http://www.denivip.ru/trailer.mp4";

	private VideoView mVideo;
	
	private FrameLayout mVideoContainer;
	
	private ViewGroup mNormalLayout;
	private ViewGroup mFullScreenLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getLayoutInflater();
		mNormalLayout = (ViewGroup) inflater.inflate(R.layout.main, null);
		mFullScreenLayout = (ViewGroup) inflater.inflate(R.layout.fullscreen, null);
		mVideo = (VideoView) inflater.inflate(R.layout.videoview, null);
		
		setContentView(mNormalLayout);

		mVideoContainer = (FrameLayout) findViewById(R.id.videoContainer);
		mVideoContainer.addView(mVideo);
		mVideo.requestFocus();

		MediaController controller = new MediaController(this);

		mVideo.setMediaController(controller);
		mVideo.setVideoURI(Uri.parse(TEST_STREAM_LOCAL));
		mVideo.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mVideo.suspend();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mVideo.resume();
		mVideo.start(); // without this pause function will not work after
						// resume
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// FIXME move to zoom/pinch event
			onFullScreen();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private boolean isFullScreen = false;

	private void onFullScreen() {
		if (isFullScreen) {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

			isFullScreen = false;

			mVideo.suspend();
			
			mVideoContainer.removeView(mVideo);
			setContentView(mNormalLayout);
			mVideoContainer = (FrameLayout)findViewById(R.id.videoContainer); 
			mVideoContainer.addView(mVideo);
			
			mVideo.resume();
			mVideo.start();

		} else {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);

			isFullScreen = true;
			
			mVideo.suspend();
			
			mVideoContainer.removeView(mVideo);
			setContentView(mFullScreenLayout);
			mVideoContainer = (FrameLayout)findViewById(R.id.videoContainer); 
			mVideoContainer.addView(mVideo);
			
			mVideo.resume();
			mVideo.start();
		}
	}
}
