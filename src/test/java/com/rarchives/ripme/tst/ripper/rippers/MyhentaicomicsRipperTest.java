package com.rarchives.ripme.tst.ripper.rippers;

import java.io.IOException;
import java.net.URL;

import com.rarchives.ripme.ripper.rippers.MyhentaicomicsRipper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class MyhentaicomicsRipperTest extends RippersTest {
    @Test
    @Tag("flaky")
    public void testMyhentaicomicsAlbum() throws IOException {
        MyhentaicomicsRipper ripper = new MyhentaicomicsRipper(new URL("http://myhentaicomics.com/index.php/Nienna-Lost-Tales"));
        testRipper(ripper);
    }

    public void testGetGID() throws IOException {
        URL url = new URL("http://myhentaicomics.com/index.php/Nienna-Lost-Tales");
        MyhentaicomicsRipper ripper = new MyhentaicomicsRipper(url);
        // Test a comic
        Assertions.assertEquals("Nienna-Lost-Tales", ripper.getGID(url));
        // Test a search
        Assertions.assertEquals("test", ripper.getGID(new URL("http://myhentaicomics.com/index.php/search?q=test")));
        // Test a tag
        Assertions.assertEquals("2409", ripper.getGID(new URL("http://myhentaicomics.com/index.php/tag/2409/")));
    }
    @Test
    @Tag("flaky")
    public void testGetAlbumsToQueue() throws IOException {
        URL url = new URL("https://myhentaicomics.com/index.php/tag/3167/");
        MyhentaicomicsRipper ripper = new MyhentaicomicsRipper(url);
        Assertions.assertEquals(15, ripper.getAlbumsToQueue(ripper.getFirstPage()).size());
    }
    @Test
    public void testPageContainsAlbums() throws IOException {
        URL url = new URL("https://myhentaicomics.com/index.php/tag/3167/");
        URL url2 = new URL("https://myhentaicomics.com/index.php/search?q=test");
        MyhentaicomicsRipper ripper = new MyhentaicomicsRipper(url);
        Assertions.assertTrue(ripper.pageContainsAlbums(url));
        Assertions.assertTrue(ripper.pageContainsAlbums(url2));
    }
}