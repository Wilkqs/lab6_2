import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongStudentAge extends Exception { }
class ZlyWybor extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break; 
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default:
                        throw new ZlyWybor(); 
                }
            } catch(IOException e) {
                
            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch(WrongStudentAge e) {
                System.out.println("Błędny wiek studenta!");
            } catch(InputMismatchException e) {
                System.out.println("Nieprawidlowy wybor");
                scan.nextLine(); 
            } catch(ZlyWybor e) {
                System.out.println("Nieprawidlowy wybor!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        try {
            return scan.nextInt();
        } catch(InputMismatchException e) {
            throw e; 
        }
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();
        return name;
    }

    public static int ReadAge() throws WrongStudentAge {
        System.out.println("Podaj wiek: ");
        int age = scan.nextInt();
        if(age < 0 || age > 100)
            throw new WrongStudentAge();
        return age;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge {
        String name = ReadName();
        int age = ReadAge();
        scan.nextLine();
        System.out.println("Podaj datę urodzenia DD-MM-YYYY");
        String date = scan.nextLine();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
