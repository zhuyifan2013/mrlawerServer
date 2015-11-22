import entity.Account;
import rongyun.RongyunRequest;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Register.doGet()");
        BasicResponse basicResponse = new BasicResponse();
        String userName = request.getParameter(Account.PARAM_USERNAME);
        String passWord = request.getParameter(Account.PARAM_PASSWORD);
        int gender = Integer.parseInt(request.getParameter(Account.PARAM_GENDER));
        int city = Integer.parseInt(request.getParameter(Account.PARAM_CITY));
        int age = Integer.parseInt(request.getParameter(Account.PARAM_AGE));
        int userType = Integer.parseInt(request.getParameter(Account.PARAM_USER_TYPE));
        String college = request.getParameter(Account.PARAM_COLLEGE);
        String education = request.getParameter(Account.PARAM_EDUCATION);
        Account account = new Account();
        account.setUserName(userName);
        account.setPassword(passWord);
        account.setGender(gender);
        account.setCityCode(city);
        account.setAge(age);
        account.setUserType(userType);
        account.setCollege(college);
        account.setEducation(education);
        HashMap<String, Integer> map = AccountManager.register(account);
        int resultCode = map.get(Constants.KEY_RESULT_CODE);
        if (resultCode != ResultCode.RESULT_OK) {
            basicResponse.setResultCode(resultCode);
            response.getWriter().println(basicResponse.toJson());
            return;
        }
        account.setUserId(map.get(Account.PARAM_USERID));
        String token = RongyunRequest.getToken(account);
        System.out.println(token);
        account.setToken(token);
        if (AccountManager.updateAccountInfo(account) != -1) {
            basicResponse.setResultCode(ResultCode.RESULT_OK);
        }
        basicResponse.addParam(Constants.KEY_ACCOUNT_INFO, account.toJson());
        response.getWriter().println(basicResponse.toJson());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("Register.doPost()");
        doGet(request, response);
    }

}
