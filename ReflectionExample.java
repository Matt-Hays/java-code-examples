import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            // Obtain the class. *Not an object.
            Class<ClassToReflect> clazz = ClassToReflect.class;

            // Get an instance of the class to manipulate in our example.
            ClassToReflect obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println("Object Prior to Reflection:\n" + obj);

            // Get the private field and manipulate its value.
            Field reflectedField = clazz.getDeclaredField("variableToReflect");
            reflectedField.setAccessible(true);
            reflectedField.set(obj, 55);
            System.out.println("\nAfter changing the private field:\n" + obj);
 
            // Get the private method and invoke the method on the private field.
            Method m = clazz.getDeclaredMethod("setVariableToReflect", int.class);
            m.setAccessible(true);
            m.invoke(obj, 22);
            System.out.println("\nAfter using the private setter method:\n" + obj);

            // Can we access the property normally? No, it's still private! We only gained access through reflection.
            // Uncommenting the following code will reveal a compile error.
            // obj.variableToReflect = 5;
            // obj.setVariableToReflect(15);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClassToReflect {
    private int variableToReflect;

    public ClassToReflect() {
        setVariableToReflect(1);
    }

    public int getVariableToReflect(){
        return this.variableToReflect;
    }

    private void setVariableToReflect(int num) {
        this.variableToReflect = num;
    }

    @Override
    public String toString() {
        return "ReflectionExample [varibleToReflect=" + variableToReflect + "]";
    }
}