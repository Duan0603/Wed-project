package model;

import view.Validation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class Company extends ArrayList<Customer> {
    public Company() {
        super();
        this.loadData("company.txt");
    }

    public void listAllCustomer(){
        this.listAllCustomer(this);
    }

    public void listAllCustomer(ArrayList<Customer> list){
        int total = list.size();
        if(total == 0){
            System.out.println("Sorry. Nothing to print!");
            return;
        }
        System.out.println("List all product");
        System.out.println("--------------------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println("Total: " + total + " customer.");
    }

    public void loadData(String fileName) {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String content;
            while((content = bf.readLine()) != null){
                String[] line = content.split(":");
                if (Validation.checkPattern(line[0], "KH\\d{2}$")) {
                    this.add(new Customer(line[0], line[1], line[2], new SimpleDateFormat("dd/MM/yyyy").parse(line[3])));
                }
            }
        }
        catch(IOException | NumberFormatException | ParseException e){
            System.err.println("Cannot read file");
        }
    }

    private boolean isPatientIDDuplicated(String productID) {
        productID = productID.trim().toUpperCase();

        for (Customer customer : this) {
            if (customer.getCustomerID().equalsIgnoreCase(productID)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Customer> search(Predicate<Customer> predicate){
        ArrayList<Customer> list = new ArrayList<>();

        for (Customer customer : this ) {
            if (predicate.test(customer)) {
                list.add(customer);
            }
        }
        if (list.isEmpty()) {
            System.err.println("List Empty.");
        }
        return list;
    }

    public void sortCustomerByName() {
        Collections.sort(this, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        listAllCustomer();
    }

    public void sortCustomerByID() {
        Collections.sort(this, (p1, p2) -> p1.getCustomerID().compareToIgnoreCase(p2.getCustomerID()));
        listAllCustomer();
    }

    public void sortCustomerByDob() {
        Collections.sort(this, Comparator.comparing(Customer::getDob));
        listAllCustomer();
    }

    public void statistical(String network) {
        System.out.println("Customer of " + network);
        int count = 0;

        System.out.println("-------------------------------------------------------------");

        for (Customer customer : this) {
            if (customer.getHomeNetwork().equalsIgnoreCase(network)) {
                System.out.println(customer);
                count++;
            }
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("Total: " + count);
    }
}
