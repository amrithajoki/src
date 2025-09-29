package ei.project.pattern.command;

import java.util.Stack;

// ========================
// Invoker
// ========================
// Executes commands and keeps history for undo/redo
public class TransactionManager {
    private final Stack<Command> History = new Stack<>();
    private final Stack<Command> RedoStack = new Stack<>();

    public void ExecuteCommand(Command command) {
        command.Execute();
        History.push(command);
        RedoStack.clear();
        BankLogger.info("Executed command: " + command.getClass().getSimpleName());
    }

    public void UndoLastCommand() {
        if (!History.isEmpty()) {
            Command command = History.pop();
            command.Undo();
            RedoStack.push(command);
            BankLogger.warning("Undid command: " + command.getClass().getSimpleName());
        } else {
            BankLogger.warning("No command to undo.");
        }
    }

    public void RedoLastCommand() {
        if (!RedoStack.isEmpty()) {
            Command command = RedoStack.pop();
            command.Execute();
            History.push(command);
            BankLogger.info("Redid command: " + command.getClass().getSimpleName());
        } else {
            BankLogger.warning("No command to redo.");
        }
    }
}
