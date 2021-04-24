package io.vincenzopalazzo.placeholder.ui;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class BasicPlaceholderUI extends BasicLabelUI {

  public static final String PREFIX = "Placeholder";

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(JComponent c) {
    return new BasicPlaceholderUI();
  }

  @Override
  protected void installDefaults(JLabel c) {
    super.installDefaults(c);
    LookAndFeel.installColors(c, PREFIX + ".background", PREFIX + ".foreground");
  }
}
