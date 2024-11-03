import java.util.Scanner;
public class Main {
    public static String[] todos = new String[10];
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        showMainMenu();
    }
    public static void showTodoList() {
        System.out.println("TODO LIST");
        for (var i = 0; i < todos.length; i++) {
            var todo = todos[i];
            if (todo != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }
    public static void addTodoList(String todo) {
        resizeArrayIfFull();
        // add todo to array that has null element
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }
    private static void resizeArrayIfFull() {
        // cek whether todos is full
        Boolean isFull = true;
        isFull = isArrayFull(isFull);
        // if full, resize current array two times bigger
        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }
    private static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }
    private static Boolean isArrayFull(Boolean isFull) {
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }
    public static boolean removeTodoList(Integer number) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        for (int i = number - 1; i < todos.length; i++) {
            // if todo is the last element
            if (i == (todos.length - 1)) {
                todos[i] = null;
            } else {
                // replace with the element on the right
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }
    private static boolean isSelectedTodoNotValid(final Integer number) {
        // cek if the number is zero or less than zero
        if (number <= 0) {
            return true;
        }
        // check if the number is greater than the todos size/length
        if (number - 1 > todos.length - 1) {
            return true;
        }
        // check whether the element is already null
        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }
    public static boolean editTodoList(Integer number, String newTodo) {
        if (isSelectedTodoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }
    public static String input(String info) {
        System.out.print(info + " : ");
        var data = scanner.nextLine();
        return data;
    }
    public static void showMainMenu() {
        // infinite loop so the program will always run
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("MENU : ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            // input untuk menerima input dari user
            String selectedMenu = input("Pilih");
            switch (selectedMenu) {
                case "1":
                    showMenuAddTodoList();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    break;
                case "3":
                    showMenuEditTodoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar");
            }
        }
    }
    public static void showMenuRemoveTodoList() {
        System.out.println("MENGHAPUS TODO LIST");
        var number = input("Nomor yang dihapus (x jika batal)");
        if (number.equals("x")) {
            //batal
        } else {
            boolean success = removeTodoList(Integer.valueOf(number));
            if (!success) {
                System.out.println("Gagal menghapus todo list : " + number);
            }
        }
    }
    public static void showMenuAddTodoList() {
        System.out.println("MENAMBAH TODO LIST");
        var todo = input("Todo (x jika batal)");
        if (todo.equals("x")) {
            // batal
        } else {
            addTodoList(todo);
        }
    }
    public static void showMenuEditTodoList() {
        System.out.println("MENGEDIT TODO LIST");
        String selectedTodo = input("Masukkan nomor todo (x jika batal)");
        if (selectedTodo.equals("x")) {
            return;
        }
        String newTodo = input("Masukkan todo yang baru (x jika batal)");
        if (newTodo.equals("x")) {
            return;
        }
        boolean isEditTodoSuccess = editTodoList(Integer.valueOf(selectedTodo), newTodo);
        if (isEditTodoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }
}