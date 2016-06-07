import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by Ignas on 2016-05-15.
 */
public class UserInterface {
    private Connection connection;

    private Car car;
    private Client client;
    private Model model;
    private Order order;

    BufferedReader input =  new BufferedReader(new InputStreamReader(System.in));

    public UserInterface(Car car, Client client, Model model, Order order, Connection con){
        this.car = car;
        this.model = model;
        this.client = client;
        this.order = order;
        this.connection = con;
    }

    public void start(){
        try {
            showMenu();
            System.out.print("\nPasirinkite menu punktą: ");
            String choice = input.readLine();

            while(true){
                switch(choice){
                    case "0":
                        exit(connection);
                        break;
                    case "1":
                        client.displayClients();
                        break;
                    case "2":
                        addClient();
                        break;
                    case "3":
                        updateClientAddress();
                        break;
                    case "4":
                        updateClientPhoneNumber();
                        break;
                    case "5":
                        findClientById();
                        break;
                    case "6":
                        removeClientById();
                        break;
                    case "7":
                        car.displayCars();
                        break;
                    case "8":
                        addModel();
                        break;
                    case "9":
                        addCar();
                        break;
                    case "10":
                        updateCarPrice();
                        break;
                    case "11":
                        removeCarById();
                        break;
                    case "12":
                        order.displayOrders();
                        break;
                    case "13":
                        createOrder();
                        break;
                    case "14":
                        returnCar();
                        break;
                    default: System.out.println("Tokio pasirinkimo nėra!");
                        break;
                }
                showMenu();
                System.out.print("\nPasirinkite menu punktą: ");
                choice = input.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createOrder() throws IOException {
        car.showAvailableCars();
        System.out.println("Pasirinkite automobilį (veskite jo valstybinį numerį");
        String carId = input.readLine();
        client.displayClients();
        System.out.println("Įveskite AK žmogaus, kuris išsinuomoja automobilį");
        long id = Long.parseLong(input.readLine());
        order.createOrder(carId, id);
    }

    private void returnCar() throws IOException {
        order.displayOrders();
        System.out.println("Valstybinis numeris automobilio, kurį norite grąžinti");
        String carId = input.readLine();
        order.returnCar(carId);
    }

    private void removeCarById() throws IOException {
        car.displayCars();
        System.out.println("Valstybinis numeris automobilio, kurį norite panaikinti");
        String carId = input.readLine();
        car.remove(carId);
    }

    private void updateCarPrice() throws IOException {
        car.displayCars();
        System.out.println("Valstybinis numeris automobilio, kurio kainą keisite");
        String carId = input.readLine();
        System.out.println("Nauja kaina");
        int price = Integer.parseInt(input.readLine());
        car.updatePrice(carId, price);
    }

    private void addCar() throws IOException {
        model.displayModels();
        System.out.println("Įveskite modelio ID");
        int id = Integer.parseInt(input.readLine());
        System.out.println("Įveskite valstybinį numerį");
        String carId = input.readLine();
        System.out.println("Įveskite kainą");
        int price = Integer.parseInt(input.readLine());
        car.addCar(id, carId, price);
    }

    private void removeClientById() throws IOException {
        System.out.println("Įveskite AK:");
        long id = Long.parseLong(input.readLine());
        client.removeClient(id);
    }

    private void findClientById() throws IOException {
        System.out.println("Įveskite AK:");
        long id = Long.parseLong(input.readLine());
        client.findClientById(id);
    }

    private void updateClientPhoneNumber() throws IOException {
        client.displayClients();
        System.out.println("Įveskite kliento AK, kurio adresą keisite:");
        long id = Long.parseLong(input.readLine());
        System.out.println("Įveskite naują tel. nr.:");
        String phone = input.readLine();
        client.updatePhoneNumber(id, phone);
    }

    private void updateClientAddress() throws IOException {
        client.displayClients();
        System.out.println("Įveskite kliento AK, kurio adresą keisite:");
        long id = Long.parseLong(input.readLine());
        System.out.println("Įveskite naują adresą:");
        String address = input.readLine();
        client.updateAddress(id, address);
    }

    private void addModel() throws IOException {
        System.out.println("Iveskite gamintoja:");
        String brand = input.readLine();
        System.out.println("Iveskite modeli");
        String models = input.readLine();
        System.out.println("Iveskite metus");
        int year = Integer.parseInt(input.readLine());
        model.addModel(brand, models, year);
    }

    private void addClient() throws IOException {
        System.out.println("Iveskite AK:");
        long id = Long.parseLong(input.readLine());

        System.out.println("Įveskite vardą");
        String name = input.readLine();

        System.out.println("Įveskite pavardę");
        String surname = input.readLine();

        System.out.println("Įveskite tel. nr.");
        String phone = input.readLine();

        System.out.println("Įveskite adresą");
        String address = input.readLine();

        client.addClient(id, name, surname, phone, address);
    }

    private void showMenu(){
        System.out.println();
        System.out.println("+-------------------------------------+");
        System.out.println("|******Automobiliu*nuomos*sistema*****|");
        System.out.println("+-------------------------------------+");
        System.out.println("|        Klientų valdymo dalis        |");
        System.out.println("+-------------------------------------+");
        System.out.println("|  1. Peržiūrėti klientus             |");
        System.out.println("|  2. Pridėti naują klientą           |");
        System.out.println("|  3. Atnaujinti kliento adresą       |");
        System.out.println("|  4. Atnaujinti kliento tel. nr.     |");
        System.out.println("|  5. Surasti klientą pagal asm. kodą |");
        System.out.println("|  6. Panaikinti klientą              |");
        System.out.println("+-------------------------------------+");
        System.out.println("|      Automobilių valdymo dalis      |");
        System.out.println("+-------------------------------------+");
        System.out.println("|  7. Peržiūrėti automobilius         |");
        System.out.println("|  8. Pridėti naują modeli            |");
        System.out.println("|  9. Pridėti naują automobili        |");
        System.out.println("| 10. Atnaujinti automobilio kainą    |");
        System.out.println("| 11. Panaikinti automobilį           |");
        System.out.println("+-------------------------------------+");
        System.out.println("|        Nuomų valdymo dalis          |");
        System.out.println("+-------------------------------------+");
        System.out.println("| 12. Peržiūrėti nuomas               |");
        System.out.println("| 13. Išnomuoti automobilį            |");
        System.out.println("| 14. Grąžinti automobilį             |");
        System.out.println("+-------------------------------------+");
        System.out.println("| 0. Išeiti iš sistemos               |");
        System.out.println("+-------------------------------------+");
    }

    public void exit(Connection con){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Can not close connection!");
                e.printStackTrace();
            }
        }
        System.exit(1);
    }
}

