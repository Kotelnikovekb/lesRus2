package ru.lesruss.Module;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class AsyncHttpPost extends AsyncTask<String, String, String> {
    public interface Listener {
        void onResult(String result);
    }
    private HashMap<String, String> mData = null;
    private String mUrl="";
    private Listener mListener;
    public void setListener(Listener listener) {
        mListener = listener;
    }
    public AsyncHttpPost(HashMap<String, String> data, String url) {
        mData = data;
        mUrl= url;
        Log.e("AsyncError","start");
        Log.e("AsyncError",mUrl);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        byte[] result = null;
        String str = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(mUrl);
        Log.e("AsyncError",mUrl);
        try {
            // set up post data
            ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            Iterator<String> it = mData.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
            }

            post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
                result = EntityUtils.toByteArray(response.getEntity());
                str = new String(result, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("AsyncError",e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AsyncError",e.toString());
        }
        return str;
    }

    protected void onPostExecute(String result) {
        mListener.onResult(result);
    }
}
