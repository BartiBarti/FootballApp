package pl.footballapp.bartek.repository;

import org.apache.commons.lang3.StringUtils;
import pl.footballapp.bartek.enums.ParameterName;
import pl.footballapp.bartek.enums.ParameterType;
import pl.footballapp.bartek.model.ParameterModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.footballapp.bartek.model.ParameterModel.*;

public class ParameterRepository implements Repository {

    public List<ParameterModel> findAll() {

        List<ParameterModel> parameterList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from PARAMETERS;");
            while (resultSet.next()) {
                ParameterModel parameter = getParameterFromResultSet(resultSet);
                parameterList.add(parameter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parameterList;
    }

    public ParameterModel findByParameterName(ParameterName parameterName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("select * from PARAMETERS where " + PARAMETER_NAME_COL + " = ?");
            preparedStatement.setString(1, parameterName.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ParameterModel parameter = getParameterFromResultSet(resultSet);
                return parameter;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private boolean update(ParameterModel parameter) {
        try {
            Statement statement = connection.createStatement();
            String query = "update PARAMETERS set ";
            if (StringUtils.isNotBlank(parameter.getParameterValue())) {
                query = query + PARAMETER_VALUE_COL + " = '" + parameter.getParameterValue() + "' ";
            }
            query = query + "where " + PARAMETER_ID_COL + " = " + parameter.getParameterId();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void updateAll(List<ParameterModel> parameters) {
        for (ParameterModel parameter : parameters) {
            update(parameter);
        }
    }

    private ParameterModel getParameterFromResultSet(ResultSet resultSet) throws SQLException {
        ParameterModel parameter = new ParameterModel();
        parameter.setParameterId(resultSet.getInt(PARAMETER_ID_COL));
        String sParameterName = resultSet.getString(PARAMETER_NAME_COL);
        ParameterName eParameterName = ParameterName.valueOf(sParameterName);
        parameter.setParameterName(eParameterName);
        parameter.setParameterValue(resultSet.getString(PARAMETER_VALUE_COL));
        String sParameterType = resultSet.getString(PARAMETER_TYPE_COL);
        ParameterType eParameterType = ParameterType.valueOf(sParameterType);
        parameter.setParameterType(eParameterType);

        return parameter;
    }

}
