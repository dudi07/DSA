

public class App {
    public static void main(String[] args) {
        int[] richer = {1, 0};
        int[] poorer = {0, 1};
        MaximumPeopleInParty party = new MaximumPeopleInParty();
        int result = party.maxPeopleInParty(richer, poorer);
        System.out.println("Maximum people in party: " + result);
    }
}
