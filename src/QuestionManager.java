import entity.Question;
import util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class QuestionManager {

    private static final String SQL_INSERT_QUESTION =
            "INSERT INTO question (questionID, userID, type, timeStart, timeEnd, text, url, status, lawerID, star) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

    public static HashMap<String, Integer> saveQuestion(Question question) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_DEFAULT);
        Connection connection = DBManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUESTION, Statement
                    .RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, question.getQuestionID());
            preparedStatement.setInt(2, question.getUserID());
            preparedStatement.setInt(3, question.getQuestionType());
            preparedStatement.setLong(4, question.getTimeStart());
            preparedStatement.setLong(5, question.getTimeEnd());
            preparedStatement.setString(6, question.getText());
            preparedStatement.setString(7, question.getUrl());
            preparedStatement.setInt(8, question.getStatus());
            preparedStatement.setInt(9, question.getLawerID());
            preparedStatement.setInt(10, question.getStar());
            int result;
            result = preparedStatement.executeUpdate();
            if (result == 1) {
                map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_OK);
            } else {
                map.put(Constants.KEY_RESULT_CODE, ResultCode.RESULT_DEFAULT);
                return map;
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int questionId = -1;
            if (resultSet.next()) {
                questionId = resultSet.getInt(1);
            }
            map.put(Question.PARAM_QUESTIONID, questionId);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return map;
    }

    //public static BasicResponse

    public static int pushQuestion(Question question) {
        return 0;
    }
}
