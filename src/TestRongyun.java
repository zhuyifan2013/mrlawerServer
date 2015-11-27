import entity.Account;
import entity.RYMessage;
import entity.RYSystemMsg;
import rongyun.RongyunRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TestRongyun extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("3");
        String content = "{\"content\":\"hello\"}";
        RYSystemMsg systemMsg = new RYSystemMsg();
        systemMsg.setFromUserId("4");
        systemMsg.setToUserIds(arrayList);
        systemMsg.setObjectName(RYMessage.TYPE_MSG_TEXT);
        systemMsg.setContent(content);
        systemMsg.setPushContent("这是一个push");
        String httpResponse = RongyunRequest.sendSystemMsg(systemMsg);
        response.getWriter().println(httpResponse);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
