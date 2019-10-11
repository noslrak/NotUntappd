package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Savable {
    void saveFile(String name) throws IOException;
}
