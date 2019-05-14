import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/*Intsructions for running:
Compile the Java class file - javac filename.java
Run the java complied file - java filename
*/

/*
Sample output
All routes: ["SRT", "CVT", "Perkiomen", "CVT", "Perkiomen", "Welsh Mountain", "SRT", "Welsh Mountain", "Oaks to Philly"]
Unique routes: ["SRT", "CVT", "Perkiomen", "Welsh Mountain", "Oaks to Philly"]
For user 42: ["42SRT", "42CVT", "42Perkiomen", "CVT42", "Perkiomen42", "Welsh Mountain42", "42SRT42", "42Welsh Mountain42", "42Oaks to Philly42"]
For user 42 services ["Komoot", "RWGPS"]: ["42SRT42", "42Welsh Mountain42", "42Oaks to Philly42", "CVT42", "Perkiomen42", "Welsh Mountain42"]
*/

/**
 * The HHinterview is a class that has implementations
 * for three "services" (Strava, RWGPS, and Komoot).
 */
public class HHinterview {

    static final String[] SERVICES = {"Strava", "RWGPS", "Komoot"};

    //Given data as per 3 services
    static final String[] STRAVA = {"SRT", "CVT", "Perkiomen"};
    static final String[] RWGPS = {"CVT", "Perkiomen", "Welsh Mountain"};
    static final String[] KOMOOT = {"SRT", "Welsh Mountain", "Oaks to Philly"};

    /**
     * Get list of all the routes across all the services
     *
     * @return all the routes
     */
    private static ArrayList<String> all_routes() {
        ArrayList<String> allroutes = new ArrayList<>();
        allroutes.addAll(Arrays.asList(STRAVA));
        allroutes.addAll(Arrays.asList(RWGPS));
        allroutes.addAll(Arrays.asList(KOMOOT));
        return allroutes;
    }

    /**
     * Get list of all unique routes across all the services
     *
     * @return all unique routes
     */
    private static Set<String> unique_routes() {
        Set<String> unique_routes = new HashSet<>();
        unique_routes.addAll(Arrays.asList(STRAVA));
        unique_routes.addAll(Arrays.asList(RWGPS));
        unique_routes.addAll(Arrays.asList(KOMOOT));
        return unique_routes;
    }


    /**
     * Get list of all the routes the user has across all services.
     *
     * @param user_id the User id for appending to respective routes
     * @return all the routes with user_id appended
     */
    private static ArrayList<String> user_allRoutes(String user_id) {
        ArrayList<String> user_allRoutes_list = new ArrayList<>();
        for (String route : STRAVA) {
            user_allRoutes_list.add(user_id + route);
        }
        for (String route : RWGPS) {
            user_allRoutes_list.add(route + user_id);
        }
        for (String route : KOMOOT) {
            user_allRoutes_list.add(user_id + route + user_id);
        }
        return user_allRoutes_list;
    }

    /**
     * Get list of all the routes the user has across all services.
     *
     * @param user_id  the User id for appending to respective routes
     * @param services user's routes for only these services listed
     * @return routes with user_id appended
     */
    private static ArrayList<String> userRoutes_byServices(String user_id, ArrayList<String> services) {
        ArrayList<String> user_services_routes_list = new ArrayList<>();
        for (String service : services) {
            switch (service) {
                case "Strava":
                    for (String route : STRAVA) {
                        user_services_routes_list.add(user_id + route);
                    }
                    break;
                case "RWGPS":
                    for (String route : RWGPS) {
                        user_services_routes_list.add(route + user_id);
                    }
                    break;
                case "Komoot":
                    for (String route : KOMOOT) {
                        user_services_routes_list.add(user_id + route + user_id);
                    }
                    break;
		default:
                    System.out.println(service+" service unavailable (check if misspelled and case-sensitive), please try again.");
            }
        }
        return user_services_routes_list;
    }

    public static void main(String[] args) {
        String ip_choice = "";
        while(!ip_choice.equals("5")) {
            System.out.println("Select an option\n 1. Get all Routes\n 2. Get all Unique Routes\n 3. Get all User Routes\n 4. Get Users Routes by Service\n 5. Exit");
            Scanner scan = new Scanner(System.in);
            ip_choice = scan.nextLine();

            switch (ip_choice) {
                case "1":
                    System.out.println("All routes: " + Arrays.toString(all_routes().toArray()));
                    break;
                case "2":
                    System.out.println("Unique routes: " + Arrays.toString(unique_routes().toArray()));
                    break;
                case "3":
                    System.out.println("Enter User Id:");
                    String user_id = scan.nextLine();
                    System.out.println("For user " + user_id + ": " + Arrays.toString(user_allRoutes(user_id).toArray()));
                    break;
                case "4":
                    System.out.println("Enter New User Id:");
                    String new_user_id = scan.nextLine();
                    ArrayList<String> ip_services = new ArrayList<>();
                    while (ip_services.size() < 3) {
                        System.out.println("Enter one of these Services:[\"Komoot\", \"RWGPS\", \"Strava\"]");
                        String ip_service = scan.nextLine();
                        if (Arrays.asList(SERVICES).contains(ip_service)){
                            if (ip_services.contains(ip_service)) {
                                System.out.println(ip_service + " service added already.");
                            } else {
                                ip_services.add(ip_service);
                            }
                            System.out.println("Add another service? Type N to stop");
                            String ip = scan.nextLine();
                            if (ip.equals("N")) {
                                break;
                            }
                        }else{
                            System.out.println(ip_service+" service unavailable (check if misspelled and case-sensitive), please try again.");
                        }
                    }
                    System.out.println("For user " + new_user_id + " services" + Arrays.toString(ip_services.toArray()));
                    System.out.println(Arrays.toString(userRoutes_byServices(new_user_id, ip_services).toArray()));
                    break;
                case "5":
                    System.out.println("Goodbye");
                    System.exit(0);
                default:
                    System.out.println("Option unavailable, please try again.");
            }
        }
    }
}

