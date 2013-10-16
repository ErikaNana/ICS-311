package dll;

/** An exception when an illegal number of nodes in a list occurred.
  * @author Jan Stelovsky
  * @inspiration E. Biagioni & W. Albritton */
@SuppressWarnings("serial")
public class NodeCountException extends Exception {

  /** Creates a new exception indicating that an illegal 
    * number of nodes in a list did occur.
    *  @param message describes why the exception is being thrown */
  public NodeCountException (String message) {
    super (message);
  }
}
