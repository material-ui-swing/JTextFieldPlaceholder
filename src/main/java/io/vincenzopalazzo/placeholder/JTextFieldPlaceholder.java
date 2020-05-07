package io.vincenzopalazzo.placeholder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JTextFieldPlaceholder extends JPanel {

    protected JLabel iconContainer;
    protected JLabel placeholder;
    private JSeparator separator;
    protected JTextField textField;

    public JTextFieldPlaceholder() {
        super(new FlowLayout());
        initView();
        initStyle();
    }

    protected void initView(){
        iconContainer = new JLabel();
        this.add(iconContainer);

        placeholder = new JLabel();
        separator = new JSeparator(JSeparator.VERTICAL);
        placeholder.setBorder(BorderFactory.createEmptyBorder(5,0,5,2));
        this.add(placeholder);
        this.add(separator);

        textField = new JTextField();
        textField.setMinimumSize(new Dimension(50, 20));
        textField.setPreferredSize(new Dimension(50, 20));
        textField.setSize(new Dimension(50, 20));
        super.add(textField);
    }

    protected void initStyle(){
        setBackground(textField.getBackground());
        placeholder.setBackground(getBackground());
        iconContainer.setBackground(getBackground());
    }

    public JTextFieldPlaceholder setIcon(Icon icon){
        if(icon == null) throw new IllegalArgumentException("icon null");
        iconContainer.setIcon(icon);
        return this;
    }

    public JTextFieldPlaceholder setText(String text){
        if(text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        placeholder.setText(text);
        return this;
    }

}
