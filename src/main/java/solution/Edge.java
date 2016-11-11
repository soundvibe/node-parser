package solution;

/**
 * @author OZY on 2016.11.11.
 */
public final class Edge implements Comparable<Edge> {

    public final Character head;
    public final Character tail;

    public Edge(Character head, Character tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        final int result = head.compareTo(o.head);
        return result == 0 ? tail.compareTo(o.tail) : result;
    }
}
