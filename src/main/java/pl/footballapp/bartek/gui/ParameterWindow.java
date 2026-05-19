package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.enums.ParameterType;
import pl.footballapp.bartek.model.ParameterModel;
import pl.footballapp.bartek.service.ParameterService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.*;

public class ParameterWindow extends JDialog {

    private List<ParameterField> fields = new ArrayList<>();

    private ParameterService parameterService = new ParameterService();

    public ParameterWindow() {
        setTitle("Parametry systemowe");
        setSize(400, 400);
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(formPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5, 5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        List<ParameterModel> parameters = parameterService.findAllParameters();
        int row = 0;
        for (ParameterModel parameter : parameters){
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel label = new JLabel(parameter.getParameterName().getPlParameterName());
            label.setHorizontalAlignment(SwingConstants.LEFT);
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            JComponent field = createParameterField(parameter);
            formPanel.add(field, gbc);

            fields.add(new ParameterField(parameter, field));

            row++;
        }

        JButton saveButton = new JButton("Zapisz");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveParameters();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private JComponent createField(ParameterModel parameter){
        JTextField textField = new JTextField();
        textField.setText(parameter.getParameterValue());
        textField.setPreferredSize(new Dimension(100, 25));
        return textField;
    }

    private JComponent createSpinner(ParameterModel parameter){
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);

        if(parameter.getParameterValue() != null){

            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(parameter.getParameterValue());
                dateSpinner.setValue(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return dateSpinner;
    }

    private JComponent createParameterField(ParameterModel parameter){
        if (parameter.getParameterType() == ParameterType.INTEGER){
            return createField(parameter);
        } else if (parameter.getParameterType() == ParameterType.DATE){
            return createSpinner(parameter);
        }

        throw new IllegalArgumentException("Unsupported Type: " + parameter.getParameterType());
    }
    private void saveParameters() {
        List<ParameterModel> updatedParameters = new ArrayList<>();
        for(ParameterField field : fields){
            ParameterModel parameter = field.getParameter();
            JComponent component = field.getComponent();

            if(parameter.getParameterType() == ParameterType.INTEGER){
                JTextField textField = (JTextField) component;
                parameter.setParameterValue(textField.getText());
            }
            if(parameter.getParameterType() == ParameterType.DATE){
                JSpinner spinner = (JSpinner) component;
                Date date = (Date) spinner.getValue();
                String sDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                parameter.setParameterValue(sDate);
            }
            updatedParameters.add(parameter);
        }
        parameterService.validateAndUpdateAllParameters(updatedParameters);
    }

}
