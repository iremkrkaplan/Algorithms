class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution{
    public ListNode sortList(ListNode head){
        //base case
        if (head == null || head.next == null){
            return head;
        }

        //divide
        ListNode mid = findMiddle(head);
        ListNode left = head;
        ListNode right = mid.next;

        mid.next = null;

        //conquer
        ListNode sortedLeft = sortList(left);
        ListNode sortedRight = sortList(right);

        //combine
        return merge(sortedLeft,sortedRight);
    }    

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next!= null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy; 
        
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        
        return dummy.next;
    }
}