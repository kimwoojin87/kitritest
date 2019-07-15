import com.kitri.dto.Test;

public class LambdaTest {
	public static void test(Test t) {
		t.m(10);
	}
	public static void main(String[] args) {
		//TestImpl impl = new TestImpl();
		//test(impl);
		//i->System.out.println(i)
		//
		test(i->System.out.println(i));//오버라이딩 할 작업이 줄어듬
	}
}
