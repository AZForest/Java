import java.util.Arrays;

//Input: pattern = "abba", s = "dog cat cat dog"
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] array = s.split(" ");
        String[] key = pattern.split("");
        if (array.length != key.length) return false;
        HashMap<String, String> hash = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            if (hash.isEmpty()) {
                hash.put(key[i], array[i]);
            } else if (!hash.containsKey(key[i])) {
                if (hash.containsValue(array[i])) return false;
                hash.put(key[i], array[i]);
            }
        }
        for (int i = 0; i < key.length; i++) {
            if (!hash.containsKey(key[i]) || !hash.get(key[i]).equals(array[i])) return false;
        }
        return true;
    }
}
