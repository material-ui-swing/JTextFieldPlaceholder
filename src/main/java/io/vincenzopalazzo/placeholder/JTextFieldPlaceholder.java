package io.vincenzopalazzo.placeholder;

import io.vincenzopalazzo.placeholder.ui.BasicPlaceholderUI;
import io.vincenzopalazzo.placeholder.ui.BasicTextFieldPlaceholderUI;
import io.vincenzopalazzo.placeholder.ui.InternalPasswordFieldUI;
import io.vincenzopalazzo.placeholder.ui.InternalTextFieldUI;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JTextFieldPlaceholder extends JPanel {

  private static final String uiClassID = "TextFieldPlaceholderUI";

  protected JToggleButton iconContainer;
  protected JLabel placeholder;
  protected JSeparator separator;
  protected JTextField textField;
  protected boolean passwordField;
  protected int gapIconToPlaceholder;
  protected int gapSeparatorToPlaceholder;
  protected int gapTextToSeparator;

  public JTextFieldPlaceholder() {
    super();
    this.textField = new JTextField();
    this.passwordField = false;
    initView();
    updateUI();
  }

  public JTextFieldPlaceholder(JTextField textField) {
    super();
    this.textField = textField;
    passwordField = (textField instanceof JPasswordField);
    initView();
    updateUI();
  }

  @Override
  public void updateUI() {
    if (UIManager.get(getUIClassID()) != null) {
      BasicTextFieldPlaceholderUI ui = (BasicTextFieldPlaceholderUI) UIManager.getUI(this);
      setUI(ui);
    } else {
      BasicTextFieldPlaceholderUI ui = new BasicTextFieldPlaceholderUI();
      setUI(ui);
    }
    this.setCorrectTextFieldUI();

    if (this.placeholder == null) return;

    if (UIManager.get(BasicPlaceholderUI.PREFIX) != null) {
      BasicPlaceholderUI ui = (BasicPlaceholderUI) UIManager.getUI(placeholder);
      this.placeholder.setUI(ui);
    } else {
      BasicPlaceholderUI ui = new BasicPlaceholderUI();
      this.placeholder.setUI(ui);
    }
  }

  @Override
  public String getUIClassID() {
    return uiClassID;
  }

  protected void initView() {
    this.initComponent();
    this.initStyle();
    this.initLayout();
  }

  protected void initStyle() {
    setBackground(textField.getBackground());

    int height = this.placeholder.getFontMetrics(this.placeholder.getFont()).getHeight() - 5;
    separator.setPreferredSize(new Dimension(2, height));
    separator.setSize(new Dimension(2, height));
    separator.setMaximumSize(new Dimension(2, height));

    this.iconContainer.setFocusable(false);
    this.placeholder.setUI(new BasicPlaceholderUI());
  }

  protected void initComponent() {
    iconContainer = new JToggleButton();
    iconContainer.setOpaque(false);

    placeholder = new JLabel();
    placeholder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
    separator = new JSeparator(JSeparator.VERTICAL);
  }

  protected void initLayout() {
    this.setLayout(new BorderLayout());
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
    this.iconContainer.setBorder(
        BorderFactory.createEmptyBorder(0, 0, 0, this.gapIconToPlaceholder));
    leftPanel.add(this.iconContainer);
    this.placeholder.setBorder(
        BorderFactory.createEmptyBorder(0, 0, 0, this.gapSeparatorToPlaceholder));
    leftPanel.add(this.placeholder);
    this.separator.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, this.gapTextToSeparator));
    leftPanel.add(this.separator);
    this.add(leftPanel, BorderLayout.WEST);
    this.add(textField, BorderLayout.CENTER);
  }

  public JTextFieldPlaceholder setIcon(Icon icon) {
    if (icon == null) throw new IllegalArgumentException("icon null");
    iconContainer.setIcon(icon);
    return this;
  }

  public JTextFieldPlaceholder setSelectedIcon(Icon icon) {
    if (icon == null) throw new IllegalArgumentException("icon null");
    iconContainer.setSelectedIcon(icon);
    return this;
  }

  public JTextFieldPlaceholder setDisabledIcon(Icon icon) {
    if (icon == null) throw new IllegalArgumentException("icon null");
    iconContainer.setDisabledIcon(icon);
    return this;
  }

  public JTextFieldPlaceholder setPlaceholderText(String text) {
    if (text == null) throw new IllegalArgumentException("Invalid text");
    placeholder.setText(text);
    return this;
  }

  public void setEnabled(boolean value) {
    this.textField.setEnabled(value);
    this.iconContainer.setEnabled(value);
    this.repaint();
  }

  public boolean isEnabled() {
    return textField.isEnabled();
  }

  public JTextFieldPlaceholder setPlaceholderTextColor(Color colorLine) {
    this.placeholder.setForeground(colorLine);
    return this;
  }

  public JTextField getTextFiled() {
    return this.textField;
  }

  public JTextFieldPlaceholder setText(String text) {
    if (text == null) throw new IllegalArgumentException("Invalid text");
    this.textField.setText(text);
    return this;
  }

  public JTextFieldPlaceholder setDimension(int wight, int height) {
    Dimension dimension = new Dimension(wight, height);
    super.setMaximumSize(dimension);
    return this;
  }

  public JTextFieldPlaceholder setDimension(Dimension dimension) {
    super.setMaximumSize(dimension);
    return this;
  }

  public JTextFieldPlaceholder addAction(ActionListener action) {
    if (action == null) throw new IllegalArgumentException("Action not valid");
    this.iconContainer.addActionListener(action);
    return this;
  }

  public String getText() {
    return textField.getText();
  }

  public String getPlaceholderText() {
    return placeholder.getText();
  }

  public Icon getIcon() {
    return this.iconContainer.getIcon();
  }

  // getter and setter
  public JToggleButton getIconContainer() {
    return iconContainer;
  }

  public JLabel getPlaceholderComponent() {
    return placeholder;
  }

  public JSeparator getSeparator() {
    return separator;
  }

  public int getGapIconToPlaceholder() {
    return gapIconToPlaceholder;
  }

  public JTextFieldPlaceholder setGapIconToPlaceholder(int gapIconToPlaceholder) {
    this.gapIconToPlaceholder = gapIconToPlaceholder;
    return this;
  }

  public int getGapSeparatorToPlaceholder() {
    return gapSeparatorToPlaceholder;
  }

  public JTextFieldPlaceholder setGapSeparatorToPlaceholder(int gapSeparatorToPlaceholder) {
    this.gapSeparatorToPlaceholder = gapSeparatorToPlaceholder;
    return this;
  }

  public int getGapTextToSeparator() {
    return gapTextToSeparator;
  }

  public JTextFieldPlaceholder setGapTextToSeparator(int gapTextToSeparator) {
    this.gapTextToSeparator = gapTextToSeparator;
    return this;
  }

  protected void setCorrectTextFieldUI() {
    if (this.textField != null) {
      if (passwordField) {
        this.textField.setUI(new InternalPasswordFieldUI(this));
      } else {
        this.textField.setUI(new InternalTextFieldUI(this));
      }
    }
  }

  public boolean isSelected() {
    return this.iconContainer.isSelected();
  }
}
