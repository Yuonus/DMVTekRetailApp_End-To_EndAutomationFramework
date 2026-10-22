package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	/*
	The following dependencies should be added
		Add the following dependencies to your POM.XML file:
		POI-OOXML <--- We just need this
		People will list the following too
				POI
				POI-OOXML-Schemas
				POI-Scratchpad
				OOXML-Schemas
				OpenXML4J
		
		7.	Create the Excel Utility Class
		Now you can start writing code to read Excel data.
		Create a utility class named: ExcelUtil.java
		This class will contain methods to:
		•	Read data from Excel sheets
		•	Get row count
		•	Get column count
		•	Fetch cell values
	 */

	
	/* 
	 	We will create one static generic method so that we don't have to create an object of this class whenever we want to call this method.
          >> We will simply call this generic method using the class name + method name.
          >> This method will read the test data from the Excel file.

   		1: Method name: getTestData() --> Get test data on the basis of what?
     		We will get the data on the basis of the sheet name that contains the data.
     		So, we will provide the sheet name as a parameter to this method, and the method will return the data from that specific sheet.

   		2: Print a statement that tells us which sheet we are reading the data from.
    		For example: "Reading test data from sheet name: Register"

    	3: Declare a two-dimensional Object array and initially assign null to it.
     		Why null? Because at this point, we still don't know what data will be stored in this array.
     		Why do we declare a two-dimensional Object array?
     		Our target is to initialize this array later, fill it with the Excel data, and return it from this generic method.

   	 	4: Now, we have to establish a connection with the Excel file.
     	To establish this connection, we will use the FileInputStream class.
          	>> Create an object of the FileInputStream class.
       		>> Provide the path of the Excel file to the FileInputStream constructor.
         	>> To get the file path, right-click on the Excel file > Properties > Path (Copy the path up to the "/src" directory and use "./src" as the relative path.
         	>> If the file does not exist at the specified path, a FileNotFoundException may be thrown. We need to handle this potential exception using try...catch block.

    	5: We will use the WorkbookFactory class from Apache POI and call its create() method.
    		This method requires the FileInputStream object as an argument.
         		>> Simply pass the input stream object "ip" to the create() method.
         		>> The create() method creates the appropriate workbook implementation (HSSFWorkbook or XSSFWorkbook) based on the Excel file format.
         		>> It loads the workbook into Java memory so that we can access its sheets and data.
         		>> This method may throw exceptions such as IOException and EncryptedDocumentException, which we need to handle.
         		   You can press CTRL + click on create() to explore its implementation and understand the exceptions it may throw.
         		>> The return type of create() is Workbook. We can store the returned workbook in a Workbook reference, which allows us to access the existing sheets in the Excel file.

    	6: Now that we have stored the workbook in a Workbook reference,
     		we can access the specific sheet from which we want to read the data.
     		To access a specific sheet, we will use the getSheet() method. This method returns the sheet with the given name.

     	7: After step 6, we are inside the specific sheet (inside the register sheet).
    		What should we do inside this sheet now? We have to read the data from this sheet in the form of rows and columns.
     		Now, we will start filling the two-dimensional array we declared in step 3.
     		First, initialize the array.
     		Example: data = new Object[4][5];
     		However, these are hard-coded values: 4 rows and 5 columns.
     		What if we use the same generic method for another sheet that has only 2 rows and 2 columns, or a sheet with a completely different structure?
     		We cannot keep hard-coding the row and column counts. So, what is the solution?
    		We need to handle the row and column counts dynamically so that the same generic method can work with different sheets containing different amounts of data.

     	8: Now, we have initialized the array using the row and column counts from the sheet.
     		At this point, the array has been created, but it is still empty. data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
     		We need to fill it with the values from the Excel sheet. To do that, we will use nested for loops:
         		>> The outer loop will iterate through the rows.
       			>> The inner loop will iterate through the columns.
    
		    Why do we use the toString() method on this line? data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
		    The data stored in Excel may be numeric, textual, or of another cell type.
		    The toString() method converts the cell's value into a Java String representation.
		    Some Notes:
		    data[i][j] = sheet.getRow(i + 1) --> We use i + 1 because the first row (index 0) contains the column headers,
		    and we want to start reading the test data from the second row (index 1).
		     Y are not adding one with j? We do not add 1 to j because the column index starts at 0, and we want to
		     read the first column as well which the row has already landed us there with i+1.

    	9: The return type of this method is a two-dimensional Object array.
     		As we discussed in the previous lecture while working with @DataProvider, we will return this 2D Object Array to supply test data to our test methods when called.
     		So, at the end, we will return the data array. --> return data;

  		10: Where should we call this utility method in our framework?
  			Since this utility method returns a two-dimensional Object array,
  			we will use it in our test classes, typically through a TestNG 
	 */

	public static Object[][] getTestData(String sheetName) {
		 // 1: Print a message indicating which sheet we are reading the data from.
		System.out.println("Reading test data from sheet: " + sheetName);
		// 2: Declare a 2D Object array and initially assign null to it.
		Object data[][] = null;
		// 3: Establish a connection with the Excel file.
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/testdata/OpenCartTestData.xlsx");
			// 4: Creating workbook in Java memory
			Workbook book = WorkbookFactory.create(ip);
			// 5: Going to specific sheet and reading its data -- sheetName -> Do not give register as that will be hard-coded
			Sheet sheet = book.getSheet(sheetName); // import org.apache.poi.ss.usermodel.

			// 6: Initializing the array
			// data = new Object[4][5];
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// 7: Filling the Object Array, as it is blank in the above line right now -- to fill the array we will use the nested for loop
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					// Filling starts here
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString(); 
					// i + 1 means we skip the first row (the header row) and start fetching the values from the second row.
					// We don't add 1 to j because j is already focused on the columns, and we want to start fetching values from the first column.
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

}
