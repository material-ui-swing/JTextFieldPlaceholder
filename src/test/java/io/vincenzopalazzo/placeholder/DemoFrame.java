package io.vincenzopalazzo.placeholder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import mdlaf.utils.MaterialImageFactory;
import mdlaf.utils.icons.MaterialIconFont;
import org.material.component.swingsnackbar.SnackBar;
import org.material.component.swingsnackbar.action.AbstractSnackBarAction;

public class DemoFrame extends JFrame {

  static {
    try {
     /* UIManager.put("TextFieldPlaceholder.placeholderColor", MaterialColors.COSMO_BLACK);
      UIManager.put("TextFieldPlaceholder.background", MaterialColors.COSMO_LIGTH_GRAY);
      UIManager.put("TextFieldPlaceholder.foreground", MaterialColors.BLACK);
      UIManager.put("TextFieldPlaceholder[Line].activeColor", MaterialColors.COSMO_BLUE);
      UIManager.put("TextFieldPlaceholder[Line].inactiveColor", MaterialColors.BLACK);
      UIManager.put("TextFieldPlaceholder.caret", MaterialColors.BLACK);
      UIManager.put("TextFieldPlaceholder.separatorColor", MaterialColors.PINK_700);
      // UIManager.put("TextFieldPlaceholder.gapIconToPlaceholder", 10);
      // UIManager.put("TextFieldPlaceholder.gapSeparatorToPlaceholder", 10);
      // UIManager.put("TextFieldPlaceholder.gapTextToSeparator", 10);

      // TODO implement this
      UIManager.put("TextFieldPlaceholder.disabledBackground", MaterialColors.COSMO_DARK_GRAY);
      UIManager.put("TextFieldPlaceholder.disabledForeground", MaterialColors.GRAY_700);
      UIManager.put("TextFieldPlaceholder.inactiveForeground", MaterialColors.COSMO_LIGTH_GRAY);
      UIManager.put("TextFieldPlaceholder.inactiveBackground", MaterialColors.BLACK);
      UIManager.put("TextFieldPlaceholder.selectionBackground", MaterialColors.LIGHT_BLUE_400);
      UIManager.put("TextFieldPlaceholder.selectionForeground", MaterialColors.BLACK);*/
      UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  private JFrame frame = this;
  private JPanel container;
  private JMarsSearch textFieldPlaceholder;

  public void initView() {
    initComponent();
    textFieldPlaceholder.setText("DISABLED");
    //textFieldPlaceholder.setEnabled(false);
    super.setContentPane(container);
    super.setSize(new Dimension(400, 400));
    super.setTitle("New Swing component from @vincenzopalazzo");
    super.setLocationRelativeTo(null);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setVisible(true);
  }

  public void initComponent() {
    container = new JPanel();
    // Init component
    Icon icon = MaterialImageFactory.getInstance().getImage(MaterialIconFont.LOCATION_SEARCHING);
    textFieldPlaceholder = new JMarsSearch(icon);
    icon = MaterialImageFactory.getInstance().getImage(MaterialIconFont.SEARCH);
    textFieldPlaceholder.setIcon(icon);
    textFieldPlaceholder.setSelectedIcon(icon);
    // configure component
    textFieldPlaceholder.setPlaceholderText("Lat/Lon");
    container.add(textFieldPlaceholder);

    JButton button =
        new JButton(
            MaterialImageFactory.getInstance()
                .getImage(MaterialIconFont.SEND, MaterialColors.WHITE));

    button.addActionListener(
        new AbstractAction() {
          private SnackBar snackBar;

          @Override
          public void actionPerformed(ActionEvent e) {
            textFieldPlaceholder.setEnabled(!textFieldPlaceholder.isEnabled());
            snackBar =
                SnackBar.make(
                        frame,
                        textFieldPlaceholder.getPlaceholderText()
                            + " "
                            + textFieldPlaceholder.getText(),
                        "CLOSE")
                    .setGap(80)
                    .setIconTextStyle(
                        MaterialFontFactory.getInstance().getFont(MaterialFontFactory.BOLD))
                    .setIconTextColor(MaterialColors.COSMO_RED)
                    .setDuration(SnackBar.LENGTH_LONG)
                    .setAction(
                        new AbstractSnackBarAction() {
                          @Override
                          public void mousePressed(MouseEvent e) {
                            snackBar.dismiss();
                          }
                        })
                    .run();
          }
        });

    container.add(button);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater( () -> {
            DemoFrame demoFrame = new DemoFrame();
            demoFrame.initView();
        });
  }
}
