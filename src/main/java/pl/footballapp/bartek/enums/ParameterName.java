package pl.footballapp.bartek.enums;

public enum ParameterName {
    ALL_TEAMS_NUMBER("Liczba wszystkich drużyn: ", ParameterType.INTEGER),
    CL_PROMOTED_TEAMS_NUMBER("Liczba drużyn z awansem do LM: ", ParameterType.INTEGER),
    UCL_PROMOTED_TEAMS_NUMBER("Liczba drużyn z awansem do LE: ", ParameterType.INTEGER),
    UECL_PROMOTED_TEAMS_NUMBER("Liczba drużyn z awansem do LK: ", ParameterType.INTEGER),
    RELEGATED_TEAMS_NUMBER("Liczba drużyn spadających: ", ParameterType.INTEGER),
    START_SEASON_DATE("Data początku sezonu: ", ParameterType.DATE),
    END_SEASON_DATE("Data końca sezonu: ", ParameterType.DATE);

    private String plParameterName;

    private ParameterType parameterType;

    ParameterName(String plParameterName, ParameterType parameterType) {
        this.plParameterName = plParameterName;
        this.parameterType = parameterType;
    }

    public String getPlParameterName() {
        return plParameterName;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }
}
