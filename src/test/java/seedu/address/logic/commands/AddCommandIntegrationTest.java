package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Expense;
import seedu.address.testutil.ExpenseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
    }

    @Test
    public void execute_newExpense_success() {
        Expense validExpense = new ExpenseBuilder().build();

        Model expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.addExpense(validExpense);
        // Budget newBudget = new Budget(model.getBudget().asInt() - validExpense.getAmount().asInt());

        assertCommandSuccess(new AddCommand(validExpense),
                model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExpense),
                expectedModel);

        // assertCommandSuccess(new AddCommand(validExpense),
        //         model,
        //         String.format(AddCommand.MESSAGE_SUCCESS, validExpense)
        //         + "\n\n"
        //         + String.format(AddCommand.BUDGET_EDITED, newBudget),
        //         expectedModel);
    }

    @Test
    public void execute_duplicateExpense_throwsCommandException() {
        Expense expenseInList = model.getExpenseExpert().getExpenseList().get(0);
        assertCommandFailure(new AddCommand(expenseInList), model, AddCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

}
