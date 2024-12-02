package view;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryViewModel;
import org.json.JSONArray;
import org.json.JSONString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view of translation history.
 */
public class HistoryView extends JPanel implements PropertyChangeListener {
    private final String viewname = "history";
    private HistoryViewModel historyViewModel;
    private HistoryController historyController;

    private final JButton back;

    public HistoryView(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
        historyViewModel.addPropertyChangeListener(this);

        this.back = new JButton("Back");

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        historyController.switchBackToTranslateView();
                    }
                }
        );
    }


    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange (PropertyChangeEvent evt){

    }

    public String getViewName() {
        return viewname;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("History");
        frame.setBounds(400, 150, 800, 600);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panel);
        frame.add(scroll);
//        for (int i = 0; i < 30; i++) {
//            JButton button = new JButton(String.valueOf(i));
//            panel.add(button, 0);
//        }
        JTextField textField = new JTextField(10);
        panel.add(textField);
        textField.setText("This is a test");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
