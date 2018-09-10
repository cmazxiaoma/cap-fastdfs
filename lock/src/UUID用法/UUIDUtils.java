package UUID用法;

import java.util.UUID;

public class UUIDUtils {

    public static void generateUUID() {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            System.out.println(uuid);
        }
    }
}
