package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class DataReader {
    public File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    public Product readDataFromXslFile(String fileName, int rowNum) throws IOException, URISyntaxException {

        //String fileName = "dataTest/exceldata.xlsx";
        File file = getFileFromResource(fileName);

        //FileInputStream fs = new FileInputStream("C:\\Users\\yknit\\Desktop\\course\\data\\dataTest.xlsx");
        FileInputStream fs = new FileInputStream(file);



        XSSFWorkbook workBook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workBook.getSheetAt(0);

        Row row = sheet.getRow(rowNum);

        String name = row.getCell(0).getStringCellValue();

        System.out.println("title" + name);
        int quantity = (int)row.getCell(1).getNumericCellValue();
        System.out.println("quantity" + quantity);

        Product product = new Product("", name, quantity);

        return product;

    }

    public Product readFromJsonFile(String fileName) throws IOException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();
        //FileReader reader = new FileReader("C:\\Users\\yknit\\Desktop\\course\\data\\jsondata.json");

        //String fileName = "dataTest/jsondata.json";
        File file = getFileFromResource(fileName);
        FileReader reader = new FileReader(file);

        Object obj = jsonParser.parse(reader);

        JSONObject book = (JSONObject) obj;
        book = (JSONObject) book.get("Book");

        String name = (String) book.get("title");
        int quantity = ((Long) book.get("quantity")).intValue();

        Product product = new Product("", name, quantity);

        System.out.println("title" + name);
        System.out.println("quantity" + quantity);

        return product;
    }

    public String propertiesReader(String key) throws URISyntaxException {
        File configFile = getFileFromResource("config.properties");
        String value = null;

        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            value = props.getProperty(key);

            System.out.print(key + " value is: " + value);
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("file does not exist");
            // file does not exist
        } catch (IOException ex) {
            System.err.println("I/O error");
            // I/O error
        }
        return value;
    }

    /// read caps from json file

    // 1 * read parse Json file
    public JSONArray parseJSON(String jsonLocation) throws Exception{
        JSONParser jsonParser = new JSONParser();
        File file = getFileFromResource(jsonLocation);
        FileReader reader = new FileReader(file);
        return (JSONArray) jsonParser.parse(reader);
    }

    // 2* get value of caps based on the name
    public JSONObject getCapability(String capabilityName, String jsoLocation) throws Exception{
        JSONArray capabilitiesArray = parseJSON(jsoLocation);
        for (Object jsonObj : capabilitiesArray){
            JSONObject capability = (JSONObject) jsonObj;
            if(capability.get("name").toString().equalsIgnoreCase(capabilityName))
                return (JSONObject) capability.get("caps");
        }
        return null;
    }

    // 3* converting the caps JSONObject to String and mapping it to HashMap
    public HashMap<String, Object> convertCapsToHashMap(String capabilityName, String jsoLocation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCapability(capabilityName, jsoLocation).toString(), HashMap.class);
    }

    // 4* creating DesiredCapabilities object using the HashMap
    public DesiredCapabilities getDesiredCapabilities(String capabilityName, String capsLocation) throws Exception{
        HashMap<String, Object> caps = convertCapsToHashMap(capabilityName, capsLocation);
        return new DesiredCapabilities(caps);
    }

}
