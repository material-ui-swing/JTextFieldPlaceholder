package io.vincenzopalazzo.placeholder;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

public class JTextFieldPlaceholder extends JPanel {

    protected JLabel iconContainer;
    protected JLabel placeholder;
    protected CustomTextField textField;
    protected Color colorLine;

    public JTextFieldPlaceholder() {
        super(new FlowLayout());
        initView();
        initStyle();
    }

    protected void initView(){
        iconContainer = new JLabel();
        this.add(iconContainer);

        placeholder = new JLabel();
        placeholder.setBorder(BorderFactory.createEmptyBorder(0,0,0,2));
        this.add(placeholder);

        textField = new CustomTextField(this);
        textField.setMinimumSize(new Dimension(50, 20));
        textField.setPreferredSize(new Dimension(95, 20));
        textField.setSize(new Dimension(95, 20));
        super.add(textField);

        textField.setBorder(BorderFactory.createEmptyBorder());
        setBorder(new RoundedCornerBorder(getBackground(), 6));
    }

    protected void initStyle(){
        setBackground(textField.getBackground());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintLine(g);
    }

    public JTextFieldPlaceholder setIcon(Icon icon){
        if(icon == null) throw new IllegalArgumentException("icon null");
        iconContainer.setIcon(icon);
        return this;
    }

    public JTextFieldPlaceholder setPlaceholderText(String text){
        if(text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        placeholder.setText(text);
        return this;
    }

    public JTextFieldPlaceholder setText(String text){
        if(text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        this.textField.setText(text);
        return this;
    }

    public String getText(){
        return textField.getText();
    }

    public String getPlaceholderText(){
        return placeholder.getText();
    }

    public Icon getIcon(){
        return this.iconContainer.getIcon();
    }

    protected void paintLine(Graphics graphics){
        if(colorLine == null){
            if(textField.isFocusOwner()){
                this.colorLine = UIManager.getColor("TextField[Line].activeColor");;
            }else{
                this.colorLine = UIManager.getColor("TextField[Line].inactiveColor");
            }
        }
        graphics.setColor(this.colorLine);
        graphics.fillRect(iconContainer.getX(), this.getHeight() - this.getY(), this.getWidth() - iconContainer.getWidth(), 1);
    }

    void doFocus(){
        this.colorLine = UIManager.getColor("TextField[Line].activeColor");
        this.repaint();
    }

    void focusLose(){
        this.colorLine = UIManager.getColor("TextField[Line].inactiveColor");
        this.repaint();
    }


}
