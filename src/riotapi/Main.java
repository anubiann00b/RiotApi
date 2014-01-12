package riotapi;

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
        
        String name = "skraman";
        long id = getIdFromName(name);
        
        String content = getContent("v1.3/game/by-summoner/" + id + "/recent");
        System.out.println(content);
    }

    private static String getContent(String httpsUrl) throws MalformedURLException, IOException {
        httpsUrl = baseURL + region + "/" + httpsUrl + "?api_key=" + APIKey;
        URL url = new URL(httpsUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input = br.readLine();
        br.close();
        return input;
    }
    
    private static String getAPIKey() throws FileNotFoundException, IOException {
        return new Scanner(new FileReader(new File("APIKey.txt"))).nextLine();
    }

    private static long getIdFromName(String name) throws IOException {
        String info = getContent("v1.1/summoner/by-name/skraman");
        info = info.substring(info.indexOf(':')+1,info.indexOf(','));
        System.out.println(info);
        return Long.parseLong(info);
    }
}
