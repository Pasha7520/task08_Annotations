import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException,
            InstantiationException, NoSuchMethodException {
        MyClass myClass = new MyClass("Ivan", "Golub", "golub123", "7520", "nothing");
        List<MyClass> list = new ArrayList<>();
        list.add(new MyClass("Ivan", "Golub", "golub123", "7520", "nothing"));
        task2(myClass);
        System.out.println("///////////////////");
        task3(myClass);
        System.out.println("///////////////////");
        task4(myClass);
        System.out.println("///////////////////");
        task5(myClass);
       // task6(myClass, list);
    }

    public static void task2(Object o) {
        List<Field> fields = Arrays.stream(o.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAnnotation.class)).collect(Collectors.toList());
        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }

    public static void task3(Object o) {
        List<Field> fields = Arrays.stream(o.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAnnotation.class)).collect(Collectors.toList());
        for (Field field : fields) {
            MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
            System.out.println(field.getName() + " notNull = " + myAnnotation.notNull() + " value = " + myAnnotation.value()
                    + " unique = " + myAnnotation.unique());
        }
    }

    public static void task4(Object o) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException,
            InstantiationException, NoSuchMethodException {
        Class<?> clazz = Class.forName(o.getClass().getName());
        System.out.println(clazz);
        Object myClass = clazz.newInstance();
        Class<?>[] paramTypes = {String[].class};
        Method method1 = myClass.getClass().getMethod("fullName", paramTypes);
        System.out.print("First method (fullName) - ");
        System.out.println(method1.invoke(myClass, new Object[]{new String[]{"LOl ", "King"}}));
        Method method2 = myClass.getClass().getMethod("fullInfo", paramTypes);
        System.out.print("Second method (fullInfo) - ");
        String[] strings = (String[]) method2.invoke(myClass, new Object[]{new String[]{"LOl", "King", "qwerty", "7520"}});
        for (String s : strings) {
            System.out.print("[" + s + "]");
        }
        System.out.println();
        Method method3 = myClass.getClass().getMethod("lengthMoreThenMust", int.class, String[].class);
        String[] test = new String[]{"LOl", "King", "qwerty", "7520"};
        System.out.println(method3.invoke(myClass, 40, test));
    }

    public static void task5(Object o) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> clazz = Class.forName(o.getClass().getName());
        Object object = clazz.newInstance();
        Field[] fields = object.getClass().getDeclaredFields();

        System.out.println(object.toString());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().equals(int.class)) {
                field.set(object, 11);
            } else if (field.getType().equals(String.class)) {
                field.set(object, "Unknown");
            }
        }
        System.out.println(object.toString());
    }

    public static void task6(MyClass myClass, List<MyClass> list) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean entrance ;
        do {
            entrance = false;
            System.out.println("Fill registration form!!");
            System.out.println("enter your name :");
            myClass.setName(scanner.nextLine());
            System.out.println("enter your sername :");
            myClass.setSername(scanner.nextLine());
            System.out.println("enter your login :");
            myClass.setLogin(scanner.nextLine());
            System.out.println("enter your password :");
            myClass.setPassword(scanner.nextLine());
            System.out.println("repeat your password :");
            if(!(scanner.nextLine().equals(myClass.getPassword()))) {
                System.out.println("repeat password wrong");
                entrance = true;
                continue;
            }
            System.out.println("enter description :");
            myClass.setDescription(scanner.nextLine());
        } while (fillWrong(myClass, list) || entrance);
        list.add(myClass);
        System.out.println("Your registration successful!");
    }

    public static boolean fillWrong(MyClass myClass, List<MyClass> list) throws IllegalAccessException {
        List<Field> fields = Arrays.stream(myClass.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAnnotation.class)).collect(Collectors.toList());
        for (Field field : fields) {
            MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
            if(field.get(myClass).toString().length() < myAnnotation.value()){
                System.out.println("too little symbols in "+field.getName());
                return true;
            }
            if(myAnnotation.notNull()){
                if(field.get(myClass).toString().length()<1){
                    System.out.println(field.getName()+" have to be not null");
                    return true;
                }
            }
            if(myAnnotation.unique()){
                for(MyClass myClass1 : list) {
                    if(field.get(myClass).toString().equals(myClass1.getLogin())) {
                        System.out.println("Such login already exists");
                        return true;
                    }
                }
            }

        }
        return false;
    }


}
