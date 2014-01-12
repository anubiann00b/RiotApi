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
    
    public static void main(String[] args) throws IOException {
        String APIKey = getAPIKey();
        String content = getContent("v1.1/summoner/by-name/RiotSchmick?api_key=" + APIKey);
        System.out.println(content);
    }

    private static String getContent(String httpsUrl) throws MalformedURLException, IOException {
        httpsUrl = "https://prod.api.pvp.net/api/lol/" + region + "/" + httpsUrl;
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
}
