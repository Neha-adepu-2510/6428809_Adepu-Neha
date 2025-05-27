import java.lang.reflect.*;

public class ReflectExample {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("Person");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println("Declared Methods:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method: " + method.getName());
                System.out.println("Return Type: " + method.getReturnType());

                Class<?>[] paramTypes = method.getParameterTypes();
                System.out.print("Parameters: ");
                for (Class<?> param : paramTypes) {
                    System.out.print(param.getName() + " ");
                }
                System.out.println("\n---");
                method.setAccessible(true);
                if (method.getName().equals("sayHello")) {
                    Object result = method.invoke(obj, "Alice");
                    System.out.println("Invocation Result: " + result);
                } else if (method.getName().equals("add")) {
                    Object result = method.invoke(obj, 10, 20);
                    System.out.println("Invocation Result: " + result);
                }
                System.out.println("=====");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
