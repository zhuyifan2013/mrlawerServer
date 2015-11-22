package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {

    public static final String PARAM_USERID = "userId";
    public static final String PARAM_TOKEN = "token";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_NICKNAME = "nickname";
    public static final String PARAM_GENDER = "gender";
    public static final String PARAM_CITY = "city";
    public static final String PARAM_AGE = "age";
    public static final String PARAM_COLLEGE = "college";
    public static final String PARAM_EDUCATION = "education";
    public static final String PARAM_USER_TYPE = "userType";

    private int mUserId;
    private String mToken;
    private String mUserName;
    private String mPassword;
    private String mNickName;
    private int mGender;
    private int mCityCode;
    private int mAge;
    private int mUserType;
    private String mCollege;
    private String mEducation;

    public int getUserType() {
        return mUserType;
    }

    public void setUserType(int userType) {
        mUserType = userType;
    }

    public String getCollege() {
        return mCollege;
    }

    public void setCollege(String college) {
        mCollege = college;
    }

    public String getEducation() {
        return mEducation;
    }

    public void setEducation(String education) {
        mEducation = education;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mId) {
        this.mUserId = mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public int getCityCode() {
        return mCityCode;
    }

    public void setCityCode(int cityCode) {
        mCityCode = cityCode;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(PARAM_USERID, mUserId);
            jsonObject.put(PARAM_TOKEN, mToken);
            jsonObject.put(PARAM_USERNAME, mUserName);
            jsonObject.put(PARAM_PASSWORD, mPassword);
            jsonObject.put(PARAM_CITY, mCityCode);
            jsonObject.put(PARAM_NICKNAME, mNickName);
            jsonObject.put(PARAM_GENDER, mGender);
            jsonObject.put(PARAM_AGE, mAge);
            jsonObject.put(PARAM_USER_TYPE, mUserType);
            jsonObject.put(PARAM_COLLEGE, mCollege);
            jsonObject.put(PARAM_EDUCATION, mEducation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
