package reflection.dependencyInjection;

class TestClassA {
    @Autowired
    TestClassB b;

    TestClassC c;

    public TestClassB getB() {
        return b;
    }

    public void setB(TestClassB b) {
        this.b = b;
    }

    public TestClassC getC() {
        return c;
    }

    public void setC( TestClassC c) {
        this.c = c;
    }
}
