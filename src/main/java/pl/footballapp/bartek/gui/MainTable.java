package pl.footballapp.bartek.gui;

import pl.footballapp.bartek.model.TeamModel;
import pl.footballapp.bartek.repository.TeamRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


public class MainTable extends JFrame {
    private JPanel mainPanel;

    public MainTable() throws HeadlessException {
        setTitle("Football App");
        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        TeamRepository teamRepository = new TeamRepository();
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamName("Widzew");
//        teamRepository.save(teamModel);
        List<TeamModel> teamList = teamRepository.findAll();
        System.out.println(teamList);
        teamRepository.delete(1);
        List<TeamModel> teamList2 = teamRepository.findAll();
        System.out.println(teamList2);
        SwingUtilities.invokeLater(() -> new MainTable().setVisible(true));
    }
}
