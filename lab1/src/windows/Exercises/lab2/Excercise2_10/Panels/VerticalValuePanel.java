package windows.Exercises.lab2.Excercise2_10.Panels;

import windows.Exercises.lab2.Excercise2_10.Sketch;
import windows.Exercises.lab2.Excercise2_10.ValueChangeable;

import javax.swing.*;
import java.awt.*;

public class VerticalValuePanel extends JPanel {

    private int value;
    private int min, max;
    private JButton upButton, downButton;
    private JLabel valueLabel, nameLabel;
    ValueChangeable valueChangeable;

    public VerticalValuePanel(String name, ValueChangeable valueChangeable, int ... args) { // name, ValueChangeable, min, max, value
        super();
        setDefaultValues();
        setValues(args);
        initElements();
        setValueName(name);
        layoutElements();
        this.valueChangeable = valueChangeable;
        addActions();
    }

    public void setValues(int ... args) { // name, min, max, value
        if (args.length == 1) {
            min = args[0];
            max = min + 10;
            value = min + 5;
        } else if (args.length > 1) {
            if (args[0] < args[1]) {
                min = args[0];
                max = args[1];
            } else {
                min = args[1];
                max = args[0];
            }
            if (args.length > 2 && min <= args[2] && args[2] <= max) {
                value = args[2];
            } else {
                value = (min + max) / 2;
            }
        }
    }

    public void setValueName(String name) {
        nameLabel.setText(name);
    }

    private void layoutElements() {
        setLayout(new FlowLayout());
        add(nameLabel);
        add(upButton);
        add(valueLabel);
        add(downButton);
    }

    private void addActions() {
        upButton.addActionListener(e -> {
            VerticalValuePanel.this.increaseValue();
        });
        downButton.addActionListener(e -> {
            VerticalValuePanel.this.decreaseValue();
        });
    }

    private void increaseValue() {
        if (value < max) {
            value += 1;
            setValueLabel();
            valueChangeable.set(value);
        }
    }

    private void decreaseValue() {
        if (value > min) {
            value -= 1;
            setValueLabel();
            valueChangeable.set(value);
        }
    }

    private void initElements() {
        upButton = new JButton("+");
        downButton = new JButton("-");
        valueLabel = new JLabel();
        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(0);
        valueLabel.setHorizontalAlignment(0);
        setValueLabel();
    }

    private void setValueLabel() {
        valueLabel.setText(String.valueOf(value));
    }

    private void setDefaultValues() {
        min = 0;
        max = 10;
        value = 5;
    }

    @Override
    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        Dimension objectDimension = new Dimension(dimension.width - 10, 80);
        upButton.setPreferredSize(objectDimension);
        downButton.setPreferredSize(objectDimension);
        valueLabel.setPreferredSize(objectDimension);
        nameLabel.setPreferredSize(objectDimension);
    }
}
