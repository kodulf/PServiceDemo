package bjpkten.pservicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import java.io.File;

import kodulf.baselibs.http.HttpUtils;
import kodulf.baselibs.http.RequestCallback;
import kodulf.baselibs.http.Result;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static final String DOWNLOAD_STATUS = "download_status";
    public static final int SUCCESS = 0;
    public static final int FAIL = 0;
    public static final int OTHER = -1;

    public static final String ACTION_DOWNLOAD_BROADCAST ="com.kodulf.download.status";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            String urlString =  "http://pic.qiushibaike.com/system/pictures/11895/118959315/medium/app118959315.jpg";

            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File file = new File(externalStoragePublicDirectory,"hello.jpg");

            try {
                HttpUtils.getDownload(urlString, file, new RequestCallback<Result<String>>() {
                    @Override
                    public void onFailure(Result result, Exception e) {
                        System.out.println("failture");
                        sendBroadCastFail();
                    }

                    @Override
                    public void onResponse(Result result) {
                        System.out.println("success");
                        sendBroadCastSuccess();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                sendBroadCastOther();
            }

        }
    }

    private void sendBroadCastSuccess() {
        Intent broadcast = new Intent();
        broadcast.putExtra(DOWNLOAD_STATUS,SUCCESS);
        broadcast.setAction(ACTION_DOWNLOAD_BROADCAST);
        sendBroadcast(broadcast);
    }

    private void sendBroadCastFail() {
        Intent broadcast = new Intent();
        broadcast.putExtra(DOWNLOAD_STATUS,FAIL);
        broadcast.setAction(ACTION_DOWNLOAD_BROADCAST);
        sendBroadcast(broadcast);
    }
    private void sendBroadCastOther() {
        Intent broadcast = new Intent();
        broadcast.putExtra(DOWNLOAD_STATUS,OTHER);
        broadcast.setAction(ACTION_DOWNLOAD_BROADCAST);
        sendBroadcast(broadcast);
    }

}
