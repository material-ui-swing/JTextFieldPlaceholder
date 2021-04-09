package io.vincenzopalazzo.placeholder.ui;

import io.vincenzopalazzo.placeholder.JTextFieldPlaceholder;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.PasswordView;
import javax.swing.text.View;

public class InternalPasswordFieldUI extends InternalTextFieldUI {

  public InternalPasswordFieldUI(JTextFieldPlaceholder textFieldPlaceholder) {
    super(textFieldPlaceholder);
  }

  @Override
  public void installUI(JComponent c) {
    super.installUI(c);
    Character echoChar = (Character) UIManager.getDefaults().get("PasswordField.echoChar");
    if (echoChar != null) {
      LookAndFeel.installProperty(getComponent(), "echoChar", echoChar);
    }
  }

  public View create(Element elem) {
    return new PasswordView(elem);
  }
}
