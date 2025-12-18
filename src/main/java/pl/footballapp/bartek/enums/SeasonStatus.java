package pl.footballapp.bartek.enums;

public enum SeasonStatus {
    OPEN("Sezon otwarty"),
    IN_PROGRESS("Sezon w trakcie"),
    CLOSED("Sezon zakończony");

    private String plTranslation;

    SeasonStatus(String plTranslation) {
        this.plTranslation = plTranslation;
    }

    public String getPlTranslation() {
        return plTranslation;
    }

}
