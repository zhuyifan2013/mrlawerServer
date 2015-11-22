package rongyun;

import entity.Account;
import entity.RYMessage;
import entity.RYSystemMsg;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import util.Constants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RongyunRequest {

    private static final String KEY_APP_KEY = "App-Key";
    private static final String KEY_NONCE = "Nonce";
    private static final String KEY_TIMESTAMP = "Timestamp";
    private static final String KEY_SIGNATURE = "Signature";
    private static final String KEY_CODE = "code";

    private static final String PREFIX_URL = "https://api.cn.ronghub.com";

    /**
     * 获取Token
     */
    private static final String URL_GETTOKEN = "/user/getToken.json";


    private static final String URL_SEND_SYSTEM_MSG = "/message/system/publish.json";

    public static String getToken(Account account) {
        HttpPost httpPost = makePost(URL_GETTOKEN);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair(Account.PARAM_USERID, Integer.toString(account.getUserId())));
        nameValuePairList.add(new BasicNameValuePair(Account.PARAM_USERNAME, account.getUserName()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            HttpResponse httpResponse = realSendRequest(httpPost);
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            return jsonObject.getString(Account.PARAM_TOKEN);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendSystemMsg(RYSystemMsg systemMsg) {
        HttpPost httpPost = makePost(URL_SEND_SYSTEM_MSG);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair(RYMessage.PARAM_FROM_USER_ID, systemMsg.getFromUserId()));
        for(String toUserId : systemMsg.getToUserIds()) {
            nameValuePairList.add(new BasicNameValuePair(RYMessage.PARAM_TO_USER_ID, toUserId));
        }
        nameValuePairList.add(new BasicNameValuePair(RYMessage.PARAM_OBJECT_NAME, systemMsg.getObjectName()));
        nameValuePairList.add(new BasicNameValuePair(RYMessage.PARAM_CONTENT, systemMsg.getContent()));
        nameValuePairList.add(new BasicNameValuePair(RYMessage.PARAM_PUSH_CONTENT, systemMsg.getPushContent()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            HttpResponse httpResponse = realSendRequest(httpPost);
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
            return jsonObject.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpPost makePost(String urlContent) {
        String nonce = Integer.toString((int) (Math.random() * 10000));
        String timestamp = Long.toString(System.currentTimeMillis());
        String signature = DigestUtils.sha1Hex(Constants.RONGYUN_APP_SECRET + nonce + timestamp);
        HttpPost httpPost = new HttpPost(PREFIX_URL + urlContent);
        httpPost.setHeader(KEY_APP_KEY, Constants.RONGYUN_APP_KEY);
        httpPost.setHeader(KEY_NONCE, nonce);
        httpPost.setHeader(KEY_TIMESTAMP, timestamp);
        httpPost.setHeader(KEY_SIGNATURE, signature);
        return httpPost;
    }

    public static HttpResponse realSendRequest(HttpPost httpPost) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

}
