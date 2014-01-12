public class Talent {
    
    private int id;
    private String name;
    private int rank;
    
    public int id() { return id; }
    public String name() { return name; }
    public int rank() { return rank; }
    
    public Talent(String data) {
        id = Integer.parseInt(data.substring(data.indexOf(':')+1,data.indexOf(',')));
        data = data.substring(data.indexOf(',')+1);
        name = data.substring(data.indexOf(':')+2,data.indexOf(',')-1);
        data = data.substring(data.indexOf(',')+1);
        rank = Integer.parseInt(data.substring(data.indexOf(':')+1,data.indexOf('}')));
    }
    
    public String toString() {
        return name;
    }
}