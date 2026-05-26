public class GenericList<E> {

    private E[] con;
    private int size;

    public GenericList() {
        con = (E[]) new Object[10];
        size = 0;
    }

    public void removeUnsortedPartitions(int n) {
        for (int i = 0; i < size; i += n) {
            if (!isValidPartition(i, n)) {
                removeSection(i, n);
            }
        }
    }

    private void removeSection(int start, int n) {
        for (int i = start + n; i < size; i++) {
            con[i - n] = con[i];
        }
        size -= n;
    }

    private boolean isValidPartition(int start, int n) {
        for (int i = start + 1; i < start + n; i++) {
            if (con[i - 1].compareTo(con[i]) >= 0) {
                return false;
            }
        }
        return true;
    }

    public int booksAvailable(Map<String, Set<Integer>> dueDates, ArrayList<String> books) {
        int available = Integer.MIN_VALUE;
        for (String book : books) {
            Set<Integer> dueDateSet = dueDates.get(book);
            if (dueDateSet == null) { return -1; }
            
            int currMin = Integer.MAX_VALUE;
            for (Integer dueDate : dueDateSet) {
                currMin = Math.min(currMin, dueDate);
            }
            
            available = Math.max(available, currMin);
        }
        return available;
    }
}
