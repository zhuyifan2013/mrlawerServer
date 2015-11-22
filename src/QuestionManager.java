import entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionManager {

    private static final String SQL_INSERT_QUESTION =
            "INSERT INTO question (questionID, userID, type, timeStart, timeEnd, text, url, status, lawerID, star) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

    public static int saveQuestion(Question question) {
        Connection connection = DBManager.getConnection();
        int result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUESTION);
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
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return result;
    }

    public static int pushQuestion(Question question) {
        return 0;
    }
}
