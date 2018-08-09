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

    public static String ARG_AUTH = "auth";

    private static final String TAG = "myLogs";
    protected String doInBackground(String... urls){
        HttpURLConnection connection = null;

        try {
            String targetURL = "http://abuka.pythonanywhere.com/auth";
            String urlParameters = urls[0];
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
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            JSONObject obj = new JSONObject(response.toString());
            String pageName = obj.getString("status");
            return pageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
