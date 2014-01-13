import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

public class Main {
    
    private static String region = "na";
    private static String APIKey;
    
    private final static String baseURL = "https://prod.api.pvp.net/api/lol/";
    
    public static void main(String[] args) throws IOException {
        APIKey = getAPIKey();
        String content = "";
        
        String[] arguments = {"masteries","skraman"};
        
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
                        String name = arguments[1];
                        System.out.println(new Masteries(
                                getContent("v1.2/summoner/" + getIdFromName(name) + "/masteries")));
                        break;
                    default:
                        throw new IllegalArgumentException("First argument not recognized.");
                }
                break;
            default:
                throw new IllegalArgumentException("Too many arguements.");
        }
    }

    private static String getContent(String httpsUrl) throws MalformedURLException, IOException {
        System.out.print("Attempting to connect...");
        httpsUrl = baseURL + region + "/" + httpsUrl + "?api_key=" + APIKey;
        URL url = new URL(httpsUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input = br.readLine();
        br.close();
        System.out.print("Connected!\n");
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
                "riotapi masteries [player] - Shows masteries of the player.\n"
        );
    }
}
