package entity;

import java.util.List;

public class RYSystemMsg extends RYMessage {

    public List<String> getToUserIds() {
        return toUserIds;
    }

    public void setToUserIds(List<String> toUserIds) {
        this.toUserIds = toUserIds;
    }

    protected List<String> toUserIds;

}
