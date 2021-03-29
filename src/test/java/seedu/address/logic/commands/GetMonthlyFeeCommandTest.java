package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;

import java.time.LocalDateTime;

public class GetMonthlyFeeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // Alice has 4 session and has only 2 session that is on 1 Jan 2021
    // The other 2 session, one has a different month, one has a different year
    @Test
    public void execute_correctMonthlyFee_success() throws CommandException {
        GetMonthlyFeeCommand getMonthlyFeeCommand = new GetMonthlyFeeCommand(ALICE.getName(),
            new Month(2), new Year(2021));
        CommandResult commandResult = getMonthlyFeeCommand.execute(model);

        LocalDateTime startPeriod = LocalDateTime.of(2021, 2, 1, 0, 0);
        LocalDateTime endPeriod = LocalDateTime.of(2021, 3, 1, 0, 0);

        double expectedFee = model.getFeePerStudent(ALICE, startPeriod, endPeriod);

        assertEquals(String.format("Monthly fee for %s on %s, %s is $%.2f", ALICE.getName(),
            new Month(2).getMonthName(), new Year(2021).toString(), expectedFee),
            commandResult.getFeedbackToUser());
    }
}
