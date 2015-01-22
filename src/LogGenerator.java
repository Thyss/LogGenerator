import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Simple thing to just generate lots of logs for testing LogStash/ElasticSearch
 *
 * Created by martin on 15-01-21.
 */
public class LogGenerator {

    public static String ip[] = {"212.28.194.152", "212.58.246.104"}; //ip for DN.se and bbc.co.uk
    public static String pages[] = {"/MARTIN/mekstrom.asp", "/Comments/Commentwhatever.jsp", "/random/randomshit.jsp", "/admin/iwanttoruineverything.jsp", "/im/not/supposed/to/be/here.jsp", "/noAccess.jsp"};
    public static String osBrowser[] = {"Mozilla/5.0+(Macintosh;+Intel+Mac+OS+X+10.9;+rv:32.0)+Gecko/20100101+Firefox/32.0"}; //mac v10.9 with firefox 32


    public static void main(String [ ] args) {

        //Generate a logline indefinately
        try {
            while (true) {
                String newLine = System.getProperty("line.separator");

                File file = new File("logfile.log");
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fileWriter = new FileWriter("logfile.log", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                Random random = null;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                String logtext = date + " " + ip[0] + " GET " + pages[1] + " - 443 - " + osBrowser[0] + " 83.233.28.2  200 0 0 103";
                bufferedWriter.write(logtext + newLine);
                bufferedWriter.close();
                //Put to sleep for a few seconds or so to not flood the system
                System.out.println("Logfile updated with new line: " + logtext);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            //Dont care about exceptions
            System.out.println(e.getStackTrace());
        }
    }
}
