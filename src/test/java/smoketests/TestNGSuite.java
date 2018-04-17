package smoketests;

import listeners.MyListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGSuite {
    public static void main(String[] args) {
        System.out.println("main start");
        try {
            new TestNGSuite(new Class[]{ LoginThenLogout.class, LoginNotSuccessful.class });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("main finish");
    }

    public TestNGSuite(Class[] classes) throws Exception {
        // Create Suite List
        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        // Add Suite to Suite List
        XmlSuite suite = new XmlSuite();
        suites.add(suite);
        suite.setName("MyTestSuite");

        // Add Test to Suite
        XmlTest test = new XmlTest(suite);
        test.setName("MyTest");

        // Add Class List to Test
        List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
        test.setXmlClasses(xmlClasses);

        // Add Class to Class List
        for(Class clazz: classes) {
            XmlClass xmlClass = new XmlClass(clazz);
            xmlClasses.add(xmlClass);
        }

        // Run TestNG
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        MyListener myTransformer = new MyListener();
        testNG.setAnnotationTransformer(myTransformer);
        testNG.run();

        if(testNG.hasFailure()) { // Throw an exception to make mvn goal fail
            throw new Exception("Failed Tests");
        }
    }
}

