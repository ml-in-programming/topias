package ui;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;
import navigation.ReferenceNavigator;
import navigation.ui.ReferenceListCellRenderer;
import navigation.wrappers.Reference;
import settings.TopiasSettingsState;
import settings.enums.DiscrType;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MySideBar implements ProjectComponent {
    private JBList<Reference> list;
    private JPanel panel;
    private Project project;

    public MySideBar(Project project) {
        this.project = project;
        list.setCellRenderer(new ReferenceListCellRenderer());
        list.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (list.getSelectedIndex() != -1) {
                        new ReferenceNavigator(((Reference) list.getSelectedValue())).navigateToReference(project);
                    }
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    new ReferenceNavigator(list.getSelectedValue()).navigateToReference(project);
                }
            }
        });
        final TopiasSettingsState state = TopiasSettingsState.getInstance(project);
        final String title = "Most changed methods in the last " + DiscrType.getById(state.getState().discrTypeId).textValue;
        ((TitledBorder) panel.getBorder()).setTitle(title);
    }

    public JPanel getPanel() {
        panel.add(list);
        return panel;
    }

    public void updateListItems(List<Reference> references) {
        list.removeAll();
        final Reference[] referencesArray = new Reference[references.size()];
        list.setListData(references.toArray(referencesArray));
        list.setCellRenderer(new ReferenceListCellRenderer());
        list.setSelectedIndex(references.size() > 0 ? 0 : -1);
        list.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (list.getSelectedIndex() != -1) {
                        new ReferenceNavigator(((Reference) list.getSelectedValue())).navigateToReference(project);
                    }
                }
            }
        });

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    new ReferenceNavigator(((Reference) list.getSelectedValue())).navigateToReference(project);
                }
            }
        });
        final TopiasSettingsState state = TopiasSettingsState.getInstance(project);
        final String title = "Most changed methods in the last " + DiscrType.getById(state.getState().discrTypeId).textValue;
        ((TitledBorder) panel.getBorder()).setTitle(title);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setEnabled(true);
        panel.setBorder(BorderFactory.createTitledBorder("Most changed methods in the last 30 days"));
        list = new JBList();
        panel.add(list, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
