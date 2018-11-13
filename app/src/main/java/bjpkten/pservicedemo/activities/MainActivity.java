package bjpkten.pservicedemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bjpkten.pservicedemo.service.Mp3Service;
import bjpkten.pservicedemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        Intent intent = new Intent(this,Mp3Service.class);
        intent.putExtra(Mp3Service.ACTION,Mp3Service.PLAY);
        startService(intent);
    }

    public void pause(View view) {
        Intent intent = new Intent(this,Mp3Service.class);
        intent.putExtra(Mp3Service.ACTION,Mp3Service.PAUSE);
        startService(intent);
    }

    /**
     * stop的方法有问题，所以建议使用pause的方法，然后seekto（0）
     * https://blog.csdn.net/rodulf/article/details/50825989
     * 4.10 MediaPlayer.stop() 后 如何再次调用start()?
     * 2016年03月08日 11:39:39 千雅爸爸 阅读数：1766
     * 个人分类： 4 android 应用 系统开发
     * MediaPlayer.stop() 后 如何再次调用start()?
     *
     *  分享| 2013-12-31 19:09匿名 | 浏览 846 次
     * 通过create方法创建了Player 第一次调用start播放正常. 然后调用stop().停止了播放. 根据查过的说明.在调用了stop方法之后, 必须要先调用pause方法使Player处于准备状态,再调用start方法才会好用.可是我试过似乎还是不好用.总之我是希望无论是create 还是new .只创建一次player 之后可以反复的进行start 和stop操作. 试了半天没弄明白. 希望有人可以帮我解答.
     * 2016-01-25 17:14网友采纳
     * @Override
     * public void stop() {
     *     //player_release();
     *     try {
     *         mMyMediaPlayer.stop();
     *         mMyMediaPlayer.prepare();
     *         mMyMediaPlayer.seekTo(0);
     *     } catch(IOException e) {
     *         e.printStackTrace();
     *     }
     *
     *     if(mPlayerUIListener != null) {
     *         mPlayerUIListener.onTrackStop();
     *     }
     * }
     * 方法2：http://blog.csdn.net/brook0344/article/details/8273958
     * 创建midi文件后
     *
     * 调用start播放正行
     *
     * 调用stop声音也停止了
     *
     * 但是之后再start或先prepare再start也不行
     *
     * 自后解决方法是用pause代替stop，下次播放用
     *
     * seekTo(0); start();
     *
     * 就可以播放了
     *
     *
     *
     * 但是其他的ARM或者WAV文件好像就正常不用这麻烦？
     * @param view
     */
    public void stop(View view) {
        Intent intent = new Intent(this,Mp3Service.class);
        intent.putExtra(Mp3Service.ACTION,Mp3Service.STOP);
        startService(intent);
    }

    /**
     * 千万要注意就是要把这个service stop掉
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this,Mp3Service.class);
        stopService(intent);
    }


    public void jumpToIntentService(View view) {
        Intent intent = new Intent(this,IntentServiceActivity.class);
        startActivity(intent);
    }
}
