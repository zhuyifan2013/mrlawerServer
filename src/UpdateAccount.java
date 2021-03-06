import entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateAccount")
public class UpdateAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BasicResponse basicResponse = new BasicResponse(ResultCode.RESULT_DEFAULT);
        Account account = new Account();
        account.fillSelf(request.getParameter(Account.PARAM_ACCOUNT_INFO));
        int result = AccountManager.updateAccountInfo(account);
        if(result != -1) {
            basicResponse.setResultCode(ResultCode.RESULT_OK);
        }
        response.getWriter().println(basicResponse.toJson());
    }
}
