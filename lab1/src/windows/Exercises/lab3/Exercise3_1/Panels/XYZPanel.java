package windows.Exercises.lab3.Exercise3_1.Panels;

import javax.swing.*;
import java.awt.*;

public class XYZPanel extends JPanel {

    int FieldsAmount;
    int fieldsWidth = 50;
    int fieldsHeight = 30;
    JLabel[] textFields;

    public XYZPanel(String ... texts) {
        super();
        setLayout(null);
        FieldsAmount = texts.length;
        textFields = new JLabel[FieldsAmount];
        for (int textFieldNumber = 0; textFieldNumber < FieldsAmount; textFieldNumber++) {
            textFields[textFieldNumber] = new JLabel(texts[textFieldNumber]);
            textFields[textFieldNumber].setBounds(0, 0, fieldsWidth, fieldsHeight);
        }
        layoutElements();
    }

    public void setFieldSizes(int fieldsWidth, int fieldsHeight) {
        this.fieldsWidth = fieldsWidth;
        this.fieldsHeight = fieldsHeight;
    }

    private void layoutElements() {
        for (var textField : textFields)
            add(textField);
    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        int blockWidth = dimension.width;
        if (FieldsAmount != 0)
            blockWidth = dimension.width / FieldsAmount;
        int realFieldWidth = fieldsWidth;
        int realFieldHeight = fieldsHeight;
        if (blockWidth < realFieldWidth)
            realFieldWidth = blockWidth;
        if (dimension.height < realFieldHeight)
            realFieldHeight = dimension.height;
        for (int textFieldNumber = 0; textFieldNumber < FieldsAmount; textFieldNumber++)
            textFields[textFieldNumber].setBounds(
                    blockWidth / 2 + blockWidth * textFieldNumber,
                    (dimension.height - textFields[textFieldNumber].getHeight()) / 2,
                    realFieldWidth, realFieldHeight);
    }
}