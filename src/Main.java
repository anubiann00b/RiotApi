import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    
    private static String region = "na";
    private static String APIKey;
    
    private final static String baseURL = "http://prod.api.pvp.net/api/lol/";
    
    public static void main(String[] args) throws IOException {
        APIKey = getAPIKey();
        
        String[] arguments = {"masteries","chaosdusk","adc"};
        
        if (args.length != 0)
            arguments = args;
        
        switch(arguments.length) {
            case 0:
                displayHelp();
                break;
            case 1:
                switch(arguments[0].toLowerCase()) {
                    case "help":
                        displayHelp();
                        break;
                    default:
                        String name = arguments[0];
                        System.out.println(new Summoner(getContent("v1.2/summoner/by-name/" + name)));
                        break;
                }
                break;
            case 2:
                switch(arguments[0].toLowerCase()) {
                    case "masteries":
                        System.out.println(new Masteries(getContent("v1.2/summoner/" + getIdFromName(arguments[1]) + "/masteries")));
                        break;
                    default:
                        throw new IllegalArgumentException("First argument not recognized.");
                }
                break;
            case 3:
                switch(arguments[0].toLowerCase()) {
                    case "masteries":
                        String name = arguments[1];
                        Masteries masteryPages = new Masteries(getContent("v1.2/summoner/" + getIdFromName(name) + "/masteries"));
                        MasteryPage masteryPage = masteryPages.getPageByName(arguments[2]);
                        if (masteryPage != null)
                            System.out.println(masteryPage);
                        else
                            System.out.println("Mastery page \"" + arguments[2] + "\" not found for summoner \"" + arguments[1] + ".\"");
                        break;
                    default:
                        throw new IllegalArgumentException("First argument not recognized.");
                }
                break;
            default:
                throw new IllegalArgumentException("Too many arguements.");
        }
    }

    private static String getContent(String httpsUrl) throws IOException {
        System.out.print("Attempting to connect...");
        long startTime = System.currentTimeMillis();
        
        httpsUrl = baseURL + region + "/" + httpsUrl + "?api_key=" + APIKey;
        URL url = new URL(httpsUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        InputStream is = null;
        
        try {
            is = con.getInputStream();
        } catch (IOException e) { 
            System.out.print("Failed to connect.\n" + e + "\n");
            throw new IOException("Failed to connect.");
        }
        
        long endTime = System.currentTimeMillis();
        System.out.print("Connected!");
        System.out.print(" Time: " + (endTime - startTime) + "\n\n");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String input = br.readLine();
        br.close();
        
        return input;
    }
    
    private static String getAPIKey() throws FileNotFoundException, IOException {
        return new Scanner(new FileReader(new File("APIKey.txt"))).nextLine();
    }

    private static long getIdFromName(String name) throws IOException {
        String info = getContent("v1.2/summoner/by-name/" + name);
        Summoner summoner = new Summoner(info);
        return summoner.id();
    }

    private static void displayHelp() {
        System.out.println(
            "Welcome to RiotApi, a command-line tool to access Riot's API.\n" +
            "riotapi - See 'riotapi help.'\n" +
            "riotapi help - Shows this help screen.\n" +
            "riotapi [player] - Shows basic player information.\n" +
            "riotapi masteries [player] - Shows masteries of the player.\n" +
            "riotapi masteries [player] [pagename] - Shows player's mastery page pagename.\n"
        );
    }
}
