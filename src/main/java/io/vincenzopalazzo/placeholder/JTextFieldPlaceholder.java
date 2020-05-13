package io.vincenzopalazzo.placeholder;

import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

public class JTextFieldPlaceholder extends JPanel {

    private static final String uiClassID = "TextFieldPlaceholderUI";

    //protected JLabel iconContainer;
    protected JToggleButton iconContainer;
    protected JLabel placeholder;
    protected JSeparator separator;
    protected JTextField textField;
    protected Color colorLine;

    public JTextFieldPlaceholder() {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.textField = new CustomTextField(this);
        initView();
        initStyle();
        updateUI();
    }

    public JTextFieldPlaceholder(JTextField textField) {
        super(new FlowLayout());
        this.textField = textField;
        this.textField.setUI(new CustomTextField.CustomTextFieldUI(this));
        initView();
        initStyle();
        updateUI();
    }

    //@Override
   public String getUIClassID() {
       return uiClassID;
    }

    protected void initView(){
        //iconContainer = new JLabel();
        iconContainer = new JToggleButton();
        iconContainer.setOpaque(false);
        this.add(iconContainer);

        placeholder = new JLabel();
        placeholder.setBorder(BorderFactory.createEmptyBorder(0,0,0,2));
        this.add(placeholder);

        separator = new JSeparator(JSeparator.VERTICAL);

        Dimension dimensionTextField = new Dimension(100, 25);
        System.out.println(dimensionTextField);
        textField.setMinimumSize(dimensionTextField);
        textField.setPreferredSize(dimensionTextField);
        textField.setSize(dimensionTextField);

        int heightString = placeholder.getFontMetrics(placeholder.getFont()).getHeight() - 5;

        super.add(Box.createHorizontalStrut(3));
        //separator.setForeground(placeholder.getForeground());
        //separator.setBackground(this.textField.getBackground());
        separator.setPreferredSize(new Dimension(2, heightString));
        separator.setSize(new Dimension(2, heightString));
        separator.setMaximumSize(new Dimension(2, heightString));
        super.add(separator);
        super.add(Box.createHorizontalStrut(10));
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

    public JTextFieldPlaceholder setSelectedIcon(Icon icon){
        if(icon == null) throw new IllegalArgumentException("icon null");
        iconContainer.setSelectedIcon(icon);
        return this;
    }

    public JTextFieldPlaceholder setPlaceholderText(String text){
        if(text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        placeholder.setText(text);
        return this;
    }

    public JTextFieldPlaceholder setPlaceholderTextColor(Color colorLine){
        this.placeholder.setForeground(colorLine);
        return this;
    }

    public JTextField getTextFiled(){
        return this.textField;
    }

    public JTextFieldPlaceholder setText(String text){
        if(text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        this.textField.setText(text);
        return this;
    }

    public JTextFieldPlaceholder setDimensionComponent(Dimension dimensionComponent){
        textField.setPreferredSize(dimensionComponent);
        textField.setSize(dimensionComponent);
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
                this.colorLine = UIManager.getColor("TextFieldPlaceholder[Line].activeColor");;
            }else{
                this.colorLine = UIManager.getColor("TextFieldPlaceholder[Line].inactiveColor");
            }
        }
        graphics.setColor(this.colorLine);
        graphics.fillRect(super.getRootPane().getX() + 5, this.textField.getY()  + this.textField.getHeight() + 1, this.getWidth() - 8, 1);
    }

    void doFocus(){
        this.colorLine = UIManager.getColor("TextFieldPlaceholder[Line].activeColor");
        this.repaint();
    }

    void focusLose(){
        this.colorLine = UIManager.getColor("TextFieldPlaceholder[Line].inactiveColor");
        this.repaint();
    }

    //getter and setter
    public JToggleButton getIconContainer() {
        return iconContainer;
    }

    public JLabel getPlaceholderComponent() {
        return placeholder;
    }

    public JSeparator getSeparator() {
        return separator;
    }
}
