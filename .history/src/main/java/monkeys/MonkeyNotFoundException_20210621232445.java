package payroll;

class MonkeyNotFoundException extends RuntimeException {

  MonkeyNotFoundException(Long id) {
    super("Could not find monkey " + id);
  }
}