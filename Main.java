
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import info.Med;
import info.Invoice;

public class Main {
    static Map<Integer, Med> map = new HashMap<Integer, Med>();
    static Map<Integer, Integer> prodmap = new HashMap<Integer, Integer>();
    static List<String> ProductName = new ArrayList<String>();
    static List<Integer> ProductPrice = new ArrayList<Integer>();
    static List<Integer> ProductQty = new ArrayList<Integer>();
    static String InvoiceTitle = new String("BBHIS Pharmacy Private Limited");
    static String SubTitle = new String("Invoice");
    static String CustName, CustPh;
    static int b;
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Welcome to BBHIS Pharmacy");

        // sc.useDelimiter("-");
        String line = "";
        String splitBy = "-";
        int count = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("medicines.csv"));
            while ((line = br.readLine()) != null) {

                String[] arr = line.split(splitBy); // use comma as separator
                Med b1 = new Med(arr[0].toUpperCase(), arr[1], arr[2], arr[3].toUpperCase(), Integer.parseInt(arr[4]));
                map.put(count, b1);
                count++;

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Please enter your name: ");
        CustName = sc.nextLine();
        System.out.println("Please enter your Phone Number: ");
        CustPh = sc.nextLine();

        String inp,x;
        int key;
        Boolean loop = true;
        while (loop) {

            System.out.println("Do you know the name of the Medicine (Y/N)");
            inp = sc.next().toUpperCase();
            if (inp.equals("N")) {
                for (Map.Entry<Integer, Integer> entry : sym().entrySet()) {
                    key = entry.getKey();
                    b = entry.getValue();
                    prodmap.compute(key, (key1, val)
                            -> (val == null)
                            ? b
                            : val + b);
                    System.out.println(key + ": " + b);
                }

            } else if (inp.equals("Y")) {
                for (Map.Entry<Integer, Integer> entry : knowname().entrySet()) {
                    key = entry.getKey();
                    b = entry.getValue();
                    prodmap.compute(key, (key1, val)
                            -> (val == null)
                            ? b
                            : val + b);
                    System.out.println(key + ": " + b);
                }
            }
            System.out.println("Do you wish to purchase more meds? (Y/N)");
            x = sc.next().toUpperCase();
            if (x.equals("N"))
            {
                loop = false;
            }

        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : prodmap.entrySet()) {
            int keyy = entry.getKey();
            int by = entry.getValue();
//            System.out.println(keyy + ": " + map.get(keyy).name + " Quantity: " + by + " Price: " + map.get(keyy).cost +  " Total: "+ map.get(keyy).cost*by );
            sum += map.get(keyy).cost*by;
            ProductName.add(map.get(keyy).name);
            ProductPrice.add(map.get(keyy).cost);
            ProductQty.add(by);

        }
        Invoice i = new Invoice(ProductName,ProductPrice,ProductQty,InvoiceTitle,SubTitle, CustName, CustPh, sum, ProductName.size());
        System.out.println("Thank you for coming to BBHIS Pharmacy! You may find you invoice at invoice.pdf");
    }

    static Map<Integer, Integer> sym() {
        Map<Integer, Integer> prodmap1 = new HashMap<Integer, Integer>();
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> products = new LinkedList<Integer>();
        LinkedList<Integer> quantity = new LinkedList<Integer>();
        System.out.println("What are your symptoms");
        String symptom = sc.nextLine();
        if (symptom.equals("0")) {
            System.out.println("Thank you for coming to BBHIS Pharmacy! Please get sick again!");
            System.exit(0);
        }
        int count = 0;
        for (Map.Entry<Integer, Med> entry : map.entrySet()) {
            int key = entry.getKey();
            Med b = entry.getValue();
            if (b.uses.contains(symptom.toUpperCase())) {
                System.out.println(
                        key + ": " + b.name + " " + b.company + " " + b.quantity + " " + b.uses + " $" + b.cost);
                count++;
            }
        }
        if(count == 0){
            System.out.println("Sorry Medicine not found. Please do come back again!");
            System.exit(0);
        }
        int choice;
        for (int i = 0; i < count; i++) {
            System.out.println("Which one do you want?");
            choice = sc.nextInt();
            if (choice == 0)
                break;
            System.out.println("How much do you want?");
            int quant = sc.nextInt();
            if (quant == 0) {
                System.out.println("0 Quantity not allowed. Please reselect what you want");
                continue;
            }
            products.push(choice);
            quantity.push(quant);
            if (i < count - 1) {
                System.out.println("Do you want to buy the other medicine too? (Y/N)");
                char m = sc.next().toUpperCase().charAt(0);
                if (m == 'N') {
                    break;
                }
            }
        }
        for (int i = 0; i < products.size(); i++) {
            prodmap1.put(products.get(i), quantity.get(i));
        }
        return prodmap1;

    }

    static Map<Integer, Integer> knowname() {
        Map<Integer, Integer> prodmap1 = new HashMap<Integer, Integer>();
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> products = new LinkedList<Integer>();
        LinkedList<Integer> quantity = new LinkedList<Integer>();
        System.out.println("What is the name of the Medicine");
        String n = sc.nextLine();
        if (n.equals("0")) {
            System.out.println("Thank you for coming to BBHIS Pharmacy! Toodeloo");
            System.exit(0);
        }
        int count = 0;

        for (Map.Entry<Integer, Med> entry : map.entrySet()) {
            int key = entry.getKey();
            Med b = entry.getValue();
            if (b.name.contains(n.toUpperCase())) {
                System.out.println(
                        key + ": " + b.name + " " + b.company + " " + b.quantity + " " + b.uses + " $" + b.cost);
                count++;
            }
        }
        if(count == 0){
            System.out.println("Sorry Medicine not found. Please do come back again!");
            System.exit(0);
        }
        int choice;
        for (int i = 0; i < count; i++) {
            System.out.println("Which one do you want?");
            choice = sc.nextInt();
            if (choice == 0)
                break;
            System.out.println("How much do you want?");
            int quant = sc.nextInt();
            if (quant == 0) {
                System.out.println("0 Quantity not allowed. Please reselect what you want");
                continue;
            }
            products.push(choice);
            quantity.push(quant);
            if (i < count - 1) {
                System.out.println("Do you want anything else? (Y/N)");
                char m = sc.next().toUpperCase().charAt(0);
                if (m == 'N') {
                    break;
                }
            }
        }
        for (int i = 0; i < products.size(); i++) {

            prodmap1.put(products.get(i), quantity.get(i));
        }
        return prodmap1;

    }

}
