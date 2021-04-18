package io.vincenzopalazzo.placeholder;

import java.awt.*;
import javax.swing.*;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.components.button.MaterialButtonUI;
import mdlaf.utils.MaterialColors;

public class JMarsSearch extends JTextFieldPlaceholder {

  private JLabel placeholderIcon;
  private JPanel placeholderContainer;
  private JButton searchButton;

  public JMarsSearch(Icon placeholderIcon) {
    super();
    this.placeholderIcon = new JLabel(placeholderIcon);
    this.placeholder.setForeground(MaterialColors.COSMO_DARK_GRAY);
  }

  public JMarsSearch(JTextField textField, Icon placeholderIcon) {
    super(textField);
    this.placeholderIcon = new JLabel(placeholderIcon);
  }

  @Override
  protected void initComponent() {
    super.initComponent();
    searchButton = new JButton(this.iconContainer.getIcon());
    this.searchButton.setUI(new SearchButtonUI());
    placeholderContainer = new JPanel();
    placeholderContainer.setBackground(this.getBackground());
    placeholderContainer.setLayout(new BorderLayout());
  }

  @Override
  public JTextFieldPlaceholder setIcon(Icon icon) {
    this.searchButton.setIcon(icon);
    return this;
  }

  @Override
  public JTextFieldPlaceholder setSelectedIcon(Icon icon) {
    return this;
  }

  @Override
  public JTextFieldPlaceholder setDisabledIcon(Icon icon) {
    return this;
  }

  @Override
  public void updateUI() {
    super.updateUI();
    if (this.searchButton != null) this.searchButton.setUI(new SearchButtonUI());
  }

  @Override
  protected void initLayout() {
    this.setLayout(new BorderLayout());
    this.add(searchButton, BorderLayout.WEST);
    this.add(textField, BorderLayout.CENTER);
    this.add(placeholderContainer, BorderLayout.EAST);
    if (this.placeholderIcon != null) {
      this.placeholderContainer.add(this.placeholderIcon, BorderLayout.WEST);
    }
    this.placeholderContainer.add(super.placeholder, BorderLayout.CENTER);
  }

  public JMarsSearch setPlaceholderIcon(Icon placeholderIcon) {
    this.placeholderIcon = new JLabel(placeholderIcon);
    return this;
  }

  @Override
  public JTextFieldPlaceholder setPlaceholderTextColor(Color colorLine) {
    super.setPlaceholderTextColor(colorLine);
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

  private class SearchButtonUI extends MaterialButtonUI {

    @Override
    public void installUI(JComponent c) {
      this.mouseHoverEnabled = false;
      super.installUI(c);
      this.borderEnabled = false;
      this.background = textField.getBackground();
      this.foreground = textField.getForeground();
      this.button.setBackground(this.background);
      this.mouseHover = MaterialUIMovement.getMovement(button, this.background);
      button.addMouseListener(this.mouseHover);
    }

    @Override
    protected void paintFocus(
        Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {}

    @Override
    protected void paintFocusRing(Graphics g, JButton b) {}
  }
}
