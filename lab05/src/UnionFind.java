public class UnionFind {
    // TODO: Instance variables
    private int[] parent;
    private int size;


    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        this.parent = new int[N];
        this.size = N;
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        while(parent[v] >= 0) {
            v = parent[v];
        }
        return -parent[v];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if (parent[v] < 0) return -1;
        else return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)){
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= this.size){
            throw new IllegalArgumentException("Invalid item found: " + v);
        }
        int new_v = v;
        while (parent[v] >= 0) {
            v = parent[v];
        }
        while (parent[new_v] >= 0){
            int new_v2 = new_v;
            parent[new_v2] = v;
            new_v = parent[new_v];
        }

        return v;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 != v2){
            if (sizeOf(v1) > sizeOf(v2)) {
                parent[find(v1)] =  - sizeOf(v1) - sizeOf(v2);
                parent[find(v2)] =  find(v1);
            }
            else {
                parent[find(v2)] =  - sizeOf(v1) - sizeOf(v2);
                parent[find(v1)] =  find(v2);

            }
        }

    }

}
