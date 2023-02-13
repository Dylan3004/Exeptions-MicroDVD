import java.io.*;

public class Odtwarzacz {
    String tekst= "";

    public String delay(String in, String out,int delay, int fps)
    {
        int klatka = Integer.valueOf(in)+delay/(fps/2);
        int klatka1 = Integer.valueOf(out)+delay/(fps/2);
       // System.out.println(klatka +"  "+klatka1);
        String koncowy = "{"+klatka+"}"+"{"+klatka1+"}";
        return koncowy;
    }

    public void read(String path_in,String path_out,int delay,int fps)
    {

        StringBuilder input=new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path_in));
            String line ="";
            while (true) {
                try {
                    if (((line = bufferedReader.readLine()) != null))
                    {
                        String liczba=line.substring(line.indexOf("{")+1, line.indexOf("}"));
                        String liczba1 =line.substring(line.indexOf("{",line.indexOf("{")+1)+1,line.indexOf("}",line.indexOf("}")+1));
                        //System.out.println(liczba+" "+liczba1);
                        tekst= delay(liczba,liczba1,delay,fps)+line.substring(line.indexOf("}",line.indexOf("}")+1)+1);
                        FileWriter writer = new FileWriter(path_out);
                        writer.write(tekst+"\n");

                        input.append(line);
                    }
                    else
                    {

                        break;
                    }
                }

                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
