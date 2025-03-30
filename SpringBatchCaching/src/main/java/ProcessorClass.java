import org.springframework.batch.item.ItemProcessor;

public class ProcessorClass implements ItemProcessor<String,String> {
    @Override
    public String process(String s) throws Exception {
        return s.toUpperCase();
    }
}
