package reflection.dependencyInjection;


class TestClassB {
    @Autowired
    TestClassC c;

    public TestClassC getC() {
        return c;
    }

    public void setC(TestClassC c) {
        this.c = c;
    }
}

