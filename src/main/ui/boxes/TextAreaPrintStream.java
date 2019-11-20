package ui.boxes;

import javafx.scene.control.TextArea;
import java.io.OutputStream;

public class TextAreaPrintStream extends OutputStream {
    private TextArea textArea;

    // Constructor
    public TextAreaPrintStream(TextArea area) {
        textArea = area;
    }

    // EFFECTS: writes given text to textArea
    @Override
    public void write(int b) {
        textArea.appendText(String.valueOf((char)b));
    }
}
