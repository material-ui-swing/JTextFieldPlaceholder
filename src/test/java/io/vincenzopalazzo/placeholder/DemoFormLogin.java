package io.vincenzopalazzo.placeholder;

import io.vincenzopalazzo.placeholder.util.RoundedCornerBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;
import mdlaf.utils.icons.MaterialIconFont;
import org.material.component.swingsnackbar.SnackBar;

public class DemoFormLogin extends JFrame {

  static {
    try {
      UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
      //UIManager.put("TextFieldPlaceholder[Line].inactiveColor", MaterialColors.BLACK);
      //UIManager.put("TextFieldPlaceholder[Line].activeColor", MaterialColors.LIGHT_BLUE_400);
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  private JPanel containerForm;
  private JPanel form;
  private JTextFieldPlaceholder usernameForm;
  private JTextFieldPlaceholder passwordFieldForm;
  private JButton submitForm;

  public DemoFormLogin() throws HeadlessException {
    this.initView();
  }

  void initView() {
    initComponent();
    initLayout();
    initAction();

    super.setTitle("Demo JTextFieldPlaceholder by @vincenzopalazzo");
    super.setSize(new Dimension(500, 500));
    super.setLocationRelativeTo(null);
    super.setVisible(true);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  void initComponent() {
    containerForm = new JPanel(new BorderLayout());

    form = new JPanel();
    form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
    //form.setBackground(MaterialColors.COSMO_STRONG_GRAY);
    form.setBorder(new RoundedCornerBorder(MaterialColors.COSMO_STRONG_GRAY, 15));

    this.usernameForm = new JTextFieldPlaceholder(new JTextField(12));

    this.usernameForm
        .setPlaceholderText("Username")
        .setIcon(MaterialImageFactory.getInstance().getImage(MaterialIconFont.STAR))
        .setSelectedIcon(
            MaterialImageFactory.getInstance()
                .getImage(MaterialIconFont.STAR, MaterialColors.LIGHT_BLUE_400))
        .setDimension(250, 35);

    form.add(Box.createVerticalStrut(35));
    form.add(usernameForm);
    form.add(Box.createVerticalStrut(10));
    this.passwordFieldForm = new JTextFieldPlaceholder(new JPasswordField());

    this.passwordFieldForm
        .setPlaceholderText("Password")
        .setIcon(
            MaterialImageFactory.getInstance().getImage(MaterialIconFont.VISIBILITY_OFF))
        .setSelectedIcon(
            MaterialImageFactory.getInstance()
                .getImage(MaterialIconFont.VISIBILITY, MaterialColors.LIGHT_BLUE_400))
        .setDimension(250, 35)
        .addAction(
            new AbstractAction() {
              @Override
              public void actionPerformed(ActionEvent e) {
                JPasswordField passwordField = (JPasswordField) passwordFieldForm.getTextFiled();
                if (!passwordFieldForm.isSelected()) {
                  passwordField.setEchoChar((char) UIManager.get("PasswordField.echoChar"));
                } else {
                  passwordField.setEchoChar((char) 0);
                }
              }
            });

    form.add(passwordFieldForm);
    form.add(Box.createVerticalStrut(25));

    this.submitForm = new JButton("LOGIN");
    super.setContentPane(containerForm);
  }

  void initAction() {
    this.submitForm.addActionListener(
        (e ->
            SnackBar.make(this, "Login fail", "CLOSE")
                .setIconTextColor(MaterialColors.COSMO_RED)
                .setSnackBarForeground(MaterialColors.WHITE)
                .run()));
  }

  void initLayout() {
    this.submitForm.setMaximumSize(new Dimension(150, 35));
    super.getRootPane().setDefaultButton(submitForm);
    form.add(Box.createVerticalStrut(15));
    form.add(submitForm);

    containerForm.add(Box.createVerticalStrut(50), BorderLayout.NORTH);
    containerForm.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
    containerForm.add(Box.createHorizontalStrut(50), BorderLayout.WEST);
    containerForm.add(Box.createVerticalStrut(50), BorderLayout.SOUTH);
    containerForm.add(form, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new DemoFormLogin());
  }
}
