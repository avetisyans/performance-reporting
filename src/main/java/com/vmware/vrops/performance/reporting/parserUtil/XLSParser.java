/*package com.vmware.vrops.performance.reporting.parserUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig.DeploymentType;
import com.vmware.vrops.performance.reporting.domain.TestDef;
import com.vmware.vrops.performance.reporting.domain.TestListRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestSuite;
import com.vmware.vrops.performance.reporting.domain.TestSuiteListPerEnv;
import com.vmware.vrops.performance.reporting.service.DailyRunInfoService;

@Service("xlsParser")
public class XLSParser {

	@Autowired
	private DailyRunInfoService dailyRunInfoService;

   public DailyRunInfo parseXLS(String filepath) {
      DailyRunInfo dailyRunInfo = new DailyRunInfo();
      Date date = new Date();

      dailyRunInfo.setDate(date);

      Set<TestSuiteListPerEnv> testSuiteListPerEnv = new LinkedHashSet<TestSuiteListPerEnv>();

      try {
         FileInputStream file = new FileInputStream(new File(filepath));

         // Create Workbook instance holding reference to .xls file
         HSSFWorkbook workbook = new HSSFWorkbook(file);

         // Get first/desired sheet from the workbook
         HSSFSheet sheet = workbook.getSheetAt(0);

         // Iterate through each rows one by one
         Iterator<Row> rowIterator = sheet.iterator();

         // take the fist raw as environment type, count environment types
         ArrayList<String> environmentList = new ArrayList<String>();

         int envCount = 0;
         Row environments = rowIterator.next();
         Iterator<Cell> envCellIterator = environments.cellIterator();
         while (envCellIterator.hasNext()) {
            Cell cell = envCellIterator.next();
            if (!cell.getStringCellValue().equals(" ")) {
               environmentList.add(cell.getStringCellValue());
               envCount++;
            }
         }
//TODO
         TestSuite testSuite = new TestSuite("");
         //create TestRunInfo list for each environment
         for(int j=0; j<envCount; j++){
            TestSuiteListPerEnv testSuitePerEnv = new TestSuiteListPerEnv();
            //TODO get environment details dynamically
            testSuitePerEnv.setEnvironmentConfig(new EnvironmentConfig("4583736", "main", "vRops6.0.1", DeploymentType.VA));

            testSuiteListPerEnv.add(testSuitePerEnv);

            Set<TestSuite> testSuites = new LinkedHashSet<TestSuite>();
            testSuitePerEnv.setTestSuites(testSuites);

            //TODO get corresponding suite name
            testSuite = new TestSuite("OOTBDashboardTest");
            //TODO calculate pass/fail rate
//            testSuite.setStatus("PASS");

            testSuites.add(testSuite);

            Set<TestListRunInfo> tests = new LinkedHashSet<TestListRunInfo>();
            testSuite.setTestListRunInfosSet(tests);
         }

         // skip columns title row
         rowIterator.next();

         while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            //take first column as test name
            String testName = cellIterator.next().getStringCellValue();
            TestDef testDef = new TestDef(testName);

            for (int i=0; cellIterator.hasNext(); i++) {
               TestListRunInfo testListRunInfo = new TestListRunInfo();

               testListRunInfo.setTestDef(testDef);
               testListRunInfo.setNumberOfRuns((int)cellIterator.next().getNumericCellValue());
               testListRunInfo.setTotalPass((int)cellIterator.next().getNumericCellValue());
               cellIterator.next();
               testListRunInfo.setMin(cellIterator.next().getNumericCellValue());
               testListRunInfo.setSuccessMin(cellIterator.next().getNumericCellValue());
               testListRunInfo.setMax(cellIterator.next().getNumericCellValue());
               testListRunInfo.setSuccessMax(cellIterator.next().getNumericCellValue());
               testListRunInfo.setAvg(cellIterator.next().getNumericCellValue());
               testListRunInfo.setSuccessAvg(cellIterator.next().getNumericCellValue());
               testListRunInfo.setTestSuite(testSuite);

               //add testListRunInfo to the list
               testSuiteListPerEnv.iterator().next().getTestSuites().iterator().next().getTestListRunInfosSet().add(testListRunInfo);
            }
         }
         file.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      dailyRunInfo.setTestSuitesPerEnv(testSuiteListPerEnv);

      return dailyRunInfo;
   }

   public static void main(String[] args) {
      //TODO get relative file path
      String filePath = "src/main/resources/Results_1418800162122.xls";

      DailyRunInfo dailyRunInfo = new XLSParser().parseXLS(filePath);


      ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml", "META-INF/spring/applicationPersistence.xml");
      DailyRunInfoService service = context.getBean("dailyRunInfoService", DailyRunInfoService.class);
      service.save(dailyRunInfo);

   }

}
*/