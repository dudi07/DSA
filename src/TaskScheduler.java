import java.util.HashMap;

public class TaskScheduler {
    HashMap<Character, Integer> map;

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        return 0;

    }

}
