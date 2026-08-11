package pl.footballapp.bartek.service.validator;

import pl.footballapp.bartek.enums.ParameterName;
import pl.footballapp.bartek.model.ParameterModel;
import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.service.ParameterService;
import pl.footballapp.bartek.service.TeamService;

import java.util.List;

public class ValidatorService {

    private TeamService teamService = new TeamService();
    private ParameterService parameterService = new ParameterService();

    public ValidatorResult validateStartSeason(int seasonId){
        ValidatorResult validatorResult = isTeamNumberValid(seasonId);
        if (!validatorResult.isValid()){
            return validatorResult;
        }
        return null;
    }

    private ValidatorResult isTeamNumberValid(int seasonId){
        List<TeamModel> teamsAddedToSeason = teamService.findAllTeamsCurrentlyAddedToSeason(seasonId);
        ParameterModel parameterModel = parameterService.findParameterByName(ParameterName.ALL_TEAMS_NUMBER);

        ValidatorResult validatorResult = new ValidatorResult();

        if(teamsAddedToSeason.size() != parameterModel.getParameterIntValue()){
            validatorResult.setValid(false);
            validatorResult.setMessage("Nieodpowiednia liczba drużyn. Sezon musi zawierać dokładnie " + parameterModel.getParameterIntValue());
        } else {
            validatorResult.setValid(true);
        }
        return validatorResult;
    }

}
