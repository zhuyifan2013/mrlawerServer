import entity.Account;
import entity.Question;
import util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AccountManager {

    private static final String SQL_INSERT_ACCOUNT_INFO =
            "INSERT INTO account(username,password,gender,city,age,token,userType,college,education,nickname, " +
                    "familiarArea) VALUES(?,?,?,?," +
                    "?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE_ACCOUNT_INFO =
            "UPDATE account SET username=?,password=?,gender=?,city=?,age=?,token=?,userType=?,college=?,education=?," +
                    "familiarArea=? " +
                    "WHERE userId=?";

    private static final String SQL_QUERY_ACCOUNT_BY_USERNAME = "SELECT * FROM account WHERE username = ?";

    private static final String SQL_QUERY_LAWER = "SELECT * FROM account WHERE userType = 1";

    private static final String SQL_GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    public static HashMap<String, Integer> register(Account account) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_DEFAULT);
        if (accountExist(account.getUserName())) {
            map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_ACCOUNT_EXISTS);
            return map;
        }
        map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_OK);
        int userId = saveAccountInfo(account);
        if (userId == ResultCode.RESULT_DEFAULT) {
            return map;
        }
        map.put(Account.PARAM_USERID, userId);
        return map;
    }

    public static int saveAccountInfo(Account account) {
        Connection connection = DBManager.getConnection();
        int userId = -1;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ACCOUNT_INFO, Statement
                    .RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getGender());
            preparedStatement.setInt(4, account.getCityCode());
            preparedStatement.setInt(5, account.getAge());
            preparedStatement.setString(6, account.getToken());
            preparedStatement.setInt(7, account.getUserType());
            preparedStatement.setString(8, account.getCollege());
            preparedStatement.setString(9, account.getEducation());
            preparedStatement.setString(10, account.getNickName());
            preparedStatement.setInt(11, account.getFamiliarArea());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return userId;
    }

    public static int updateAccountInfo(Account account) {
        Connection connection = DBManager.getConnection();
        int result = -1;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_UPDATE_ACCOUNT_INFO);
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getGender());
            preparedStatement.setInt(4, account.getCityCode());
            preparedStatement.setInt(5, account.getAge());
            preparedStatement.setString(6, account.getToken());
            preparedStatement.setInt(7, account.getUserType());
            preparedStatement.setString(8, account.getCollege());
            preparedStatement.setString(9, account.getEducation());
            preparedStatement.setInt(10, account.getFamiliarArea());
            preparedStatement.setInt(11, account.getUserId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return result;
    }

    public static Map<String, String> getAccountInfo(String username, String password) {
        Connection connection = DBManager.getConnection();
        Map<String, String> data = new HashMap<>();
        data.put(Constants.KEY_RESULT_CODE, Integer.toString(ResultCode.RESULT_DEFAULT));
        Account account = new Account();
        try {
            if (!accountExist(username)) {
                data.put(Constants.KEY_RESULT_CODE, Integer.toString(ResultCode.RESULT_NO_ACCOUNT));
                return data;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (!Objects.equals(password, resultSet.getString(Account.PARAM_PASSWORD))) {
                    data.put(Constants.KEY_RESULT_CODE, Integer.toString(ResultCode.RESULT_WRONG_PASSWORD));
                    return data;
                }
                account.setUserId(resultSet.getInt(Account.PARAM_USERID));
                account.setToken(resultSet.getString(Account.PARAM_TOKEN));
                account.setUserName(username);
                account.setPassword(password);
                account.setCityCode(resultSet.getInt(Account.PARAM_CITY));
                account.setGender(resultSet.getInt(Account.PARAM_GENDER));
                account.setAge(resultSet.getInt(Account.PARAM_AGE));
                account.setNickName(resultSet.getString(Account.PARAM_NICKNAME));
                account.setUserType(resultSet.getInt(Account.PARAM_USER_TYPE));
                account.setCollege(resultSet.getString(Account.PARAM_COLLEGE));
                account.setEducation(resultSet.getString(Account.PARAM_EDUCATION));
                account.setFamiliarArea(resultSet.getInt(Account.PARAM_FAMILIAR_AREA));
            }
            resultSet.close();
            preparedStatement.close();
            data.put(Constants.KEY_RESULT_CODE, Integer.toString(ResultCode.RESULT_OK));
            data.put(Constants.KEY_ACCOUNT_INFO, account.toJson());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return data;
    }

    public static Map<String, Object> getAccountInfoWithoutPwd(String username) {
        Connection connection = DBManager.getConnection();
        Map<String, Object> data = new HashMap<>();
        data.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_DEFAULT);
        Account account = new Account();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setUserId(resultSet.getInt(Account.PARAM_USERID));
                account.setUserName(username);
                account.setPassword(resultSet.getString(Account.PARAM_PASSWORD));
                account.setCityCode(resultSet.getInt(Account.PARAM_CITY));
                account.setGender(resultSet.getInt(Account.PARAM_GENDER));
                account.setAge(resultSet.getInt(Account.PARAM_AGE));
            }
            resultSet.close();
            preparedStatement.close();
            data.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_OK);
            data.put(Constants.KEY_ACCOUNT_INFO, account.toJson());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return data;
    }

    public static boolean accountExist(String username) {
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return false;
    }

    public static List<Account> queryLawerByQuestionType(Question question) {
        Connection connection = DBManager.getConnection();
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_LAWER);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account();
                account.setUserId(resultSet.getInt(Account.PARAM_USERID));
                account.setFamiliarArea(resultSet.getInt(Account.PARAM_FAMILIAR_AREA));
                accountList.add(account);
            }
            for (Account account : accountList) {
                if ((account.getFamiliarArea() & question.getQuestionType()) == 0) {
                    accountList.remove(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

}
