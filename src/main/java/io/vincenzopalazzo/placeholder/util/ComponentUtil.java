package io.vincenzopalazzo.placeholder.util;

import io.vincenzopalazzo.placeholder.util.exception.ComponentUtilException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ComponentUtil {

    public static IconUIResource loadImage(String resourcesPath){
        if(resourcesPath == null || resourcesPath.isEmpty()){
            throw new IllegalArgumentException("Resources Path null or empty");
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(ComponentUtil.class.getResourceAsStream(resourcesPath));
            return new IconUIResource(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new ComponentUtilException(e.getCause());
        }
    }
}
