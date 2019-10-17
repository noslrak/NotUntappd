package ui;

class EmptyListException extends Exception {
    EmptyListException() {
        System.out.println("No beers have been entered");
    }
}
