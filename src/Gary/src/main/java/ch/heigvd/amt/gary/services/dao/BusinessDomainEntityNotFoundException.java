package ch.heigvd.amt.gary.services.dao;

/**
 *
 * @author Jan Purro
 */
public class BusinessDomainEntityNotFoundException extends Exception {

  /**
   * Creates a new instance of
   * <code>BusinessDomainEntityNotFoundException</code> without detail message.
   */
  public BusinessDomainEntityNotFoundException() {
  }

  /**
   * Constructs an instance of
   * <code>BusinessDomainEntityNotFoundException</code> with the specified
   * detail message.
   *
   * @param msg the detail message.
   */
  public BusinessDomainEntityNotFoundException(String msg) {
    super(msg);
  }
}