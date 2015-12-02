package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Account {

    public static final String PARAM_ACCOUNT_INFO = "accountInfo";

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
    public static final String PARAM_FAMILIAR_AREA = "familiarArea";

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
    private int mFamiliarArea;

    public int getFamiliarArea() {
        return mFamiliarArea;
    }

    public void setFamiliarArea(int familiarArea) {
        mFamiliarArea = familiarArea;
    }

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

    public Account() {
        this.mUserId = 0;
        this.mUserType = 0;
        this.mToken = "";
        this.mUserName = "";
        this.mPassword = "";
        this.mCityCode = 0;
        this.mNickName = "";
        this.mGender = 0;
        this.mAge = 0;
        this.mCollege = "";
        this.mEducation = "";
        this.mFamiliarArea = 0;
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
            jsonObject.put(PARAM_FAMILIAR_AREA, mFamiliarArea);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public void fillSelf(String rawJson) {
        System.out.println(rawJson);
        try {
            JSONObject jsonObject = new JSONObject(rawJson);
            this.setUserId(jsonObject.getInt(PARAM_USERID));
            this.setToken(jsonObject.getString(PARAM_TOKEN));
            this.setUserName(jsonObject.getString(PARAM_USERNAME));
            this.setPassword(jsonObject.getString(PARAM_PASSWORD));
            this.setNickName(jsonObject.getString(PARAM_NICKNAME));
            this.setGender(jsonObject.getInt(PARAM_GENDER));
            this.setCityCode(jsonObject.getInt(PARAM_USERID));
            this.setAge(jsonObject.getInt(PARAM_AGE));
            this.setUserType(jsonObject.getInt(PARAM_USER_TYPE));
            this.setCollege(jsonObject.getString(PARAM_COLLEGE));
            this.setEducation(jsonObject.getString(PARAM_EDUCATION));
            this.setFamiliarArea(jsonObject.getInt(PARAM_FAMILIAR_AREA));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
