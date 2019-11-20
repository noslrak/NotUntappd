package ui.gui;

import javafx.scene.control.TextArea;
import java.io.OutputStream;

public class TextAreaPrintStream extends OutputStream {
    private TextArea textArea;

    public TextAreaPrintStream(TextArea area) {
        textArea = area;
    }

    @Override
    public void write(int b) {
        textArea.appendText(String.valueOf((char)b));
    }
}
