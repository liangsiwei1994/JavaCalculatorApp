package ic.doc;

import java.util.Stack;

public class ReversePolishCalculator {
    private Stack<Integer> stack = new Stack<>();
    private Updatable observer;
    private boolean successfulOperation = false;

    public ReversePolishCalculator(Updatable observer) {
        this.observer = observer;
    }

    public void addOperand(Integer i) {
        successfulOperation = true;
        stack.push(i);
        observer.update(this);
    }

    public void performOperation(Character operator) {
        Integer stackSize = stack.size();

        if (stackSize.equals(0) || stackSize.equals(1)) {
            successfulOperation = false;
            observer.update(this);
            return;
        }

        Integer secondElement = stack.pop();
        Integer firstElement = stack.pop();
        if (operator.equals('+')) {
            stack.push(firstElement + secondElement);
        } else {
            stack.push(firstElement - secondElement);
        }
        successfulOperation = true;
        observer.update(this);
    }

    public Integer getValue() {
        Integer topValue = stack.peek();
        return topValue;
    }

    public boolean operationFailed() {
        if (successfulOperation) {
            return false;
        }
        return true;
    }

    public void addObserver(Updatable observer) {
        this.observer = observer;
    }
}
