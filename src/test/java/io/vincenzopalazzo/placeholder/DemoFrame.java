package io.vincenzopalazzo.placeholder;

import io.swingsnackbar.SnackBar;
import io.swingsnackbar.action.AbstractSnackBarAction;
import io.vincenzopalazzo.placeholder.ui.BasicTextFieldPlaceholderUI;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import mdlaf.utils.MaterialImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class DemoFrame extends JFrame {

    static {
        try {
            UIManager.put("TextFieldPlaceholder.placeholderColor", MaterialColors.COSMO_BLACK);
            UIManager.put("TextFieldPlaceholder.background", MaterialColors.COSMO_LIGTH_GRAY);
            UIManager.put("TextFieldPlaceholder.foreground", MaterialColors.BLACK);
            UIManager.put("TextFieldPlaceholder[Line].activeColor", MaterialColors.COSMO_BLUE);
            UIManager.put("TextFieldPlaceholder[Line].inactiveColor", MaterialColors.BLACK);
            UIManager.put("TextFieldPlaceholder.caret", MaterialColors.BLACK);
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private JFrame frame = this;
    private JPanel container;
    private JTextFieldPlaceholder textFieldPlaceholder;

    public void initView() {
        initComponent();

        super.setContentPane(container);
        super.setSize(new Dimension(400, 400));
        super.setTitle("New Swing component from @vincenzopalazzo");
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public void initComponent() {
        container = new JPanel();
        //Init component
        textFieldPlaceholder = new JTextFieldPlaceholder();
        //configure component
        textFieldPlaceholder
                .setPlaceholderText("Username")
                .setIcon(MaterialImageFactory.getInstance().getImage(
                        GoogleMaterialDesignIcons.STAR,
                        MaterialColors.COSMO_BLACK
                )).setSelectedIcon(
                MaterialImageFactory.getInstance().getImage(
                        GoogleMaterialDesignIcons.STAR,
                        MaterialColors.YELLOW_800
                ))
        .setDimension(350, 45);

        container.add(textFieldPlaceholder);
        JButton button = new JButton(MaterialImageFactory.getInstance().getImage(
                GoogleMaterialDesignIcons.SEND,
                MaterialColors.WHITE
        ));

        button.addActionListener(new AbstractAction() {
            private SnackBar snackBar;

            @Override
            public void actionPerformed(ActionEvent e) {
                snackBar = SnackBar.make(frame, textFieldPlaceholder.getPlaceholderText() + " " + textFieldPlaceholder.getText(), "CLOSE")
                        .setGap(80)
                        .setIconTextStyle(MaterialFontFactory.getInstance().getFont(MaterialFontFactory.BOLD))
                        .setIconTextColor(MaterialColors.COSMO_RED)
                        .setDuration(SnackBar.LENGTH_LONG)
                        .setAction(new AbstractSnackBarAction() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                snackBar.dismiss();
                            }
                        })
                        .run();
            }
        });

        container.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DemoFrame demoFrame = new DemoFrame();
                demoFrame.initView();
            }
        });
    }
}
