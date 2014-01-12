import java.util.ArrayList;

public class Masteries {
    
    private long summonerId;
    private ArrayList<MasteryPage> pages;
    
    public long summonerId() { return summonerId; }
    public ArrayList<MasteryPage> pages() { return pages; }
    
    public Masteries(String data) {
        summonerId = Long.parseLong(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        
        data = data.substring(data.indexOf('{')+1);
        while (data.length()>1) {
            pages.add(new MasteryPage(data.substring(data.indexOf('{'), data.indexOf(','))));
            data = data.substring(data.indexOf('{'));
        }
    }
}