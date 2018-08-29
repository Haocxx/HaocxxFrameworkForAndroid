package com.haocxx.haocxxframework.util.net;

import com.haocxx.haocxxframework.base.bean.BaseListener;
import com.haocxx.haocxxframework.util.file.FileUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Haocxx
 * on 2018/8/29
 */
public class DownloadUtil {

    public static void downloadByOkHttp(OkHttpClient okHttpClient, String url, final File localFile, final BaseListener listener) {
        if (localFile.exists()) {
            localFile.delete();
        } else {
            FileUtil.makeDirs(localFile);
        }
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                //listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                if (!localFile.exists()) {
                    localFile.mkdirs();
                }
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(localFile);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        //listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    //listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    //listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    public static void downloadByHttp(HttpClient HttpClient, String url, File localFile) {
        if (localFile.exists()) {
            localFile.delete();
        } else {
            FileUtil.makeDirs(localFile);
        }
        FileOutputStream out = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = HttpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() >= 400) {
                //fail
            }
            BufferedInputStream in = new BufferedInputStream(response
                    .getEntity().getContent());
            int len = 1024;
            byte[] bytes = new byte[len];
            out = new FileOutputStream(localFile);
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
