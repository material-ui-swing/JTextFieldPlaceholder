package io.vincenzopalazzo.placeholder;

import java.awt.*;
import javax.swing.*;

public class JMarsSearch extends JTextFieldPlaceholder {

  private JLabel placeholderIcon;
  private JPanel placeholderContainer;

  public JMarsSearch(Icon placeholderIcon) {
    super();
    this.placeholderIcon = new JLabel(placeholderIcon);
  }

  public JMarsSearch(JTextField textField, Icon placeholderIcon) {
    super(textField);
    this.placeholderIcon = new JLabel(placeholderIcon);
  }

  @Override
  protected void initComponent() {
    super.initComponent();
    placeholderContainer = new JPanel();
    placeholderContainer.setBackground(this.getBackground());
    placeholderContainer.setLayout(new BorderLayout());
  }

  @Override
  protected void initLayout() {
    this.setLayout(new BorderLayout());
    this.add(iconContainer, BorderLayout.WEST);
    this.add(textField, BorderLayout.CENTER);
    this.add(placeholderContainer, BorderLayout.EAST);
    if (this.placeholderIcon != null) {
      this.placeholderIcon.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      this.placeholderContainer.add(this.placeholderIcon, BorderLayout.WEST);
    }
    this.placeholderContainer.add(super.placeholder, BorderLayout.CENTER);
  }

  public JMarsSearch setPlaceholderIcon(Icon placeholderIcon) {
    this.placeholderIcon = new JLabel(placeholderIcon);
    return this;
  }

  @Override
  public JTextFieldPlaceholder setPlaceholderText(String text) {
    if (text == null) throw new IllegalArgumentException("Invalid text");
    super.placeholder.setText(text);
    if (this.placeholderIcon != null) {
      this.placeholderIcon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
      this.placeholderContainer.add(this.placeholderIcon, BorderLayout.WEST);
    }
    this.placeholderContainer.add(super.placeholder, BorderLayout.CENTER);
    repaint();
    return this;
  }
}
