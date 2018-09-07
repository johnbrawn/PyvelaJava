package askdat.pyvela.data.remote;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class DataBaseClass extends AsyncTask<String, Integer, String>{

    protected String doInBackground(String... urls){
        HttpURLConnection connection = null;

        try {
            String urlParameters = urls[0];
            String targetURL = "http://185.111.107.111/api/" + urls[1] + ".php";
            URL url = new URL(targetURL);

            //Open connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            reader.close();
            //Parsing JSON
            JSONObject obj = new JSONObject(response.toString());
            String status = obj.getString("status");
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    protected void onPostExecute(String feed) {
        super.onPostExecute(feed);
    }
}
