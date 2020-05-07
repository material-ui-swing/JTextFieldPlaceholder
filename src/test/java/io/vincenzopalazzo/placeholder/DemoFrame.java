package io.vincenzopalazzo.placeholder;

import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;

import javax.swing.*;
import java.awt.*;

public class DemoFrame extends JFrame {

    static {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

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
        textFieldPlaceholder = new JTextFieldPlaceholder();
        textFieldPlaceholder.setIcon(MaterialImageFactory.getInstance().getImage(
                GoogleMaterialDesignIcons.BOOKMARK,
                MaterialColors.COSMO_DARK_GRAY
        ))
        .setText("Lan/Lon")
        .setVisible(true);
        container.add(textFieldPlaceholder);
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
