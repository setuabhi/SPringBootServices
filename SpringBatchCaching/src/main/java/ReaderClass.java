import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

public class ReaderClass implements ItemReader<String>, StepExecutionListener {

    List<String> readerData;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!readerData.isEmpty())
        {
            readerData.remove(0);
        }
        return null;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        readerData = new ArrayList<>();
        readerData.add("Abhinav");
        readerData.add("Setu");
        readerData.add("kancahni");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
