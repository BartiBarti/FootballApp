package pl.footballapp.bartek.gui;

import org.apache.commons.lang3.StringUtils;
import pl.footballapp.bartek.service.SeasonLeagueService;
import pl.footballapp.bartek.service.TeamService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTeam extends JFrame {

    private JPanel mainPanel;
    private JComboBox teamsComboBox;
    private JTextField newTeamTextField;
    private JButton selectTeamButton;
    private JButton addTeamButton;

    private TeamService teamService = new TeamService();

    private SeasonLeagueService seasonLeagueService = new SeasonLeagueService();

    public AddTeam(int seasonId) {
        setTitle("Dodawanie drużyny");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTeam = newTeamTextField.getText();
                boolean teamNameValid = true;
                if (StringUtils.isBlank(newTeam)) {
                    JOptionPane.showMessageDialog(AddTeam.this, "Nie wpisano nazwy drużyny!", "Błąd!", JOptionPane.ERROR_MESSAGE);
                    teamNameValid = false;
                } else if (teamService.teamExist(newTeam)) {
                    JOptionPane.showMessageDialog(AddTeam.this, "Drużyna już istnieje", "Błąd!", JOptionPane.ERROR_MESSAGE);
                    teamNameValid = false;
                }
                if (teamNameValid) {
                    int teamId = teamService.createTeam(newTeam);
                    if (teamId != -1) {
                        boolean teamAdded = seasonLeagueService.addTeamToSeasonLeague(teamId, seasonId);
                        if (teamAdded) {
                            JOptionPane.showMessageDialog(AddTeam.this,
                                    "Drużyna " + newTeam + " pomyślnie dodana do sezonu ligowego!",
                                    "Potwierdzenie",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(AddTeam.this,
                                    "Wystąpił błąd podczas dodania drużyny " + newTeam + " do sezonu ligowego.",
                                    "Błąd",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(AddTeam.this,
                                "Wystąpił błąd podczas dodania drużyny!",
                                "Błąd!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
    }


}
