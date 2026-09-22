package pl.footballapp.bartek.service.validator;

import pl.footballapp.bartek.enums.ParameterName;
import pl.footballapp.bartek.model.ParameterModel;
import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.service.MatchResultService;
import pl.footballapp.bartek.service.MatchweekService;
import pl.footballapp.bartek.service.ParameterService;
import pl.footballapp.bartek.service.TeamService;

import java.util.List;

public class ValidatorService {

    private TeamService teamService = new TeamService();
    private ParameterService parameterService = new ParameterService();
    private MatchweekService matchweekService = new MatchweekService();

    private MatchResultService matchResultService = new MatchResultService();

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

    public ValidatorResult validateScheduleGeneration(int seasonId) {
        ValidatorResult validatorResult = new ValidatorResult();
        validatorResult.setValid(true);


        int matchweekCount = matchweekService.countBySeason(seasonId);
        int matchCount = matchResultService.countBySeason(seasonId);
        if (matchweekCount > 0 || matchCount > 0) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Terminarz dla sezonu został już wygenerowany!");
            return validatorResult;
        }

        ParameterModel parameterModel = parameterService.findParameterByName(ParameterName.ALL_TEAMS_NUMBER);
        validatorResult = isTeamNumberValid(seasonId, parameterModel);
        if (!validatorResult.isValid()) {
            return validatorResult;
        }

        validatorResult = validateTeamNumberForRoundRobin(seasonId);
        if (!validatorResult.isValid()) {
            return validatorResult;
        }

        return validatorResult;
    }

    private ValidatorResult validateTeamNumberForRoundRobin(int seasonId) {
        List<TeamModel> teamsAddedToSeason = teamService.findAllTeamsCurrentlyAddedToSeason(seasonId);
        ValidatorResult validatorResult = new ValidatorResult();
        validatorResult.setValid(true);

        if (teamsAddedToSeason.size() < 2) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Do wygenerowania terminarza potrzebne są \n" +
                    " minimum 2 drużyny. Dodano: " + teamsAddedToSeason.size());
            return validatorResult;
        }
        if (teamsAddedToSeason.size() % 2 != 0) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Terminarz wymaga parzystej liczby drużyn. Dostaliśmy: " + teamsAddedToSeason.size());
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
        int requiredMatches = requiredTeams * (requiredTeams - 1);
        int matchesCount = matchResultService.countBySeason(seasonId);
        ValidatorResult validatorResult = new ValidatorResult();
        validatorResult.setValid(true);

        if (matchweeksCount == 0) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Nie wygenerowano terminarza!");
        } else if (matchweeksCount != requiredMatchweeks) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Niepoprawna liczba kolejek! \n Wymagane: "
                    + requiredMatchweeks + " Znaleziono: " + matchweeksCount);
        } else if (matchesCount != requiredMatches) {
            validatorResult.setValid(false);
            validatorResult.setMessage("Niepoprawna liczba meczy! \n Wymagane: "
                    + requiredMatches + " Znaleziono: " + matchesCount);
        }
        return validatorResult;
    }

}
