public abstract class ClassWithAbstractMethod {
    public abstract String absMethod1(int num);
    protected abstract boolean absMethod2(String str);
    abstract float absMethod3(int num, String str);
    protected abstract boolean absMethod4(String str);
    abstract float absMethod5(int num, String str);

    public static void main(String[] args) {
        System.out.println("This is a class with Abstract Methods");
    }
}