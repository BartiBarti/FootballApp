package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.enums.SeasonStatus;
import pl.footballapp.bartek.model.SeasonLeagueModel;
import pl.footballapp.bartek.model.SeasonModel;
import pl.footballapp.bartek.service.SeasonLeagueService;
import pl.footballapp.bartek.service.SeasonService;
import pl.footballapp.bartek.service.TeamService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainTable extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton addTeamButton;
    private JButton deleteTeamButton;
    private JButton enterResultButton;
    private JButton startSeasonButton;
    private JButton endSeasonButton;
    private JLabel tableTitleLabel;
    private JTable seasonLeagueTable;
    private JLabel seasonStatusLabel;
    private JLabel seasonLabel;
    private JComboBox seasonComboBox;
    private JScrollPane scrollPane;
    private SeasonModel choosenSeason;

    private SeasonLeagueService seasonLeagueService = new SeasonLeagueService();

    private SeasonService seasonService = new SeasonService();

    private TeamService teamService = new TeamService();

    private static MainTable mainTable = new MainTable();

    public MainTable() {
        setTitle("Football App");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        fillSeasonComboBox();
        setButtonsVisibility();
        loadTable();
        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new AddTeam(choosenSeason.getSeasonId(), mainTable).setVisible(true));

            }
        });
    }

    public void loadTable() {
        String[] headers = new String[]{"#", "Drużyna", "M", "Z", "R", "P", "B+", "B-", "RB", "Punkty"};
        DefaultTableModel tableModel = new DefaultTableModel(headers, 0);
        tableModel.setRowCount(0);
//        todo posortować drużyny zgodnie z zasadmi piłki nożnej
//        1. Sortujemy po liczbie zdobytych punktów
//        2. Sortujemy po różnicy bramkowej
//        3. Sortujemy po liczbie zdobytych bramek
//        3. Sortujemy po liczbie straconych bramek (im mniej tym drużyna wyżej)

        List<SeasonLeagueModel> seasonLeagueList = seasonLeagueService.findAllSeasonLeagueTeams(choosenSeason.getSeasonId());

        int teamOrdinalNumber = 1;
        for (SeasonLeagueModel seasonLeagueTeam : seasonLeagueList) {
            tableModel.addRow(new Object[]{teamOrdinalNumber,
                    teamService.findTeam(seasonLeagueTeam.getTeamId()).getTeamName(),
                    seasonLeagueTeam.getMatches(),
                    seasonLeagueTeam.getMatchesWin(),
                    seasonLeagueTeam.getMatchesDraw(),
                    seasonLeagueTeam.getMatchesLoss(),
                    seasonLeagueTeam.getGoalsScored(),
                    seasonLeagueTeam.getGoalsLost(),
                    seasonLeagueTeam.getGoalsDifference(),
                    seasonLeagueTeam.getPoints()});
            teamOrdinalNumber++;
        }

        seasonLeagueTable.setModel(tableModel);
        seasonLeagueTable.getColumnModel().getColumn(0).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        seasonLeagueTable.getColumnModel().getColumn(2).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(3).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(4).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(5).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(6).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(7).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(8).setPreferredWidth(35);
        seasonLeagueTable.getColumnModel().getColumn(9).setPreferredWidth(35);
    }

    private void fillSeasonComboBox() {
        DefaultComboBoxModel<SeasonModel> model =
                new DefaultComboBoxModel<>();
        List<SeasonModel> seasons = seasonService.findAllSeasonsWithCurrent();
        for (SeasonModel season : seasons) {
            model.addElement(season);
        }
        choosenSeason = seasons.get(0);
        seasonStatusLabel.setText(choosenSeason.getSeasonStatus().getPlTranslation());

        seasonComboBox.setModel(model);
    }

    private void setButtonsVisibility(){
        SeasonStatus choosenSeasonStatus = choosenSeason.getSeasonStatus();
        if(SeasonStatus.OPEN == choosenSeasonStatus) {
            endSeasonButton.setEnabled(false);
            enterResultButton.setEnabled(false);
            addTeamButton.setEnabled(true);
            deleteTeamButton.setEnabled(true);
            startSeasonButton.setEnabled(true);
        } else if (SeasonStatus.IN_PROGRESS == choosenSeasonStatus) {
            endSeasonButton.setEnabled(true);
            enterResultButton.setEnabled(true);
            addTeamButton.setEnabled(false);
            deleteTeamButton.setEnabled(false);
            startSeasonButton.setEnabled(false);
        } else if (SeasonStatus.CLOSED == choosenSeasonStatus) {
            endSeasonButton.setEnabled(false);
            enterResultButton.setEnabled(false);
            addTeamButton.setEnabled(false);
            deleteTeamButton.setEnabled(false);
            startSeasonButton.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mainTable.setVisible(true));
    }

    private void createUIComponents() {
        seasonComboBox = new JComboBox();
    }
}
