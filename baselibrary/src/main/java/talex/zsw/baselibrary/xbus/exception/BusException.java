package talex.zsw.baselibrary.xbus.exception;

/**
 * User: mcxiaoke
 * Date: 15/8/5
 * Time: 16:33
 */
public class BusException extends RuntimeException
{

    public BusException(final String detailMessage) {
        super(detailMessage);
    }

    public BusException(final String detailMessage, final Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BusException(final Throwable throwable) {
        super(throwable);
    }

    public BusException() {
        super();
    }
}
