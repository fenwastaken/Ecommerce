package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

        private static final Logger logger = LogManager.getLogger("myLogger");

        public static void catchError(Throwable t){
        	logger.catching(t);
        }
        
        public static void debug(String message){
        	logger.debug(message);
        }
	
}
