package io.vincenzopalazzo.placeholder;

import io.vincenzopalazzo.placeholder.ui.BasicTextFieldPlaceholderUI;
import io.vincenzopalazzo.placeholder.ui.InternalPasswordFieldUI;
import io.vincenzopalazzo.placeholder.ui.InternalTextFieldUI;
import io.vincenzopalazzo.placeholder.util.RoundedCornerBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JTextFieldPlaceholder extends JPanel {

    private static final String uiClassID = "TextFieldPlaceholderUI";

    protected JToggleButton iconContainer;
    protected JLabel placeholder;
    protected JSeparator separator;
    protected JTextField textField;
    protected boolean passwordField;

    public JTextFieldPlaceholder() {
        super();
        this.textField = new JTextField();
        initView();
        initStyle();
        updateUI();
    }

    public JTextFieldPlaceholder(JTextField textField) {
        super();
        this.textField = textField;
        passwordField = (textField instanceof JPasswordField);
        initView();
        updateUI();
    }

    @Override
    public void updateUI() {
        if (UIManager.get(getUIClassID()) != null) {
            BasicTextFieldPlaceholderUI ui = (BasicTextFieldPlaceholderUI) UIManager.getUI(this);
            setUI(ui);
        } else {
            BasicTextFieldPlaceholderUI ui = new BasicTextFieldPlaceholderUI();
            setUI(ui);
        }
        this.setCorrectTextFieldUI();
    }

    @Override
    public String getUIClassID() {
        return uiClassID;
    }

    protected void initView() {
        this.initComponent();
        this.initStyle();
        this.initLayout();
    }

    protected void initStyle() {
        setBackground(textField.getBackground());

        setBorder(new RoundedCornerBorder(getBackground(), 7));

        int height = this.placeholder.getFontMetrics(this.placeholder.getFont()).getHeight();
        separator.setPreferredSize(new Dimension(2, height));
        separator.setSize(new Dimension(2, height));
        separator.setMaximumSize(new Dimension(2, height));

        this.iconContainer.setFocusable(false);
        this.placeholder.setFocusable(false);
    }

    protected void initComponent() {
        iconContainer = new JToggleButton();
        iconContainer.setOpaque(false);

        placeholder = new JLabel();
        placeholder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));

        separator = new JSeparator(JSeparator.VERTICAL);
    }

    protected void initLayout() {
        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(this.iconContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(this.placeholder, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(this.separator, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(2)
                        .addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
                        .addComponent(this.iconContainer)
                        .addComponent(this.placeholder, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.separator)
                        .addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    /**
     *
     * @param dimensionComponent
     * @return JtextFieldPlaceholder component
     *
     * @deprecated This method will be removed in the version 0.1.0
     */
    @Deprecated
    public JTextFieldPlaceholder setDimensionComponent(Dimension dimensionComponent) {
        textField.setPreferredSize(dimensionComponent);
        textField.setSize(dimensionComponent);
        return this;
    }

    public JTextFieldPlaceholder setIcon(Icon icon) {
        if (icon == null) throw new IllegalArgumentException("icon null");
        iconContainer.setIcon(icon);
        return this;
    }

    public JTextFieldPlaceholder setSelectedIcon(Icon icon) {
        if (icon == null) throw new IllegalArgumentException("icon null");
        iconContainer.setSelectedIcon(icon);
        return this;
    }

    public JTextFieldPlaceholder setPlaceholderText(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        placeholder.setText(text);
        return this;
    }

    public JTextFieldPlaceholder setPlaceholderTextColor(Color colorLine) {
        this.placeholder.setForeground(colorLine);
        return this;
    }

    public JTextField getTextFiled() {
        return this.textField;
    }

    public JTextFieldPlaceholder setText(String text) {
        if (text == null || text.isEmpty()) throw new IllegalArgumentException("Invalid text");
        this.textField.setText(text);
        return this;
    }

    public JTextFieldPlaceholder setDimension(int wight, int height){
        Dimension dimension = new Dimension(wight, height);
        super.setPreferredSize(dimension);
        int newWight = wight - this.iconContainer.getWidth() - this.placeholder.getWidth();
        Dimension textFieldDimension = new Dimension(newWight, height - 5);
        this.textField.setPreferredSize(textFieldDimension);
        this.initStyle();
        return this;
    }

    public JTextFieldPlaceholder addAction(ActionListener action){
        if(action == null) throw new IllegalArgumentException("Action not valid");
        this.iconContainer.addActionListener(action);
        return this;
    }

    public String getText() {
        return textField.getText();
    }

    public String getPlaceholderText() {
        return placeholder.getText();
    }

    public Icon getIcon() {
        return this.iconContainer.getIcon();
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

    protected void setCorrectTextFieldUI() {
        if (this.textField != null) {
            if (passwordField) {
                this.textField.setUI(new InternalPasswordFieldUI(this));
            } else {
                this.textField.setUI(new InternalTextFieldUI(this));
            }
        }
    }

    public boolean isSelected() {
        return this.iconContainer.isSelected();
    }
}
