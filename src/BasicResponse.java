import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BasicResponse {

    public static final String PARAM_RESULT_CODE = "result_code";

    private int mResultCode;
    private Map<String, String> mParams = new HashMap<>();

    public BasicResponse() {
        mResultCode = ResultCode.RESULT_DEFAULT;
    }

    public BasicResponse(int resultCode) {
        mResultCode = resultCode;
    }

    public int getResultCode() {
        return mResultCode;
    }

    public void setResultCode(int mResultCode) {
        this.mResultCode = mResultCode;
    }

    public void addParam(String key, String value) {
        mParams.put(key, value);
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(PARAM_RESULT_CODE, mResultCode);
            for (Map.Entry<String, String> entry : mParams.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
