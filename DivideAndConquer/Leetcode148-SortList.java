/**
 * LeetCode 148: Sort List
 * 
 * Problem: Given the head of a linked list, return the list after sorting it in ascending order.
 * 
 * Approach: Divide and Conquer (Merge Sort)
 * 
 * Divide and Conquer Strategy:
 * 1. DIVIDE: Split the linked list into two halves
 * 2. CONQUER: Recursively sort both halves
 * 3. COMBINE: Merge the two sorted halves back together
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(log n) for recursion stack
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    
    /**
     * Main function to sort a linked list
     * 
     * HEAD NEDIR?
     * - head: Linked list'in ilk node'unu gosteren pointer/referans
     * - head bir ListNode objesine isaret eder, liste degil!
     * - head = null ise liste bos
     * - head.next = null ise liste tek elemanli
     * 
     * ORNEK: [4 -> 2 -> 1 -> 3]
     * head -> 4 node'una isaret eder
     * head.val = 4
     * head.next -> 2 node'una isaret eder
     */
    public ListNode sortList(ListNode head) {
        // BASE CASE: Neden return head yapiyoruz?
        // - Eger liste bos (head == null) veya tek elemanli (head.next == null) ise
        // - Liste zaten sirali durumda! (bos liste veya tek eleman her zaman sirali)
        // - Bu yuzden ayni head'i geri donduruyoruz, degistirmeye gerek yok
        if (head == null || head.next == null) {
            return head; // Zaten sirali, oldugu gibi dondur
        }
        
        // ============================================
        // DIVIDE ADIMI: Listeyi ikiye bol
        // ============================================
        
        // ADIM 1: Orta noktayi bul
        // findMiddle(head) bize listenin ortasindaki node'u dondurur
        ListNode mid = findMiddle(head);
        
        // ADIM 2: Sol yariyi belirle
        // left = head demek: "Sol yarinin basi, orijinal listenin basi"
        // head zaten listenin ilk node'una isaret ediyor, bu yuzden left = head
        ListNode left = head;
        
        // ADIM 3: Sag yariyi belirle
        // mid.next, orta noktadan SONRAKI node'a isaret eder
        // Bu da sag yarinin baslangici olur
        ListNode right = mid.next;
        
        // ADIM 4: Iki yariyi birbirinden ayir
        // mid.next = null yaparak, sol ve sag yari arasindaki baglantiyi kesiyoruz
        // Boylece iki ayri liste elde ediyoruz: [head...mid] ve [mid.next...son]
        mid.next = null; // Baglantiyi kes, iki ayri liste olustur
        
        // ============================================
        // CONQUER ADIMI: Her iki yariyi recursive olarak sirala
        // ============================================
        ListNode sortedLeft = sortList(left);   // Sol yariyi sirala
        ListNode sortedRight = sortList(right); // Sag yariyi sirala
        
        // ============================================
        // COMBINE ADIMI: Iki siralanmis yariyi birlestir
        // ============================================
        return merge(sortedLeft, sortedRight);
    }
    
    /**
     * Helper function to find the middle node of the linked list
     * Uses slow and fast pointer technique (tortoise and hare)
     * 
     * SORU: Neden ListNode parametre aliyor ve ListNode donduruyor?
     * 
     * CEVAP:
     * 1. Parametre olarak ListNode head:
     *    - Linked list'te bir liste yok, sadece ilk node'a isaret eden bir pointer var
     *    - head bir ListNode objesine isaret eder (pointer/referans)
     *    - Bu yuzden parametre tipi ListNode (bir node objesi)
     * 
     * 2. Return tipi ListNode:
     *    - Fonksiyonumuz orta noktadaki NODE'u bulmaya calisiyor
     *    - Orta nokta da bir ListNode objesi
     *    - Bu yuzden ListNode donduruyoruz (orta noktadaki node'a isaret eden pointer)
     * 
     * ORNEK:
     * Liste: [4 -> 2 -> 1 -> 3]
     * head -> [4] node'una isaret ediyor (ListNode tipinde)
     * findMiddle(head) -> [2] node'una isaret ediyor (ListNode tipinde)
     * 
     * ORNEK CALISMA:
     * Liste: [4 -> 2 -> 1 -> 3]
     * 
     * Baslangic:
     * slow -> 4, fast -> 2
     * 
     * Adim 1:
     * slow -> 2, fast -> 1 (fast.next = 3, fast.next.next = null)
     * Dongu biter cunku fast.next.next == null
     * 
     * Sonuc: slow -> 2 (orta nokta)
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;      // Yavas pointer, 1 adim ilerler
        ListNode fast = head.next; // Hizli pointer, 2 adim ilerler
        
        // Fast sona ulasana kadar devam et
        // fast != null: Fast henuz null'a ulasmadi
        // fast.next != null: Fast'in bir sonraki adimi da var
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Slow 1 adim ilerle
            fast = fast.next.next;   // Fast 2 adim ilerle
        }
        
        return slow; // Slow artik orta noktada
    }
    
    /**
     * Helper function to merge two sorted linked lists
     * This is the COMBINE step of Divide and Conquer
     * 
     * SORU 1: Dummy node (121-123) ne ise yariyor?
     * 
     * CEVAP:
     * - Dummy node, yeni listemizin baslangic noktasi olarak kullanilir
     * - current pointer'i, yeni listemizi olustururken nereye ekleyecegimizi gosterir
     * - Dummy node olmadan, ilk node'u eklerken null kontrolu yapmamiz gerekir (karmasik)
     * - Dummy node ile, her zaman current.next'e ekleriz (basit)
     * - En sonunda dummy.next'i dondururuz (dummy'yi atlariz, gercek liste baslar)
     * 
     * ORNEK:
     * list1: [2 -> 4]
     * list2: [1 -> 3]
     * 
     * dummy -> [0] -> null  (baslangic)
     * current -> dummy
     * 
     * Adim 1: 1 < 2, ekle
     * dummy -> [0] -> [1] -> null
     * current -> [1]
     * 
     * Adim 2: 2 < 3, ekle
     * dummy -> [0] -> [1] -> [2] -> null
     * current -> [2]
     * 
     * Adim 3: 3 < 4, ekle
     * dummy -> [0] -> [1] -> [2] -> [3] -> null
     * current -> [3]
     * 
     * Adim 4: list2 bitti, list1'den kalanlari ekle
     * dummy -> [0] -> [1] -> [2] -> [3] -> [4] -> null
     * 
     * return dummy.next -> [1] -> [2] -> [3] -> [4] (dummy'yi atladik)
     * 
     * SORU 2: Neden list2'deki value daha buyukken next'i list1'den seciyoruz? (126-135)
     * 
     * CEVAP: Aslinda TAM TERSI! Kucuk olani seciyoruz!
     * 
     * Mantik:
     * - Iki sirali listeyi birlestiriyoruz (kucukten buyuge)
     * - list1.val <= list2.val ise: list1'deki deger DAHA KUCUK veya ESIT
     *   -> Kucuk olani (list1) yeni listemize ekliyoruz
     * - list1.val > list2.val ise: list2'deki deger DAHA KUCUK
     *   -> Kucuk olani (list2) yeni listemize ekliyoruz
     * 
     * ORNEK ADIM ADIM:
     * list1: [2 -> 4]  (sirali)
     * list2: [1 -> 3]  (sirali)
     * 
     * Adim 1:
     * list1.val = 2, list2.val = 1
     * 2 <= 1? HAYIR! (2 > 1)
     * else bloguna girer: list2'yi ekle (1 daha kucuk)
     * Sonuc: [1]
     * 
     * Adim 2:
     * list1.val = 2, list2.val = 3
     * 2 <= 3? EVET!
     * if bloguna girer: list1'yi ekle (2 daha kucuk)
     * Sonuc: [1 -> 2]
     * 
     * Adim 3:
     * list1.val = 4, list2.val = 3
     * 4 <= 3? HAYIR! (4 > 3)
     * else bloguna girer: list2'yi ekle (3 daha kucuk)
     * Sonuc: [1 -> 2 -> 3]
     * 
     * Adim 4:
     * list2 bitti, list1'den kalanlari ekle
     * Sonuc: [1 -> 2 -> 3 -> 4]
     */
    private ListNode merge(ListNode list1, ListNode list2) {
        // DUMMY NODE: Yeni listemizin baslangic noktasi
        // Dummy node'un degeri onemli degil (0), sadece bir baslangic noktasi
        ListNode dummy = new ListNode(0);
        ListNode current = dummy; // current, yeni listemizi olustururken nereye ekleyecegimizi gosterir
        
        // Iki listeden de eleman varken, kucuk olani sec ve ekle
        while (list1 != null && list2 != null) {
            // list1'deki deger DAHA KUCUK veya ESIT ise
            if (list1.val <= list2.val) {
                current.next = list1;  // list1'deki node'u yeni listemize ekle
                list1 = list1.next;    // list1'de bir sonraki node'a gec
            } else {
                // list2'deki deger DAHA KUCUK ise
                current.next = list2;  // list2'deki node'u yeni listemize ekle
                list2 = list2.next;    // list2'de bir sonraki node'a gec
            }
            current = current.next;    // current'i bir sonraki pozisyona tasi
        }
        
        // Bir listedeki tum elemanlar bitti, diger listeden kalanlari ekle
        if (list1 != null) {
            current.next = list1; // list1'den kalan tum elemanlari ekle
        }
        if (list2 != null) {
            current.next = list2; // list2'den kalan tum elemanlari ekle
        }
        
        // Dummy node'u atla, gercek listenin basini dondur
        return dummy.next;
    }
}
