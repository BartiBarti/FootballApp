package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.enums.SeasonStatus;
import pl.footballapp.bartek.model.SeasonModel;
import pl.footballapp.bartek.service.SeasonService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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

    private SeasonService seasonService = new SeasonService();

    public MainTable() {
        setTitle("Football App");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);

        fillSeasonComboBox();
        loadTable();
    }

    private void loadTable() {
        String[] headers = new String[]{"#", "Drużyna", "M", "Z", "R", "P", "B+", "B-", "RB", "Punkty"};
        DefaultTableModel tableModel = new DefaultTableModel(headers, 0);
        tableModel.setRowCount(0);
//        todo zasilenie tabeli ligowej w zależności od wybranego sezonu.

        seasonLeagueTable.setModel(tableModel);
    }

    private void fillSeasonComboBox() {
        DefaultComboBoxModel<SeasonModel> model =
                new DefaultComboBoxModel<>();
        List<SeasonModel> seasons = seasonService.findAllSeasonsWithCurrent();
        for (SeasonModel season : seasons) {
            model.addElement(season);
        }
        SeasonStatus seasonStatus = seasons.get(0).getSeasonStatus();
        seasonStatusLabel.setText(seasonStatus.getPlTranslation());

        seasonComboBox.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainTable().setVisible(true));
    }

    private void createUIComponents() {
        seasonComboBox = new JComboBox();
    }
}
