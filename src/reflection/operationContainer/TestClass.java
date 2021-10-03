package reflection.operationContainer;

public class TestClass {
        @BotRequestMapping("hello")
        void methodA1() {
            System.out.println("hello world!!!");
        }

        @BotRequestMapping("goodbye")
        void methodA2() {
            System.out.println("goodbye my friend!!!");
        }

        void methodA3() {
            System.out.println("bad call!!");
        }
}
