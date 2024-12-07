public class Node implements Comparable<Node> {
    private int x;
    private int y;
    private boolean walkable = true;

    private Node parent;
    private double g;
    private double f;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isWalkable() { return walkable; }
    public void setWalkable(boolean walkable) { this.walkable = walkable; }

    public Node getParent() { return parent; }
    public void setParent(Node parent) { this.parent = parent; }

    public double getG() { return g; }
    public void setG(double g) { this.g = g; }

    public double getF() { return f; }
    public void setF(double f) { this.f = f; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.f, other.f);
    }
}
