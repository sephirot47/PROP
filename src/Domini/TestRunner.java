package Domini;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class TestRunner 
{
	public static void RunDriver(final Class<?> testClass)
	{
		 Class c = EdgeTest.class;
			try 
			{
				while(true)
				{

					Method[] methods = c.getDeclaredMethods();
			        ArrayList<Method> testMethods = new ArrayList<Method>();
			        
			    	System.out.println("Test menu for " + c.getName() + ": " + "--------------------");
			    	System.out.println("");
			    	
			    	for (int i = 0; i < methods.length; i++) 
			        	if(methods[i].getName().contains("test")) testMethods.add(methods[i]);

			        for (int i = 0; i < testMethods.size(); i++) 
			        	System.out.println("\t" + (i+1) + ". " + testMethods.get(i).getName().substring(4));
			    	System.out.println("\t" + (testMethods.size() + 1) + ". Test ALL methods.");
			    	
		        	System.out.println("");
		            System.out.print("Introdueixi el número del test que vol provar: ");
		            Scanner in = new Scanner(System.in);
		            int testNum = in.nextInt();
		            
		            if(testNum <= 0  || testNum > testMethods.size() + 1) 
		            {
		            	System.out.println("Numero de test NO valid");
		            	continue;
		            }
		            
		            if(testNum == testMethods.size() + 1)
		            {
		            	for(int i = 0; i < testMethods.size(); ++i)
		            	{
		            		TestRunner tr = new TestRunner();
			            	TestRunner.Result r = tr.RunTest(c, testMethods.get(i).getName());
			            	if(r.isOK()) System.out.println((i+1) + ". Test OK!");
			            	else System.out.println((i+1) + ". Test fail.");
		            	}
		            }
		            else
	            	{
	            		TestRunner tr = new TestRunner();
	    		    	System.out.println("");
		            	System.out.println("Testing " + testMethods.get(testNum-1).getName().substring(4) + "...");
				    	System.out.println("");
		            	TestRunner.Result r = tr.RunTest(c, testMethods.get(testNum-1).getName());
		            	if(r.isOK()) System.out.println("************** Test OK! **************");
		            	else System.out.println("************** El test ha fallat **************");
	            	}
			    	System.out.println("");
			    	for(int i = 0; i < 2; ++i)System.out.println(":::::::::::::::::::::");
			    	System.out.println("");
				}
	        }
			catch (Throwable e)
			{
	            System.err.println("ERROR: " + e);
	            e.printStackTrace();
	        }
	}
	
	public static Result RunTest(final Class<?> testClass, final String methodName) throws InitializationError 
	{
        BlockJUnit4ClassRunner runner = new BlockJUnit4ClassRunner(testClass)
        {
            @Override
            protected List<FrameworkMethod> computeTestMethods() 
            {
                try 
                {
                    Method method = testClass.getMethod(methodName);
                    return Arrays.asList(new FrameworkMethod(method));

                } 
                catch (Exception e) 
                {
                    throw new RuntimeException(e);
                }
            }
        };
        
        Result res = new Result();
        runner.run(res);
        return res;
    }

    public static class Result extends RunNotifier 
    {
        Failure failure;

        @Override
        public void fireTestFailure(Failure failure) 
        {
            this.failure = failure;
        };

        boolean isOK() 
        {
            return failure == null;
        }

        public Failure getFailure() 
        {
            return failure;
        }
    }
}
