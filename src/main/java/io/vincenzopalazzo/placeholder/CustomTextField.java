package io.vincenzopalazzo.placeholder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class CustomTextField extends JTextField {

    private JTextFieldPlaceholder textFieldPlaceholder;

    public CustomTextField(JTextFieldPlaceholder placeholder) {
        setUI(new CustomTextFieldUI(placeholder));
        this.textFieldPlaceholder = placeholder;
    }

    @Override
    public void updateUI() {
        setUI(new CustomTextFieldUI(textFieldPlaceholder));
    }

    public static class CustomTextFieldUI extends BasicTextFieldUI{

        protected FocusListener focusListener = new LineFocusListener();
        protected JTextFieldPlaceholder textFieldPlaceholder;

        public CustomTextFieldUI(JTextFieldPlaceholder textFieldPlaceholder) {
            this.textFieldPlaceholder = textFieldPlaceholder;
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

        public class LineFocusListener implements FocusListener{

            @Override
            public void focusGained(FocusEvent e) {
                textFieldPlaceholder.doFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldPlaceholder.focusLose();
            }
        }
    }
}
