package pl.footballapp.bartek.service;

import org.apache.commons.lang3.StringUtils;
import pl.footballapp.bartek.enums.ParameterName;
import pl.footballapp.bartek.model.ParameterModel;
import pl.footballapp.bartek.repository.ParameterRepository;

import javax.swing.*;
import java.util.List;

public class ParameterService {

    private ParameterRepository parameterRepository = new ParameterRepository();

    public List<ParameterModel> findAllParameters() {
        return parameterRepository.findAll();
    }

    public ParameterModel findParameterByName(ParameterName parameterName) {
        return parameterRepository.findByParameterName(parameterName);
    }

    public void validateAndUpdateAllParameters(List<ParameterModel> parameters) {
        boolean allParametersValid = true;
        for (ParameterModel parameter : parameters) {
            if (StringUtils.isBlank(parameter.getParameterValue())) {
                allParametersValid = false;
            }
        }
        if (allParametersValid) {
            parameterRepository.updateAll(parameters);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Błędnie wypełniono wartości parametrów.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
