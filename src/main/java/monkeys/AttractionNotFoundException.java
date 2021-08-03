package monkeys;

class AttractionNotFoundException extends RuntimeException {

    AttractionNotFoundException(Long id) {
        super("Could not find attraction " + id);
    }
}