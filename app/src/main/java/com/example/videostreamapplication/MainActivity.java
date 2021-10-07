package com.example.videostreamapplication;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView simpleVideoView1,simpleVideoView2;
    MediaController mediaControls;
    Button btnPlay,btnStop,btnRestart;
    TextView timeview,timeview2;
    Chronometer counter;
    public int ispause=1,isplay=0,isstop=1,isprepared1=0,isprepared2=0, isseek1=0,isseek2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find your VideoView in your video_main.xml layout
        simpleVideoView1 = (VideoView) findViewById(R.id.simpleVideoView1);
        simpleVideoView2 = (VideoView) findViewById(R.id.simpleVideoView2);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        timeview = (TextView) findViewById(R.id.timeview);
        timeview2 = (TextView) findViewById(R.id.timeview2);

        counter = (Chronometer) findViewById(R.id.chrono);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnRestart = (Button) findViewById(R.id.btnRestart);

        simpleVideoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                isprepared1 = 1;

                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mediaPlayer) {
                        isseek1 =1;

                        int cnt = simpleVideoView1.getCurrentPosition();
                            simpleVideoView2.seekTo(cnt - 700);

                    }
                });
            }
        });
        simpleVideoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                isprepared2 = 1;

                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer mediaPlayer) {
                        isseek2 =1;


                    }
                });
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isprepared1==1 && isprepared2==1) {
                    simpleVideoView1.start();
                    simpleVideoView2.start();
                    simpleVideoView1.seekTo(500);

//

                    ispause = 0;

//
//                    Thread view2 = new Thread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
//                            int cnt = simpleVideoView1.getCurrentPosition();
//                            simpleVideoView1.seekTo(cnt);
//                            simpleVideoView2.seekTo(cnt - 1000);
////                            simpleVideoView2.start();
//                        }
//                    });

//                    view2.start();

                }
            }
        });
       btnStop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               simpleVideoView1.pause();
               simpleVideoView2.pause();
               ispause =1;
           }
       });
       btnRestart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isprepared1==1 && isprepared2==1) {

                    simpleVideoView1.seekTo(1000);
                    simpleVideoView2.seekTo(1);

                    simpleVideoView1.start();
                    simpleVideoView2.start();
                    Thread view1 = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                        }
                    });

                    Thread view2 = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                            int cnt = simpleVideoView1.getCurrentPosition();
                            simpleVideoView1.seekTo(cnt);
                            simpleVideoView2.seekTo(cnt - 1000);

                        }
                    });

//                    view1.start();
                    view2.start();
                    ispause = 0;
                }
            }
        });
        counter.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            public void onChronometerTick(Chronometer arg0) {

                final int countUp = simpleVideoView1.getCurrentPosition();
                final int countUp2 = simpleVideoView2.getCurrentPosition();
                Log.i("chrono:", "videoview1: " +countUp+ " videoview2: "+countUp2+"  diff = "+ (countUp-countUp2));

//                if (countUp>500 && simpleVideoView2.isPlaying()==false && ispause==0)
//               {
////                   simpleVideoView2.seekTo(countUp-500);
////                   simpleVideoView2.start();
//
//                   simpleVideoView1.seekTo(countUp);
//                   simpleVideoView2.seekTo(countUp-500);
//                   simpleVideoView1.start();
//                   simpleVideoView2.start();
//               }
//                final int count1 = simpleVideoView1.getCurrentPosition();
//                final int count2 = simpleVideoView2.getCurrentPosition();
//                Log.i("chrono:", "videoview1: " +count1+ " videoview2: "+count2+"  diff = "+ (count1-count2));
//
//                if (abs(abs(count1-count2)-500)>80 && ispause==0){
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                          Log.i("postDelay:", "update videoview2:" + (count1-500));
//                            int cnt = simpleVideoView1.getCurrentPosition();
//                            simpleVideoView1.seekTo(cnt);
//                            simpleVideoView2.seekTo(cnt-500);
//                        }
//                    }).start();
//                }

//                if ((countUp-countUp2)>550){
//                      simpleVideoView1.postDelayed(new Runnable() {
//                          @Override
//                          public void run() {
//                              Log.i("postDelay:", "update videoview2:" + (count1-500));
//
//                              simpleVideoView2.seekTo(count1-500);
//                              simpleVideoView2.start();
//                          }
//                      },countUp-countUp2 -500);
//                }
//                else  if ((countUp-countUp2)<450){
//                                        simpleVideoView2.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                      simpleVideoView2.pause();
//                                            }
//                                        },50);
//                                        simpleVideoView2.start();
//                                  }
                int count = Math.round(countUp / 1000);
                int duration = simpleVideoView1.getDuration();
                duration = duration / 1000;
                long a = (long) count;
                String countText;
                if (count % 60000 <= 9) {
                    countText = (a / 60000) + ":0" + (a % 60000);
                } else {
                    countText = (a / 60000) + ":" + (a % 60000);
                }
                String durationText;
                if (duration % 60000 <= 9) {
                    durationText = (duration / 60000) + ":0" + (duration % 60000);
                } else {
                    durationText = (duration / 60000) + ":" + (duration % 60000);
                }
//                timeview.setText(countText + " / " + durationText);
                timeview.setText( ((String) ""+countUp));
                timeview2.setText( ((String) "diff:"+(countUp-countUp2)));

            }
        });
        counter.start();

        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(MainActivity.this);
            mediaControls.setAnchorView(simpleVideoView1);
        }
        // set the media controller for video view
        simpleVideoView1.setMediaController(mediaControls);
        // set the uri for the video view
        simpleVideoView1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cliptest));
        simpleVideoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cliptest));

        // start a video
//        simpleVideoView1.start();

        // implement on completion listener on video view
        simpleVideoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        simpleVideoView1.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
    }
}