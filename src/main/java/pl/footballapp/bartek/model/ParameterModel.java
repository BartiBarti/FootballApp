package pl.footballapp.bartek.model;

import pl.footballapp.bartek.enums.ParameterType;

public class ParameterModel {

    public static final String PARAMETER_ID_COL = "PARAMETER_ID";

    public static final String PARAMETER_NAME_COL = "PARAMETER_NAME";

    public static final String PARAMETER_VALUE_COL = "PARAMETER_VALUE";

    public static final String PARAMETER_TYPE_COL = "PARAMETER_TYPE";

    private int parameterId;

    private String parameterName;

    private String parameterValue;

    private ParameterType parameterType;

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }
}
