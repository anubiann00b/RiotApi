public class Summoner {
    
    private long id;
    private String name;
    private int iconId;
    private long revisionDate;
    private byte level;
    
    public long id() { return id; }
    public String name() { return name; }
    public int iconId() { return iconId; }
    public byte level() { return level; }
    public long revisionDate() { return revisionDate; }
    
    public Summoner(String data) {
        id = Long.parseLong(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        name = data.substring(data.indexOf(':')+2,data.indexOf(',')-1);
        data = data.substring(data.indexOf(',')+1);
        iconId = Integer.parseInt(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        level = Byte.parseByte(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        revisionDate = Long.parseLong(data.substring(data.indexOf(':')+1,data.indexOf('}')));
    }
    
    public String toString() {
        return name + ": level " + level + ", id: " + id + ".";
    }
}