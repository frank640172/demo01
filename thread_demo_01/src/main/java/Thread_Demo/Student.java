package Thread_Demo;

public class Student {
       String name;
       Integer age;

    public static void main(String[] args) {
        Student  s = new Student(){
            String  name = "xnn";
            Integer age = 18;
        };

        System.out.println(s.age + " " + s.name);

        Person p  = new Person() {
            @Override
            public void SetName() {

            }

            @Override
            public void SetAge() {

            }
        };

    }

    interface Person{
        void SetName();
        void SetAge();
    }

}
