import static org.junit.Assert.*;

public class HashTableTest {
    @org.junit.Test
    public void HashTableConstructor1() {
       HashTable hash1 = new HashTable();
       HashTable hash2 = new HashTable();
       HashTable hash3 = new HashTable();
       assertEquals(hash2.size(),0);
       assertEquals(hash3.size(),0);
       hash1.insert("Ow");
       hash1.insert("Ow");
       hash1.insert("wow");
       assertEquals(hash1.size(),2);
       System.out.println(hash1);
    }
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void HashTableConstructor2() {
        HashTable hash1 = new HashTable(5);
        HashTable hash2 = new HashTable(5);
        HashTable hash3 = new HashTable(6);
        assertEquals(hash2.size(),0);
        assertEquals(hash3.size(),0);
        hash1.insert("Ow");
        hash1.insert("Ow");
        hash1.insert("wow");
        hash1.insert("lll");
        hash1.insert("ll");
        hash1.insert("wl");
        hash1.insert("mmm");
        hash1.insert("123");
        assertEquals(hash1.size(),7);
        System.out.println(hash1);
        HashTable hash5 = new HashTable(4);
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void insert() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert(null));
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void delete() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert("Owww1"));
        assertTrue(hash1.insert("Owww2"));
        assertTrue(hash1.delete("Ow"));
        assertFalse(hash1.delete("Ow"));
        assertTrue(hash1.delete("Owww2"));
        assertTrue(hash1.delete(null));
    }

    @org.junit.Test (expected = NullPointerException.class)
    public void lookup() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert("Owww1"));
        assertTrue(hash1.insert("Owww2"));
        assertTrue(hash1.lookup("Ow"));
        assertTrue(hash1.lookup("Owww2"));
        assertTrue(hash1.delete("Ow"));
        assertFalse(hash1.lookup("Ow"));
        assertTrue(hash1.lookup(null));
    }

    @org.junit.Test
    public void size() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertEquals(1,hash1.size());
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert("Owww1"));
        assertTrue(hash1.insert("Owww2"));
        assertTrue(hash1.lookup("Ow"));
        assertTrue(hash1.lookup("Owww2"));
        assertEquals(4,hash1.size());
        assertTrue(hash1.delete("Ow"));
        assertFalse(hash1.lookup("Ow"));
        System.out.println(hash1);
        assertEquals(3,hash1.size());

    }

    @org.junit.Test
    public void capacity() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertEquals(5,hash1.capacity());
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert("Owww1"));
        assertEquals(5,hash1.capacity());
        assertTrue(hash1.insert("Owww2"));
        assertEquals(10,hash1.capacity());
        assertTrue(hash1.lookup("Ow"));
        assertTrue(hash1.lookup("Owww2"));
        assertEquals(4,hash1.size());
        assertTrue(hash1.delete("Ow"));
        assertFalse(hash1.lookup("Ow"));
        System.out.println(hash1);
        assertEquals(3,hash1.size());
    }

    @org.junit.Test
    public void getStatsLog() {
        HashTable hash1 = new HashTable(5);
        assertTrue(hash1.insert("Ow"));
        assertFalse(hash1.insert("Ow"));
        assertEquals(5,hash1.capacity());
        assertTrue(hash1.insert("Owww"));
        assertTrue(hash1.insert("Owww1"));
        assertEquals(5,hash1.capacity());
        assertTrue(hash1.insert("Owww2"));
        assertEquals(10,hash1.capacity());
        assertTrue(hash1.lookup("Ow"));
        assertTrue(hash1.lookup("Owww2"));
        assertEquals(4,hash1.size());
        assertTrue(hash1.delete("Ow"));
        assertFalse(hash1.lookup("Ow"));
        System.out.println(hash1);
        assertEquals(3,hash1.size());
        System.out.println(hash1.getStatsLog());
        hash1.insert("123");
        hash1.insert("1234");
        hash1.insert("1235");
        hash1.insert("123t");
        hash1.insert("1w23");
        hash1.insert("  ");
        hash1.insert("    ");
        hash1.insert("    ");
        hash1.insert("      ");
        hash1.insert("        ");
        hash1.insert("           ");
        hash1.insert("         ");
        hash1.insert("              ");
        System.out.println(hash1.getStatsLog());
        hash1.insert("a");
        hash1.insert("aa");
        hash1.insert("aaaaa");
        hash1.insert("aaaaaa");
        hash1.insert("aaaaaa");
        hash1.insert("aaaaaaa");
        hash1.insert("aaaaaaaaaaa");
        hash1.insert("aaaaaaaaaaaa");
        hash1.insert("aaaaaaaaaaaaaa");
        hash1.insert("b");
        hash1.insert("b ");
        hash1.insert("bbbb");
        hash1.insert("bbbbbb");
        System.out.println(hash1.getStatsLog());
    }
}