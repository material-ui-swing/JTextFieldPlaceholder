# JTextFieldPlaceholder
![Maven Central](https://img.shields.io/maven-central/v/material-ui-swing/JTextFieldPlaceholder?color=orange&style=for-the-badge)

Simple Swing component with placeholder called JTextFieldPlaceholder

## TODO
This component has bad design, in the future should introduce the following effect

- [X] Used a personal UI component as BasicTextFieldPlaceholderUI
- [X] Introduce a toggle button with an icon
- [ ] Introduce rules to check the errors inside the text field and paint the line with an error color

## Code Style
> We live in a world where robots can drive a car, so we shouldn't just write code, we should write elegant code.

This repository use [google-java-format](https://github.com/sherter/google-java-format-gradle-plugin) to maintains the code of the repository elegant, so
before submit the code check the Java format with the following command on the root of the directory

```bash
./gradlew verifyGoogleJavaFormat
```

It any error are reported please run the following command to try to fix it

```bash
./gradlew googleJavaFormat
```

p.s: The gradle plugin work with all the JDK version >= 9 (or better with java byte code version compatible with the version  55.0)

For more details about the JDK support see the [this issue](https://github.com/sherter/google-java-format-gradle-plugin/issues/58)
and to know more about the Google Java code Style see the [this reference](https://google.github.io/styleguide/javaguide.html)


## Actual Style

<div align="center">
<img src="https://i.ibb.co/HHttmZx/Selection-079.png"/>
</div>

## Example with code

```java
JTextFieldPlaceholder passwordFieldForm = new JTextFieldPlaceholder(new JPasswordField());
passwordFieldForm.setPlaceholderText("Password")
                .setIcon(
                        MaterialImageFactory.getInstance().getImage(
                                GoogleMaterialDesignIcons.VISIBILITY_OFF
                        ))
                .setSelectedIcon(
                        MaterialImageFactory.getInstance().getImage(
                                GoogleMaterialDesignIcons.VISIBILITY,
                                MaterialColors.LIGHT_BLUE_400
                        )
                )
                .setDimension(150, 35)
                .addAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPasswordField passwordField = (JPasswordField) passwordFieldForm.getTextFiled();
                        if (!passwordFieldForm.isSelected()) {
                            passwordField.setEchoChar((char) UIManager.get("PasswordField.echoChar"));
                        } else {
                            passwordField.setEchoChar((char) 0);
                        }
                    }
                });
```

In this example is use [material-ui-swing](https://github.com/vincenzopalazzo/material-ui-swing) library, you can set all type icon you want, by default there are two icons, as:

- [Selected icon](https://github.com/vincenzopalazzo/JTextFieldPlaceholder/blob/master/src/main/resources/icons/selected.png)
- [Unselected Icon](https://github.com/vincenzopalazzo/JTextFieldPlaceholder/blob/master/src/main/resources/icons/unselected.png)

## List of projects that used this component

- [JMars 5 beta](http://jmars.mars.asu.edu/)

## Author

This component is developer by [@vincenzopalazzo](https://github.com/vincenzopalazzo)

<p align="center" style="center">In addition, this component is developed in collaborations with Arizona State University. </p>

<div align="center">
<img src="https://sundevilgymnastics.com/wp-content/uploads/2016/10/ASU-Womens-Gymnastics-Website.png" />
</div>