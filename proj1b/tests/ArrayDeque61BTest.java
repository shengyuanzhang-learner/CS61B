import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }
     @Test
     void addfirsttext(){
         ArrayDeque61B deque = new ArrayDeque61B();
         deque.addFirst("Hello");
         deque.addFirst("World");
         assertThat(deque.size() == 2).isTrue();
         assertThat(deque.get(0)).isEqualTo("World");
         assertThat(deque.get(1)).isEqualTo("Hello");
     }
     @Test
     void addlasttext(){
         ArrayDeque61B deque = new ArrayDeque61B();
         deque.addLast("Hello");
         deque.addLast("World");
         assertThat(deque.size() == 2).isTrue();
         assertThat(deque.get(0)).isEqualTo("Hello");
         assertThat(deque.get(1)).isEqualTo("World");
     }
     @Test
     void removetext(){
         ArrayDeque61B deque = new ArrayDeque61B();
         deque.addFirst("Hello");
         deque.addFirst("World");
         assertThat(deque.size() == 2).isTrue();
         deque.removeFirst();
         assertThat(deque.size() == 1).isTrue();
         assertThat(deque.get(0)).isEqualTo("Hello");
         deque.addLast("World");
         deque.addLast("Hello");
         assertThat(deque.size() == 3).isTrue();
         deque.removeLast();
         assertThat(deque.size() == 2).isTrue();
         assertThat(deque.get(1)).isEqualTo("World");


     }
     @Test
     void resizetext(){
         ArrayDeque61B deque = new ArrayDeque61B();
         deque.addFirst("Hello1");
         deque.addFirst("World2");
         deque.addLast("Hello3");
         deque.addLast("World4");
         deque.addFirst("hello,");
         deque.addLast("world1");
         deque.addLast("world2");
         deque.addLast("Hello5");
         deque.addLast("world6");
         deque.addLast("hello7");
         deque.addLast("world8");
         deque.addLast("hello9");
         deque.addLast("world10");
         deque.addLast("world11");
         deque.addLast("world12");
         assertThat(deque.size() == 15).isTrue();
         assertThat(deque.get(0)).isEqualTo("Hello5");
         assertThat(deque.get(1)).isEqualTo("hello,");
         assertThat(deque.get(14)).isEqualTo("world11");

     }



}
