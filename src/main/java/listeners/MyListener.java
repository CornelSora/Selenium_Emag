package listeners;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.ConstructorOrMethod;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class MyListener implements IAnnotationTransformer {

    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        ConstructorOrMethod consOrMethod =iInvokedMethod.getTestMethod().getConstructorOrMethod();
        DisableListener disable = consOrMethod.getMethod().getDeclaringClass().getAnnotation(DisableListener.class);

        if (disable != null) {
            return;
        } else {
            System.out.println("test here");
        }
        // else resume your normal operations
    }


    public static LinkedHashMap<String, String> accounts = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> accountsNS = new LinkedHashMap<>();

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        //System.out.println(method.getName());
        //System.out.println("------------------------------------starting...");
        if (accounts.keySet().size() > 0) {
            accounts.clear();
        }
        if (accountsNS.keySet().size() > 0) {
            accountsNS.clear();
        }

        pupulateData(true);
        pupulateData(false);

        if ("LoginThenLogout".equals(method.getName())) {
            iTestAnnotation.setInvocationCount(accounts.keySet().size());
            //iTestAnnotation.setThreadPoolSize(4);
        } else if ("LoginNotSuccessful".equals(method.getName())) {
            iTestAnnotation.setInvocationCount(accountsNS.keySet().size());
        }
    }

    protected void pupulateData(boolean loginSuccessful) {
        try {
            String fileName = "";
            if (loginSuccessful) {
                fileName = "accounts-ok";
            } else {
                fileName = "accounts-not-ok";
            }

            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] datas = line.split(",");
                String uname = datas[0].trim();
                String password = datas[1].trim();
                if (loginSuccessful) {
                    MyListener.accounts.put(uname, password);
                } else {
                    MyListener.accountsNS.put(uname, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
