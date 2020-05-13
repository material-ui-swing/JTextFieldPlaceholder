package io.vincenzopalazzo.placeholder.ui;

import io.vincenzopalazzo.placeholder.JTextFieldPlaceholder;
import io.vincenzopalazzo.placeholder.util.ComponentUtil;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;

public class BasicTextFieldPlaceholderUI extends BasicPanelUI {

    protected static final String PREFIX = "TextFieldPlaceholder";
    protected static final String PREFIX_FATHER = "Panel";

    protected static String UNSELECTED_ICON_PATH = "/unselected.png";
    protected static String SELECTED_ICON_PATH = "/selected.png";

    protected Color background;
    protected Color foreground;
    protected Color colorFocusLine;
    protected Color colorUnfocusLine;
    protected JTextFieldPlaceholder textFieldPlaceholder;
    protected JTextField textField;

    @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
    public static ComponentUI createUI(JComponent c) {
        return new BasicTextFieldPlaceholderUI();
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

        JSeparator separator = textFieldPlaceholder.getSeparator();
        if(separator != null){

            separator.setBackground(this.background);
            separator.setForeground(this.foreground);
        }
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
        this.colorFocusLine = UIManager.getColor(this.getPrefix() + ".focusColorLine") == null ? Color.CYAN : this.colorFocusLine;
        this.colorUnfocusLine = UIManager.getColor(this.getPrefix() + ".unfocusColorLine") == null ? Color.GRAY : this.colorUnfocusLine;
    }

    //getter
    public String getPrefix() {
        return PREFIX;
    }
}
