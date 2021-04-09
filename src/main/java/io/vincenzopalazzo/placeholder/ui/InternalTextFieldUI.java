package io.vincenzopalazzo.placeholder.ui;

import io.vincenzopalazzo.placeholder.JTextFieldPlaceholder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class InternalTextFieldUI extends BasicTextFieldUI {

  protected FocusListener focusListener = new LineFocusListener();
  protected JTextFieldPlaceholder textFieldPlaceholder;

  public InternalTextFieldUI(JTextFieldPlaceholder textFieldPlaceholder) {
    this.textFieldPlaceholder = textFieldPlaceholder;
  }

  @Override
  public void installUI(JComponent c) {
    super.installUI(c);

    JTextField textField = (JTextField) c;
    c.setBackground(UIManager.getColor("TextFieldPlaceholder.background"));
    c.setForeground(UIManager.getColor("TextFieldPlaceholder.foreground"));
    textField.setCaretColor(UIManager.getColor("TextFieldPlaceholder.caret"));
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
  }

  @Override
  protected void paintBackground(Graphics g) {
    super.paintBackground(g);
  }

  @Override
  protected void installListeners() {
    super.installListeners();
    this.getComponent().addFocusListener(focusListener);
  }

  @Override
  protected void uninstallListeners() {
    super.uninstallListeners();
    this.getComponent().removeFocusListener(focusListener);
  }

  public class LineFocusListener implements FocusListener {

    @Override
    public void focusGained(FocusEvent e) {
      textFieldPlaceholder.repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
      textFieldPlaceholder.repaint();
    }
  }
}
