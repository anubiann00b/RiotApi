import java.util.ArrayList;

public class MasteryPage {
    
    private long id;
    private String name;
    private boolean current;
    private ArrayList<Talent> talents = new ArrayList<Talent>();
    
    private static String[][] offense = {
        {"Double-Edged Sword","Fury","Sorcery","Butcher"},
        {"Expose Weakness","Brute Force","Mental Force","Feast"},
        {"Spell Weaving","Martial Mastery","Arcane Mastery","Executioner"},
        {"Blade Weaving","Warlord","Archmage","Dangerous Game"},
        {"Frenzy","Devastating Strikes","","Arcane Blade"},
        {"","Havoc","",""}
    };
    private static boolean[][] offenseLinks = {
        {false,false,false,false},
        {false,false,false,true},
        {false,true,true,false},
        {true,false,false,true},
        {false,false,false,false},
        {false,false,false,false}
    };
    //private static String[][] defense = 
    //private static String[][] utility = 
    
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
        
        data = data.substring(data.indexOf('{'),data.indexOf("]}"));
        while (data.indexOf('{')!=-1) {
            talents.add(new Talent(data.substring(data.indexOf('{'),data.indexOf('}')+1)));
            if (data.indexOf('}')+2<data.length())
                data = data.substring(data.indexOf('}')+2);
            else
                data = data.substring(data.indexOf('}'));
        }
    }
    
    public String toString() {
        String ns = "";
        Talent currentTalent = null;
        ns += name + ": " + (current?"Current":"Not Current") + "\n";
        ns += "\n Offense:\n";
        for (int i=0;i<offense.length;i++) {
            for (int j=0;j<offense[0].length;j++) {
                for (Talent e : talents) {
                    if (e.name().equals(offense[i][j])) {
                        currentTalent = e;
                        break;
                    }
                }
                ns += "  " + pad((offense[i][j].equals("")
                                ?"":(currentTalent!=null?"[" + currentTalent.rank() + "]":"[0]"))
                                + " " + offense[i][j],26);
                currentTalent = null;
            }
            ns += "\n";
            ns += pad("",5);
            for (int j=0;j<offenseLinks[0].length;j++) {
                ns += pad((offenseLinks[i][j]?"||":""),28);
            }
            ns += "\n";
        }
        return ns;
    }
    
    private String pad(String s, int padding) {
        if (s.length()>padding)
            throw new IndexOutOfBoundsException("\"" + s + "\" is longer than " + padding);
        while (s.length()<padding) {
            s += " ";
        }
        return s;
    }
}
