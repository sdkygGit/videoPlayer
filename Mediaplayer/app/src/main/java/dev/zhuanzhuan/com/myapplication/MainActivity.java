package dev.zhuanzhuan.com.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.surfaceView)
    SurfaceView mSurfaceView;
    MediaPlayer mediaPlayer;
    boolean isRealdy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        if (mediaPlayer == null){
//            mediaPlayer = new MediaPlayer();
//            mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//
//                    try {
//                        mediaPlayer.setDataSource(MainActivity.this, Uri.parse("http://video.jiecao.fm/8/18/%E5%A4%A7%E5%AD%A6.mp4"));
//                        mediaPlayer.setDisplay(holder);
//                        mediaPlayer.prepare();
//                        if (isRealdy) {
//                            mediaPlayer.start();
//                        } else
//                            isRealdy = true;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
////                    mediaPlayer.setDisplay(holder);
//                }
//
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//                }
//            });
//
//        }
//    }

    @OnClick(R.id.start)
    void start() {
        if (isRealdy)
            mediaPlayer.start();
        else
            isRealdy = true;
    }

    @OnClick(R.id.go)
    void go() {
        startActivity(new Intent(MainActivity.this, VideoPlayerActivity.class));
    }

    @OnClick(R.id.fullScreen)
    void fullScreen() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();
            lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, getResources().getDisplayMetrics());
            mSurfaceView.setLayoutParams(lp);
        } else {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mSurfaceView.getLayoutParams();
            lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
            mSurfaceView.setLayoutParams(lp);
        }
    }


    void showDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();

        View view = getLayoutInflater().inflate(R.layout.dialog_menu, null);
        window.addContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


    }
}
