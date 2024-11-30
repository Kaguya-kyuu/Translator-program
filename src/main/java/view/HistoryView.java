package view;

import interface_adapter.history.HistoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view of translation history.
 */
public class HistoryView extends JPanel implements PropertyChangeListener {
    private HistoryViewModel historyViewModel;

    private final JButton back;

    public HistoryView(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
        historyViewModel.addPropertyChangeListener(this);

        this.back = new JButton("Back");
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("History");
        frame.setBounds(400, 150, 800, 600);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panel);
        frame.add(scroll);
        for (int i = 0; i < 30; i++) {
            JButton button = new JButton(String.valueOf(i));
            panel.add(button, 0);
        }
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
