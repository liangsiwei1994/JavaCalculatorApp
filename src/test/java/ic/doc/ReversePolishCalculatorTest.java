package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class ReversePolishCalculatorTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Updatable updatable = context.mock(Updatable.class);

    ReversePolishCalculator calculator = new ReversePolishCalculator(updatable);

    @Test
    public void viewIsUpdatedAfterButtonIsPressed() {
        context.checking(new Expectations() {{
            exactly(1).of(updatable).update(calculator);
        }});

        calculator.addObserver(updatable);
        calculator.addOperand(1);
    }

    @Test
    public void sumTwoNumberOnPressingNumbersAndPlus() {

        setUpCalculatorWithTwoOperands();
        calculator.performOperation('+');
        assertThat(calculator.getValue(), is(7));
    }

    @Test
    public void subtractTwoNumberOnPressingNumbersAndMinus() {

        setUpCalculatorWithTwoOperands();
        calculator.performOperation('-');
        assertThat(calculator.getValue(), is(-1));
    }

    @Test
    public void onlyLastTwoNumberSummedOnPressingThreeNumbersAndPlus() {

        setUpCalculatorWithTwoOperands();
        calculator.addOperand(5);
        calculator.performOperation('+');
        assertThat(calculator.getValue(), is(9));
    }

    @Test
    public void oneOperandIsTooLittle() {

        context.checking(new Expectations() {{
            ignoring(updatable).update(calculator);
        }});

        calculator.addObserver(updatable);
        calculator.addOperand(3);
        calculator.performOperation('+');
        assertTrue(calculator.operationFailed());
    }

    private void setUpCalculatorWithTwoOperands() {
        context.checking(new Expectations() {{
            ignoring(updatable).update(calculator);
        }});

        calculator.addObserver(updatable);
        calculator.addOperand(3);
        calculator.addOperand(4);
    }
}

