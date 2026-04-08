import java.util.*;
class DataBlock {
    String id;
    String type;
    Integer linkId;
    DataBlock(String id, String type, Integer linkId) {
        this.id = id;
        this.type = type;
        this.linkId = linkId;
    }
}
// q==input queue
public class Main {
    public static void main(String[] args) {
        List<DataBlock> q = new LinkedList<>();
        q.add(new DataBlock("a", "High-Priority", null));
        q.add(new DataBlock("b", "Standard", 101));
        q.add(new DataBlock("c", "High-Priority", null));
        q.add(new DataBlock("d", "Standard", 101));
        q.add(new DataBlock("e", "High-Priority", null));

        run(q);
    }
    // f==filter, v=vault , b= datablock name
    static void run(List<DataBlock> q) {
        List<DataBlock> f = new ArrayList<>();
        List<DataBlock> v = new ArrayList<>();
        while (!q.isEmpty() || !f.isEmpty()) {
            boolean moved = false;
            for (int i = 0; i < f.size(); i++) {
                DataBlock b = f.get(i);
                boolean canMove = true;
                if (b.linkId != null) {
                    long countInFilter = f.stream()
                            .filter(x -> b.linkId.equals(x.linkId))
                            .count();
                    boolean stillInQueue = q.stream()
                            .anyMatch(x -> b.linkId.equals(x.linkId));
                    if (countInFilter < 2 && stillInQueue) {
                        canMove = false;
                    }
                }
                if (canMove) {
                    f.remove(i);
                    v.add(b);
                    System.out.println("Move Block " + b.id + ": Filter -> Vault");
                    moved = true;
                    break;
                }
            }
            if (moved) continue;
            if (!q.isEmpty() && f.size() < 2) {
                DataBlock b = q.get(0);
                boolean hasHighPriority = f.stream()
                        .anyMatch(x -> x.type.equals("High-Priority"));
                if (!(hasHighPriority && b.type.equals("Standard"))) {
                    f.add(q.remove(0));
                    System.out.println("Move Block " + b.id + ": Input -> Filter");
                    moved = true;
                }
            }
            if (!moved) {
                System.out.println("invalid state");
                break;
            }
        }
    }
}