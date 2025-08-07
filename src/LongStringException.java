public class LongStringException extends Exception

{

        public LongStringException() {
        }

        public LongStringException(String message) {
            super(message);
        }

        public LongStringException(String message, Throwable cause) {
            super(message, cause);
        }

        public LongStringException(Throwable cause) {
            super(cause);
        }

        public LongStringException(String message, Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }


