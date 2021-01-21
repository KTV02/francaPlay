public class HardcoreTask extends AbstractTask{
    private String duration;
    public HardcoreTask(boolean female, boolean male, int factor, boolean once, String content,String duration) {
        super(female, male, factor, once, content);
        this.duration=duration;
    }
    public String getDuration(){
        return duration;
    }
}
