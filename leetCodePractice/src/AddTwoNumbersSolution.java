public class AddTwoNumbersSolution {
    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {

        ListNode l3 = new ListNode();
        ListNode curr = l3;
        int sum = 0, rollover = 0, remainder = 0;

        while (l1 != null || l2 != null) {
            
            sum = rollover;
            remainder = 0;


            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            rollover = sum / 10;
            remainder = sum % 10;

            curr.next = new ListNode(remainder);
            curr = curr.next;
        }

        if (rollover > 0) {
            curr.next = new ListNode(rollover);
            curr = curr.next;
        }

        return l3.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}