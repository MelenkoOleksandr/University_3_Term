package ClassReader;

public class Programmer extends Human{

        private String language;

        public Programmer(String name, int age, String language) {
            super(name, age);
            this.language = language;
        }

        @Override
        public void sayHello() {
            System.out.println("Hello, my name is " + getName() + " and I'm " + getAge() + " years old. I'm a " + language + " programmer.");
        }

        public void code() {
            System.out.println("I'm coding in " + language + " language.");
        }
}
