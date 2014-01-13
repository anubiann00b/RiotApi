import java.util.ArrayList;

public class Masteries {
    
    private long summonerId;
    private ArrayList<MasteryPage> pages = new ArrayList<MasteryPage>();
    
    public long summonerId() { return summonerId; }
    public ArrayList<MasteryPage> pages() { return pages; }
    
    public Masteries(String data) {
        summonerId = Long.parseLong(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        
        data = data.substring(data.indexOf('[')+1);
        while (data.indexOf("{\"id\"")!=-1) {
            pages.add(new MasteryPage(data.substring(data.indexOf('{'),data.indexOf("]}")+2)));
            data = data.substring(data.indexOf("]}")+3);
        }
    }
    
    public MasteryPage getPageByName(String name) {
        for (MasteryPage page : pages) {
            if (page.name().equals(name))
                return page;
        }
        return null;
    }
    
    public String toString() {
        String ns = "";
        for (MasteryPage e : pages) {
            if (e.current()) {
                ns += e.toString();
                ns += "\n\n";
                break;
            }
        }
        for (MasteryPage e : pages) {
            if (!e.current()) {
                ns += e.toString();
                ns += "\n\n";
            }
        }        
        return ns;
    }
}
