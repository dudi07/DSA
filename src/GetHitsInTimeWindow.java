import java.util.LinkedList;
import java.util.Queue;

public class GetHitsInTimeWindow {
    public static class Hit {
        int timeStamp;
        int count;

        public Hit(int timeStamp, int count) {
            this.timeStamp = timeStamp;
            this.count = count;
        }
    }

    Queue<Hit> queue;

    public GetHitsInTimeWindow() {
        queue = new LinkedList<>();
    }

    public int[] getHits(int[] hits) {
        return hits;
    }

    public void hit(int timestamp) {
        queue.add(new Hit(timestamp, 1));
    }

    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek().timeStamp >= 300) {
            queue.poll();
        }
        return queue.size();
    }

    public static void main(String[] args) {
        GetHitsInTimeWindow counter = new GetHitsInTimeWindow();

        System.out.println("=== Test Case 1: Basic Hit Counter (5-minute window) ===");
        System.out.println();

        // Record hits at different timestamps
        counter.hit(1);
        System.out.println("Hit at timestamp 1");

        counter.hit(2);
        System.out.println("Hit at timestamp 2");

        counter.hit(3);
        System.out.println("Hit at timestamp 3");

        // Get hits at timestamp 4 (all 3 hits are within 300 seconds)
        int hits1 = counter.getHits(4);
        System.out.println("getHits(4) = " + hits1 + " (Expected: 3)");
        System.out.println();

        // Add more hits
        counter.hit(300);
        System.out.println("Hit at timestamp 300");

        int hits2 = counter.getHits(300);
        System.out.println("getHits(300) = " + hits2 + " (Expected: 4)");
        System.out.println();

        // Get hits at timestamp 301 (hit at timestamp 1 should be expired)
        int hits3 = counter.getHits(301);
        System.out.println("getHits(301) = " + hits3 + " (Expected: 3, hit at t=1 expired)");
        System.out.println();

        // Get hits at timestamp 302 (hits at timestamp 1,2 should be expired)
        int hits4 = counter.getHits(302);
        System.out.println("getHits(302) = " + hits4 + " (Expected: 2, hits at t=1,2 expired)");
        System.out.println();

        // Get hits at timestamp 303 (hits at timestamp 1,2,3 should be expired)
        int hits5 = counter.getHits(303);
        System.out.println("getHits(303) = " + hits5 + " (Expected: 1, hits at t=1,2,3 expired)");
        System.out.println();

        // Get hits at timestamp 600 (hit at timestamp 300 should be expired)
        int hits6 = counter.getHits(600);
        System.out.println("getHits(600) = " + hits6 + " (Expected: 0, all hits expired)");
        System.out.println();

        System.out.println("=== Test Case 2: Multiple Hits in Short Time ===");
        GetHitsInTimeWindow counter2 = new GetHitsInTimeWindow();

        // Simulate rapid hits
        for (int i = 1; i <= 10; i++) {
            counter2.hit(100 + i);
            System.out.println("Hit at timestamp " + (100 + i));
        }

        int hits7 = counter2.getHits(110);
        System.out.println("getHits(110) = " + hits7 + " (Expected: 10)");
        System.out.println();

        // Check after 300 seconds from first hit
        int hits8 = counter2.getHits(401);
        System.out.println("getHits(401) = " + hits8 + " (Expected: 10, all still within window)");
        System.out.println();

        // Check after 300 seconds from last hit
        int hits9 = counter2.getHits(411);
        System.out.println("getHits(411) = " + hits9 + " (Expected: 0, all expired)");
        System.out.println();

        System.out.println("=== All Tests Completed ===");
    }
}
