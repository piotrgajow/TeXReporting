package webappcraft.reports.main

class UserInterface {

    static final Scanner scanner = new Scanner(System.in)

    String askFor(String prompt) {
        print "Enter ${prompt}: "
        return scanner.nextLine()
    }

}
