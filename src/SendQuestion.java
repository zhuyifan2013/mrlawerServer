import entity.Account;
import entity.Question;
import entity.RYMessage;
import entity.RYSystemMsg;
import org.json.JSONException;
import org.json.JSONObject;
import rongyun.RongyunRequest;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet(name = "SendQuestion")
public class SendQuestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        BasicResponse basicResponse = new BasicResponse();
        final Question question = new Question();
        System.out.println("receive question !!!!");
        String rawJson = request.getParameter(Question.PARAM_QUESTIONID_INFO);
        question.fillFromJson(rawJson);
        question.setTimeStart(System.currentTimeMillis());
        HashMap<String, Integer> map = QuestionManager.saveQuestion(question);
        int result = map.get(Constants.KEY_RESULT_CODE);
        if (result != ResultCode.RESULT_OK) {
            basicResponse.setResultCode(result);
            response.getWriter().println(basicResponse.toJson());
            return;
        }
        question.setQuestionID(map.get(Question.PARAM_QUESTIONID));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                sendQuestionToLawer(question);
            }
        });
        basicResponse.addParam(Question.PARAM_QUESTIONID_INFO, question.toJson());
        basicResponse.setResultCode(ResultCode.RESULT_OK);
        System.out.println("发送成功");
        response.getWriter().println(basicResponse.toJson());
    }

    private void sendQuestionToLawer(Question question) {
        List<Account> accountList = AccountManager.queryLawerByQuestionType(question);
        List<String> accountIdList = new ArrayList<>();
        for (Account account : accountList) {
            accountIdList.add(Integer.toString(account.getUserId()));
            System.out.println("send to " + account.getUserId());
        }
        RYSystemMsg systemMsg = new RYSystemMsg();
        systemMsg.setToUserIds(accountIdList);
        System.out.println("from:  " + question.getUserID());
        systemMsg.setFromUserId(Integer.toString(question.getUserID()));
        systemMsg.setObjectName(RYMessage.TYPE_MSG_TEXT);
        String content = "{\"content\":\"" + question.getText() + "\"}";
        systemMsg.setContent(content);
        systemMsg.setPushContent("这是一个push");
        String httpResponse = RongyunRequest.sendSystemMsg(systemMsg);
        System.out.println("response :  " + httpResponse);
    }
}
