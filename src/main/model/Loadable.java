package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    void loadFile(String name) throws IOException, ClassNotFoundException;
}
