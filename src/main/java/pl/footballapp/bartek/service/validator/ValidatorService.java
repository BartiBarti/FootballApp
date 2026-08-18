package pl.footballapp.bartek.service.validator;

import pl.footballapp.bartek.enums.ParameterName;
import pl.footballapp.bartek.model.ParameterModel;
import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.service.MatchweekService;
import pl.footballapp.bartek.service.ParameterService;
import pl.footballapp.bartek.service.TeamService;

import java.util.List;

public class ValidatorService {

    private TeamService teamService = new TeamService();
    private ParameterService parameterService = new ParameterService();

    private MatchweekService matchweekService = new MatchweekService();

    public ValidatorResult validateStartSeason(int seasonId) {
        ParameterModel parameterModel = parameterService.findParameterByName(ParameterName.ALL_TEAMS_NUMBER);
        ValidatorResult validatorResult = isTeamNumberValid(seasonId, parameterModel);
        if (!validatorResult.isValid()) {
            return validatorResult;
        }
        validatorResult = validateSchedule(seasonId, parameterModel);
        if (!validatorResult.isValid()) {
            return validatorResult;
        }
        return validatorResult;
    }

    private ValidatorResult isTeamNumberValid(int seasonId, ParameterModel parameterModel) {
        List<TeamModel> teamsAddedToSeason = teamService.findAllTeamsCurrentlyAddedToSeason(seasonId);
        ValidatorResult validatorResult = new ValidatorResult();

        if (teamsAddedToSeason.size() != parameterModel.getParameterIntValue()) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Nieodpowiednia liczba drużyn. Sezon musi zawierać dokładnie " + parameterModel.getParameterIntValue());
        } else {
            validatorResult.setValid(true);
        }
        return validatorResult;
    }

    private ValidatorResult validateSchedule(int seasonId, ParameterModel parameterModel) {
        int requiredTeams = parameterModel.getParameterIntValue();
        int requiredMatchweeks = (requiredTeams - 1) * 2;
        int matchweeksCount = matchweekService.countBySeason(seasonId);
        if (matchweeksCount == 0) {
            ValidatorResult validatorResult = new ValidatorResult();
            validatorResult.setValid(false);
            validatorResult.setMessage("Nie wygenerowano terminarza!");
            return validatorResult;
        }
        if (matchweeksCount != requiredMatchweeks) {
            ValidatorResult validatorResult = new ValidatorResult();
            validatorResult.setValid(false);
            validatorResult.setMessage("Niepoprawna liczba kolejek! \n Wymagane: "
                    + requiredMatchweeks + " Znaleziono: " + matchweeksCount);
            return validatorResult;
        }
        return null;
    }

}
