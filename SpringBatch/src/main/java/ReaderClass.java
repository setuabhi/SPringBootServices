import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

public class ReaderClass implements ItemReader<String>, ItemReadListener<String>, StepExecutionListener {

    List<String> readerData;
    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!readerData.isEmpty())
        {
            readerData.remove(0);
        }
        return null;
    }

  /*✅ beforeRead() runs before every read().
    ✅ afterRead(item) runs after successful reads.
    ✅ onReadError(exception) runs if read() throws an exception.
    ✅ At the end, when read() returns null, afterRead() is not called.
    Output:
    Before reading item...
    After reading item: Abhinav
    Before reading item...
    After reading item: Setu
    Before reading item...
    After reading item: kancahni
    Before reading item...
    */
    @Override
    public void beforeRead() {
        System.out.println("Before reading item...");
    }

    @Override
    public void afterRead(String s) {
        System.out.println("After reading item: " + s);
    }

    @Override
    public void onReadError(Exception e) {

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
        return stepExecution.getExitStatus(); // returns  "COMPLETED", "FAILED", "STOPPED", "NOOP
    }
}
