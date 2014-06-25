import org.json.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class TocHw3 {

    public static String url_to_string(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        InputStreamReader Sread = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader in = new BufferedReader(Sread);
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
        in.close();
        return response.toString();
    }
	
	public static void main(String[] args) throws JSONException, Exception {
		JSONArray data = null;
		data = new JSONArray(url_to_string(args[0]));
		long sum=0;
		int num=0;
		for(int i=0;i<data.length();i++){
			JSONObject item = data.getJSONObject(i);

			if( item.getString("鄉鎮市區").equals(args[1]) ){
				if(item.getString("土地區段位置或建物區門牌").contains(args[2]) ){
					if( (item.getInt("交易年月")/100) >= Integer.parseInt(args[3]) ){
						sum += item.getInt("總價元");
						num+=1;
					//	System.out.println( item.getInt("總價元") );	
					}
				}
			}	
		}
		if(num==0)
			System.out.println("NO result");
		else
			System.out.println( ((int)sum/num) );
	}
}
