/**
 * GetData
 */

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class GetData {

    public static void main(String[] args) throws Exception {
        String [] data = new String [6];
        data = inputData();

        
        StringBuilder infoForWrite = new StringBuilder();
        try{
            infoForWrite = getInfoForWrite(data);
        } catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
        writeToFile(data, infoForWrite);
    }

    public static String[] inputData(){

        Scanner iScanner = new Scanner (System.in);
        while(true){
            System.out.println("Введите данные в строку через пробел (Ф.И.О, дата рождения - dd.mm.yyyy, номер телефона - цифры, пол - f/m ): ");
            String inputData = iScanner.nextLine();
            String [] data = inputData.split(" ");
            if(data.length == 6){
                iScanner.close();
                return data;
                
            } 
            else {
                System.out.println("Вы ввели не корректные данные! Повторите попытку!"); 
            }
           
        }
    }

    public static StringBuilder getInfoForWrite(String[] data) throws Exception{
        StringBuilder infoForWrite = new StringBuilder();
        infoForWrite.append("<").append(data[0]).append(">").append("<").append(data[1]).append(">").append("<").append(data[2]).append(">").append("<").append(data[3]).append(">").append("<").append(data[4]).append(">").append("<").append(data[5]).append(">");
        try{
        String lastName = data[0];
        String secondName = data[1];
        String midleName = data[2];
        } catch (IllegalArgumentException e) {
            throw new ArgumentException();

        }
        try{String date = data[3];
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = format.parse(date);
        } catch (Exception e){
            throw new Exception("Неверный формат даты!");
            //System.out.println("Неверный формат даты!");;
        }
        try{
            Integer phoneNumber = Integer.parseInt(data[4]);
        } catch (NumberFormatException e){
            throw new NumberFormatException("неверный формат номера телефона!");
           
        }
        
        try{
        char gender = data[5].charAt(0);
        } catch(RuntimeException e){
             throw new GenderException();
        }
       
        
        return infoForWrite;
    }
    
    public static void writeToFile(String[] data, StringBuilder infoForWrite) throws Exception {
        try{
        String path = "Files\\" + data[0].toLowerCase() + ".txt";
        File file = new File(path);
        file.createNewFile();
        FileWriter writer = null;
        String info = infoForWrite.toString();
        File folder = new File ("Files\\");
        for(File files: folder.listFiles()){
            String fileName = files.getName().split("\\.")[0];
            if(fileName.equals(data[0])) {
                writer = new FileWriter (files, true);
                
            }
           
        }
        writer = new FileWriter (file, true);
        writer.write(info + "\n");
        System.out.println("Данные записаны в файл!");
        writer.close();
        } catch (Exception e){
            throw new FileNotExist();
    }

        
    }
}

class FileNotExist extends FileNotFoundException{

    public FileNotExist() {
        super("Файл не существует!");
        
    }
}

class ArgumentException extends IllegalArgumentException{

    public ArgumentException() {
        super("Недопустимое значение аргумента!");
        
    }
}

class GenderException extends RuntimeException{

    public GenderException() {
        super("Недопустимое указание гендера!");
        
    }
}




    





