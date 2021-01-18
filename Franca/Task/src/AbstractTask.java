public class AbstractTask {
    private boolean female;
    private boolean male;
    private int factor;
    private String content;
    private boolean once;

    public AbstractTask(boolean female, boolean male, int factor,boolean once, String content){
        this.female=female;
        this.male=male;
        this.factor=factor;
        this.content=content;
        this.once=once;
    }

    public int getFactor() {
        return factor;
    }
    public void setMF(boolean female, boolean male){
        this.female=female;
        this.male=male;
    }
    public boolean getM(){

        return male;
    }
    public boolean getF(){
        return female;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content=content;
    }


    public void setFactor(int factor) {
        this.factor = factor;
    }
}


