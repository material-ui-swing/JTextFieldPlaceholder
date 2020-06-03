package io.vincenzopalazzo.placeholder.ui;

import io.vincenzopalazzo.placeholder.JTextFieldPlaceholder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InternalTextFieldUI extends BasicTextFieldUI {

    protected FocusListener focusListener = new LineFocusListener();
    protected JTextFieldPlaceholder textFieldPlaceholder;
    protected Color disabledBackground;
    protected Color disabledForeground;
    protected Color inactiveForeground;
    protected Color inactiveBackground;
    protected Color selectionBackground;
    protected Color selectionForeground;
    private JTextField textField;

    public InternalTextFieldUI(JTextFieldPlaceholder textFieldPlaceholder) {
        this.textFieldPlaceholder = textFieldPlaceholder;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        this.textField = (JTextField) c;
        c.setBackground(UIManager.getColor("TextFieldPlaceholder.background"));
        c.setForeground(UIManager.getColor("TextFieldPlaceholder.foreground"));
        textField.setCaretColor(UIManager.getColor("TextFieldPlaceholder.caret"));
        Color tmp = UIManager.getColor(this.getPropertyPrefix() + ".disabledBackground");
        this.disabledBackground = tmp == null ? Color.CYAN : tmp;
        tmp = UIManager.getColor(this.getPropertyPrefix() + ".disabledForeground");
        this.disabledForeground = tmp == null ? Color.CYAN : tmp;
        textField.setDisabledTextColor(disabledForeground);
        tmp = UIManager.getColor(this.getPropertyPrefix() + ".inactiveForeground");
        this.inactiveForeground = tmp == null ? Color.CYAN : tmp;
        tmp = UIManager.getColor(this.getPropertyPrefix() + ".inactiveBackground");
        this.inactiveBackground = tmp == null ? Color.CYAN : tmp;
        tmp = UIManager.getColor(this.getPropertyPrefix() + ".selectionBackground");
        this.selectionBackground = tmp == null ? Color.CYAN : tmp;
        tmp = UIManager.getColor(this.getPropertyPrefix() + ".selectionForeground");
        this.selectionForeground = tmp == null ? Color.BLACK : tmp;
        textField.setSelectionColor(selectionBackground);
        textField.setSelectedTextColor(selectionForeground);
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

    @Override //TODO i lose some event
    protected String getPropertyPrefix() {
        return "TextFieldPlaceholder";
    }

    public class LineFocusListener implements FocusListener{

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
