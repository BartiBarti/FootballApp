package pl.footballapp.bartek.repository;

import pl.footballapp.bartek.model.MatchResultModel;

public class MatchResultRepository implements Repository {

    @Override
    public String getTableName() {
        return MatchResultModel.TABLE_NAME;
    }
}
