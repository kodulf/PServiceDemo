package bjpkten.pservicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * 音乐播放service
 */
public class Mp3Service extends Service {


    public static final String ACTION = "action";
    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int STOP = 2;

    MediaPlayer mediaPlayer;

    public Mp3Service() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //这里，歌曲的名字不能有大写字母？
        //还有要注意的是
        int action = intent.getIntExtra(ACTION,-1);
        if(action==PLAY){
            if(!mediaPlayer.isPlaying())
                    mediaPlayer.start();
        }else if(action == PAUSE){
            mediaPlayer.pause();
        }else if(action == STOP){
            //stop的方法有问题，所以建议使用pause的方法，然后seekto（0）
            //mediaPlayer.stop();
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }else {
            //do nothing
        }

        //return super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    public class Controller extends Binder{


    }

    /**
     * onCreate 只会被执行一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.erge);

    }


}
