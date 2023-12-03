import java.lang.*;
class SingleTonMultiThread
{
    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();
    private static final Thread[] THREADS = new Thread[PROCESSOR_COUNT];
    private static int instancesCount = 0;
    private static SingleTonMultiThread instance = null;
 
    private SingleTonMultiThread()
    {}
 
    public static SingleTonMultiThread getInstance()
    {
        if (instance == null)
        {
            instancesCount++;
            instance = new SingleTonMultiThread();
        }
        return instance;
    }
 

    private static void reset()
    {
        instancesCount = 0;
        instance = null;
    }
 
    private static void validate()
    {
        if (SingleTonMultiThread.PROCESSOR_COUNT < 2)
        {
            System.out.print("PROCESSOR_COUNT Must be >= 2 to Run the test.");
            System.exit(0);
        }
    }
    public static void main(String... args)
    {
        long currentMili = System.currentTimeMillis();
        int testCount = 0;

        validate();
        System.out.printf("Summary :: PROCESSOR_COUNT %s, Running Test with %s of Threads. %n", PROCESSOR_COUNT, PROCESSOR_COUNT);
        do
        {
            reset();
            for (int i = 0; i < PROCESSOR_COUNT; i++)
            {
                THREADS[i] = new Thread(SingleTonMultiThread::getInstance);
            }   
                
            for (int i = 0; i < PROCESSOR_COUNT; i++)
            {
                THREADS[i].start();
            }
            
            for (int i = 0; i < PROCESSOR_COUNT; i++)
            {
                try
                {
                    THREADS[i].join();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                testCount++;
            }
        }while(instancesCount <= 1 && testCount < Integer.MAX_VALUE);
        
        System.out.printf("Singleton Pattern is broken after %d try. %nNumber of instances count is %d. %nTest duration %dms", testCount,instancesCount, System.currentTimeMillis() - currentMili);   
    }
} 