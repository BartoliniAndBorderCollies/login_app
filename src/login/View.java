package login;

import java.util.ArrayList;
import java.util.List;

public class View {
    private final List<String> lines = new ArrayList<>();

    // update view
    public void update(String text) {
        addLine(text);
        print();
        clearLines();
    }

    public void update(List<String> lines) {
        this.lines.addAll(lines);
        print();
        clearLines();
    }

    private boolean addLine(String text) {
        return lines.add(text);
    }

    private void print() {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private void clearLines() {
        lines.clear();
    }
}
