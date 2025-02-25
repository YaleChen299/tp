package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.schedule.DescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all appointments in address book whose description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAppCommand extends Command {

    public static final String COMMAND_WORD = "findApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all appointments whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " meeting";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindAppCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppCommand // instanceof handles nulls
                && predicate.equals(((FindAppCommand) other).predicate)); // state check
    }
}
