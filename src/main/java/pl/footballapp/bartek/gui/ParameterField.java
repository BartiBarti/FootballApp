package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.model.ParameterModel;

import javax.swing.*;

public class ParameterField {

    private ParameterModel parameter;

    private JComponent component;

    public ParameterField(ParameterModel parameter, JComponent component) {
        this.parameter = parameter;
        this.component = component;
    }

    public ParameterModel getParameter() {
        return parameter;
    }

    public void setParameter(ParameterModel parameter) {
        this.parameter = parameter;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }


}
