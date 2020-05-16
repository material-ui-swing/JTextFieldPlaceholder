package io.vincenzopalazzo.placeholder.ui;

import io.vincenzopalazzo.placeholder.JTextFieldPlaceholder;
import io.vincenzopalazzo.placeholder.listener.AbstractFocusComponent;
import io.vincenzopalazzo.placeholder.util.ComponentUtil;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BasicTextFieldPlaceholderUI extends BasicPanelUI {

    protected static final String PREFIX = "TextFieldPlaceholder";
    protected static final String PREFIX_FATHER = "Panel";

    protected static String UNSELECTED_ICON_PATH = "/icons/unselected.png";
    protected static String SELECTED_ICON_PATH = "/icons/selected.png";

    protected Color background;
    protected Color foreground;
    protected Color colorFocusLine;
    protected Color colorUnfocusLine;
    protected JTextFieldPlaceholder textFieldPlaceholder;
    protected JTextField textField;
    protected TextFieldPlaceholderFocusListener focusListener;

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new BasicTextFieldPlaceholderUI();
    }

    public BasicTextFieldPlaceholderUI() {
        this.focusListener = new TextFieldPlaceholderFocusListener();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        textFieldPlaceholder = (JTextFieldPlaceholder) c;
        JToggleButton iconContainer = textFieldPlaceholder.getIconContainer();
        Icon selectedIcon = UIManager.getIcon(this.getPrefix() + ".selectedIcon");
        Icon icon = UIManager.getIcon(this.getPrefix() + ".icon");
        if (icon == null || selectedIcon == null) {
            selectedIcon = ComponentUtil.loadImage(SELECTED_ICON_PATH);
            icon = ComponentUtil.loadImage(UNSELECTED_ICON_PATH);
        }
        if (iconContainer != null) {
            iconContainer.setIcon(icon);
            iconContainer.setSelectedIcon(selectedIcon);
            iconContainer.setOpaque(false);
            iconContainer.setBackground(this.background);
        }

        JLabel placeholder = textFieldPlaceholder.getPlaceholderComponent();
        if (placeholder != null) {
            Color placeholderColor = UIManager.getColor(this.getPrefix().concat(".placeholderColor"));
            placeholderColor = placeholderColor == null ? Color.DARK_GRAY : placeholderColor;
            placeholder.setOpaque(false);
            placeholder.setForeground(placeholderColor);
            placeholder.setBackground(this.background);
        }
/*
        if(textField != null){
            textField = textFieldPlaceholder.getTextFiled();
            textField.setForeground(this.foreground);
            textField.setBackground(this.background);
        }
*/
        this.textField = this.textFieldPlaceholder.getTextFiled();

        JSeparator separator = textFieldPlaceholder.getSeparator();
        if(separator != null){

            separator.setBackground(this.background);
            separator.setForeground(this.foreground);
        }

        this.installListener();
    }

    @Override
    protected void installDefaults(JPanel p) {
        super.installDefaults(p);

        if (UIManager.getColor(this.getPrefix() + ".background") == null &&
                UIManager.getColor(this.getPrefix() + ".foreground") == null) {
            LookAndFeel.installColorsAndFont(p, PREFIX_FATHER + ".background", PREFIX_FATHER + ".foreground", ".font");
            LookAndFeel.installBorder(p, PREFIX_FATHER + ".border");
        } else {
            LookAndFeel.installColorsAndFont(p, this.getPrefix() + ".background", this.getPrefix() + ".foreground", ".font");
            LookAndFeel.installBorder(p, this.getPrefix() + ".border");
        }
        this.background = p.getBackground();
        this.foreground = p.getForeground();
        this.colorFocusLine = UIManager.getColor(this.getPrefix() + "[Line].activeColor") == null ? Color.CYAN : this.colorFocusLine;
        this.colorUnfocusLine = UIManager.getColor(this.getPrefix() + "[Line].inactiveColore") == null ? Color.GRAY : this.colorUnfocusLine;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        this.paintLine(g);
    }

    @Override
    public void update(Graphics g, JComponent c) {
        super.update(g, c);
        this.paintLine(g);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        this.uninstallListener();
        this.textField = null;
        this.focusListener = null;
        this.textFieldPlaceholder = null;
        this.colorUnfocusLine = null;
        this.colorFocusLine = null;
    }

    protected void installListener(){
        this.textFieldPlaceholder.addMouseListener(focusListener);
    }

    protected void uninstallListener(){
        this.textFieldPlaceholder.removeMouseListener(focusListener);
    }

    protected void paintLine(Graphics graphics) {
        if (colorFocusLine == null || colorUnfocusLine == null) {
            if (textField.isFocusOwner()) {
                this.colorFocusLine = UIManager.getColor("TextFieldPlaceholder[Line].activeColor");
            } else {
                this.colorUnfocusLine = UIManager.getColor("TextFieldPlaceholder[Line].inactiveColor");
            }
        }
        Color colorLine;
        if(this.textField.isFocusOwner()){
            colorLine = this.colorFocusLine;
        }else{
            colorLine = this.colorUnfocusLine;
        }
        graphics.setColor(colorLine);
        graphics.fillRect(this.textFieldPlaceholder.getRootPane().getX() + 5, this.textField.getY() + this.textField.getHeight() + 1, this.textFieldPlaceholder.getWidth() - 8, 1);
    }
    //getter
    public String getPrefix() {
        return PREFIX;
    }

    protected class TextFieldPlaceholderFocusListener extends AbstractFocusComponent {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(textField != null){
                textField.requestFocusInWindow();
            }
        }
    }
}
