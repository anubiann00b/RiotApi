import java.util.ArrayList;

public class MasteryPage {
    
    private long id;
    private String name;
    private boolean current;
    private ArrayList<Talent> talents;
    
    public long id() { return id; }
    public String name() { return name; }
    public boolean current() { return current; }
    public ArrayList<Talent> talents() { return talents; }
    
    public MasteryPage(String data) {
        id = Integer.parseInt(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        name = data.substring(data.indexOf(':')+2,data.indexOf(',')-1);
        data = data.substring(data.indexOf(',')+1);
        current = Boolean.parseBoolean(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        
        data = data.substring(data.indexOf('{')+1);
        while (data.length()>1) {
            talents.add(new Talent(data.substring(data.indexOf('{'), data.indexOf(','))));
            data = data.substring(data.indexOf('{'));
        }
    }
}