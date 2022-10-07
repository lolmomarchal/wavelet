import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchEngine implements URLHandler {
    String[] list ;

    int index = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Strings: %d", list);
        } else if (url.getPath().contains("/add")) {
            String[] string = url.getQuery().split("=");
            list[index] = string[1];
            index++;
            return String.format("String added to list!");
        }
        else if(url.getPath().equals("/search")){
            String[] search = new String[0];
            int indexSearch =0;
            if(list.length == 0)
            {
                return String.format("Currently, no Strings are on the list. To search" +
                        "please add some strings first.");
            }
            String[] string = url.getQuery().split("=");
            for(int i = 0; i < list.length;i++ ){
                if(list[i].contains(string[1])){
                    search[indexSearch] = list[i];
                }
                return String.format("Results: %s",  Arrays.toString(search));
            }

            return String.format("Please add or search a string!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/list")) {

                    return String.format("Current list of strings: %s", Arrays.toString(list));
                }
            }
            return "404 Not Found!";
        }
    }


    class SearchEngine1 {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }

            int port = Integer.parseInt(args[0]);

            Server.start(port, new Handler());
        }



    }


