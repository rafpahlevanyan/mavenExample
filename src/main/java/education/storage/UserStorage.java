package education.storage;


import education.exception.UserNotFoundException;
import education.model.User;
import education.util.FileUtil;


import java.util.HashMap;
import java.util.Map;


public class UserStorage {
    Map<String, User> userMap = new HashMap<>();

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }
//    private User[] users = new User[20];
//    private int size;

    public void add(User user) {
        userMap.put(user.getEmail(), user);


        FileUtil.serializeUserMap(userMap);

//        if (size == users.length) {
//            extend();
//        }
//        users[size++] = user;
    }

//    private void extend() {
//        User[] tmp = new User[users.length * 2];
//        System.arraycopy(users, 0, tmp, 0, size);
//        users = tmp;
//    }

    public void print() {
        System.out.println(userMap.toString());
//        for (int i = 0; i < size; i++) {
//            System.out.println(users[i]);
//        }
    }

    public User getByEmail(String email) throws UserNotFoundException {
//        for (int i = 0; i < size; i++) {
//            if (users[i].getEmail().equals(email)) {
//                return users[i];
//            }
//        }
        return userMap.get(email);
    }

    public void initData() {
        Map<String, User> userMapFromFile = FileUtil.deSerializeUserMap();
        if (userMapFromFile != null) {
            userMap = userMapFromFile;
        }
    }
}
