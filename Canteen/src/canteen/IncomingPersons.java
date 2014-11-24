package canteen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IncomingPersons {

    ArrayList<Integer> values = new ArrayList<>();

    public IncomingPersons(String filename) {
        try {
            loadFromFile(filename);
        } catch (Exception e) {
           
        }
    }
    
    public int getNumberOfPerson(int time){
        if(time < values.size() && time >= 0){
            return values.get(time);
        } else {
            return 0;
        }
    }

    private void loadFromFile(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));
        int i = 0;
        while (scanner.hasNextInt()) {
            values.add(scanner.nextInt());
        }
    }
}
