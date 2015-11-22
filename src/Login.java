import entity.Account;
import util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Login.doGet()");
        BasicResponse basicResponse = new BasicResponse();
        String userName = request.getParameter(Account.PARAM_USERNAME);
        String passWord = request.getParameter(Account.PARAM_PASSWORD);
        Map<String, String> resultMap = AccountManager.getAccountInfo(userName, passWord);
        basicResponse.setResultCode(Integer.parseInt(resultMap.get(Constants.KEY_RESULT_CODE)));
        basicResponse.addParam(Constants.KEY_ACCOUNT_INFO, resultMap.get(Constants.KEY_ACCOUNT_INFO));
        response.getWriter().println(basicResponse.toJson());
        System.out.println(basicResponse.toJson());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
