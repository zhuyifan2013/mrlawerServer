package entity;

public abstract class RYMessage {


    public static final String PARAM_FROM_USER_ID = "fromUserId";
    public static final String PARAM_TO_USER_ID = "toUserId";
    public static final String PARAM_OBJECT_NAME = "objectName";
    public static final String PARAM_CONTENT = "content";
    public static final String PARAM_PUSH_CONTENT = "pushContent";

    public static final String TYPE_MSG_TEXT = "RC:TxtMsg";

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    protected String fromUserId;
    protected String toUserId;
    protected String objectName;
    protected String content;
    protected String pushContent;
}
