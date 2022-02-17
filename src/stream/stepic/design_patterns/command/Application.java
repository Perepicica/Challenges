package stream.stepic.design_patterns.command;

/**
 * Need to implement the Command pattern using a functional approach
 * to avoid creating unnecessary classes.
 * There are three commands to be implemented: save to clipboard, paste, and select all.
 * <p>
 * In order to implement the pattern, create the Command interface
 * and the Executor class.
 */
class Executor {
    void executeCommand(Command command) {
        command.execute();
    }
}

interface Command {
    void execute();
}

class Application {

    private final Executor executor;
    private final Editor editor;

    public Application(Executor executor, Editor editor) {
        this.executor = executor;
        this.editor = editor;
    }

    void run() {
        editor.selectAll();
        String selection = editor.getSelection();
        editor.saveToClipboard(selection);
        String clipboard = editor.getClipboard();
        editor.replaceSelection(clipboard);

    }
}

interface Editor {

    /**
     * Select all text.
     */
    void selectAll();

    /**
     * Returns previously selected text.
     */
    String getSelection();

    /**
     * Replaces selected text with the specified value.
     *
     * @param value the text that replaces selected text
     */
    void replaceSelection(String value);

    /**
     * Returns text in the clipboard.
     */
    String getClipboard();

    /**
     * Saves provided value to the clipboard.
     *
     * @param value the text that will be saved to the clipboard
     */
    void saveToClipboard(String value);
}