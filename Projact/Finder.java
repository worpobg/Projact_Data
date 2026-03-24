package Projact;

import java.util.ArrayList;
import java.util.List;

/**
 * Finder - คลาสสำหรับจัดการเคาท์เตอร์และค้นหาดาต้า
 * ใช้ Circular Queue ด้วย ArrayList - เคาท์เตอร์จะหมุนกลับมาหลังสุดท้าย
 */
class Finder {
    // ทำงานกับ: addCounter (บรรทัด 16), nextCounter (บรรทัด 26), display (บรรทัด 38)
    private final List<String> counters = new ArrayList<>();  // เก็บชื่อเคาท์เตอร์ทั้งหมด (Counter A, B, C)
    
    // ทำงานกับ: nextCounter (บรรทัด 24-25)
    private int currentIndex = 0;  // ตัวชี้ตำแหน่งปัจจุบันของเคาท์เตอร์ (ใช้สำหรับ circular)

    /**
     * addCounter - เพิ่มชื่อเคาท์เตอร์เข้าไประบบ
     * @param counter - ชื่อของเคาท์เตอร์ (เช่น "Counter A")
     * Time Complexity: O(1)
     */
    public void addCounter(String counter) {
        counters.add(counter);  // เพิ่มเคาท์เตอร์เข้า ArrayList
    }

    /**
     * nextCounter - คืนเคาท์เตอร์ถัดไป (Circular Queue)
     * ตัวชี้จะเพิ่มขึ้นทีละ 1 และหมุนกลับเป็น 0 เมื่อถึงท้าย
     * @return ชื่อเคาท์เตอร์ที่ควรเรียกต่อไป
     * Time Complexity: O(1)
     */
    public String nextCounter() {
        if (counters.isEmpty()) {
            return "No counters available";  // ถ้าไม่มีเคาท์เตอร์
        }
        String counter = counters.get(currentIndex);  // ดึงเคาท์เตอร์ปัจจุบัน
        currentIndex = (currentIndex + 1) % counters.size();  // เลื่อนไปเคาท์เตอร์ถัดไป (หมุนวน)
        return counter;
    }

    /**
     * display - แสดงรายชื่อเคาท์เตอร์ที่มี
     * แสดงจำนวนตามที่กำหนด (max) รายการ
     * @param max - จำนวนเคาท์เตอร์สูงสุดที่ต้องการแสดง
     * Time Complexity: O(max)
     */
    public void display(int max) {
        System.out.println("Available counters:");  // หัวข้อ
        for (int i = 0; i < counters.size() && i < max; i++) {  // วนลูปแสดงเคาท์เตอร์
            System.out.println((i + 1) + ". " + counters.get(i));  // พิมพ์เคาท์เตอร์พร้อมหมายเลข
        }
    }

    /**
     * search - ค้นหาเรียกซ้ำแบบ Recursive (ค้นหาเชิงลึกแบบแรกก่อน)
     * วนลูปผ่าน Linked List เพื่อค้นหา student ที่มี ID ตรงกัน
     * @param node - node ปัจจุบันของ Linked List
     * @param id - รหัส ID ที่ต้องการค้นหา
     * @return true ถ้าพบ, false ถ้าไม่พบ
     * Time Complexity: O(n), Space Complexity: O(n) (call stack)
     */
    public static boolean search(StudentList.Node node, int id) {
        if (node == null) return false;  // ถ้าถึง node สุดท้าย ไม่พบ
        if (node.data.id == id) return true;  // ถ้าเจอ ID ตรงกัน คืนค่า true
        return search(node.next, id);  // ไม่เจอ ให้แบ่งเล็กลงและค้นหา node ถัดไป (Recursive)
    }
}