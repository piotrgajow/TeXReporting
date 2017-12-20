package webappcraft.reports.ui

class UserInterface {

    private final Scanner scanner = new Scanner(System.in)

    String askFor(String prompt) {
        print "Enter ${prompt}: "
        return scanner.nextLine()
    }

}
