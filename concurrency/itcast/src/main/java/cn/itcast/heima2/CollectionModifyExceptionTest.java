package cn.itcast.heima2;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Collection<User> users = new ArrayList();

        //new ArrayList();

		/*Field modCount = AbstractList.class.getDeclaredField("modCount");
		modCount.setAccessible(true);
		System.out.println(modCount.get(users));
		Arrays.stream(ArrayList.class.getDeclaredClasses())
				.filter((e)->e.getSimpleName().equals("Itr")).forEach(e->{
			Field expectedModCount = null;
			try {
				expectedModCount = e.getDeclaredField("expectedModCount");
				System.out.println(expectedModCount);
				expectedModCount.setAccessible(true);
				Object o = expectedModCount.get(itrUsers);
				System.out.println("--->"+o);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}

		});*/
        users.add(new User("张三", 28));
        users.add(new User("李四", 25));
        users.add(new User("王五", 31));
        final Iterator<User> itrUsers = users.iterator();
        while (itrUsers.hasNext()) {
            System.out.println("aaaa");
            User u = itrUsers.next();
            System.out.println(u.getName());
            if ("李四".equals(u.getName())) {
                users.remove(u);
            }

        }
    }
}	 
